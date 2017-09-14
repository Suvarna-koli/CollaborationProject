package com.niit.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.messaging.simp.annotation.SubscribeMapping;
import org.springframework.stereotype.Controller;

import com.niit.model.Chat;

@Controller
public class SocketController {

	private SimpMessagingTemplate messagingTemplate;
	private List<String> users=new ArrayList<String>();
	
	@Autowired
	public SocketController(SimpMessagingTemplate messagingTemplate){
		super();
		this.messagingTemplate=messagingTemplate;
	}
	
	@SubscribeMapping("/join/{username}")
	public List<String> join(@DestinationVariable("username") String username){
		if(!users.contains(username))
			users.add(username);
		messagingTemplate.convertAndSend("/topic/join",username);
		return users;
	}
	
	@MessageMapping("/chat")
	public void chatReceived(Chat chat){
		if("all".equals(chat.getTo())){
			messagingTemplate.convertAndSend("/queue/chats", chat);
		}
		else{
			messagingTemplate.convertAndSend("/queue/chats/"+chat.getTo(), chat);
		messagingTemplate.convertAndSend("/queue/chats/"+chat.getFrom(), chat);
		}
	}
}
