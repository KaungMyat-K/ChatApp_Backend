package com.chatApp.chatApp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessageType;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.chatApp.chatApp.model.ChatMessage;
import com.chatApp.chatApp.model.ChatNotification;
import com.chatApp.chatApp.services.ChatMessageService;

@Controller
public class ChatController {
    
    @Autowired
    private ChatMessageService chatMessageService;

    @Autowired
    private SimpMessagingTemplate simpMessagingTemplate;


    @MessageMapping("/chat")
    public void processMessage(@Payload ChatMessage chatMessage){
        ChatMessage saveMsg = chatMessageService.save(chatMessage);
        ChatNotification chatNotification = ChatNotification.builder().id(saveMsg.getId()).senderId(saveMsg.getSenderId()).recipientId(saveMsg.getRecipientId()).content(saveMsg.getContent()).build();
        simpMessagingTemplate.convertAndSendToUser(chatMessage.getRecipientId(),"/queue/messages",chatNotification);
    }

    @GetMapping("/messages/{senderId}/{recipientId}")
    public ResponseEntity<List<ChatMessage>> findChatMessages(@PathVariable("senderId") String senderId,@PathVariable("recipientId") String recipientId){
        return ResponseEntity.ok(chatMessageService.findChatMessage(senderId, recipientId));
    }


}
