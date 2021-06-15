package com.example.demo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MQconfig {
	public List<String> a=new ArrayList<String>();
	public static final String que1="que1";
	public static final String que2="que2";
	
	
	public static final String ex="ex1";
	
	public static final String route1="route1";
	public static final String route2="route2";
	/**************************************************************************************/
	@Bean
	public Queue queue1() {
		
		return new Queue(que1);
	}
	@Bean
	public Queue queue2() {
		
		return new Queue(que2);
	}
	
/********************************************************************************************************/	
	@Bean
	public TopicExchange exchange() {
		return new TopicExchange(ex);
	}
/******************************************************************************************************/	
	@Bean
	public Binding binding1(@Qualifier("queue1") Queue que,TopicExchange ex) {
		return BindingBuilder
				.bind(que)
				.to(ex)
				.with(route1);
	}
	@Bean
	public Binding binding2(@Qualifier("queue2") Queue que,TopicExchange ex) {
		return BindingBuilder
				.bind(que)
				.to(ex)
				.with(route2);
	}
/********************************************************************************************************/	
	@Bean
	public MessageConverter msgconv() {
		return new Jackson2JsonMessageConverter();
	}
	
	@Bean
	public AmqpTemplate template(ConnectionFactory fac) {
		RabbitTemplate temp=new RabbitTemplate(fac);
		temp.setMessageConverter(msgconv());
		return temp;
	}

}
