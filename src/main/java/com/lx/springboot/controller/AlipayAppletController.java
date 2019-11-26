package com.lx.springboot.controller;


import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.alibaba.fastjson.parser.Feature;
import com.alibaba.fastjson.JSON;
import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.internal.util.AlipayEncrypt;
import com.alipay.api.internal.util.AlipaySignature;
import com.alipay.api.request.AlipaySystemOauthTokenRequest;
import com.alipay.api.response.AlipaySystemOauthTokenResponse;
import com.lx.springboot.request.AlipayAppletAuthRequest;
import com.lx.springboot.response.AlipayAppletAuthResponse;
import com.lx.springboot.service.UserInfoService;
import com.lx.springboot.utils.EnhanceBeanUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * <p>
 * 申请人 前端控制器
 * </p>
 */
@Slf4j
@RestController
@RequestMapping(value = {"/alipayApplet"})
public class AlipayAppletController {

    String signVeriKey = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAkFRN+aJCedDRtznGWfj3aDAA1YpXdEOpB/AWz39GjHLSmCqSB9bidrqSpD62DOUFg3uoWSvsIQFzyCWTkrQz+oOiA6y8xiRSdYuRoh0bjUu5xeA9TPV6qURptrJh8xDFfnVpX6gub4+lp5KOLRqYPavnfzydRJehbWlbQXwsB//odW20vwEvGncYCos9kCbms0tyju/0a+FPcVeHY94w49K/wic3RFqZ8A8ZqFjtpQb8MyzsJq1DcqyU58tdsyjrlIsFA7eClfjyi1i/58nSJbldZNqDmNfGlz6cxePgvn3aHT5A3OVt80E3BcomhWZ+MdqQPpluvGWAEqbATvbqRwIDAQAB";
    String decryptKey = "QPgSY1C8zNf9BYA2JcF7Pg==";

    @Autowired
    private UserInfoService userInfoService;

