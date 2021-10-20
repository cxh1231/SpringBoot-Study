package com.cxhit.captcha.controller;


import com.cxhit.captcha.entity.CaptchaDomain;
import com.cxhit.captcha.service.ICaptchaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.security.SecureRandom;
import java.util.Random;

/**
 * <p>
 * 生成验证码接口
 * </p>
 *
 * @author 拾年之璐
 * @since 2021-10-04 0004 21:16
 */
@Controller
@RequestMapping("/")
public class CaptchaController {

    @Autowired
    private ICaptchaService captchaService;

    @Resource
    protected HttpServletRequest request;
    @Resource
    protected HttpServletResponse response;

    @GetMapping("")
    @ResponseBody
    public String index() {
        return "<html>\n" +
                "\n" +
                "<head>\n" +
                "<title>验证码生成</title>\n" +
                "</head>\n" +
                "\n" +
                "<body>\n" +
                "<p>获取验证码实体：<a href=\"/captcha/get\" target=_blank>localhost:9003/captcha/get</a><br></p>\n" +
                "<p>获取验证码图片：<a href=\"/captcha/get/image?type=math\" target=_blank>localhost:9003/captcha/get/image?type=math</a><br></p>\n" +
                "<p>链接末尾可加的type参数有：char、math、math2" +
                "</body>\n" +
                "\n" +
                "</html>\n";
    }

    /**
     * 获取验证码接口
     *
     * @param type 类型，char - 字符(默认) | math - 一位数算式 | math2 - 两位数算式
     * @return 结果
     */
    @GetMapping("/captcha/get")
    @ResponseBody
    public CaptchaDomain getCaptcha(@RequestParam(value = "type", required = false, defaultValue = "char") String type) {
        // 生成验证码实体
        CaptchaDomain captchaDomain = captchaService.createGoogleCaptcha(type);
        if (null != captchaDomain) {
            // 将验证码保存至redis，包含前缀
            // redisUtils.set(captchaDomain.getToken(), captchaDomain.getCode(), 300L);
            System.out.println("生成验证码："+captchaDomain.getCode());
            // 无用信息设空
            captchaDomain.setText(null);
            captchaDomain.setCode(null);
            // 返回前端信息
            return captchaDomain;
        } else {
            return null;
        }

    }

    /**
     * 直接输出图片
     *
     * @param type 类型，char - 字符(默认) | math - 一位数算式 | math2 - 两位数算式
     */
    @GetMapping("/captcha/get/image")
    public void getCaptchaImage(@RequestParam(value = "type", required = false, defaultValue = "char") String type) {
        ServletOutputStream out = null;
        Random random = new SecureRandom();
        int rand = random.nextInt(2);
        CaptchaDomain captchaDomain = null;
        if (rand == 0) {
            // 生成验证码实体
            captchaDomain = captchaService.createGoogleCaptcha(type);
        } else {
            captchaDomain = captchaService.createHutoolCaptcha(160, 60);
        }
        System.out.println("生成验证码："+captchaDomain.getCode());
        try {
            response.setContentType("image/jpeg");
            out = response.getOutputStream();
            ImageIO.write(captchaDomain.getImage(), "jpg", out);
            out.flush();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (out != null) {
                    out.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }

}
