package com.chatApp.chatApp.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.chatApp.chatApp.model.ChatMessage;

public interface ChatMessageRepository extends MongoRepository<ChatMessage,String> {
    
    List<ChatMessage> findByChatId(String s);
}
