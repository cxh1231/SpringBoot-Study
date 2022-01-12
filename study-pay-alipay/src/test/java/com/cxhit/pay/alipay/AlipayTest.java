package com.cxhit.pay.alipay;

import cn.hutool.core.util.IdUtil;
import com.cxhit.pay.alipay.service.AlipayService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;

/**
 * 支付宝支付测试
 *
 * @author 拾年之璐
 * @since 2022/1/12 20:24
 */
@SpringBootTest
public class AlipayTest {

    @Autowired
    private AlipayService alipayService;

    /**
     * 支付测试
     */
    @Test
    void payTest() {
        String outTradeNo = IdUtil.simpleUUID();
        System.out.println(outTradeNo);
        // f9bd5856549b47b684284816ac324f8c
        System.out.println(
                alipayService.pay("faceToFace", "测试商品名称", outTradeNo, "6666", "https://www.zxdmy.com", "https://www.zxdmy.com"));
        // https://qr.alipay.com/bax06441nyplipons00i00f7
    }

    /**
     * 查询测试
     */
    @Test
    void queryTest() {
        System.out.println(
                Arrays.toString(
                        alipayService.queryPay("2022011122001428220502263620", "f9bd5856549b47b684284816ac324f8c")));
        // [Y, 2022011122001428220502263620, f9bd5856549b47b684284816ac324f8c, TRADE_SUCCESS, 666.00, 2088622956328227, aev***@sandbox.com]
    }

    /**
     * 退款测试
     */
    @Test
    void refundTest() {
        System.out.println(
                Arrays.toString(
                        alipayService.refund("2022011122001428220502263620", null, "20.00", "不想要了")));
        // [Y, 2022011122001428220502263620, f9bd5856549b47b684284816ac324f8c, da7486bdbfe644c48c815af84af423cd, 10.00]
        // [Y, 2022011122001428220502263620, f9bd5856549b47b684284816ac324f8c, 1dde059885134947948197f3b3268cf0, 30.00]
    }

    /**
     * 查询退款测试
     */
    @Test
    void queryRefundTest() {
        System.out.println(
                Arrays.toString(
                        alipayService.queryRefund("f9bd5856549b47b684284816ac324f8c0", null)));
        // [Y, 2022011122001428220502263620, f9bd5856549b47b684284816ac324f8c, da7486bdbfe644c48c815af84af423cd, 666.00, 10.00, null]
        // [Y, 2022011122001428220502263620, f9bd5856549b47b684284816ac324f8c, 1dde059885134947948197f3b3268cf0, 666.00, 20.00, null]
        // [E, ACQ.TRADE_NOT_EXIST, 交易不存在]
    }

    /**
     * 查询下载对账单
     */
    @Test
    void downLoadTest() {
        System.out.println(
                Arrays.toString(
                        alipayService.downloadBill("2021-12")));
    }
}
