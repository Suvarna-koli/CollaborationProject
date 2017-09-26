package com.niit.configuration;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.ChannelRegistration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

@Configuration
@EnableWebSocketMessageBroker
@ComponentScan(basePackages="com.niit")
public class WebSocketConfiguration implements WebSocketMessageBrokerConfigurer
{

	

	
	public void registerStompEndpoints(StompEndpointRegistry registry) {
		System.out.println("Inside register STOPM");//this method is initialized the channel
	registry.addEndpoint("/portfolio").withSockJS();
	}

	public void configureMessageBroker(MessageBrokerRegistry registry) {
		
		System.out.println("configurer massage broker");
		registry.enableSimpleBroker("/queue/","/topic/");//queue is client id and topic is subscriber
		registry.setApplicationDestinationPrefixes("/app");//client to server
	}

	
	public void configureClientInboundChannel(ChannelRegistration arg0) {
		// TODO Auto-generated method stub
		
	}

	
	public void configureClientOutboundChannel(ChannelRegistration arg0) {
		// TODO Auto-generated method stub
		
	}

}
