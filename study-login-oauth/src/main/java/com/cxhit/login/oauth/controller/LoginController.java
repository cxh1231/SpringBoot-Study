package com.cxhit.login.oauth.controller;

import me.zhyd.oauth.config.AuthConfig;
import me.zhyd.oauth.model.AuthCallback;
import me.zhyd.oauth.model.AuthResponse;
import me.zhyd.oauth.model.AuthUser;
import me.zhyd.oauth.request.AuthQqRequest;
import me.zhyd.oauth.request.AuthRequest;
import me.zhyd.oauth.request.AuthWeiboRequest;
import me.zhyd.oauth.utils.AuthStateUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * <p>
 * 描述
 * </p>
 *
 * @author 拾年之璐
 * @since 2021/11/30 19:31
 */
@Controller
public class LoginController {

    /**
     * 默认访问域名，跳转至登录页面
     *
     * @return 页面跳转
     */
    @RequestMapping("/")
    public String loginPage() {
        return "/login";
    }

    /**
     * 跳转至登录页面
     *
     * @param response 页面跳转
     */
    @RequestMapping("/qqLogin")
    public void qqLogin(HttpServletResponse response) throws IOException {
        //获取QQ登录的对象
        AuthRequest authRequest = this.getAuthRequest("QQ");
        //打印生成的链接
        System.out.println("生成登录链接：" + authRequest.authorize("yourState"));
        //页面跳转，其中state参数可自定义
        response.sendRedirect(authRequest.authorize(AuthStateUtils.createState()));
    }

    /**
     * QQ登录成功后返回到此页
     *
     * @param callback 登录用户的信息
     * @return
     */
    @RequestMapping("/qqlogin/callback")
    public Object qqLoginCallback(AuthCallback callback) {
        //获取实例
        AuthRequest authRequest = this.getAuthRequest("QQ");
        // 打印返回的授权信息(QQ登录为code，通过code获取用户信息）
        System.out.println(callback.getCode());

        //根据返回的参数，执行登录请求（获取用户信息）
        AuthResponse<AuthUser> authResponse = authRequest.login(callback);

        //打印授权返回代码（2000 表示成功，可以用来判断用户登录成功与否）
        System.out.println("状态码：" + authResponse.getCode());

        //打印用户的昵称、ID、头像等基本信息
        System.out.println("用户的UnionID：" + authResponse.getData().getUuid());
        System.out.println("用户的昵称：" + authResponse.getData().getNickname());
        System.out.println("用户的头像：" + authResponse.getData().getAvatar());

        //打印用户的Token中的信息
        System.out.println("access_token：" + authResponse.getData().getToken().getAccessToken());
        System.out.println("用户的OpenId：" + authResponse.getData().getToken().getOpenId());

        // 打印更加详细的信息（第三方平台返回的原始用户信息）
        //getInnerMap()：将JSONObject转换成Map键值对
        System.out.println("用户的城市：" + authResponse.getData().getRawUserInfo().getInnerMap().get("city"));
        System.out.println("用户的年份：" + authResponse.getData().getRawUserInfo().getInnerMap().get("year"));

        // 将JSON包返回到前端页面显示
        return authResponse;
    }


    /**
     * 根据登录类型构造指定的授权请求
     *
     * @param type 类型：QQ、Weibo、Weixin、
     * @return 授权请求
     */
    private AuthRequest getAuthRequest(String type) {
        AuthRequest authRequest = null;
        switch (type) {
            case "QQ":
                authRequest = new AuthQqRequest(AuthConfig.builder()
                        .clientId("APPID")
                        .clientSecret("APP Secret")
                        .redirectUri("https://xxx.com/plugin/qqlogin/callback")
                        .unionId(true)
                        .build()
                );
                break;
            case "Weibo":
                authRequest = new AuthWeiboRequest(AuthConfig.builder()
                        .clientId("appid")
                        .clientSecret("app secret")
                        .redirectUri("https://xxx.com/plugin/sinalogin/callback")
                        .build());
                break;
            case "WeChat":
                break;
        }
        return authRequest;
    }

}
