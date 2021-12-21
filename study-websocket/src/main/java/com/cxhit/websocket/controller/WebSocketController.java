package com.cxhit.websocket.controller;


import com.cxhit.websocket.entity.MessageEntity;

import com.cxhit.websocket.service.MessageService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;
import java.util.Date;


/**
 * <p>
 * 描述
 * </p>
 *
 * @author 拾年之璐
 * @since 2021/12/21 18:42
 */
@Controller
public class WebSocketController {

    private final MessageService messageService;

    @Autowired
    public WebSocketController(MessageService messageService) {
        this.messageService = messageService;
    }

    // 这里的 @MessageMapping 可以当成 @RequestMapping,
    // 当有信息(sendMsg 方法中的 msg 参数即为客服端发送的信息)发送到 /sendMsg 时
    // 会在这里进行处理
    @MessageMapping("/sendMsg")
    public void sendMsg(MessageEntity messageEntity) {

        messageService.sendToUser(messageEntity);
    }

    @RequestMapping("/index")
    public String index() {
        return "index";
    }

    @RequestMapping("/index2")
    public String index2() {
        return "index2";
    }

    @RequestMapping("/chat")
    public String chat() {
        return "chat";
    }

    @RequestMapping("/chat2")
    public String chat2() {
        return "chat2";
    }
}
