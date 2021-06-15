package com.example.demo;

import java.util.Date;
import java.util.UUID;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MessagePublisher {
	
	@Autowired
	public RabbitTemplate rabit;
	@PostMapping("/publish")
	public String messagePublish(@RequestBody Customermessage mes) {
		
		mes.setMessageId(UUID.randomUUID().toString());
		mes.setMessageDate(new Date());
		rabit.convertAndSend(MQconfig.ex,MQconfig.route1, mes);
		rabit.convertAndSend(MQconfig.ex,MQconfig.route2, mes);
		return "message sent!!!!";
		
	}

}
