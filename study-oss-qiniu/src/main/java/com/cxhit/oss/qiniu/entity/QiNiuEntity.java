package com.cxhit.oss.qiniu.entity;

import java.io.Serializable;

/**
 * <p>
 * 七牛云的相关属性配置，一般与数据库对应，即从数据库中读取七牛云的相关配置
 * 当然也可以写在配置类（yaml文件）中。
 * </p>
 *
 * @author 拾年之璐
 * @since 2021/11/15 16:53
 */
public class QiNiuEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    private Integer id;

    /**
     * 七牛云账号的秘钥管理之：AccessKey
     */
    private String accessKey;

    /**
     * 七牛云账号的秘钥管理之：SecretKey
     */
    private String secretKey;

    /**
     * 七牛云空间名称
     */
    private String bucket;

    /**
     * 七牛云该空间（bucket）绑定的CDN加速域名
     */
    private String domain;

    /**
     * 七牛云该空间（bucket）绑定的CDN加速域名的协议 http | https
     */
    private String protocol;

    /*以下信息是在项目中产生*/
    private String uploadToken;

    public QiNiuEntity() {
    }

    public QiNiuEntity(String accessKey, String secretKey, String bucket, String domain, String protocol) {
        this.accessKey = accessKey;
        this.secretKey = secretKey;
        this.bucket = bucket;
        this.domain = domain;
        this.protocol = protocol;
    }

    public Integer getId() {
        return id;
    }

    public String getAccessKey() {
        return accessKey;
    }

    public String getSecretKey() {
        return secretKey;
    }

    public String getBucket() {
        return bucket;
    }

    public String getDomain() {
        return domain;
    }

    public String getProtocol() {
        return protocol;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setAccessKey(String accessKey) {
        this.accessKey = accessKey;
    }

    public void setSecretKey(String secretKey) {
        this.secretKey = secretKey;
    }

    public void setBucket(String bucket) {
        this.bucket = bucket;
    }

    public void setDomain(String domain) {
        this.domain = domain;
    }

    public void setProtocol(String protocol) {
        this.protocol = protocol;
    }

    public String getUploadToken() {
        return uploadToken;
    }

    public void setUploadToken(String uploadToken) {
        this.uploadToken = uploadToken;
    }

    @Override
    public String toString() {
        return "QiNiuEntity{" +
                "id=" + id +
                ", accessKey='" + accessKey + '\'' +
                ", secretKey='" + secretKey + '\'' +
                ", bucket='" + bucket + '\'' +
                ", domain='" + domain + '\'' +
                ", protocol='" + protocol + '\'' +
                '}';
    }
}
