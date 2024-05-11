package com.chatApp.chatApp.repository;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.chatApp.chatApp.model.Chatroom;

@Repository
public interface ChatRoomRepository extends MongoRepository<Chatroom,String> {
    
    Optional<Chatroom> findBySenderIdAndRecipientId(String senderId,String recipientId);

}
