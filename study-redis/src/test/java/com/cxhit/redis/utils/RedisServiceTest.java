package com.cxhit.redis.utils;

import com.cxhit.redis.entity.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * <p>
 * redis测试
 * </p>
 *
 * @author 拾年之璐
 * @since 2021-10-19 0019 17:28
 */
@SpringBootTest
class RedisServiceTest {

    @Autowired
    private RedisService redisService;

    @Test
    void redisTest() {
        // 写入Redis测试：随机key，永久的值
        redisService.set(UUID.randomUUID().toString(), UUID.randomUUID());
        // 写入redis测试：随机key，带过期时间
        redisService.set(UUID.randomUUID().toString(), UUID.randomUUID(), 100L);

        // 写入Redis测试：指定key，永久的值
        redisService.set("10000", "这个值是永久的");
        // 写入Redis测试：指定key，有过期时间
        redisService.set("10001", "这个值是有过期时间的，过期时间120S", 120L);

        // 读取redis测试：获取存在的值
        String name = (String) redisService.get("10000");
        System.out.println(name);  // 输出：这个值是永久的
        // 读取redis测试：获取不存在的值
        String name2 = (String) redisService.get("abcdefg");
        System.out.println(name2);  //输出：null

        // 获取过期时间测试：永久的值
        Long time = redisService.getExpire("10000");
        System.out.println(time);  // 输出：-1
        // 获取过期时间测试：定时
        Long time2 = redisService.getExpire("10001");
        System.out.println(time2); // 输出：（一个正数）
        // 获取过期时间测试：不存在
        Long time3 = redisService.getExpire("534564564564");
        System.out.println(time3); // 输出：-2

        // key值存在测试
        if (redisService.hasKey("10001")) {
            System.out.println("key为10001的缓存存在");
        }
        // key值不存在测试
        if (redisService.hasKey("11111")) {
            System.out.println("key为11111的缓存存在");
        } else {
            System.out.println("key为11111的缓存不存在");  // 输出：11111不存在
        }

        // 写入实体测试
        User user = new User();
        user.setId(10006);
        user.setNickname("张三");
        redisService.set("10006", user);
        // 获取实体测试
        User userGet = (User) redisService.get("10006");
        System.out.println(userGet.getNickname()); // 输出：张三

        // 写入列表测试
        List<User> userList = new ArrayList<>();
        // 重复写入3条记录
        userList.add(user);
        userList.add(user);
        userList.add(user);
        redisService.set("userList", (Serializable) userList);

        // 读取列表测试
        List<User> userListGet = (List<User>) redisService.get("userList");
        for (User user1 : userListGet) {
            System.out.println(user1);
        }
    }
}