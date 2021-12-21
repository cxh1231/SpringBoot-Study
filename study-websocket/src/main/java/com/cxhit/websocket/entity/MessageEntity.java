package com.cxhit.websocket.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * <p>
 * 描述
 * </p>
 *
 * @author 拾年之璐
 * @since 2021/12/21 19:57
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class MessageEntity {

    private Long from;
    private Long to;
    private String message;
    private Date time;
}
