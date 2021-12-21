package com.cxhit.websocket;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 描述
 * </p>
 *
 * @author 拾年之璐
 * @since 2021/12/21 18:26
 */
@RestController
@SpringBootApplication
public class StudyWebSocketApplication {

    public static void main(String[] args) {
        SpringApplication.run(StudyWebSocketApplication.class, args);
    }
}
