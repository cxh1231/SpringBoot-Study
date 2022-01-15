package com.cxhit.pay.wechat.entity;

import java.io.Serializable;

/**
 * 微信支付配置信息
 *
 * @author 拾年之璐
 * @since 2022/1/13 17:20
 */
public class WeChatPayEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 必填：微信支付商户号
     */
    private String mchId;

    /**
     * 必填：商户绑定的微信公众号、小程序、开放平台等的appid
     */
    private String appId;

    /**
     * 必填：APIv2密钥（调用v2版本的API时，需用APIv2密钥生成签名）
     */
    private String mchKey;

    /**
     * 必填：APIv3密钥（调用APIv3的下载平台证书接口、处理回调通知中报文时，要通过该密钥来解密信息）
     */
    private String apiV3Key;

    /**
     * 必填：apiclient_cert.p12证书文件的绝对路径，或者以classpath:开头的类路径。
     */
    private String keyPath;

    /**
     * 必填：apiclient_key.pem证书文件的绝对路径，或者以classpath:开头的类路径。
     */
    private String privateKeyPath;

    /**
     * 必填：apiclient_cert.pem证书文件的绝对路径，或者以classpath:开头的类路径。
     */
    private String privateCertPath;

    /**
     * 必填：微信支付异步回调通知地址。通知url必须以https开头（SSL协议），外网可访问，不能携带参数。
     */
    private String notifyUrl;

    public String getMchId() {
        return mchId;
    }

    public void setMchId(String mchId) {
        this.mchId = mchId;
    }

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public String getMchKey() {
        return mchKey;
    }

    public void setMchKey(String mchKey) {
        this.mchKey = mchKey;
    }

    public String getApiV3Key() {
        return apiV3Key;
    }

    public void setApiV3Key(String apiV3Key) {
        this.apiV3Key = apiV3Key;
    }

    public String getKeyPath() {
        return keyPath;
    }

    public void setKeyPath(String keyPath) {
        this.keyPath = keyPath;
    }

    public String getPrivateKeyPath() {
        return privateKeyPath;
    }

    public void setPrivateKeyPath(String privateKeyPath) {
        this.privateKeyPath = privateKeyPath;
    }

    public String getPrivateCertPath() {
        return privateCertPath;
    }

    public void setPrivateCertPath(String privateCertPath) {
        this.privateCertPath = privateCertPath;
    }

    public String getNotifyUrl() {
        return notifyUrl;
    }

    public void setNotifyUrl(String notifyUrl) {
        this.notifyUrl = notifyUrl;
    }
}
