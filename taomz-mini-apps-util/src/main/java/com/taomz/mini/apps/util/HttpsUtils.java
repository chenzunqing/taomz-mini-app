package com.taomz.mini.apps.util;

import cn.hutool.core.util.CharsetUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.ssl.SSLContextBuilder;
import org.apache.http.ssl.TrustStrategy;
import org.apache.http.util.EntityUtils;
import org.springframework.http.MediaType;

import javax.net.ssl.SSLContext;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.security.GeneralSecurityException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * @author nanmu@taomz.com
 * @version V1.0
 * @title: HttpsUtils
 * @Package: com.taomz.common.certify.util
 * @Description: http请求工具类
 * @date 2020/7/14 19:09
 **/
@Slf4j
public class HttpsUtils {

    private static PoolingHttpClientConnectionManager connMgr;
    private static RequestConfig requestConfig;
    private static final int MAX_TIMEOUT = 7000;

    static {
        // 设置连接池
        connMgr = new PoolingHttpClientConnectionManager();
        // 设置连接池大小
        connMgr.setMaxTotal(100);
        connMgr.setDefaultMaxPerRoute(connMgr.getMaxTotal());
        // Validate connections after 1 sec of inactivity
        connMgr.setValidateAfterInactivity(1000);
        RequestConfig.Builder configBuilder = RequestConfig.custom();
        // 设置连接超时
        configBuilder.setConnectTimeout(MAX_TIMEOUT);
        // 设置读取超时
        configBuilder.setSocketTimeout(MAX_TIMEOUT);
        // 设置从连接池获取连接实例的超时
        configBuilder.setConnectionRequestTimeout(MAX_TIMEOUT);

        requestConfig = configBuilder.build();
    }

    /**
     * 发送 GET 请求（HTTP），不带输入数据
     *
     * @param url
     * @return
     */
    public static JSONObject doGet(String url) {
        return doGet(url, new HashMap<>(), null);
    }

    /**
     * 发送 GET 请求（HTTP），K-V形式
     *
     * @param url
     * @param params
     * @return
     */
    public static JSONObject doGet(String url, Map<String, Object> params, Map<String, String> headerMap) {
        String apiUrl = url;
        StringBuffer param = new StringBuffer();
        int i = 0;
        if (Objects.nonNull(params) && !params.isEmpty()) {
            for (String key : params.keySet()) {
                if (i == 0) {
                    param.append("?");
                } else {
                    param.append("&");
                }
                param.append(key).append("=").append(params.get(key));
                i++;
            }
        }
        apiUrl += param;
        String result = null;
        HttpClient httpClient;
        if (apiUrl.startsWith("https")) {
            httpClient = HttpClients.custom().setSSLSocketFactory(createSSLConnSocketFactory()).setConnectionManager(connMgr).setDefaultRequestConfig(requestConfig).build();
        } else {
            httpClient = HttpClients.createDefault();
        }
        HttpGet httpGet = new HttpGet(apiUrl);
        if (Objects.nonNull(headerMap) && !headerMap.isEmpty()) {
            for (Map.Entry<String, String> vo : headerMap.entrySet()) {
                httpGet.setHeader(vo.getKey(), vo.getValue());
            }
        }
        HttpResponse response = null;
        InputStream instream = null;
        try {
            response = httpClient.execute(httpGet);
            HttpEntity entity = response.getEntity();
            if (entity != null) {
                instream = entity.getContent();
                result = IOUtils.toString(instream, "UTF-8");
            }
        } catch (IOException e) {
            log.error("发送 GET 请求失败 ：{}", e);
        } finally {
            if (response != null) {
                try {
                    instream.close();
                } catch (IOException e) {
                    log.error("发送 GET 请求失败：{}", e);
                }
            }
        }
        return JSON.parseObject(result);
    }

    /**
     * 发送 POST 请求（HTTP），不带输入数据
     *
     * @param apiUrl
     * @return
     */
    public static com.alibaba.fastjson.JSONObject doPost(String apiUrl) {
        return doPost(apiUrl, new HashMap<>(), null);
    }

