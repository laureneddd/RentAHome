package com.rentahome.dto;

import java.util.Date;

import com.rentahome.entity.Message;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class ReplyDTO {
    
    private int replyId;
    private Message message;
    private String content;
    private Date time;
}
