package com.cxhit.pay.wechat.service;

import com.cxhit.pay.wechat.entity.WeChatPayEntity;
import com.github.binarywang.wxpay.bean.request.WxPayRefundV3Request;
import com.github.binarywang.wxpay.bean.request.WxPayUnifiedOrderV3Request;
import com.github.binarywang.wxpay.bean.result.WxPayOrderQueryV3Result;
import com.github.binarywang.wxpay.bean.result.WxPayRefundV3Result;
import com.github.binarywang.wxpay.bean.result.enums.TradeTypeEnum;
import com.github.binarywang.wxpay.config.WxPayConfig;
import com.github.binarywang.wxpay.exception.WxPayException;
import com.github.binarywang.wxpay.service.WxPayService;
import com.github.binarywang.wxpay.service.impl.WxPayServiceImpl;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;


/**
 * 微信支付服务类（只实现V3接口）
 *
 * @author 拾年之璐
 * @since 2022/1/13 17:32
 */
@Service
public class WeChatPayService {

    // 是否启用沙箱环境。微信支付的沙箱环境贼垃圾...好多年不维护...千万不要用。。。
    private static final Boolean SAND_BOX_ENV = false;
    // 支付结果回调地址
    private static final String NOTIFY_URL = "https://open.zxdmy.com/api/wx/pay/notify";


    /**
     * 获取微信支付相关接口服务（后续的几个服务方法，实现了基本的实例）
     * （此接口也可以直接在controller中使用）
     *
     * @return 微信支付服务接口
     */
    public WxPayService getWxPayService() {
        // TODO 此处可以从数据库读取微信支付的相关秘钥、证书等配置信息。但我们这里就直接写入静态数据进行演示
        WeChatPayEntity weChatPayEntity = new WeChatPayEntity();
        // 1. 填充基本信息（商户号与APPID）
        weChatPayEntity.setMchId("1533360691");
        weChatPayEntity.setAppId("wx112dd8e2fead84e2");
        // 2. 填充秘钥信息
        weChatPayEntity.setMchKey("wuqukejiwuqukejiwuqukejiwuqukeji");
        weChatPayEntity.setApiV3Key("wuqukejiwuqukejiwuqukejiwuqukeji");
        // 3. 填充证书路径信息
        weChatPayEntity.setKeyPath("E:\\微信支付\\Cert\\apiclient_cert.p12");
        weChatPayEntity.setPrivateKeyPath("E:\\微信支付\\Cert\\apiclient_key.pem");
        weChatPayEntity.setPrivateCertPath("E:\\微信支付\\Cert\\apiclient_cert.pem");
        // 4. 填充回调URL
        weChatPayEntity.setNotifyUrl(NOTIFY_URL);

        // 以下代码无需修改
        // 生成配置
        WxPayConfig payConfig = new WxPayConfig();
        // 填充基本配置信息
        payConfig.setAppId(StringUtils.trimToNull(weChatPayEntity.getAppId()));
        payConfig.setMchId(StringUtils.trimToNull(weChatPayEntity.getMchId()));
        payConfig.setMchKey(StringUtils.trimToNull(weChatPayEntity.getMchKey()));
        payConfig.setApiV3Key(StringUtils.trimToNull(weChatPayEntity.getApiV3Key()));
        payConfig.setKeyPath(StringUtils.trimToNull(weChatPayEntity.getKeyPath()));
        payConfig.setPrivateCertPath(StringUtils.trimToNull(weChatPayEntity.getPrivateCertPath()));
        payConfig.setPrivateKeyPath(StringUtils.trimToNull(weChatPayEntity.getPrivateKeyPath()));
        payConfig.setNotifyUrl(StringUtils.trimToNull(weChatPayEntity.getNotifyUrl()));
        // 创建配置服务
        WxPayService wxPayService = new WxPayServiceImpl();
        wxPayService.setConfig(payConfig);
        // 可以指定是否使用沙箱环境
        payConfig.setUseSandboxEnv(SAND_BOX_ENV);
        if (SAND_BOX_ENV) {
            try {
                payConfig.setMchKey(wxPayService.getSandboxSignKey());
                wxPayService.setConfig(payConfig);
            } catch (WxPayException e) {
                throw new RuntimeException(e.getMessage());
            }
        }
        // 返回结果
        return wxPayService;
    }

