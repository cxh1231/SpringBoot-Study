package com.cxhit.pay.alipay.service;

import cn.hutool.core.util.IdUtil;
import com.alipay.easysdk.factory.Factory;
import com.alipay.easysdk.kernel.Config;
import com.alipay.easysdk.kernel.util.ResponseChecker;
import com.alipay.easysdk.payment.app.models.AlipayTradeAppPayResponse;
import com.alipay.easysdk.payment.common.models.AlipayDataDataserviceBillDownloadurlQueryResponse;
import com.alipay.easysdk.payment.common.models.AlipayTradeFastpayRefundQueryResponse;
import com.alipay.easysdk.payment.common.models.AlipayTradeQueryResponse;
import com.alipay.easysdk.payment.common.models.AlipayTradeRefundResponse;
import com.alipay.easysdk.payment.facetoface.models.AlipayTradePrecreateResponse;
import com.alipay.easysdk.payment.page.models.AlipayTradePagePayResponse;
import com.alipay.easysdk.payment.wap.models.AlipayTradeWapPayResponse;
import com.cxhit.pay.alipay.entity.AlipayEntity;
import org.springframework.stereotype.Service;

/**
 * 支付宝支付服务实现类
 *
 * @author 拾年之璐
 * @since 2022/1/12 20:07
 */

@Service
public class AlipayService {

