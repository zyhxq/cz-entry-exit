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

    String signVeriKey = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAvCJAPH+gT/UNDd/OAwcPqJyYthJczbrI6zO0E6k2zY1JAcCynf+/tfEkHZktDPrYD0SEMxjlSMaOGOS6Pe+VQBG8u81fCKDF2M5Yck8Lk8IO2QAKtnL7eK1J3pCkapJP4So4ft8EVjdNM4s2Okw4pt970i8kMdWYqYS/9KlOoUtEqnU94EYbJTc9+jTPl5duVnbzoGwv4OVKf1QumhvVgamJst54bX+66S1uEc6oN7/CYjO+VzFUHiGyUco7NAcOBDcvKB+PszmDm4bVZJ4jwZBoZCQ6aXlKcJ/EbMKfSAFxqXnoS4q2hnfzVCEL22Avaq6wW8P1uZoQ6o91FRCgVQIDAQAB";
    String decryptKey = "y4aadou8owaZ1pbxDhI7Dg==";

    @Autowired
    private UserInfoService userInfoService;

    @RequestMapping(value = {"/auth"})
    @ResponseBody
    public AlipayAppletAuthResponse addUserInfo(@RequestBody AlipayAppletAuthRequest alipayAppletAuthRequest) {
        log.info("AlipayAppletController addUserInfo alipayAppletAuthRequest:" + JSONObject.toJSONString(alipayAppletAuthRequest));
        //AlipayClient alipayClient = new DefaultAlipayClient("https://openapi.alipay.com/gateway.do","app_id","your private_key","json","GBK","alipay_public_key","RSA2");
        AlipayClient alipayClient = new DefaultAlipayClient("https://openapi.alipay.com/gateway.do", "2019082766450935", "MIIEvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQCYKrImnxpYLxiRwt/r+zzRpxMD06gztEw+LpB6PxCiPnKO4UKjqR+wdx6BbTJQl7z37NJp14S455jLvGb4w0t/cQeXQ0pcR7TlSrYSPuNQcYzJ6wQBYPe92gnEev51vLKXgv/pk/DIq926uRb5Q/dXeVB28JCgi4inVUJDqqhmRbL0Bt9OCXDWsyJD16n4xThGDu4nGQl7m1sLbo0z41EjPvgv0rmIKimVmpFPmSgm6DE2daCteMfGG9WNgwuh40HttNCgcKOrhIloneGtvBoza+Hj0yf9+1T244xuT/xBYggUgB7+2UCZSOJf8Bc6piPCQzsTw2xzBTWfS5ZB3+tvAgMBAAECggEAXU7ZHVu0sNx4SXFhE/hka/7yZwdT/hiSJBMznvKrBYIbh0fUMD1V5ES3HRBTqR3Sz4iV4jc4+JVzmYL29ZXz5rrtt7CYmYuS/oNYsEmF5F8swOhu9VV7tRxNnwD4pkPGk6fNu3JvVO4msggrcUBfHHGUiMqtNPU7H7jlQE8Wolcq1XrBh8I2ns0HEgvK3ejorZiudLVig1X9u6iR5aezqZ8PBeHYgl/QP79xzivEUMLb1tA/r3pCqD6BYVwd5A+pwk6SHJOZibtF35ZgYsO4Dzupz4TK7OSm9KNdrIijlAjoGsOPIST9VKCKrSDiF1RmaPWBFCFJvW3BbiyfcP8nQQKBgQDMnCD1RETiPCzXGDmwCI8eGMsTom7tCq3Dm5g1gnIQ+pAsW9lciosvoRKdrczx6WR2y0tcVFSxK8ZUWQYdS6gdfXW804+hglkQgVrhFXOSLwMQ0wknuiJgtsI/Ozwe8GAAuCdBjgSATvPgBsdPZZcUkEy1MIN4haPEcBOPpKcpjwKBgQC+Yp2MZ8f4FORyQE717vBQEM6XGyfTclHoyWtz/p0gbUKOQp6HHZWvRGV93KLB/J66TLcDiHCwd0cUHAkznkMOJ9cjX+LmUIHSt81QDLlRMgdBqHXL8SorQwoKuhenchD3TbKg4YaeC+eciQCgsTkkhtjBNaIutQOG4cGzkwtwIQKBgGGx+pqhajKLgO2DB6hhrdQ1CmHpoQVVn/pSBK8oBcbNnKFRicuB60tmoTjgj4bCZdHC58zCB15BBIWprB24ziCmkt0CNwZq8pPK79WhJHmcMN6LpUduvJNpL/55Dg/Hvk8ZnsTSRpvtFszU772dSDHtZHLvIxcc2EEnMm/XsmlJAoGAE62xxy969eOC8WRARR9ba4sxL2E5LpvkZ4FaP/UTXKSuenHcXO7r59/T9bx2fkKUTYFccVsK+tJ5YFS9hr5XBAGONTPWCiRMJvGkoR+PkBrxsRGui1P0HeCiCxXNGBusMUOwsPxeo7trRzzJ+dIXq/TcNqxKhTVkle6DzQHn7sECgYEAn2AFuRQn8WwfRdWBY6iZVW1Brz2hP0QnRk2Gf2rJKnBkg6POL3a+HPApkdDcJ1gTxsnJDXnrh1PLkkdbMVTFVFC+EZaXZvsxdP6hT6W99sViL28V01EWA7J1lKR1WCKzO8zn6oL6QC8ax+B3Ub6NOm+UP9BewX6Gu1GgZ0Z8R/4=", "json", "GBK", "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAvCJAPH+gT/UNDd/OAwcPqJyYthJczbrI6zO0E6k2zY1JAcCynf+/tfEkHZktDPrYD0SEMxjlSMaOGOS6Pe+VQBG8u81fCKDF2M5Yck8Lk8IO2QAKtnL7eK1J3pCkapJP4So4ft8EVjdNM4s2Okw4pt970i8kMdWYqYS/9KlOoUtEqnU94EYbJTc9+jTPl5duVnbzoGwv4OVKf1QumhvVgamJst54bX+66S1uEc6oN7/CYjO+VzFUHiGyUco7NAcOBDcvKB+PszmDm4bVZJ4jwZBoZCQ6aXlKcJ/EbMKfSAFxqXnoS4q2hnfzVCEL22Avaq6wW8P1uZoQ6o91FRCgVQIDAQAB", "RSA2");
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
            log.error("AlipayAppletController addUserInfo is error alipayAppletAuthRequest:" + JSONObject.toJSONString(alipayAppletAuthRequest), e);
        }
        if (null != response && response.isSuccess()) {
            log.info("AlipayAppletController addUserInfo is success alipayAppletAuthRequest:" + JSONObject.toJSONString(alipayAppletAuthRequest));
            EnhanceBeanUtils.copyProperties(response, alipayAppletAuthResponse);
            alipayAppletAuthResponse.setSuccess(true);
        } else {
            alipayAppletAuthResponse.setSuccess(false);
            log.info("AlipayAppletController addUserInfo is fail alipayAppletAuthRequest:" + JSONObject.toJSONString(alipayAppletAuthRequest));
        }
        log.info("AlipayAppletController addUserInfo success alipayAppletAuthRequest:" + JSONObject.toJSONString(alipayAppletAuthRequest) + ",memberAuthResponse:" + JSONObject.toJSONString(alipayAppletAuthResponse));
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
