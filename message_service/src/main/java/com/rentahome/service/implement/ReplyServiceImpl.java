// package com.rentahome.service.implement;

// import java.util.List;

// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.stereotype.Service;

// import com.rentahome.entity.Reply;
// import com.rentahome.repository.ReplyRepository;
// import com.rentahome.service.ReplyService;

// @Service
// public class ReplyServiceImpl implements ReplyService{
        
//     @Autowired
//     ReplyRepository replyRepository;

//     @Override
//     public Reply addReply(Reply reply) {

//         return replyRepository.save(reply);

//     }

//     @Override
//     public List<Reply> findAllReplies() {

//         return replyRepository.findAll();
        
//     }

//     @Override
//     public List<Reply> findByMessageId(int messageId) {

//         return replyRepository.findByMessageMessageId(messageId);

//     }
// }
