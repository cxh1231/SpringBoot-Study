package com.cxhit.redis.service;

import com.cxhit.redis.entity.User;

/**
 * <p>
 * 描述
 * </p>
 *
 * @author 拾年之璐
 * @since 2021-10-19 0019 19:09
 */
public interface IUserService {

    /**
     * 保存用户
     *
     * @param user 用户对象
     * @return 操作结果
     */
    User saveUser(User user);

    /**
     * 获取用户
     *
     * @param id key值
     * @return 返回结果
     */
    User getUser(Integer id);

    /**
     * 删除用户
     *
     * @param id key值
     */
    void deleteUser(Integer id);
}
