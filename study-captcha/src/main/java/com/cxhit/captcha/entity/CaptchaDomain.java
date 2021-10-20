package com.cxhit.captcha.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.awt.image.BufferedImage;
import java.io.Serializable;

/**
 * <p>
 * 第三方验证码实体类
 * </p>
 *
 * @author 拾年之璐
 * @since 2021-10-04 0004 20:02
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CaptchaDomain implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 验证码的Token
     */
    private String token;

    /**
     * 验证码的字符。返回的JSON，禁止返回给前端。
     */
    @JsonIgnore
    private String text;

    /**
     * 验证码的验证字符。比如算式的结果等。
     */
    @JsonIgnore
    private String code;

    /**
     * 验证码缓冲图像
     */
    @JsonIgnore
    private BufferedImage image;

    /**
     * 验证码图片的Base64字符串
     */
    private String base64;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public BufferedImage getImage() {
        return image;
    }

    public void setImage(BufferedImage image) {
        this.image = image;
    }

    public String getBase64() {
        return base64;
    }

    public void setBase64(String base64) {
        this.base64 = base64;
    }

    @Override
    public String toString() {
        return "CaptchaDomain{" +
                "token='" + token + '\'' +
                ", text='" + text + '\'' +
                ", code='" + code + '\'' +
                ", image=" + image +
                ", base64='" + base64 + '\'' +
                '}';
    }
}
