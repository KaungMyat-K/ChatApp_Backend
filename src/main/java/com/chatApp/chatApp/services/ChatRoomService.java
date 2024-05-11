package com.chatApp.chatApp.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.chatApp.chatApp.model.Chatroom;
import com.chatApp.chatApp.repository.ChatRoomRepository;

@Service
public class ChatRoomService {

    @Autowired
    private ChatRoomRepository chatRoomRepository;
    
    public Optional<String> getChatRoomId(String senderId,String recipientId,boolean createNewRoomIfNotExists){
        return chatRoomRepository.findBySenderIdAndRecipientId(senderId,recipientId)
        .map(Chatroom::getChatId)    
        .or(()->{
            if(createNewRoomIfNotExists){
                var chatId = createChatId(senderId,recipientId);
                return Optional.of(chatId);
            }
            return Optional.empty();
        });
    }

    public String createChatId(String senderId,String recipientId){
        var chatId = String.format("%s_%s",senderId,recipientId);
        Chatroom senderRecipient = Chatroom.builder().chatId(chatId).senderId(senderId).recipientId(recipientId).build();
        Chatroom recipientSender = Chatroom.builder().chatId(chatId).senderId(recipientId).recipientId(senderId).build();
        chatRoomRepository.save(senderRecipient);
        chatRoomRepository.save(recipientSender);
        return chatId;
    }

}