    @RequestMapping(value = {"/auth"})
    @ResponseBody
    public AlipayAppletAuthResponse auth(@RequestBody AlipayAppletAuthRequest alipayAppletAuthRequest) {
        log.info("AlipayAppletController auth alipayAppletAuthRequest:" + JSONObject.toJSONString(alipayAppletAuthRequest));
        //AlipayClient alipayClient = new DefaultAlipayClient("https://openapi.alipay.com/gateway.do","app_id","your private_key","json","GBK","alipay_public_key","RSA2");
        //AlipayClient alipayClient = new DefaultAlipayClient("https://openapi.alipay.com/gateway.do", "2019110768638076", "MIIEvAIBADANBgkqhkiG9w0BAQEFAASCBKYwggSiAgEAAoIBAQCVtP3dAk53VeHaRPoiz6QgyhsL7W78dk2vzZltLRtPnUjz4E3Cz5JIE7Kh4qJosBlkdvCBU9YmdBjXvpp24lvH3Fa30GjuCwgc37W0w9KFp9pVhpvyjU45shPa48kTRDCTcf8PeUMx7mR9lsEsdO+GSYvsCk6BAc6xi5SWNDeRKkbC3qq4OVwWO8OBKCD0E3C/Mm9CJvMPNfoEsWyl/FMqMrs/ZZ4WveeEgWilTJAauHwHkM05NMVsvSBqzyLUYgH1SCAgEHhvYaOp0kESV10SFGvHQwUuAPgOeNInigfIxnYxSy4EvznEuV1D89tOUhsK6xPBleH/8/jP5leOmCKHAgMBAAECggEABUuHhx+jwtjIyj2SU1Tgy0AawMs/F7LfgI5XFON5idFYWTffyDorRF1d/hfUArvnygPi8dATTPziPA9Dy1yqPmFut8WpUHIXcV4AQUqD0bJkKI5ASc3xrFy+EwnnfMT2quBnUxbI56U07LQ+VOI17pv/VPPwXuO/ecwkD0P9iybTzC1TVUX1ihepz7KT576ciGY3AI3OAkL1uUmk9gHC1BL3bYKnDZsM53y9ukHSxfUGRBw/DhZlxLz6iiI5dlwzT0e8Xbzm7lVUjdaJtf+0c2BAnu83HlYKXsIUwN4LooLyzzeM/GmyJj8miP8V/Hrft7F3HfStTbot8Zz94SlTkQKBgQDHCOqB6cRAV6LEtXWibnQzQ6bYUzyuOpe5k0LVPOgLzXUWMePE5HdGJSDUTIQPwG5pnyptwkZZFSEviwLWOZpp+iP8+/hSAExuQhEOtdiO+MsfGrVJsIimGtFJm6NzTUl3qBVpZ5h1UrgDt66nE6IY8jcj2a1ZOLdoHfxvBPjIXQKBgQDAjd/kfQfnY+drGPZZKFqEDXbkmR/hbVw10EJjUj+zK4HXXFo/p/5YpMXUSrJMsOWL/MZTuJfaqCXZjxep+OlsvvJB5sU/CTpweNzbRnjKqow5/TFmDx9yxlL9ghgAbmRUqTKnPafrjCV831zzVqTxPznr/nX6tS53GIUFrUWYMwKBgFS5zOoEkxA4vgcyn6LHlhuEfu+zby9TrebHcznGtZjgmykZj7Xu838eqvz0nXr/FYFMygjzHHMrdXcBm/GJj9vxKYS11h1CF0cQRKaYblleO0lAW0/FIEZlQBYvdMLOcanIGWVHPjUjQ0YB2Q0yZFxuL17JeOt0I07lBOJVoXnZAoGAGcIg5FDxgLu1lncVZWHnkei3MedmA2L8xdohN2YmTVKRoPyiugdAGB2atUZ0yZuUaYCcC1ETR1XdElh5AuzgVQLKJ5S2aDNU1PzmFpM7z2i3L362X/+/5juvmGgRy+T4mPa1ZvfxgaoSzbyXk5nQoNEjb3Vv1OTt3VKzYMoIGE8CgYAgcKCUkGPZ+ckoB+gzA18nUa4IHwWILzn3YV3NI7izxTO42i3/xwd7EgDz1MNdl7OT3c/tshtXxVWYqnjTORPthgUljfFEQ/cGfFmSNtEUKESrDEB+6iekI/HBIOcrL5nHHFpjgMmbyhMUY8kSUCviYESqJB42OnKbRCbuf2ahGg==", "json", "GBK", "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAkFRN+aJCedDRtznGWfj3aDAA1YpXdEOpB/AWz39GjHLSmCqSB9bidrqSpD62DOUFg3uoWSvsIQFzyCWTkrQz+oOiA6y8xiRSdYuRoh0bjUu5xeA9TPV6qURptrJh8xDFfnVpX6gub4+lp5KOLRqYPavnfzydRJehbWlbQXwsB//odW20vwEvGncYCos9kCbms0tyju/0a+FPcVeHY94w49K/wic3RFqZ8A8ZqFjtpQb8MyzsJq1DcqyU58tdsyjrlIsFA7eClfjyi1i/58nSJbldZNqDmNfGlz6cxePgvn3aHT5A3OVt80E3BcomhWZ+MdqQPpluvGWAEqbATvbqRwIDAQAB", "RSA2");
        AlipayClient alipayClient = new DefaultAlipayClient("https://openapi.alipay.com/gateway.do","2019110768638076","MIIEvAIBADANBgkqhkiG9w0BAQEFAASCBKYwggSiAgEAAoIBAQCLCKaSQJheldHv2anufmPXjdDybgFgQRMAfOwQA2R9MyLNTJ7mPMIyEyMATgVHbw8QfgzVyKiS58r8IkUAF9P4uNoX2y3Z7rS6VYfZinaDhDAcUjdc0VW4EENQWK83Saxp7dRkPI6wdWkmh3iEhnsh3BB5RTmLtdSLsJw04w2HePnwsO0H07Ruyi5UGdOltMpOLEh1n84OS9qiq4DbSkNAq7MeVGVejmukB2H+oICNykrpGM/VNR6s8h5WDvvkov51lLo3/KH1tdP3NNfGOrvm8t39GnZUm+2iW7E3qH19TgOEH+ED9dnwDAxrR7DKk/oD1NCMJfVjeMdAcjp46tJ1AgMBAAECggEAI+FSo3G6IMbHS/+/RAYoHAvEPZw4DLAeiES04xkNCOZ2d8brRv2tHCEmKkD4ScwWdiohg9Zp8qeREeqCJhkAIjMKTZfJyHE7AM9r5K9AEIa/p79r/C8l0P+Zb5XDeiAwN5Ih+tBJ/q3+OexCJOlTw5Q4SCOeSzjcpkQou9lQt4IVIcQ0Q5+Tm3nuXRKrr3uQETAnpzT9QLbfzz4r+yge1BON0NMiRlHq79RmP0aNCApChXyhI9vUxZMr0kKA1DdOo7Z2zUERbw4z69mYjAZhaxAmhJwTV/ebha2wubcWLA2ODx9A9Uez/nwkIUiGHSaAeGxqT853zTSZpa9hPZY1oQKBgQDgOJb6XVWanMC8pHZX3zzvG0FZUvTheft3C3fn8EMv1y92ofzMfkjq+zlf84Jm6uMJqZhqezwOxicbMNovPLEi/ZTKZTTqVO4kFuH7WCrOll9mQQwKPTrlsbisuC4qTBJTxpd+VriN5umQeZOwJaRa9KeUuR3hod6fR26L59tymQKBgQCevTZbtuRAjaxbvQiguHp/LI6ezTqPfuTtuPxsFFVBRaMEXqkihMEtYiS4/El9wQFH4a8/lDufOIuCczqYZQpR2W4yZaJlXEdYyAHI/EyKh1Sc8XabIl5j0BsOrbT28x4zBbKlDm9F23OLd7HcvvYLe0wCf/eKBWBaV4pCfnskPQKBgAMVJBY7FEM05n1xcWMR4Z+T61kUB+PVrnxbfwGETxgoSDHEVlplXhFE9Ov5yBAsKITofPzxxsiZNs5r5IQDvJezjskdjRWvpzp9yguPkb8XWCZqvISjhNzFjblqKtBQDWxzbX/CJ3Vj+FARK9OfINxTQr6LsPp6JDpiXAW2YVD5AoGAX57u/bRgLpqo9uIaLmayM6VtjC9oVURFNwY12A0vhhAisOEs1URO8b2rDVu8EWqQEPLcFFqAx9rR/xTWeTJ0UvW9MdKTTqeVut9QY/fHS7NVvuydW7oKTenwQplU52VwDaAjCtZ1JQmawqqpk6XcpPHyyZa4UIYIkqMlAAUcvKkCgYAIDNIq6pyrSG6e/Jc7lz+CXSHsYZRbocgO0eRDb5/nob3W0/ji5K6v4n2pSPy2BKq3Rb5QTZkp1H1Fy4BMcuojvfuY9coEFGs7qN/kzS11J0ghyPRSGm/LFyYTUihBi0Yj03kFTZgM5HIB5CvvcuTEoiHaJPDEOyxUqecj3WGD2g==","json","GBK","MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEA0CtCfUwI15INsPI63Cp6Y9JdfjuntoaCskH/QkCJbEa6J4MCUMi4B0dngqmOxya57izb6KxAcTtL3ywpKTxf/RaFyxw5weSi3V+7pxq7QI0uCs09iseF7LqXMiJf+B3uYTbyhX7MBO1nnML05ihnWkKZTCgeaZWBVbxb8BeIzJYDHD2dkrI/n+C9afh7oMtWk6TcmJUMoJgSCtte8oPpNEgOlYfIplnR5OV7rijSSS+tAuesRFwt8G9vd/JN9DT6OC9ziRZlb5S9usNiQOMD6CG7ozfZuI1etxn3AcVLy10wnk0aSVmQHHTqqe+jvlZjLi8Czqf/YD8M10KjgevN/wIDAQAB","RSA2");
        AlipaySystemOauthTokenRequest request = new AlipaySystemOauthTokenRequest();
        if ("authorization_code".equals(alipayAppletAuthRequest.getGrant_type())) {
            request.setGrantType("authorization_code");
            request.setCode(alipayAppletAuthRequest.getCode());
        }
        if ("refresh_token".equals(alipayAppletAuthRequest.getGrant_type())) {
            request.setGrantType("refresh_token");
            request.setRefreshToken(alipayAppletAuthRequest.getRefresh_token());
        }

        if (StringUtils.isNotEmpty(alipayAppletAuthRequest.getGrant_type())) {
            request.setGrantType("authorization_code");
            request.setCode(alipayAppletAuthRequest.getCode());
        }
        AlipaySystemOauthTokenResponse response = null;
        AlipayAppletAuthResponse alipayAppletAuthResponse = new AlipayAppletAuthResponse();
        try {
            response = alipayClient.execute(request);
        } catch (Exception e) {
            log.error("AlipayAppletController auth is error alipayAppletAuthRequest:" + JSONObject.toJSONString(alipayAppletAuthRequest), e);
        }
        if (null != response && response.isSuccess()) {
            log.info("AlipayAppletController auth is success alipayAppletAuthRequest:" + JSONObject.toJSONString(alipayAppletAuthRequest));
            EnhanceBeanUtils.copyProperties(response, alipayAppletAuthResponse);
            alipayAppletAuthResponse.setSuccess(true);
        } else {
            alipayAppletAuthResponse.setSuccess(false);
            log.info("AlipayAppletController auth is fail alipayAppletAuthRequest:" + JSONObject.toJSONString(alipayAppletAuthRequest));
        }
        log.info("AlipayAppletController auth success alipayAppletAuthRequest:" + JSONObject.toJSONString(alipayAppletAuthRequest) + ",memberAuthResponse:" + JSONObject.toJSONString(alipayAppletAuthResponse));
        return alipayAppletAuthResponse;
    }