    /**
     * 设置支付宝配置信息
     *
     * @return 配置信息
     */
    private Config getOptions() {
        // TODO 此处可以添加从数据库读取的支付宝支付配置信息。但我们这里就直接写入模拟数据
        AlipayEntity alipayEntity = new AlipayEntity();
        // 1. 填充基本信息
        alipayEntity.setAppId("2021000117696058");
        alipayEntity.setAppName("沙箱测试应用");
        alipayEntity.setMerchantPrivateKey("MIIEvAIBADANBgkqhkiG9w0BAQEFAASCBKYwggSiAgEAAoIBAQCm3yMk46Z9PfHoigWdGYwc74knARabEHOeUpFY8wf6zJVf22u2kNomfr6+amzTzYE31j47jpOSNnB01zv8jiEFBWvGHLqeFU3eNpsen2HUbbP7drl58z9RhliJIamZ1+UXR4vV+3pzzd32oXUSUId35fjEYGiwPwy0LAqXn2kFD7Txk4kiw5sP1xdv4YIEEGWHrOtN9fznt6vNqY9BWyZCDMJ46SqWBqHY7XVemP991JgDa1HdFVsy6SnTMChhDLGcR3ND0+EdsYYv6MeNV89xdeakmXNQUFZ3KPFYnBhMnY01u4Gb6lZAZQA2zrhIS1CSERCsRLyqd/DmX5hX3U7JAgMBAAECggEAXxat7xpuR9XhoHHuCvyA1AhHLlu3Zvsz9xbzzi0G8gpcYDgno+vc86yrwtPgWb00Ef5ajhOL0fiFkn8Hpr3S4QqaxFSgnu5i5epV8FhAJg2xTuzzeiN7hFg64UG2ZH0bBW32qCPaPl0kS6LrlFkhKNh6LmUFotD6yzyBeK8U6BWEIq07KOKB0nW16duQ8BDj9BwLcyOjYLCIUccK0Z3+N5KVr2aJev6LZcNwZuXhdk4s1tGnX2dn9ATGi5xNFBDBS0nnCdAYnIYddNbozWJvSqqHJ0igaSOmO14dZBnpElY4vA3zXTsglV3A6BjMq4ahjG2crHA3ZQvgFXZ2QCY7gQKBgQDc19nWcQHJB3mvEg1DAdXZ5VBoy1Yo2sRAsjzffS7dUcPfEEod1VTgpxolv8awNxmge1wUYhZfGEb+cJacmYFTjyxKzUQreCKaIwqlKd+TsCLsviDvGzsMcKmhWe+JAAXZnTtsv/aVCGzN8ziOulDAkD7KRbyp2Uf2ekOWTEDAOQKBgQDBb8DNCY7pDU9/Dz11RMA0QOdhpWfvmv5ysYhq9wnTcmad3ySJvj83+Bf/OsASqcDY9w8mVKoVSVFhIfAH5ML1f5jSWkGSxyZQnQoh1pBBbG7bUIanTnsDy7hch/iGk/N9UN/kFak6fLBHVlnI+qfniAcJiNy03FaxwclMKg7jEQKBgCxybO9R0zAohv8LPQwNZIL0Ohi9Q9v5G6KBvOqmATad7DQKzT/v3aNRPlv2mwCANnIsIb4gd6wv8Kno8wcVhgfROvLbGSs+hIhNISlYohzRSFYpdetpqZq5WgqVVTZXgNXpZTpf8DrSdUOF/g4LxZDb9ycyneP5TRh4Rv4K3sVRAoGATVoB8Dv1QO6IrpeKjP1cGsklfZ+mK2OAgp7JnXSCImLp9BGKS+ae4yO7fN2idxQYwOoyzbInfXGfMEdg89cfuwo2M0/STv6CLNRPe+6QKwlQXzUZU4gHmyH47E+XK0G4qZEQpuWekXvRBgXay4qoX+a+YaqwD0bZCCYk9+cNovECgYBnJ+ez1B4YcADmNruPhKLiQ0/RkkuaGl8+CqslGhXJ4rqlS9u7uA66EDD3A5Fn1DX89i3wMYIVU0M8PCC2VBz8q4KdIPzRVe+NcFVTdOvDqr4dxrDhpu//HKQJFpwtildby2/MArP7HGDDI6oVhzd70ij/TzprmsRMvxC315+TJA==");
        // 2. 【2选1】证书模式
        alipayEntity.setMerchantCertPath("E:\\支付宝支付\\appCertPublicKey_2021000117696058.crt");
        alipayEntity.setAlipayCertPath("E:\\支付宝支付\\alipayCertPublicKey_RSA2.crt");
        alipayEntity.setAlipayRootCertPath("E:\\支付宝支付\\alipayRootCert.crt");
        // 2. 【2选1】公钥模式
        // alipayEntity.setAlipayPublicKey("");
        // 3.选填信息配置
        //ali payEntity.setNotifyUrl("https://demo.com/alipay/notify");
        alipayEntity.setEncryptKey("cAikHtKWeTYvCvw==");
        // ...
        // 4. 如果是沙箱环境，请配置网关域名为沙箱域名
        // 支付宝沙箱环境地址：https://openhome.alipay.com/platform/appDaily.htm
        alipayEntity.setGatewayHost("openapi.alipaydev.com");

        // 以下内容无需修改
        // 将配置信息写入支付宝支付配置类中
        Config config = new Config();
        config.protocol = alipayEntity.getProtocol();
        config.gatewayHost = alipayEntity.getGatewayHost();
        config.signType = alipayEntity.getSignType();

        config.appId = alipayEntity.getAppId();

        // 为避免私钥随源码泄露，推荐从文件中读取私钥字符串而不是写入源码中
        config.merchantPrivateKey = alipayEntity.getMerchantPrivateKey();
        // 如果三个证书不为空，则优先设置三个证书
        if (null != alipayEntity.getMerchantCertPath() && null != alipayEntity.getAlipayCertPath() && null != alipayEntity.getAlipayRootCertPath()) {
            //注：证书文件路径支持设置为文件系统中的路径或CLASS_PATH中的路径，优先从文件系统中加载，加载失败后会继续尝试从CLASS_PATH中加载
            config.merchantCertPath = alipayEntity.getMerchantCertPath();
            config.alipayCertPath = alipayEntity.getAlipayCertPath();
            config.alipayRootCertPath = alipayEntity.getAlipayRootCertPath();
        } else if (null != alipayEntity.getAlipayPublicKey()) {
            //注：如果采用非证书模式，则无需赋值上面的三个证书路径，改为赋值如下的支付宝公钥字符串即可
            config.alipayPublicKey = alipayEntity.getAlipayPublicKey();
        } else {
            throw new RuntimeException("配置信息有误：证书模式配置不完整或未配置公钥模式，请检查！");
        }

        //可设置异步通知接收服务地址（可选）
        config.notifyUrl = alipayEntity.getNotifyUrl();

        //可设置AES密钥，调用AES加解密相关接口时需要（可选）
        config.encryptKey = alipayEntity.getEncryptKey();

        return config;
    }