    /**
     * 发送 POST 请求，K-V形式
     *
     * @param apiUrl API接口URL
     * @param params 参数map
     * @return
     */
    public static JSONObject doPost(String apiUrl, Map<String, Object> params, Map<String, String> headerMap) {
        CloseableHttpClient httpClient;
        if (apiUrl.startsWith("https")) {
            httpClient = HttpClients.custom().setSSLSocketFactory(createSSLConnSocketFactory())
                    .setConnectionManager(connMgr).setDefaultRequestConfig(requestConfig).build();
        } else {
            httpClient = HttpClients.createDefault();
        }
        String httpStr = null;
        HttpPost httpPost = new HttpPost(apiUrl);
        if (Objects.nonNull(headerMap) && !headerMap.isEmpty()) {
            for (Map.Entry<String, String> vo : headerMap.entrySet()) {
                httpPost.setHeader(vo.getKey(), vo.getValue());
            }
        }
        if (Objects.nonNull(params) && !params.isEmpty()) {
            List<NameValuePair> pairList = new ArrayList<>(params.size());
            for (Map.Entry<String, Object> entry : params.entrySet()) {
                NameValuePair pair = new BasicNameValuePair(entry.getKey(), entry.getValue().toString());
                pairList.add(pair);
            }
            httpPost.setEntity(new UrlEncodedFormEntity(pairList, Charset.forName("UTF-8")));
        }
        CloseableHttpResponse response = null;
        try {
            response = httpClient.execute(httpPost);
            HttpEntity entity = response.getEntity();
            httpStr = EntityUtils.toString(entity, "UTF-8");
        } catch (IOException e) {
            log.error("发送 POST 请求失败 K-V 形式 ：{}", e);
        } finally {
            if (response != null) {
                try {
                    EntityUtils.consume(response.getEntity());
                } catch (IOException e) {
                    log.error("发送 POST 请求失败 K-V 形式 ：{}", e);
                }
            }
        }
        return JSON.parseObject(httpStr);
    }

    /**
     * 发送 POST 请求，JSON形式
     *
     * @param apiUrl
     * @param json   json对象
     * @return
     */
    public static JSONObject doPost(String apiUrl, String json, Map<String, String> headerMap) {
        CloseableHttpClient httpClient;
        if (apiUrl.startsWith("https")) {
            httpClient = HttpClients.custom().setSSLSocketFactory(createSSLConnSocketFactory())
                    .setConnectionManager(connMgr).setDefaultRequestConfig(requestConfig).build();
        } else {
            httpClient = HttpClients.createDefault();
        }
        String httpStr = null;
        HttpPost httpPost = new HttpPost(apiUrl);
        if (Objects.nonNull(headerMap) && !headerMap.isEmpty()) {
            for (Map.Entry<String, String> vo : headerMap.entrySet()) {
                httpPost.setHeader(vo.getKey(), vo.getValue());
            }
        }
        StringEntity stringEntity = new StringEntity(json, CharsetUtil.UTF_8);
        stringEntity.setContentEncoding(CharsetUtil.UTF_8);
        stringEntity.setContentType(MediaType.APPLICATION_JSON_VALUE);
        httpPost.setEntity(stringEntity);
        CloseableHttpResponse response = null;
        try {
            response = httpClient.execute(httpPost);
            HttpEntity entity = response.getEntity();
            httpStr = EntityUtils.toString(entity, "UTF-8");
        } catch (IOException e) {
            log.error("发送 POST 请求失败 JSON形式 ：{}", e);
        } finally {
            if (response != null) {
                try {
                    EntityUtils.consume(response.getEntity());
                } catch (IOException e) {
                    log.error("发送 POST 请求失败 JSON 形式 ：{}", e);
                }
            }
        }
        return JSON.parseObject(httpStr);
    }

    /**
     * 创建SSL安全连接
     *
     * @return
     */
    private static SSLConnectionSocketFactory createSSLConnSocketFactory() {
        SSLConnectionSocketFactory sslsf = null;
        try {
            SSLContext sslContext = new SSLContextBuilder().loadTrustMaterial(null, (TrustStrategy) (chain, authType) -> true).build();
            sslsf = new SSLConnectionSocketFactory(sslContext, (arg0, arg1) -> true);
        } catch (GeneralSecurityException e) {
            log.error("创建SSL安全连接 ：{}", e);
        }
        return sslsf;
    }
}
