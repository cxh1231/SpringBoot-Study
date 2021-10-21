package com.cxhit.mybatisplus;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 项目启动入口
 *
 * @author 拾年之璐
 */
@SpringBootApplication
@MapperScan({"com.cxhit.mybatisplus.system.mapper", ""})
public class StudyMybatisPlusApplication {

    public static void main(String[] args) {
        SpringApplication.run(StudyMybatisPlusApplication.class, args);
    }

}