    /**
     * 统一支付服务接口（当面付、电脑网站支付、手机网站支付、APP支付）
     *
     * @param payType     支付类型：当面付（faceToFace），电脑网站支付（page），手机网站支付（wap），APP支付（app）
     * @param subject     商品名称
     * @param outTradeNo  商户订单号：商户内唯一
     * @param totalAmount 总金额（单位：元），实例：12.34
     * @param returnUrl   支付成功后跳转页面（只针对网站支付有效）
     * @param quitUrl     支付取消跳转页面（只针对手机网站支付有效）
     * @return 返回结果：当面付为二维码链接，其他均为网站Body代码
     */
    public String pay(String payType, String subject, String outTradeNo, String totalAmount, String returnUrl, String quitUrl) {
        // 必填信息不能为空
        if (null == subject || null == outTradeNo || null == totalAmount) {
//            return new String[]{"E", "ERROR:", "商品名称、商户订单号、商品价格不能为空！"};
            throw new RuntimeException("商品名称、商户订单号、商品价格不能为空！");
        }
        // 1. 设置参数
        Factory.setOptions(this.getOptions());
        try {
            // 2. 调用API发起创建支付
            switch (payType.toLowerCase()) {
                // 当面付
                case "facetoface":
                    // 创建订单
                    AlipayTradePrecreateResponse response = Factory.Payment.FaceToFace().preCreate(subject, outTradeNo, totalAmount);
                    // 成功？
                    if (ResponseChecker.success(response))
                        return response.getQrCode();
                    break;
                // 电脑网站支付
                case "page":
                    if (null == returnUrl) {
                        throw new RuntimeException("电脑网站支付的跳转地址（returnUrl）不能为空！");
                    }
                    // 调用接口
                    AlipayTradePagePayResponse response1 = Factory.Payment.Page().pay(subject, outTradeNo, totalAmount, returnUrl);
                    // 成功？
                    if (ResponseChecker.success(response1))
                        return response1.getBody();
                    break;
                // 手机网站支付
                case "wap":
                    if (null == returnUrl || null == quitUrl) {
                        throw new RuntimeException("手机网站支付的失败退出地址（quitUrl）、成功跳转地址（returnUrl）不能为空！");
                    }
                    AlipayTradeWapPayResponse response2 = Factory.Payment.Wap().pay(subject, outTradeNo, totalAmount, quitUrl, returnUrl);
                    // 成功
                    if (ResponseChecker.success(response2))
                        return response2.getBody();
                    break;
                // APP支付
                case "app":
                    AlipayTradeAppPayResponse response3 = Factory.Payment.App().pay(subject, outTradeNo, totalAmount);
                    // 成功
                    if (ResponseChecker.success(response3))
                        return response3.getBody();
                    break;
                // 其他类型：错误
                default:
                    throw new RuntimeException("输入的支付类型[" + payType + "]不在许可支付类型范围内。许可类型：当面付（faceToFace），电脑网站支付（page），手机网站支付（wap），APP支付（app）");
            }
        } catch (Exception e) {
            System.err.println("调用遭遇异常，原因：" + e.getMessage());
            throw new RuntimeException(e.getMessage());
        }
        return null;
    }

    /**
     * 查询支付订单接口
     *
     * @param tradeNo    特殊可选：支付宝交易号（订单号）
     * @param outTradeNo 特殊可选：商家订单号
     * @return 查询成功：{ 0:Y，1:支付宝交易号，2:商家订单号，3:交易状态，4:订单金额，5:买家ID，6:买家支付宝账号 } <br> 查询失败：{ E，错误代码，错误描述 }
     * @apiNote tradeNo 和 outTradeNo 不能同时为空。同时存在优先取 tradeNo。
     */
    public String[] queryPay(String tradeNo, String outTradeNo) {
        // 判断
        if (null == tradeNo && null == outTradeNo) {
            return new String[]{"E", "ERROR:", "tradeNo 和 outTradeNo 不能同时为空！"};
//            throw new RuntimeException("tradeNo 和 outTradeNo 不能同时为空！");
        }
        // 设置参数
        Factory.setOptions(this.getOptions());
        try {
            // 执行查询
            AlipayTradeQueryResponse response = Factory.Payment.Common().optional("trade_no", tradeNo).query(outTradeNo);
            // 请求成功（即返回信息中没有sub_code）
            if (ResponseChecker.success(response)) {
                return new String[]{
                        "Y",
                        response.tradeNo,
                        response.outTradeNo,
                        response.tradeStatus,
                        response.totalAmount,
                        response.buyerUserId,
                        response.buyerLogonId
                };
            } else {
                return new String[]{"E", response.subCode, response.subMsg};
            }
        } catch (Exception e) {
            System.err.println("调用遭遇异常，原因：" + e.getMessage());
            return new String[]{"E", "ERROR:", e.getMessage()};
//            throw new RuntimeException(e.getMessage());
        }
    }

