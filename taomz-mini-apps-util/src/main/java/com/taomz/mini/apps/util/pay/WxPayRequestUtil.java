package com.taomz.mini.apps.util.pay;

import cn.hutool.core.util.CharsetUtil;
import com.alibaba.fastjson.JSONObject;
import com.taomz.mini.apps.util.exception.ExceptionWrapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpEntity;
import org.apache.http.HttpHeaders;
import org.apache.http.StatusLine;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.TrustStrategy;
import org.apache.http.entity.ByteArrayEntity;
import org.apache.http.entity.ContentType;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.ssl.SSLContextBuilder;
import org.apache.http.util.EntityUtils;
import org.springframework.http.MediaType;

import javax.net.ssl.SSLContext;
import java.net.ConnectException;
import java.security.GeneralSecurityException;
import java.util.Objects;
import java.util.SortedMap;
import java.util.TreeMap;

/**
 * @author nanmu@taomz.com
 * @version V1.0
 * @title : WxPayRequestUtil
 * @Package : com.taomz.mini.apps.util.pay
 * @Description: 微信支付请求工具类
 * @date 2020/11/23 10:01
 **/
@Slf4j
public class WxPayRequestUtil {

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

    /**
     * 微信支付请求
     * @param requestUrl
     * @param request
     * @return
     */
    public static JSONObject wechatRequest(String requestUrl, String request) {
        try {
            CloseableHttpClient httpClient = HttpClients.custom().setSSLSocketFactory(createSSLConnSocketFactory())
                    .setConnectionManager(connMgr).setDefaultRequestConfig(requestConfig).build();
            HttpPost httpPost = new HttpPost(requestUrl);
            httpPost.setHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_FORM_URLENCODED_VALUE);
            if (Objects.nonNull(request)) {
                httpPost.setEntity(new ByteArrayEntity(request.getBytes(CharsetUtil.UTF_8)));
            }
            CloseableHttpResponse response = httpClient.execute(httpPost);
            final HttpEntity entity = response.getEntity();
            final StatusLine statusLine = response.getStatusLine();
            String[] value = entity.getContentType().getValue().split(";");
            if (statusLine.getStatusCode() >= 300 && statusLine.getStatusCode() != 304) {
                if (isXml(value[0], "")) {
                    return WxPayUtil.toJSONObject(EntityUtils.toString(entity, CharsetUtil.UTF_8), CharsetUtil.CHARSET_UTF_8);
                }
                log.error("微信支付请求发送失败，状态码：{}，url：{}，reasonPhrase：", statusLine.getStatusCode(), requestUrl, statusLine.getReasonPhrase());
                EntityUtils.consume(entity);
                throw new ExceptionWrapper("微信支付请求发送失败，错误码：" + statusLine.getStatusCode() + "，错误信息：" + statusLine.getReasonPhrase());
            }
            return WxPayUtil.toJSONObject(EntityUtils.toString(entity, CharsetUtil.UTF_8), CharsetUtil.CHARSET_UTF_8);
        } catch (ConnectException ce) {
            log.error("连接超时：{}", ce);
        } catch (Exception e) {
            log.error("https请求异常：{}", e);
        }
        return null;
    }

    /**
     * 微信支付请求沙箱signkey
     * @param requestUrl
     * @param mchId
     * @param keyStorePwd
     * @return
     */
    public static String retrieveSandboxSignKey(String requestUrl, String mchId, String keyStorePwd) {
        try {
            CloseableHttpClient httpClient = HttpClients.custom().setSSLSocketFactory(createSSLConnSocketFactory())
                    .setConnectionManager(connMgr).setDefaultRequestConfig(requestConfig).build();
            HttpPost httpPost = new HttpPost(requestUrl);
            httpPost.setHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_FORM_URLENCODED_VALUE);
            SortedMap<String, Object> map = new TreeMap<>();
            map.put("mch_id", mchId);
            map.put("nonce_str", WxPayUtil.getRandomString(16));
            map.put("sign", WxPayUtil.createSign(map, keyStorePwd));
            String requestXML = WxPayUtil.getMap2Xml(map);
            httpPost.setEntity(new ByteArrayEntity(requestXML.getBytes(CharsetUtil.UTF_8)));
            CloseableHttpResponse response = httpClient.execute(httpPost);
            final HttpEntity entity = response.getEntity();
            final StatusLine statusLine = response.getStatusLine();
            if (statusLine.getStatusCode() >= 300 && statusLine.getStatusCode() != 304) {
                log.error("微信支付请求发送失败，状态码：{}，url：{}，reasonPhrase:", statusLine.getStatusCode(), requestUrl, statusLine.getReasonPhrase());
                EntityUtils.consume(entity);
                throw new ExceptionWrapper("获取微信沙箱环境支付key失败，错误码：" + statusLine.getStatusCode() + "，错误信息：" + statusLine.getReasonPhrase());
            }
            JSONObject respMap = WxPayUtil.toJSONObject(EntityUtils.toString(entity, CharsetUtil.UTF_8), CharsetUtil.CHARSET_UTF_8);
            log.info("获取验签秘钥API：{}", respMap);
            if ("SUCCESS".equals(respMap.get("result_code"))) {
                return respMap.getString("sandbox_signkey");
            }
            throw new ExceptionWrapper("获取微信沙箱环境支付key失败，错误码：" + respMap.get("return_code") + "，错误信息：" + respMap.get("return_msg"));
        } catch (ConnectException ce) {
            log.error("连接超时：{}", ce);
        } catch (Exception e) {
            log.error("https请求异常：{}", e);
        }
        return null;
    }

    /**
     * 检测响应类型是否为xml
     *
     * @param contentType 内容类型
     * @param textFirst   文本第一个字符
     * @return 布尔型， true为xml内容类型
     */
    private static boolean isXml(String contentType, String textFirst) {
        return (ContentType.APPLICATION_XML.getMimeType().equals(contentType) || "<".indexOf(textFirst) >= 0);
    }
}
