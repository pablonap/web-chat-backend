package com.binary_winters.springbootbackendchat;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer{

	@Override
	public void registerStompEndpoints(StompEndpointRegistry registry) {
		
		// I'm gonna use this route to connect from Angular to this broker.
		registry.addEndpoint("/chat/websocket")
		.setAllowedOrigins("http://localhost:4200")
		.withSockJS(); // With sock 10 the http protocol can be used to connect to the broker. So from a web browser will be possible to connect by using the http protocol instead of ws. 

	}

	@Override
	public void configureMessageBroker(MessageBrokerRegistry registry) {
		
		// This is a prefix used for the name of an event. For example when the server issues a message or notifies to the clients 
		// we have to define the name of the event to which the clients will need to be subscripted.
		registry.enableSimpleBroker("/chat/");
		// Prefix to which clients will send messages to be received by the broker.
		registry.setApplicationDestinationPrefixes("/app");

	}

}