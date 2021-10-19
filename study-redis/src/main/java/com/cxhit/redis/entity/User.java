package com.cxhit.redis.entity;

import java.io.Serializable;

/**
 * <p>
 * 用户实体类，用于测试使用
 * </p>
 *
 * @author 拾年之璐
 * @since 2021-10-19 0019 17:30
 */
public class User implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 用户主键
     */
    private Integer id;

    /**
     * 用户昵称
     */
    private String nickname;

    /**
     * 用户头像
     */
    private String avatar;

    /**
     * 用户性别（0-未知 | 1-男 | 2-女）
     */
    private Integer sex;

    /**
     * 用户邮箱
     */
    private String email;

    /**
     * 用户手机号
     */
    private String phone;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", nickname='" + nickname + '\'' +
                ", avatar='" + avatar + '\'' +
                ", sex=" + sex +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                '}';
    }
}
