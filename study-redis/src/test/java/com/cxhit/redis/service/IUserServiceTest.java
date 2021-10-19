package com.cxhit.redis.service;

import com.cxhit.redis.entity.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

/**
 * <p>
 * redis注解测试
 * </p>
 *
 * @author 拾年之璐
 * @since 2021-10-19 0019 19:16
 */
@SpringBootTest
class IUserServiceTest {

    @Autowired
    private IUserService userService;

    /**
     * 保存测试
     */
    @Test
    void saveTest() {
        // 初始：先执行一次保存（或更新），使得Redis中有该缓存
        User user1 = new User();
        user1.setId(10011);
        userService.saveUser(user1);  // 这里会输入日志 [操作：将id=10011 的用户信息保存至数据库]
        // 然后：执行查询
        User user2 = userService.getUser(10011);  //这里不会输入日志。证明保存缓存生效。
    }

    /**
     * 读取测试
     */
    @Test
    void getTest() {
        // 初始，Redis中无数据，执行查询操作，会执行getUser()方法
        User user1 = userService.getUser(10022);// 这里会输出日志 [操作：从数据库中查询id=10022 的用户]
        // 再次查询：Redis中已经有了缓存，getUser()方法不会被执行，无日志输出
        User user2 = userService.getUser(10022); //这里不会输出日志
    }

    /**
     * 删除测试
     */
    @Test
    void deleteTest() {
        // 初始：Redis中无数据，执行查询操作，保证Redis中有缓存数据
        userService.getUser(10033); // 这里会输出日志：[操作：从数据库中查询id=10033 的用户]
        // 然后：再次执行查询，没有输出日志，证明Redis中有key=10033的缓存数据
        userService.getUser(10033); // 这里不会输出日志。
        // 最后：执行删除，使用Redis可视化工具查看Redis，没有key=10033的缓存数据，证明删除缓存生效
        userService.deleteUser(10033);
    }
}