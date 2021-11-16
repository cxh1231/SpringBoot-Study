package com.cxhit.oss.qiniu.service.impl;

import com.cxhit.oss.qiniu.entity.QiNiuEntity;
import com.cxhit.oss.qiniu.service.IQiNiuService;
import com.qiniu.common.QiniuException;
import com.qiniu.http.Response;
import com.qiniu.storage.Configuration;
import com.qiniu.storage.Region;
import com.qiniu.storage.UploadManager;
import com.qiniu.util.Auth;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;

/**
 * <p>
 * 七牛云相关的服务类，实现类
 * </p>
 *
 * @author 拾年之璐
 * @since 2021/11/15 16:55
 */
@Service
public class QiNiuServiceImpl implements IQiNiuService {


    /**
     * 前端上传用
     *
     * @param fileKey       文件的key，又称文件名
     * @param expireSeconds 过期时间
     * @return 结果
     */
    @Override
    public QiNiuEntity createUploadToken(String fileKey, Long expireSeconds) {
        // 首先从数据库中读取七牛云的基本配置信息，或者从yml文件中读取。
        // 这里就使用静态数据模拟此过程
        QiNiuEntity qiNiu = new QiNiuEntity(
                "8bI0GWOa8888888888",
                "WzYdBI8FL9999999999999999999",
                "test-spring",
                "666.hd-bkt.clouddn.com",
                "http"
        );

        if (fileKey != null)
            System.out.println(fileKey);
        // 生成Token的操作
        Auth auth = Auth.create(qiNiu.getAccessKey(), qiNiu.getSecretKey());
        String upToken = auth.uploadToken(qiNiu.getBucket(), fileKey, expireSeconds, null);
        System.out.println("uploadToken：" + upToken);
        qiNiu.setUploadToken(upToken);
        return qiNiu;
    }

    /**
     * 后端上传使用
     *
     * @param file 文件
     * @return 结果
     * @throws QiniuException 异常
     */
    @Override
    public QiNiuEntity uploadFile(File file) throws QiniuException {
        // 生成Token
        QiNiuEntity qiNiu = this.createUploadToken(file.getName(), 3600L);
        // 配置信息，
        Configuration configuration = new Configuration(
                Region.huadong() // 这里指定区域
        );
        // 构造上传管理类
        UploadManager uploadManager = new UploadManager(configuration);
        // 进行上传
        Response response = uploadManager.put(file, file.getName(), qiNiu.getUploadToken());
        if (response.isOK()) {
//            qiNiu.set
            return qiNiu;
        }
        return null;
    }
}
