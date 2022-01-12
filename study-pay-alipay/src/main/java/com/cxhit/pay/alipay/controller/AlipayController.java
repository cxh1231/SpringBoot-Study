package com.cxhit.pay.alipay.controller;

import cn.hutool.core.lang.Dict;
import cn.hutool.core.util.IdUtil;
import com.cxhit.pay.alipay.service.AlipayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 支付宝支付控制器
 *
 * @author 拾年之璐
 * @since 2022/1/12 20:45
 */
@Controller
@RequestMapping("")
public class AlipayController {

    @Autowired
    private AlipayService alipayService;

    @GetMapping("")
    public String index() {
        return "index";
    }

    /**
     * 支付接口
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
        // 发起支付请求
        String qrcode = alipayService.pay("facetoface", title, outTradeNo, price, null, null);
        // 返回结果
        return Dict.create().set("code", 200).set("qrcode", qrcode).set("outTradeNo", outTradeNo);
    }

    /**
     * 电脑网站支付
     *
     * @param title 商品标题
     * @param price 价格
     * @return 输出结果
     */
    @GetMapping(value = "/pay/page")
    @ResponseBody
    public String payWeb(String title, String price) {
        // 生成商家单号
        String outTradeNo = IdUtil.simpleUUID();
        // 发起支付请求
        String body = alipayService.pay("page", title, outTradeNo, price, "http://localhost:9011/pay/page/return", null);
        // 返回结果
        return body;
    }

    /**
     * 电脑网站支付
     *
     * @param out_trade_no 商户单号
     * @param trade_no     支付宝交易号
     * @return 输出结果
     */
    @GetMapping(value = "/pay/page/return")
    @ResponseBody
    public Dict payWebResult(String out_trade_no, String trade_no) {
        // 查询支付结果
        String[] result = alipayService.queryPay(trade_no, out_trade_no);
        // 返回结果
        if ("Y".equals(result[0])) {
            return Dict.create()
                    .set("code", 200)
                    .set("msg", "查询成功！")
                    .set("tradeNo", "支付宝交易号：" + result[1])
                    .set("outTradeNo", "商家订单号：" + result[2])
                    .set("tradeStatus", "交易状态：" + result[3])
                    .set("totalAmount", "订单金额：" + result[4])
                    .set("user", "买家账号：" + result[6])
                    ;
        }
        return Dict.create().set("code", 500).set("msg", "查询失败：" + result[1] + result[2]);
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
        String[] result = alipayService.refund(null, outTradeNo, amount, "用户取消退款");
        if ("Y".equals(result[0])) {
            return Dict.create().set("code", 200).set("msg", "退款成功，退款金额：" + result[4]);
        } else if ("N".equals(result[0])) {
            return Dict.create().set("code", 200).set("msg", "该订单早已退款成功！退款金额：" + result[4]);
        } else
            return Dict.create().set("code", 500).set("msg", "退款错误：" + result[1] + result[2]);
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
        String[] result = alipayService.queryPay(null, outTradeNo);
        if ("Y".equals(result[0])) {
            return Dict.create().set("code", 200).set("msg", "查询成功！" +
                    " 支付宝交易号：" + result[1] +
                    " 交易状态：" + result[3] +
                    " 订单金额：" + result[4] +
                    " 买家账号：" + result[6]);
        }
        return Dict.create().set("code", 500).set("msg", "查询失败：" + result[1] + result[2]);
    }

}
