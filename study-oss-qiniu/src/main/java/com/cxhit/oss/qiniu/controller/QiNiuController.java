package com.cxhit.oss.qiniu.controller;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.lang.Dict;
import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONObject;
import com.cxhit.oss.qiniu.entity.QiNiuEntity;
import com.cxhit.oss.qiniu.service.IQiNiuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.Dictionary;

/**
 * <p>
 * 描述
 * </p>
 *
 * @author 拾年之璐
 * @since 2021/11/15 19:14
 */
@Controller
@RequestMapping("/qiniu")
public class QiNiuController {

    @Autowired
    private IQiNiuService qiNiuService;

    /**
     * 生成上传图片的token等信息【注意：使用GET方式请求此接口】
     *
     * @return Token信息
     */
    @GetMapping("createUploadToken")
    @ResponseBody
    public Dict defaultToken() {
        QiNiuEntity qiNiu = qiNiuService.createUploadToken(null, 3600L);
        return Dict.create().set("code", 200).set("token", qiNiu.getUploadToken()).set("domain", qiNiu.getDomain()).set("protocol", qiNiu.getProtocol());
    }

    /**
     * 生成上传图片的token等信息【注意：使用POST方式请求此接口】
     *
     * @return Token信息
     */
    @PostMapping("createUploadToken")
    @ResponseBody
    public Dict setFileNameToken(@RequestParam("fileName") String fileName) {
        QiNiuEntity qiNiu = qiNiuService.createUploadToken(fileName, 3600L);
        return Dict.create().set("code", 200).set("token", qiNiu.getUploadToken()).set("domain", qiNiu.getDomain()).set("protocol", qiNiu.getProtocol());
    }

    /**
     * 后端上传接口
     *
     * @return Token信息
     */
    @PostMapping("upload")
    @ResponseBody
    public Dict uploadFile(@RequestParam("file") MultipartFile file) {
        if (file.isEmpty()) {
            return Dict.create().set("code", 400).set("message", "上传的文件为空");
        }
        // 构造文件名
        String fileName = file.getOriginalFilename();
        String fileType = StrUtil.subAfter(fileName, ".", true);
        String newFileName = System.currentTimeMillis() + "." + fileType;
        // 临时文件保存的路径和文件名，这个路径要存在！
        String tempPath = "D:/temp/" + newFileName;
        // 生成临时文件
        File newFile = new File(tempPath);
        try {
            // 复制一份文件
            file.transferTo(newFile);
            // 将新生成文件上传至七牛云
            QiNiuEntity qiNiu = qiNiuService.uploadFile(newFile);
            // 删除复制后的文件
            FileUtil.del(newFile);

            if (qiNiu != null) {
                // 返回结果
                return Dict.create().set("code", 200).set("key", newFileName).set("domain", qiNiu.getDomain()).set("protocol", qiNiu.getProtocol());
            }
            // 返回结果
            return Dict.create().set("code", 400).set("message", "上传失败，请重试");
        } catch (Exception e) {
            return Dict.create().set("code", 400).set("message", "上传失败");
        }


    }

}