    /**
     * 下单接口（只设置了必填信息）（V3版本）
     *
     * @param tradeType   必填：交易类型：jsapi（含小程序）、app、h5、native
     * @param description 必填：商品描述（商品标题）
     * @param outTradeNo  必填：商家订单号
     * @param total       必填：商品金额（单位：分）
     * @param openId      特殊必填：支付用户的OpenId，JSAPI支付时必填。
     * @return 支付返回结果：{0:Y|N，1:支付结果} <br>
     * 关于支付结果： <br>
     * APP支付、JSAPI支付为[预支付交易会话标识] <br>
     * Native支付为[二维码链接] <br>
     * H5支付为[支付跳转链接]
     */
    public String[] pay(String tradeType, String description, String outTradeNo, Integer total, String openId) {
        // 构建统一下单请求参数对象
        WxPayUnifiedOrderV3Request wxPayUnifiedOrderV3Request = new WxPayUnifiedOrderV3Request();
        // 对象中写入数据
        wxPayUnifiedOrderV3Request
                // 【1】必填信息
                // 商品描述：必填
                .setDescription(description)
                // 商户订单号：必填，同一个商户号下唯一
                .setOutTradeNo(outTradeNo)
                // 通知地址：必填，公网域名必须为https，外网可访问。可不填，通过配置信息读取（但这个组件没写...）
                .setNotifyUrl(NOTIFY_URL)
                // 订单金额：单位（分）
                .setAmount(new WxPayUnifiedOrderV3Request.Amount().setTotal(total))
                // 【2】选填信息
                // 附加信息
                .setAttach("附加信息")
                // 订单优惠标记
                // ...
                .setGoodsTag("ABCD");

        try {
            // 根据请求类型，返回指定类型，其中包含：【3】条件必填信息
            switch (tradeType.toLowerCase()) {
                // Native支付
                case "native":
                    return new String[]{
                            "Y", this.getWxPayService().unifiedOrderV3(TradeTypeEnum.NATIVE, wxPayUnifiedOrderV3Request).getCodeUrl()
                    };
                // JSAPI支付
                case "jsapi":
                    // 用户在直连商户appid下的唯一标识。 下单前需获取到用户的Openid
                    wxPayUnifiedOrderV3Request.setPayer(new WxPayUnifiedOrderV3Request.Payer().setOpenid(openId));
                    return new String[]{
                            "Y", this.getWxPayService().unifiedOrderV3(TradeTypeEnum.JSAPI, wxPayUnifiedOrderV3Request).getPrepayId()
                    };
                // H5支付
                case "h5":
                    wxPayUnifiedOrderV3Request.setSceneInfo(
                            new WxPayUnifiedOrderV3Request.SceneInfo()
                                    // 用户终端IP
                                    .setPayerClientIp("12.34.56.78")
                                    .setH5Info(
                                            new WxPayUnifiedOrderV3Request.H5Info()
                                                    // 场景类型
                                                    .setType("wechat")
                                    )
                    );
                    return new String[]{
                            "Y", this.getWxPayService().unifiedOrderV3(TradeTypeEnum.H5, wxPayUnifiedOrderV3Request).getH5Url()
                    };
                // APP支付
                case "app":
                    return new String[]{
                            "Y", this.getWxPayService().unifiedOrderV3(TradeTypeEnum.APP, wxPayUnifiedOrderV3Request).getPrepayId()
                    };
                default:
                    // throw new RuntimeException("输入的[" + tradeType + "]不合法，只能为native、jsapi、h5、app其一，请核实！");
                    return new String[]{
                            "N", "输入的[" + tradeType + "]不合法，只能为native、jsapi、h5、app其一，请核实！"
                    };
            }
        } catch (WxPayException e) {
            // throw new RuntimeException(e.getMessage());
            return new String[]{
                    "N", e.getMessage()
            };
        }
    }

