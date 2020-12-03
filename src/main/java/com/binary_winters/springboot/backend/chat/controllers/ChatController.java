package com.binary_winters.springboot.backend.chat.controllers;

import java.util.Date;
import java.util.Random;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

import com.binary_winters.springboot.backend.chat.models.documents.Mensaje;

@Controller
// This controller will manage the broker's messages.
public class ChatController {
	
	private String[] colores = {"red", "green", "blue", "magenta", "purple", "orange"};
	
	// Be aware of "/app/" prefix set in WebSocketConfig.
	@MessageMapping("/mensaje")
	// The client has to be subscripted to this prefix. 
	// Be aware that in WebSocketConfig I specified "/chat/" so here I should match with that.
	@SendTo("/chat/mensaje") 
	public Mensaje recibeMensaje(Mensaje mensaje) {
		
		mensaje.setFecha(new Date().getTime());
		
		if(mensaje.getTipo().equals("NUEVO_USUARIO")) {
			mensaje.setColor(colores[new Random().nextInt(colores.length)]);
			mensaje.setTexto("nuevo usuario");
		}

		mensaje.setTexto("Recibido por el broker: " + mensaje.getTexto());
		
		return mensaje;
		
	}
	

}