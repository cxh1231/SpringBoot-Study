package com.cxhit.mybatisplus.system.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cxhit.mybatisplus.system.entity.SysUser;
import com.cxhit.mybatisplus.system.mapper.SysUserMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.DigestUtils;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * <p>
 * 描述
 * </p>
 *
 * @author 拾年之璐
 * @since 2021-10-21 0021 20:12
 */
@SpringBootTest
class ISysUserServiceTest {

    @Autowired
    private SysUserMapper userMapper;

    @Autowired
    private ISysUserService userService;

    /**
     * 测试添加一个用户
     */
    @Test
    void createOneTest() {
        SysUser user = new SysUser();
        user.setNickname("测试用户名");
        user.setEmail("10066@domain.com");
        user.setPassword("sfdnfjdsfhdsjk");
        user.setIsDelete(0);
        if (userService.save(user)) {
            System.out.println("新增用户成功");
            System.out.println(user);
        } else {
            System.out.println("新增用户失败");
        }
    }


    /**
     * 测试添加一系列数据
     */
    @Test
    void createListTest() {
        // 定义用户列表
        List<SysUser> userList = new ArrayList<>();
        // 随机生成 5个用户
        for (int i = 101; i <= 105; i++) {
            // 定义用户实体
            SysUser user = new SysUser();
            // 随机填充数据
            user.setNickname("用户" + i);
            user.setEmail("10" + i + "@domain.com");
            user.setPassword(DigestUtils.md5DigestAsHex(("password" + i).getBytes()));
            // 添加至列表
            userList.add(user);
        }
        // 打印用户列表
        System.out.println(userList);
        // 执行添加
        if (userService.saveBatch(userList)) {
            System.out.println("新增用户成功");
        } else {
            System.out.println("新增用户失败");
        }
    }

    /**
     * 更新一条数据
     */
    @Test
    void updateOneTest() {
        // 定义用户实体
        SysUser user = new SysUser();
        user.setId(10018);
        user.setNickname("李四");
        // 执行修改
        if (userService.updateById(user)) {
            System.out.println("修改用户信息成功");
        } else {
            System.out.println("修改用户信息失败");
        }
    }


    /**
     * 逻辑删除测试
     */
    @Test
    void logicDeleteTest() {
        if (userService.removeById(10018)) {
            System.out.println("删除成功");
        } else {
            System.out.println("删除失败");
        }
    }

    /**
     * 查询单个用户测试
     */
    @Test
    void getOneTest() {
        SysUser user = userService.getById(10018);
        if (null != user) {
            System.out.println(user);
        } else {
            System.out.println("查无此用户");
        }
    }

    /**
     * 测试分页删除
     */
    @Test
    void pageTest() {
        // 进行分页查询
        Page<SysUser> userPage = userService.page(new Page<>(1, 5), null);
        // 获取查询的用户列表
        List<SysUser> userList = userPage.getRecords();
        System.out.println(userList);
        System.out.println("当前页：" + userPage.getCurrent());
        System.out.println("当前页显示条数：" + userPage.getSize());
        System.out.println("总数：" + userPage.getTotal());
    }

    /**
     * 复杂查询
     * 更多内容，请访问官网：https://mp.baomidou.com/guide/wrapper.html
     */
    void selectDIY(Boolean condition) {
        QueryWrapper<SysUser> wrapper = new QueryWrapper<>();
        wrapper
                // 指定某个字段，等于
                .eq("status", 0)
                // 指定某个字段不等于，其开启条件是condition=True
                .ne(condition, "is_delete", 0)
                // 排序
                .orderByDesc("id");
        // 执行
        List<SysUser> userList = userMapper.selectList(wrapper);

    }
}





