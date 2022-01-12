package com.cxhit.pay.alipay.entity;

import java.io.Serializable;

/**
 * 支付宝支付配置信息实体
 *
 * @author 拾年之璐
 * @since 2022/1/10 22:13
 */

public class AlipayEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 必填：应用AppId，例如：2019091767145019
     */
    private String appId;

    /**
     * 必填：应用名称
     */
    private String appName;

    /**
     * 必填：应用私钥（Java语言），例如：MIIEvQIBADANB ... ...
     */
    private String merchantPrivateKey;

    // 接口加签方式分：证书模式（收、退款）和公钥模式（只能收款）
    // 证书模式文档：https://opendocs.alipay.com/common/02kipl
    // 优先级：证书模式 ＞ 非证书模式

    /**
     * 证书模式：【应用公钥证书】文件路径，路径优先级：文件系统 ＞ CLASS_PATH
     */
    private String merchantCertPath;

    /**
     * 证书模式：【支付宝公钥证书】文件路径，路径优先级：文件系统 ＞ CLASS_PATH
     */
    private String alipayCertPath;

    /**
     * 证书模式：【支付宝根证书】文件路径，路径优先级：文件系统 ＞ CLASS_PATH
     */
    private String alipayRootCertPath;

    /**
     * 公钥模式：只需要填写此【支付宝公钥】即可，无需赋值上面的三个证书路径
     */
    private String alipayPublicKey;

    /**
     * 可选：异步通知接收服务地址，例如：https://www.test.com/callback
     */
    private String notifyUrl;

    /**
     * 可选：AES密钥（接口内容加密方式），调用AES加解密相关接口时需要
     */
    private String encryptKey;

    /**
     * 必填：协议类型：http | https 二选一
     */
    private String protocol = "https";

    /**
     * 必填：gatewayHost，默认：openapi.alipay.com
     */
    private String gatewayHost = "openapi.alipay.com";

    /**
     * 必填：签名类型，默认RSA2
     */
    private String signType = "RSA2";


    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public String getAppName() {
        return appName;
    }

    public void setAppName(String appName) {
        this.appName = appName;
    }

    public String getMerchantPrivateKey() {
        return merchantPrivateKey;
    }

    public void setMerchantPrivateKey(String merchantPrivateKey) {
        this.merchantPrivateKey = merchantPrivateKey;
    }

    public String getMerchantCertPath() {
        return merchantCertPath;
    }

    public void setMerchantCertPath(String merchantCertPath) {
        this.merchantCertPath = merchantCertPath;
    }

    public String getAlipayCertPath() {
        return alipayCertPath;
    }

    public void setAlipayCertPath(String alipayCertPath) {
        this.alipayCertPath = alipayCertPath;
    }

    public String getAlipayRootCertPath() {
        return alipayRootCertPath;
    }

    public void setAlipayRootCertPath(String alipayRootCertPath) {
        this.alipayRootCertPath = alipayRootCertPath;
    }

    public String getAlipayPublicKey() {
        return alipayPublicKey;
    }

    public void setAlipayPublicKey(String alipayPublicKey) {
        this.alipayPublicKey = alipayPublicKey;
    }

    public String getNotifyUrl() {
        return notifyUrl;
    }

    public void setNotifyUrl(String notifyUrl) {
        this.notifyUrl = notifyUrl;
    }

    public String getEncryptKey() {
        return encryptKey;
    }

    public void setEncryptKey(String encryptKey) {
        this.encryptKey = encryptKey;
    }

    public String getProtocol() {
        return protocol;
    }

    public void setProtocol(String protocol) {
        this.protocol = protocol;
    }

    public String getGatewayHost() {
        return gatewayHost;
    }

    public void setGatewayHost(String gatewayHost) {
        this.gatewayHost = gatewayHost;
    }

    public String getSignType() {
        return signType;
    }

    public void setSignType(String signType) {
        this.signType = signType;
    }
}
