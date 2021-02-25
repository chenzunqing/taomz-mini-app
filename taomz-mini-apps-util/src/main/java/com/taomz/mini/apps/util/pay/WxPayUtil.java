package com.taomz.mini.apps.util.pay;

import cn.hutool.core.exceptions.ExceptionUtil;
import cn.hutool.core.util.CharsetUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.crypto.SecureUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.taomz.mini.apps.util.enums.ErrorMessageEnum;
import com.taomz.mini.apps.util.exception.ExceptionWrapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.crypto.spec.SecretKeySpec;
import javax.xml.XMLConstants;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringWriter;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Random;
import java.util.Set;
import java.util.SortedMap;

/**
 * @author nanmu@taomz.com
 * @version V1.0
 * @title : WxPayUtil
 * @Package : com.taomz.mini.apps.util.pay
 * @Description: 微信支付工具类
 * @date 2020/11/23 9:59
 **/
@Slf4j
public class WxPayUtil {

    private static final String ALGORITHM = "AES";

    /**
     * 签名类型，默认为MD5，支持HMAC-SHA256和MD5。注意此处需与统一下单的签名类型一致
     */
    public final static String PAYSIGN = "MD5";

    /**
     * 小程序支付的交易类型为JSAPI
     */
    public final static String JSAPI_TRADE_TYPE = "JSAPI";

    /**
     * H5支付的交易类型为MWEB
     */
    public final static String H5_TRADE_TYPE = "MWEB";

    private static final String SIGN = "sign";
    public static final String SUCCESS = "SUCCESS";
    public static final String RETURN_CODE = "return_code";
    public static final String RETURN_MSG = "return_msg";
    public static final String RESULT_CODE = "result_code";
    public static final String ERR_CODE_DES = "err_code_des";

    /**
     * 商户订单号重复
     */
    public static final String FAIL_201 = "201 商户订单号重复";

    private WxPayUtil(){

    }

    public static SecretKeySpec getSandBoxSkeySpec(String keyStorePwd) {
        try {
            return new SecretKeySpec(SecureUtil.md5(keyStorePwd).getBytes(CharsetUtil.UTF_8), ALGORITHM);
        } catch (UnsupportedEncodingException e) {
            log.error("wechat sandBox skeySpec generate failure: {}", ExceptionUtils.getStackTrace(e));
            return null;
        }
    }

