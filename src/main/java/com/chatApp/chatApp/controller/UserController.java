package com.chatApp.chatApp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

import com.chatApp.chatApp.model.User;
import com.chatApp.chatApp.services.UserService;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class UserController {
    
    @Autowired
    private UserService userService;

    @MessageMapping("/user.addUser")
    @SendTo("/user/topic")
    public User addUser(@Payload User user){
        userService.saveUser(user);
        return user;
    }

    @MessageMapping("/user.disconnectUser")
    @SendTo("/user/topic")
    public User disconnect(@Payload User user){
        userService.disconnect(user);
        return user;
    }

    @GetMapping("/users")
    public ResponseEntity<List<User>> findConnectedUser() {
        return ResponseEntity.ok(userService.findConnectedUser());
    }
    


}
