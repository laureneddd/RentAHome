package com.rentahome.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.rentahome.entity.Reply;

@Repository
public interface  ReplyRepository  extends JpaRepository<Reply, Integer>{
    
    public List<Reply> findByMessageMessageId(int messageId);
}
