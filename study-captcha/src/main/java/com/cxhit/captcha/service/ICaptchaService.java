package com.cxhit.captcha.service;

import com.cxhit.captcha.entity.CaptchaDomain;

/**
 * <p>
 * 验证码服务接口
 * </p>
 *
 * @author 拾年之璐
 * @since 2021-10-06 0006 19:47
 */
public interface ICaptchaService {

    /**
     * Kaptcha生成验证码实体的服务层接口
     *
     * @param type 类型，char - 字符(缺省) | math - 一位数算式 | math2 - 两位数算式
     * @return 验证码实体
     */
    public CaptchaDomain createGoogleCaptcha(String type);


    /**
     * Hutool生成验证码实体的服务层接口
     *
     * @param width  验证码图片的宽度
     * @param height 验证码图片的高度
     * @return 验证码实体
     */
    public CaptchaDomain createHutoolCaptcha(Integer width, Integer height);
}