    /**
     * 退款接口（支持部分退款）
     *
     * @param tradeNo      特殊可选：商户订单号
     * @param outTradeNo   特殊可选：商户订单号
     * @param refundAmount 必填：退款金额
     * @param reason       可选：退款原因
     * @return 本次请求退款成功：{ 0:Y，1:支付宝交易号，2:商家订单号，3:退款请求号，4:总退款金额 } <br>
     * 历史请求退款成功：{ 0:N，1:支付宝交易号，2:商家订单号，3:退款请求号，4:退款金额 } <br>
     * 退款发生错误：{ 0:E，1:错误代码，2:错误描述 }
     * @apiNote tradeNo 和 outTradeNo 不能同时为空。同时存在优先取 tradeNo。
     */
    public String[] refund(String tradeNo, String outTradeNo, String refundAmount, String reason) {
        // 判断
        if (null == tradeNo && null == outTradeNo) {
            return new String[]{"E", "ERROR:", "tradeNo 和 outTradeNo 不能同时为空！"};
//            throw new RuntimeException("tradeNo 和 outTradeNo 不能同时为空！");
        }
        // 设置参数
        Factory.setOptions(this.getOptions());
        try {
            // 生成唯一的款请求号
            String outRequestNo = IdUtil.simpleUUID();
            // 发起请求
            AlipayTradeRefundResponse response = Factory.Payment.Common()
                    // 支付宝交易号
                    .optional("trade_no", tradeNo)
                    // 退款原因
                    .optional("refund_reason", reason)
                    // 退款请求号
                    .optional("out_request_no", outRequestNo)
                    // 执行退款
                    .refund(outTradeNo, refundAmount);
            // 如果请求成功（即返回信息中没有sub_code）
            if (ResponseChecker.success(response)) {
                return new String[]{
                        // 本次请求退款状态（即资金有改变，详情：https://opensupport.alipay.com/support/knowledge/27585/201602348776 ）
                        response.fundChange,
                        response.tradeNo,
                        response.outTradeNo,
                        outRequestNo,
                        response.refundFee
                };
            } else {
                return new String[]{"E", response.subCode, response.subMsg};
            }
        } catch (Exception e) {
            System.err.println("调用遭遇异常，原因：" + e.getMessage());
            return new String[]{"E", "ERROR:", e.getMessage()};
//            throw new RuntimeException(e.getMessage());
        }
    }

    /**
     * 查询退款
     *
     * @param outTradeNo   必填：商户订单号
     * @param outRequestNo 必填：退款请求号
     * @return 已退款：{ Y，支付宝交易号，商家订单号，退款请求号，订单金额，退款金额，退款原因 } <br> 未退款：{ N，描述 } <br> 发生错误：{ E，错误代码，错误描述 }
     */
    public String[] queryRefund(String outTradeNo, String outRequestNo) {
        // 设置参数
        Factory.setOptions(this.getOptions());
        // 如果请求号为空，则表示全额退款，设置请求号位商家订单号
        if (null == outRequestNo) {
            outRequestNo = outTradeNo;
        }
        try {
            // 发起请求
            AlipayTradeFastpayRefundQueryResponse response = Factory.Payment.Common().queryRefund(outTradeNo, outRequestNo);
            // 如果请求成功（即返回信息中没有sub_code）
            if (ResponseChecker.success(response)) {
                // 如果该接口返回了查询数据，则代表退款成功（详情：https://opensupport.alipay.com/support/knowledge/27585/201602348776 ）
                if (null != response.refundAmount) {
                    return new String[]{
                            "Y",
                            response.tradeNo,
                            response.outTradeNo,
                            response.outRequestNo,
                            response.totalAmount,
                            response.refundAmount,
                            response.refundReason
                    };
                } else {
                    return new String[]{
                            "N",
                            "该订单未退款或输入的退款请求号有误，请检查！"
                    };
                }
            } else {
                return new String[]{"E", response.subCode, response.subMsg};
            }
        } catch (Exception e) {
            System.err.println("调用遭遇异常，原因：" + e.getMessage());
            return new String[]{"E", "ERROR:", e.getMessage()};
//            throw new RuntimeException(e.getMessage());
        }
    }

    /**
     * 下载对账单（不能查询当天或当月的)
     * https://developer.aliyun.com/article/710922
     *
     * @param date 必填：交易的具体日期（如2022-01-01）或月份（2021-12）
     * @return 获取成功：{Y，下载URL} <br> 发生错误：{ E，错误代码，错误描述 }
     */
    public String[] downloadBill(String date) {
        // 设置参数
        Factory.setOptions(this.getOptions());
        try {
            // 发送请求
            AlipayDataDataserviceBillDownloadurlQueryResponse response = Factory.Payment.Common().downloadBill("trade", date);

            // 如果请求成功（即返回信息中没有sub_code）
            if (ResponseChecker.success(response)) {
                return new String[]{
                        "Y",
                        response.billDownloadUrl
                };
            } else {
                return new String[]{"E", response.subCode, response.subMsg};
            }

        } catch (Exception e) {
            System.err.println("调用遭遇异常，原因：" + e.getMessage());
            return new String[]{"E", "ERROR:", e.getMessage()};
//            throw new RuntimeException(e.getMessage());
        }
    }

}
