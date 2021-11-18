package com.cxhit.notice.sms;

import com.aliyun.dysmsapi20170525.Client;
import com.aliyun.dysmsapi20170525.models.SendSmsRequest;
import com.aliyun.dysmsapi20170525.models.SendSmsResponse;
import com.aliyun.teaopenapi.models.Config;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * <p>
 * 描述
 * </p>
 *
 * @author 拾年之璐
 * @since 2021/11/18 21:00
 */
@SpringBootTest
public class StudyNoticeSmsTests {

    @Test
    void Test() throws Exception {
        // 基本信息配置
        Config config = new Config()
                // 必填，开发者的 AccessKey ID
                .setAccessKeyId("ABCDEFG")
                // 必填，开发者的 AccessKey Secret
                .setAccessKeySecret("ABCDEFG")
                // 默认，暂时不支持多Region，默认即可
                .setRegionId("cn-hangzhou")
                // 默认，产品域名，开发者无需替换
                .setEndpoint("dysmsapi.aliyuncs.com")
                // 可选，启用https协议
                .setProtocol("https");

        // 短信请求信息配置
        SendSmsRequest sendSmsRequest = new SendSmsRequest()
                // 必填，接收短信的手机号
                .setPhoneNumbers("15100000000")
                // 必填，设置签名名称，应严格按"签名名称"填写，格式如：阿里云
                .setSignName("阿里云")
                // 必填，设置模板CODE，应严格按"模板CODE"填写，格式：SMS_88888888
                .setTemplateCode("SMS_123456789")
                // 可选，设置模板参数, 假如模板中存在变量需要替换则为必填项
                .setTemplateParam("{" +
                        "\"code\":\"1234\"," +  //如果还有更多的参数，复制此行即可
                        "}"
                )
                // 可选，设置流水号
                //.setOutId("10086")
                ;

        // 发起请求
        SendSmsResponse response = new Client(config).sendSms(sendSmsRequest);

        // 打印结果
        System.out.println(response.body.code); // 发送成功为OK
        System.out.println(response.body.message); // 发送成功为OK
        System.out.println(response.body.requestId); // 请求ID
        System.out.println(response.body.bizId); // 请求ID
    }
}
