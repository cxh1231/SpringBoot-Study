package com.cxhit.pay.wechat.controller;

import cn.hutool.core.lang.Dict;
import cn.hutool.core.util.IdUtil;
import com.cxhit.pay.wechat.service.WeChatPayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

/**
 * 微信支付控制类
 *
 * @author 拾年之璐
 * @since 2022/1/13 18:04
 */
@Controller
@RequestMapping("")
public class WeChatPayController {

    @Autowired
    private WeChatPayService weChatPayService;

    /**
     * 首页
     *
     * @return 首页
     */
    @GetMapping("")
    public String index() {
        return "index";
    }

    /**
     * 微信支付接口
     *
     * @param title 商品名称
     * @param price 商品价格
     * @return 返回结果
     */
    @PostMapping(value = "/pay")
    @ResponseBody
    public Dict pay(String title, String price) {
        // 生成商家单号
        String outTradeNo = IdUtil.simpleUUID();
        // 支付宝价格转换成浮点数后，乘100，再取整，得以分为单位的价格
        Integer total = (int) (Float.parseFloat(price) * 100);
        // 发起支付请求
        String[] result = weChatPayService.pay("native", title, outTradeNo, total, null);
        // 返回结果：下单成功
        if ("Y".equals(result[0])) {
            return Dict.create().set("code", 200).set("qrcode", result[1]).set("outTradeNo", outTradeNo);
        }
        // 下单失败
        else {
            return Dict.create().set("code", 500).set("msg", result[1]);
        }
    }

    /**
     * 退款接口
     *
     * @param outTradeNo 商家单号
     * @param amount     退款金额（不能大于总金额）
     * @return 退款结果
     */
    @PostMapping(value = "/refund")
    @ResponseBody
    public Dict refund(String outTradeNo, String amount) {
        // 生成商家退款单号
        String outRefundNo = IdUtil.simpleUUID();
        // 查询订单金额
        String[] query = weChatPayService.query(null, outTradeNo);
        // 查询成功
        if (query[0].equals("Y")) {
            int total = Integer.parseInt(query[3]);
            // 支付宝价格转换成浮点数后，乘100，再取整，得以分为单位的价格
            int refund = (int) (Float.parseFloat(amount) * 100);
            if (refund > total) {
                return Dict.create().set("code", 500).set("msg", "退款错误：退款金额不能大于支付金额！");
            }
            // 发起退款
            String[] result = weChatPayService.refund(outTradeNo, outRefundNo, total, refund);
            // 退款成功
            if (result[0].equals("Y")) {
                return Dict.create().set("code", 200).set("msg", "退款进行中，稍后到账！" +
                        " <br>商户单号：" + result[1] +
                        " <br>退款单号：" + result[3] +
                        " <br>订单金额：" + result[4] + "分" +
                        " <br>退款金额：" + result[5] + "分" +
                        " <br>退款时间：" + result[6]
                );
            }
            // 退款失败
            else if (result[0].equals("N")) {
                return Dict.create().set("code", 500).set("msg", "退款失败：" + result[1] + result[2]);
            }
            // 退款发生错误
            else {
                return Dict.create().set("code", 500).set("msg", "退款错误：" + result[1] + result[2]);
            }
        }
        // 查询失败
        else {
            return Dict.create().set("code", 500).set("msg", "退款错误：" + query[1] + query[2]);
        }
    }

    /**
     * 查询接口
     *
     * @param outTradeNo 商家订单号
     * @return 结果
     */
    @PostMapping(value = "/query")
    @ResponseBody
    public Dict query(String outTradeNo) {
        // 查询订单
        String[] query = weChatPayService.query(null, outTradeNo);
        // 查询成功
        if (query[0].equals("Y")) {
            return Dict.create().set("code", 200).set("msg", "查询成功！" +
                    " <br>商户单号：" + query[1] +
                    " <br>微信单号：" + query[2] +
                    " <br>订单金额：" + query[3] + "分" +
                    " <br>交易时间：" + query[4] +
                    " <br>交易状态：" + query[5] +
                    " <br>交易描述：" + query[6]
            );
        }
        // 查询失败
        else if (query[0].equals("N")) {
            return Dict.create().set("code", 500).set("msg", "查询结果：" + query[1] + query[2]);
        }
        // 查询发送异常
        else {
            return Dict.create().set("code", 500).set("msg", "查询失败：" + query[1] + query[2]);
        }
    }

}
