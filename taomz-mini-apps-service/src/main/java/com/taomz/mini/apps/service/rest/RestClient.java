package com.taomz.mini.apps.service.rest;

import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.taomz.mini.apps.util.HttpsUtils;
import com.taomz.mini.apps.util.enums.ServerNameEnums;
import com.taomz.mini.apps.util.exception.ExceptionWrapper;
import com.taomz.sha.util.response.BaseCodeResultForTaomz;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * rest请求工具类
 *
 * @author Wei.Guang
 * @create 2018-06-08 11:10
 **/
@Component
@Slf4j
public class RestClient {

    @Autowired
    @Qualifier("restTemplate")
    private RestTemplate restTemplate;

    /**
     * 获取对象
     *
     * @param url   请求地址
     * @param param 参数
     * @return
     */
    public <T> T requestForString(String address, String url, Map<String, Object> param, String json, HttpMethod method, Class<T> returnClass, Map<String, String> header) throws ExceptionWrapper {
        JSONObject response = request(address + url, param, json, method, header);
        String str = response.getString("data");
        if (Objects.isNull(str)) {
            return null;
        }
        return JSON.parseObject(str, returnClass);
    }

    /**
     * 获取对象
     *
     * @param url   请求地址
     * @param param 参数
     * @return
     */
    public <T> T requestForObject(String address, String url, Map<String, Object> param, String json, HttpMethod method, Class<T> returnClass, Map<String, String> header) throws ExceptionWrapper {
        JSONObject response = request(address + url, param, json, method, header);
        JSONObject date = response.getJSONObject("data");
        if (Objects.isNull(date)) {
            return null;
        }
        return JSON.parseObject(date.toJSONString(), returnClass);
    }

    /**
     * 获取List
     *
     * @param url         请求地址
     * @param param       参数
     * @param returnClass 返回类型
     * @param header      自定义的头信息
     * @return
     */
    public <E> List<E> requestForContentList(String address, String url, Map<String, Object> param, String json, HttpMethod method, Class<E> returnClass, Map<String, String> header) throws ExceptionWrapper {
        JSONObject response = request(address + url, param, json, method, header);
        JSONArray data = response.getJSONArray("data");
        if (Objects.isNull(data) || data.isEmpty()) {
            return null;
        }
        return JSON.parseArray(data.toJSONString(), returnClass);
    }

    public <T> T callEurekaForContent(ServerNameEnums eurekaName, String url, Object params, Class<T> responseType,
                                      boolean logSwitch) throws ExceptionWrapper {
        JSONObject callEureka = call(eurekaName, url, params, logSwitch);
        JSONObject data = callEureka.getJSONObject("data");
        if (null != data) {
            return JSONObject.parseObject(data.toJSONString(), responseType);
        }
        return null;
    }

    public <T> List<T> callEurekaForContentList(ServerNameEnums eurekaName, String url, Object object,
                                                Class<T> contentType, boolean logSwitch) throws ExceptionWrapper {
        JSONObject callEureka = call(eurekaName, url, object, logSwitch);
        List<T> arrayList = new ArrayList<>();
        if (callEureka == null) {
            return arrayList;
        }
        JSONArray jsonArray = callEureka.getJSONArray("data");
        if (jsonArray == null || jsonArray.isEmpty()) {
            return arrayList;
        }
        for (Object object2 : jsonArray) {
            T parseObject = JSONObject.parseObject(object2.toString(), contentType);
            arrayList.add(parseObject);
        }
        return arrayList;
    }

    private JSONObject request(String url, Map<String, Object> param, String json, HttpMethod method, Map<String, String> header)
            throws ExceptionWrapper {
        Map<String, String> headerMap = new HashMap<>();
        headerMap.put(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_UTF8_VALUE);
        RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
        if (Objects.nonNull(requestAttributes)) {
            HttpServletRequest request = ((ServletRequestAttributes) requestAttributes).getRequest();
            headerMap.put("login_token", request.getHeader("login_token"));
        }
        JSONObject jsonObject = null;
        if (HttpMethod.POST == method) {
            if (StrUtil.isBlank(json)) {
                jsonObject = HttpsUtils.doPost(url, param, headerMap);
            } else {
                jsonObject = HttpsUtils.doPost(url, json, headerMap);
            }
        } else if (HttpMethod.GET == method) {
            jsonObject = HttpsUtils.doGet(url, param, headerMap);
        }
        if (null == jsonObject) {
            log.error("接口调用异常,返回值为空：{}", url);
            throw new ExceptionWrapper("微服务调用异常,返回值为空");
        }
        String code = jsonObject.getString("code");
        if (!BaseCodeResultForTaomz.SUCCESS.getCode().equals(code)) {
            log.error("接口调用异常{}，url：{}:", jsonObject.getString("message"), url);
            throw new ExceptionWrapper(code, jsonObject.getString("message"));
        }
        return jsonObject;
    }

    /**
     * @param eurekaName
     * @param url
     * @param params
     * @param logSwitch  是否打印请求参数日志
     * @return
     * @throws ExceptionWrapper
     */
    private JSONObject call(ServerNameEnums eurekaName, String url, Object params, boolean logSwitch)
            throws ExceptionWrapper {
        if (logSwitch) {
            log.info("微服务{},请求参数{}", eurekaName.getServerName() + url, JSON.toJSONString(params));
        }
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<Object> entity = new HttpEntity<>(params, headers);
        ResponseEntity<String> responseEntity = restTemplate.exchange(eurekaName.getServerName() + url, HttpMethod.POST, entity, String.class);
        JSONObject response = JSON.parseObject(responseEntity.getBody());

        if (null == response) {
            log.error("微服务{}调用异常,返回值为空", eurekaName + url);
            throw new ExceptionWrapper("微服务调用异常,返回值为空");
        }
        String code = response.getString("code");
        if (!BaseCodeResultForTaomz.SUCCESS.getCode().equals(code)) {
            log.error("微服务{}调用异常:" + response.getString("message"), eurekaName + url);
            throw new ExceptionWrapper(code, response.getString("message"));
        }
        return response;
    }
}