package com.cxhit.swagger3.client.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.v3.oas.annotations.media.Schema;


import java.io.Serializable;

/**
 * <p>
 * 用户表
 * </p>
 *
 * @author 拾年之璐
 * @since 2021-09-01
 */
@Schema(name = "SysUser", description = "系统用户实体")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ClientUser implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 用户主键
     */
    @Schema(name = "id", description = "用户主键")
    private Integer id;

    /**
     * 用户昵称
     */
    @Schema(name = "nickname", description = "用户昵称")
    private String nickname;

    /**
     * 用户头像
     */
    @Schema(name = "avatar", description = "用户头像")
    private String avatar;

    /**
     * 用户性别（0-未知 | 1-男 | 2-女）
     */
    @Schema(name = "sex", description = "用户性别")
    private Integer sex;

    /**
     * 用户邮箱
     */
    @Schema(name = "email", description = "用户邮箱账号")
    private String email;

    /**
     * 用户手机号
     */
    @Schema(name = "phone", description = "用户手机号")
    private String phone;

    /**
     * 用户密码
     */
    @JsonIgnore
    @Schema(name = "password", description = "用户密码")
    private String password;

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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "ClientUser{" +
                "id=" + id +
                ", nickname='" + nickname + '\'' +
                ", avatar='" + avatar + '\'' +
                ", sex=" + sex +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
