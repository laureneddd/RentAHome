package com.rentahome.service;

import java.util.List;

import com.rentahome.entity.Reply;

public interface ReplyService {


    public Reply addReply(Reply reply);

    public List<Reply> findAllReplies();

    public List<Reply> findByMessageId(int messageId);
    
}
