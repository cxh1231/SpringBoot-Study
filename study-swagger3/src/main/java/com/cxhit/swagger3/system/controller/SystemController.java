package com.cxhit.swagger3.system.controller;

import com.cxhit.swagger3.system.entity.SysUser;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * <p>
 * 描述
 * </p>
 *
 * @author 拾年之璐
 * @since 2021/11/13 20:17
 */
@Tag(name = "系统用户控制器")
@Controller
@RequestMapping("/system")
public class SystemController {

    /**
     * 保存用户信息
     *
     * @param user 用户信息实体
     * @return 保存成功后的用户信息
     */
    @Operation(summary = "保存用户信息",
            description = "新增用户信息接口",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "保存成功信息",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(
                                            description = "保存成功的用户信息实体",
                                            implementation = SysUser.class, required = true)
                            )),
                    @ApiResponse(
                            responseCode = "400",
                            description = "保存至数据库失败信息",
                            content = @Content(mediaType = "application/json"))},
            security = @SecurityRequirement(name = "需要认证"))
    @GetMapping(value = "/save")
    @ResponseBody
    public SysUser save(@Parameter(description = "用户信息实体") SysUser user) {
        System.out.println(user);
        return user;
    }

    /**
     * 根据ID查询用户信息
     *
     * @param id 用户ID
     * @return 用户信息
     */
    @Operation(summary = "获取用户信息接口",
            description = "根据ID查询用户信息",
            responses = {
                    @ApiResponse(responseCode = "200", description = "获取成功", content = @Content(mediaType = "application/json")),
                    @ApiResponse(responseCode = "400", description = "获取失败", content = @Content(mediaType = "application/json"))},
            security = @SecurityRequirement(name = "需要认证"))
    @GetMapping(value = "/get/{id}")
    @ResponseBody
    public String getOne(@PathVariable String id) {
        SysUser user = new SysUser();
        user.setNickname("昵称");
        if (null != user) {
            return user.toString();
        } else {
            return "用户信息不存在";
        }
    }
}