    @RequestMapping(value = {"/queryPhone"})
    @ResponseBody
    public String queryPhone(@RequestBody String request) {
        log.info("AlipayAppletController queryPhone request:" + request);

        try {
            //1. 获取验签和解密所需要的参数
            Map<String, String> openapiResult = JSON.parseObject(request,
                    new TypeReference<Map<String, String>>() {
                    }, Feature.OrderedField);
            String signType = StringUtils.defaultIfBlank(openapiResult.get("signType"), "RSA2");
            String charset = StringUtils.defaultIfBlank(openapiResult.get("charset"), "UTF-8");
            String encryptType = StringUtils.defaultIfBlank(openapiResult.get("encryptType"), "AES");
            String sign = openapiResult.get("sign");
            String content = openapiResult.get("response");

            //如果密文的
            boolean isDataEncrypted = !content.startsWith("{");
            boolean signCheckPass = false;

            //2. 验签
            String signContent = content;
            //String signVeriKey = "你的小程序对应的支付宝公钥（为扩展考虑建议用appId+signType做密钥存储隔离）";
            //String decryptKey = "你的小程序对应的加解密密钥（为扩展考虑建议用appId+encryptType做密钥存储隔离）"
            //如果是加密的报文则需要在密文的前后添加双引号
            if (isDataEncrypted) {
                signContent = "\"" + signContent + "\"";
            }
            try {
                signCheckPass = AlipaySignature.rsaCheck(signContent, sign, signVeriKey, charset, signType);
            } catch (AlipayApiException e) {
                //验签异常, 日志
                log.error("{}", e);
            }
            if (!signCheckPass) {
                //验签不通过（异常或者报文被篡改），终止流程（不需要做解密）
                throw new Exception("验签失败");
            }
            //3. 解密
            String plainData = null;
            if (isDataEncrypted) {
                try {
                    String info= AlipayEncrypt.decryptContent(content, encryptType, decryptKey, charset);
                    log.info("AlipayAppletController queryPhone info:" + info+",request:" + request);
                    return info;
                } catch (AlipayApiException e) {
                    log.error("AlipayAppletController queryPhone is error request:" + request,e);
                    //解密异常, 记录日志
                    throw new Exception("解密异常");
                }
            } else {
                plainData = content;
            }
            log.info("AlipayAppletController.queryPhone content:"+content+",request:" + request);
            return plainData;
        } catch (Exception e) {
            log.error("AlipayAppletController queryPhone is error request:" + request, e);
        }

        return "";
    }


}