    /**
     * 随机字符串生成
     * @param length
     * @return
     */
    public static String getRandomString(int length) { // length表示生成字符串的长度
        String base = "abcdefghijklmnopqrstuvwxyz0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        Random random = new Random();
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < length; i++) {
            int number = random.nextInt(base.length());
            sb.append(base.charAt(number));
        }
        return sb.toString();
    }

    /**
     * 请求xml组装
     * @param parameters
     * @return
     */
    public static String getRequestXml(SortedMap<String, String> parameters) {
        StringBuffer sb = new StringBuffer();
        sb.append("<xml>");
        Set es = parameters.entrySet();

        for (Object e : es) {
            Map.Entry entry = (Map.Entry) e;
            String key = (String) entry.getKey();
            String value = (String) entry.getValue();
            if ("attach".equalsIgnoreCase(key) || "body".equalsIgnoreCase(key)) {
                sb.append("<").append(key).append(">").append("<![CDATA[").append(value).append("]]></").append(key).append(">");
            } else {
                sb.append("<").append(key).append(">").append(value).append("</").append(key).append(">");
            }
        }
        sb.append("</xml>");
        return sb.toString();
    }

    /**
     * 生成签名
     * @param parameterMap
     * @param signKey
     * @return
     */
    public static String createSign(Map<String, Object> parameterMap, String signKey) {
        if (parameterMap == null) {
            return null;
        }
        StringBuffer sb = new StringBuffer();
        List<String> keys = new ArrayList<>(parameterMap.keySet());
        Collections.sort(keys);

        for (int i = 0; i < keys.size(); i++) {
            String key = keys.get(i);
            Object value = parameterMap.get(key);
            if (Objects.nonNull(value) && StrUtil.isNotBlank(value.toString())) {
                sb.append(i == 0 ? "" : "&").append(key).append("=").append(value.toString());
            }
        }
        sb.append("&key=").append(signKey);
        log.info("【生成签名：{}】", sb.toString());
        return SecureUtil.md5(sb.toString()).toUpperCase();
    }

    /**
     * 解析xml并转化为JSON值
     * @param content 字符串
     * @param charset 字符编码
     * @return JSON值 {@link JSONObject}
     * @throws ExceptionWrapper
     */
    public static JSONObject toJSONObject(String content, Charset charset) {
        log.info("解析xml并转化为JSON值：{}", content);
        if (StrUtil.isEmpty(content)) {
            return null;
        }
        return toJSONObject(content.getBytes(charset));
    }

    /**
     * 解析xml并转化为JSON值 字符串
     * @param content JSON值 {@link JSONObject}
     * @return
     * @throws ExceptionWrapper
     */
    public static JSONObject toJSONObject(byte[] content) {

        if (null == content) {
            return null;
        }
        try (InputStream in = new ByteArrayInputStream(content)) {
            return (JSONObject) inputStream2Map(in, null);
        } catch (IOException e) {
            log.info("XML解析失败：{}", ExceptionUtil.getMessage(e));
            return null;
        }
    }

    /**
     * 输入流转换
     * @param in xml输入流
     * @param m 参数集
     * @return 整理完成的参数集 {@link Map}
     * @throws IOException
     * @throws ExceptionWrapper
     */
    public static Map inputStream2Map(InputStream in, Map m) throws IOException {
        if (null == m) {
            m = new JSONObject();
        }
        try {
            DocumentBuilder documentBuilder = newDocumentBuilder();
            org.w3c.dom.Document doc = documentBuilder.parse(in);
            doc.getDocumentElement().normalize();
            NodeList children = doc.getDocumentElement().getChildNodes();
            for (int idx = 0; idx < children.getLength(); ++idx) {
                Node node = children.item(idx);
                NodeList nodeList = node.getChildNodes();
                if (node.getNodeType() == Node.ELEMENT_NODE && nodeList.getLength() <= 1) {
                    m.put(node.getNodeName(), node.getTextContent());
                } else if (node.getNodeType() == Node.ELEMENT_NODE && nodeList.getLength() > 1) {
                    m.put(node.getNodeName(), getChildren(nodeList));
                }
            }
        } catch (Exception e) {
            log.info("XML解析失败：{}", ExceptionUtil.getMessage(e));
        } finally {
            in.close();
        }
        return m;
    }

    /**
     * 整理完成的参数集
     * @param children 集合
     * @return 子结点的xml {@link JSON}
     */
    public static JSON getChildren(NodeList children) {
        JSON json = null;
        for (int idx = 0; idx < children.getLength(); ++idx) {
            Node node = children.item(idx);
            NodeList nodeList = node.getChildNodes();
            if (node.getNodeType() == Node.ELEMENT_NODE && nodeList.getLength() <= 1) {
                if (null == json) {
                    json = new JSONObject();
                }
                ((JSONObject) json).put(node.getNodeName(), node.getTextContent());
            } else if (node.getNodeType() == Node.ELEMENT_NODE && nodeList.getLength() > 1) {
                if (null == json) {
                    json = new JSONObject();
                }
                if (json instanceof JSONObject) {
                    JSONObject j = ((JSONObject) json);
                    if (j.containsKey(node.getNodeName())) {
                        JSONArray array = new JSONArray();
                        array.add(json);
                        json = array;
                    } else {
                        j.put(node.getNodeName(), getChildren(nodeList));
                    }
                }

                if (json instanceof JSONArray) {
                    JSONObject c = new JSONObject();
                    c.put(node.getNodeName(), getChildren(nodeList));
                    ((JSONArray) json).add(c);
                }
            }
        }

        return json;
    }

    public static DocumentBuilder newDocumentBuilder() throws ParserConfigurationException {
        DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
        documentBuilderFactory.setFeature("http://apache.org/xml/features/disallow-doctype-decl", true);
        documentBuilderFactory.setFeature("http://xml.org/sax/features/external-general-entities", false);
        documentBuilderFactory.setFeature("http://xml.org/sax/features/external-parameter-entities", false);
        documentBuilderFactory.setFeature("http://apache.org/xml/features/nonvalidating/load-external-dtd", false);
        documentBuilderFactory.setFeature(XMLConstants.FEATURE_SECURE_PROCESSING, true);
        documentBuilderFactory.setXIncludeAware(false);
        documentBuilderFactory.setExpandEntityReferences(false);

        return documentBuilderFactory.newDocumentBuilder();
    }

    public static Document newDocument() throws ParserConfigurationException {
        return newDocumentBuilder().newDocument();
    }

    /**
     * 将Map转换为XML格式的字符串
     *
     * @param data Map类型数据
     * @return XML格式的字符串
     */
    public static String getMap2Xml(Map<String, Object> data) {
        return getMap2Xml(data, "xml", "UTF-8");
    }

    /**
     * 将Map转换为XML格式的字符串
     *
     * @param data Map类型数据
     * @param rootElementName 最外层节点名称
     * @param encoding 字符编码
     * @return XML格式的字符串
     */
    public static String getMap2Xml(Map<String, Object> data, String rootElementName, String encoding) {
        log.info("Map转换XML原始字符串：{}", JSON.toJSONString(data));
        Document document;
        try {
            document = newDocument();
        } catch (ParserConfigurationException e) {
            log.error("Map转换XML失败：{}，{}", JSON.toJSONString(data), ExceptionUtil.getMessage(e));
            return null;
        }
        org.w3c.dom.Element root = document.createElement(rootElementName);
        document.appendChild(root);

        map2Xml(data, document, root);
        try {
            TransformerFactory tf = TransformerFactory.newInstance();
            Transformer transformer = tf.newTransformer();
            DOMSource source = new DOMSource(document);
            transformer.setOutputProperty(OutputKeys.ENCODING, encoding);
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            StringWriter writer = new StringWriter();
            StreamResult result = new StreamResult(writer);
            transformer.transform(source, result);
            String output = writer.getBuffer().toString();
            log.info("Map转换XML最终字符串：{}", output);
            return output;
        } catch (TransformerException e) {
            e.printStackTrace();
        }
        return "";
    }

    /**
     * 将Map转换为XML格式的字符串
     *
     * @param data Map类型数据
     * @param document 文档
     * @param element  节点
     */
    public static void map2Xml(Map<String, Object> data, Document document, org.w3c.dom.Element element) {
        for (Map.Entry<String, Object> entry : data.entrySet()) {
            Object value = entry.getValue();
            if (value == null) {
                value = "";
            }
            org.w3c.dom.Element filed = document.createElement(entry.getKey());
            if (value instanceof Map){
                map2Xml((Map)value, document, filed);
            }else {
                value = value.toString().trim();
                filed.appendChild(document.createTextNode(value.toString()));
            }
            element.appendChild(filed);
        }
    }

    /**
     * 验证微信回调签名
     * @param params
     * @param signKey
     * @return
     */
    public static boolean checkWXSign(Map<String, Object> params, String signKey) throws ExceptionWrapper {
        if (!WxPayUtil.SUCCESS.equals(params.get(WxPayUtil.RETURN_CODE))) {
            throw new ExceptionWrapper((String) params.get(WxPayUtil.RETURN_CODE), (String) params.get(WxPayUtil.RETURN_MSG));
        }
        if (!WxPayUtil.SUCCESS.equals(params.get(WxPayUtil.RESULT_CODE))) {
            String errorMsg = (String) params.get(WxPayUtil.ERR_CODE_DES);
            if (FAIL_201.equals(errorMsg)) {
                return Boolean.FALSE;
            }
            throw new ExceptionWrapper((String) params.get(WxPayUtil.RESULT_CODE), errorMsg);
        }

        if (null == params.get(SIGN)) {
            log.error("微信支付异常：签名为空！out_trade_no={}" + params.get("out_trade_no"));
            throw new ExceptionWrapper(ErrorMessageEnum.WX_PAY_SIGN_NULL.getMessage());
        }
        String signFromAPIResponse = (String) params.get(SIGN);
        String content = parameterText(params, "&", SIGN, "appId") + "&key=" + signKey;
        String responseSign = SecureUtil.md5(content).toUpperCase();
        if (!signFromAPIResponse.equals(responseSign)) {
            //签名验不过，表示这个API返回的数据有可能已经被篡改了
            log.error("微信支付异常，API返回的数据签名验证不通过，有可能被第三方篡改!!! responseSign生成的签名为：{}", responseSign);
            throw new ExceptionWrapper(ErrorMessageEnum.WX_PAY_ERROR.getMessage());
        }
        return Boolean.TRUE;
    }

    /**
     * 把数组所有元素排序，并按照“参数=参数值”的模式用“@param separator”字符拼接成字符串
     * @param parameters 参数
     * @param separator 分隔符
     * @param ignoreKey 需要忽略添加的key
     * @return 去掉空值与签名参数后的新签名，拼接后字符串 {@link String}
     */
    public static String parameterText(Map parameters, String separator, String... ignoreKey ) {
        if(parameters == null){
            return "";
        }
        StringBuffer sb = new StringBuffer();
        if (null != ignoreKey){
            Arrays.sort(ignoreKey);
        }
        if (parameters instanceof SortedMap) {
            for (Map.Entry<String, Object> entry : (Set<Map.Entry<String, Object>>)parameters.entrySet()) {
                Object v = entry.getValue();
                if (null == v || "".equals(v.toString().trim()) || (null != ignoreKey && Arrays.binarySearch(ignoreKey, entry.getKey() ) >= 0)) {
                    continue;
                }
                sb.append(entry.getKey() ).append("=").append( v.toString().trim()).append(separator);
            }
            if (sb.length() > 0 && !"".equals(separator)) {
                sb.deleteCharAt(sb.length() - 1);
            }
            return sb.toString();

        }

        List<String> keys = new ArrayList<>(parameters.keySet());
        //排序
        Collections.sort(keys);
        for (String k : keys) {
            String valueStr = "";
            Object o = parameters.get(k);
            if (null == o) {
                continue;
            }
            if (o instanceof String[]) {
                String[] values = (String[]) o;

                for (int i = 0; i < values.length; i++) {
                    String value = values[i].trim();
                    if ("".equals(value)){ continue;}
                    valueStr += (i == values.length - 1) ?  value :  value + ",";
                }
            } else {
                valueStr = o.toString();
            }
            if (null == valueStr || "".equals(valueStr.trim()) || (null != ignoreKey && Arrays.binarySearch(ignoreKey, k ) >= 0)) {
                continue;
            }
            sb.append(k ).append("=").append( valueStr).append(separator);
        }
        if (sb.length() > 0) {
            sb.deleteCharAt(sb.length() - 1);
        }
        return sb.toString();
    }

    /**
     * 将Map中的数据转换成key1=value1&key2=value2的形式
     *
     * @param date 待拼接的Map数据
     * @return
     */
    public static String coverMap2String(Map<String, Object> date) {
        StringBuffer sb = new StringBuffer();
        if (!date.isEmpty()) {
            for (Map.Entry<String, Object> entry : date.entrySet()) {
                if (entry.getValue() != null) {
                    sb.append(entry.getKey() + "=" + entry.getValue() + "&");
                }
            }
            return sb.substring(0, sb.length() - 1);
        } else {
            return null;
        }
    }

    /**
     * Map转化为对应得参数字符串
     * @param param 参数
     * @return 参数字符串
     */
    public static String getMapToParameters(Map param){
        StringBuilder builder = new StringBuilder();
        for (Map.Entry entry : (Set<Map.Entry>) param.entrySet()) {
            Object o = entry.getValue();

            if (null == o) {
                continue;
            }

            if (o instanceof List) {
                o = ((List) o).toArray();
            }
            try {
                if (o instanceof Object[]) {
                    Object[] os = (Object[]) o;
                    String valueStr = "";
                    for (int i = 0, len = os.length; i < len; i++) {
                        if (null == os[i]) {
                            continue;
                        }
                        String value = os[i].toString().trim();
                        valueStr += (i == len - 1) ?  value :  value + ",";
                    }
                    builder.append(entry.getKey()).append("=").append(URLEncoder.encode(valueStr, "utf-8")).append("&");

                    continue;
                }
                builder.append(entry.getKey()).append("=").append(URLEncoder.encode( entry.getValue().toString(), "utf-8")).append("&");
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }
        if (builder.length() > 1) {
            builder.deleteCharAt(builder.length() - 1);
        }
        return builder.toString();
    }
}
