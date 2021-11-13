package com.cxhit.swagger3;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RestController;

/**
 * 项目启动的入口
 *
 * @author 拾年之璐
 */
@RestController
@SpringBootApplication
public class StudySwagger3Application {

    public static void main(String[] args) {
        SpringApplication.run(StudySwagger3Application.class, args);
    }

}
