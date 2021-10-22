package com.cxhit.captcha.config;

import com.google.code.kaptcha.impl.DefaultKaptcha;
import com.google.code.kaptcha.util.Config;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Properties;

import static com.google.code.kaptcha.Constants.*;

/**
 * <p>
 * Google验证码配置类
 * </p>
 *
 * @author 拾年之璐
 * @since 2021-10-04 0004 20:12
 */
@Configuration
public class KaptchaConfig {

    /**
     * 验证码配置默认配置
     *
     * @return 配置信息
     */
    @Bean(name = "captchaProducer")
    public DefaultKaptcha getKaptchaBean() {
        DefaultKaptcha defaultKaptcha = new DefaultKaptcha();
        Properties properties = new Properties();
        // 是否有边框。默认true，可选：yes，no
        properties.setProperty(KAPTCHA_BORDER, "yes");
        // 验证码文本字符颜色。默认Color.BLACK
        properties.setProperty(KAPTCHA_TEXTPRODUCER_FONT_COLOR, "black");
        // 验证码图片宽度。默认200
        properties.setProperty(KAPTCHA_IMAGE_WIDTH, "160");
        // 验证码图片高度。默认50
        properties.setProperty(KAPTCHA_IMAGE_HEIGHT, "60");
        // 验证码文本字符大小。默认40
        properties.setProperty(KAPTCHA_TEXTPRODUCER_FONT_SIZE, "38");
        // KAPTCHA_SESSION_KEY
        properties.setProperty(KAPTCHA_SESSION_CONFIG_KEY, "kaptchaCode");
        // 验证码文本字符长度。默认5
        properties.setProperty(KAPTCHA_TEXTPRODUCER_CHAR_LENGTH, "4");
        // 验证码文本字体样式。默认：new Font("Arial", 1, fontSize), new Font("Courier", 1, fontSize)
        properties.setProperty(KAPTCHA_TEXTPRODUCER_FONT_NAMES, "Arial,Courier");
        // 图片样式。
        // 水纹：com.google.code.kaptcha.impl.WaterRipple
        // 鱼眼：com.google.code.kaptcha.impl.FishEyeGimpy
        // 阴影：com.google.code.kaptcha.impl.ShadowGimpy
        properties.setProperty(KAPTCHA_OBSCURIFICATOR_IMPL, "com.google.code.kaptcha.impl.ShadowGimpy");
        Config config = new Config(properties);
        defaultKaptcha.setConfig(config);
        return defaultKaptcha;
    }

    /**
     * 验证码数学题类配置（一位数加减乘除）
     *
     * @return 配置信息
     */
    @Bean(name = "captchaProducerMathOne")
    public DefaultKaptcha getKaptchaBeanMathOne() {
        DefaultKaptcha defaultKaptcha = new DefaultKaptcha();
        Properties properties = commonConfig("com.cxhit.captcha.config.KaptchaMathOneTextCreator");
        Config config = new Config(properties);
        defaultKaptcha.setConfig(config);
        return defaultKaptcha;
    }

    /**
     * 验证码数学题类配置（两位数的加减乘除）
     *
     * @return 配置信息
     */
    @Bean(name = "captchaProducerMathTwo")
    public DefaultKaptcha getKaptchaBeanMathTwo() {
        DefaultKaptcha defaultKaptcha = new DefaultKaptcha();
        Properties properties = commonConfig("com.cxhit.captcha.config.KaptchaMathTwoTextCreator");
        Config config = new Config(properties);
        defaultKaptcha.setConfig(config);
        return defaultKaptcha;
    }

    /**
     * 算式运算配置的公共配置类
     *
     * @param textImpl 验证码文本生成器
     * @return 配置类
     */
    protected static Properties commonConfig(String textImpl) {
        Properties properties = new Properties();
        // 是否有边框。默认为true，可设置：yes，no
        properties.setProperty(KAPTCHA_BORDER, "yes");
        // 边框颜色。默认：Color.BLACK
        properties.setProperty(KAPTCHA_BORDER_COLOR, "105,179,90");
        // 验证码文本字符颜色。默认：Color.BLACK
        properties.setProperty(KAPTCHA_TEXTPRODUCER_FONT_COLOR, "blue");
        // 验证码图片宽度。默认：200
        properties.setProperty(KAPTCHA_IMAGE_WIDTH, "160");
        // 验证码图片高度。默认：50
        properties.setProperty(KAPTCHA_IMAGE_HEIGHT, "60");
        // 验证码文本字符大小。默认：40
        properties.setProperty(KAPTCHA_TEXTPRODUCER_FONT_SIZE, "35");
        // KAPTCHA_SESSION_KEY
        properties.setProperty(KAPTCHA_SESSION_CONFIG_KEY, "kaptchaCodeMath");
        // 验证码文本生成器
        properties.setProperty(KAPTCHA_TEXTPRODUCER_IMPL, textImpl);
        // 验证码文本字符间距。默认：2
        properties.setProperty(KAPTCHA_TEXTPRODUCER_CHAR_SPACE, "3");
        // 验证码文本字符长度。默认：5
        properties.setProperty(KAPTCHA_TEXTPRODUCER_CHAR_LENGTH, "6");
        // 验证码文本字体样式。默认：new Font("Arial", 1, fontSize), new Font("Courier", 1, fontSize)
        properties.setProperty(KAPTCHA_TEXTPRODUCER_FONT_NAMES, "Arial,Courier");
        // 验证码噪点颜色。默认：Color.BLACK
        properties.setProperty(KAPTCHA_NOISE_COLOR, "white");
        // 干扰实现类
        properties.setProperty(KAPTCHA_NOISE_IMPL, "com.google.code.kaptcha.impl.NoNoise");
        // 图片样式。
        // 水纹：com.google.code.kaptcha.impl.WaterRipple
        // 鱼眼：com.google.code.kaptcha.impl.FishEyeGimpy
        // 阴影：com.google.code.kaptcha.impl.ShadowGimpy
        properties.setProperty(KAPTCHA_OBSCURIFICATOR_IMPL, "com.google.code.kaptcha.impl.ShadowGimpy");
        // 返回生成的配置类
        return properties;
    }
}
