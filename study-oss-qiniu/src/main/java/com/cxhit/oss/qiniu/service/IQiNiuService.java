package com.cxhit.oss.qiniu.service;

import com.cxhit.oss.qiniu.entity.QiNiuEntity;
import com.qiniu.common.QiniuException;

import java.io.File;

/**
 * <p>
 * 七牛云相关的服务
 * </p>
 *
 * @author 拾年之璐
 * @since 2021/11/15 16:55
 */
public interface IQiNiuService {

    /**
     * 前端上传：生成上传的Token
     *
     * @param fileKey       文件的key，又称文件名
     * @param expireSeconds 过期时间
     * @return 上传文件的Token信息
     */
    QiNiuEntity createUploadToken(String fileKey, Long expireSeconds);

    /**
     * 后端上传
     * @param file 文件
     * @return 上传结果
     * @throws QiniuException 异常
     */
    QiNiuEntity uploadFile(File file) throws QiniuException;

}
