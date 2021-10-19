package com.cxhit.redis.service.impl;

import com.cxhit.redis.entity.User;
import com.cxhit.redis.service.IUserService;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

/**
 * <p>
 * IUserService服务实现类
 * </p>
 *
 * @author 拾年之璐
 * @since 2021-10-19 0019 19:09
 */
@Service
public class UserServiceImpl implements IUserService {

    @Override
    @CachePut(value = "user", key = "#user.id")
    public User saveUser(User user) {
        System.out.println("操作：将id=" + user.getId().toString() + " 的用户信息保存至数据库");
        // TODO 保存至数据库的具体实现
        return user;
    }

    @Override
    @Cacheable(value = "user", key = "#id")
    public User getUser(Integer id) {
        System.out.println("操作：从数据库中查询id=" + id.toString() + " 的用户");
        // TODO 从数据库中查询的具体实现
        // 这里就固定使用 User{id=id, nickname='李四'} 测试
        User user = new User();
        user.setId(id);
        user.setNickname("李四");
        return user;
    }

    @Override
    @CacheEvict(value = "user", key = "#id")
    public void deleteUser(Integer id) {
        System.out.println("操作：从数据库中删除id=" + id.toString() + " 的用户");
        // TODO 保存至数据库的具体实现
    }
}