    /**
     * 订单查询接口（新版V3）
     *
     * @param transactionId 微信订单号
     * @param outTradeNo    商户系统内部的订单号，当没提供微信订单号（transactionId）时需要传
     * @return 订单成功（SUCCESS）：{0:Y，1:商户单号，2:微信单号，3:订单金额(分)，4:交易时间}
     * 订单异常：{0:N，1:订单状态，2:订单描述}
     * 查询错误：{0:E，1:错误代码，2:错误描述}
     */
    public String[] query(String transactionId, String outTradeNo) {
        // 商家单号和微信单号不能同时为空
        if (null == transactionId && null == outTradeNo) {
            return new String[]{
                    "E",
                    "ERROR",
                    "微信单号和商户单号不能同时为空，请检查！"
            };
        }
        try {
            // 执行查询并返回查询结果
            WxPayOrderQueryV3Result wxPayOrderQueryV3Result = this.getWxPayService().queryOrderV3(transactionId, outTradeNo);
            // 如果交易成功，或者在退款中
            if ("SUCCESS".equals(wxPayOrderQueryV3Result.getTradeState()) || "REFUND".equals(wxPayOrderQueryV3Result.getTradeState())) {
                return new String[]{
                        "Y",
                        wxPayOrderQueryV3Result.getOutTradeNo(),
                        wxPayOrderQueryV3Result.getTransactionId(),
                        String.valueOf(wxPayOrderQueryV3Result.getAmount().getTotal()),
                        wxPayOrderQueryV3Result.getSuccessTime()
                };
            } else {
                return new String[]{
                        "N",
                        wxPayOrderQueryV3Result.getTradeState(),
                        wxPayOrderQueryV3Result.getTradeStateDesc()
                };
            }
        } catch (WxPayException e) {
            // throw new RuntimeException(e.getMessage());
            return new String[]{
                    "E",
                    e.getErrCode(),
                    e.getErrCodeDes()
            };
        }
    }


    /**
     * 退款接口（新版V3）
     *
     * @param outTradeNo  商户订单号
     * @param outRefundNo 商户退款单号
     * @param total       订单总金额（单位：分）
     * @param refund      退款金额（单位：分）
     * @return 退款成功或退款处理中：{0:Y，1:商户单号，2:微信单号，3:退款单号，4:订单金额(分)，5:退款金额（分），6:退款时间}<br>
     * 订单异常：{0:N，1:订单状态，2:订单描述}
     * 退款错误：{0:E，1:错误代码，2:错误描述}
     */
    public String[] refund(String outTradeNo, String outRefundNo, Integer total, Integer refund) {
        // 几个参数不能为空
        if (null == outTradeNo || null == outRefundNo || null == total || null == refund) {
            return new String[]{
                    "E",
                    "ERROR",
                    "商户单号、退款单号、订单金额、退款金额均不能为空，请检查！"
            };
        }
        // 构造请求参数
        WxPayRefundV3Request wxPayRefundV3Request = new WxPayRefundV3Request();
        wxPayRefundV3Request
                .setOutTradeNo(outTradeNo)
                .setOutRefundNo(outRefundNo)
                .setAmount(new WxPayRefundV3Request.Amount()
                        .setTotal(total)
                        .setRefund(refund)
                        .setCurrency("CNY")
                );
        try {
            // 执行请求并返回信息
            WxPayRefundV3Result wxPayRefundV3Result = this.getWxPayService().refundV3(wxPayRefundV3Request);
            // 退款处理中 || 退款成功
            if ("PROCESSING".equals(wxPayRefundV3Result.getStatus()) || "SUCCESS".equals(wxPayRefundV3Result.getStatus())) {
                return new String[]{
                        "Y",
                        wxPayRefundV3Result.getOutTradeNo(),
                        wxPayRefundV3Result.getTransactionId(),
                        wxPayRefundV3Result.getOutRefundNo(),
                        String.valueOf(wxPayRefundV3Result.getAmount().getTotal()),
                        String.valueOf(wxPayRefundV3Result.getAmount().getRefund()),
                        wxPayRefundV3Result.getCreateTime()
                };
            } else {
                return new String[]{
                        "N",
                        wxPayRefundV3Result.getStatus(),
                        "退款失败"
                };
            }

        } catch (WxPayException e) {
            // throw new RuntimeException(e.getMessage());
            return new String[]{
                    "E",
                    e.getErrCode(),
                    e.getErrCodeDes()
            };
        }
    }

}
