package com.cxhit.oss.qiniu;

import com.qiniu.util.Auth;
import com.qiniu.util.StringMap;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * <p>
 * 描述
 * </p>
 *
 * @author 拾年之璐
 * @since 2021/11/15 16:24
 */
@SpringBootTest
public class QiniuTest {

    @Test
    public void Test1() {
        String accessKey = "888";
        String secretKey = "888";
        String bucket = "test-spring";
        String key = "file-key";

//        Auth auth = Auth.create(accessKey, secretKey);
//        String upToken = auth.uploadToken(bucket, key);
//        System.out.println(upToken);
        Auth auth = Auth.create(accessKey, secretKey);
        StringMap putPolicy = new StringMap();
        putPolicy.put("callbackUrl", "http://api.example.com/qiniu/upload/callback");
        putPolicy.put("callbackBody", "key=$(key)&hash=$(etag)&bucket=$(bucket)&fsize=$(fsize)");
        long expireSeconds = 3600;
        String upToken = auth.uploadToken(bucket, null, expireSeconds, putPolicy);
        System.out.println(upToken);
    }
}
