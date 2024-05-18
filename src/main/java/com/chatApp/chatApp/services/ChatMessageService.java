package com.chatApp.chatApp.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.chatApp.chatApp.model.ChatMessage;
import com.chatApp.chatApp.repository.ChatMessageRepository;

@Service
public class ChatMessageService {
    
    @Autowired
    private ChatMessageRepository chatMessageRepository;

    @Autowired
    private ChatRoomService chatRoomService;

    public ChatMessage save(ChatMessage chatMessage){
        var chatId = chatRoomService.getChatRoomId(chatMessage.getSenderId(), chatMessage.getRecipientId(), true).orElseThrow();
        chatMessage.setChatId(chatId);
        chatMessageRepository.save(chatMessage);
        return chatMessage;
    }

    public List<ChatMessage> findChatMessage(String senderId,String recipientId){
        var chatId = chatRoomService.getChatRoomId(senderId, recipientId, false);
        return chatId.map(chatMessageRepository::findByChatId).orElse(new ArrayList<>());
    }


}
