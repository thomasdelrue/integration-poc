package com.example.integrationpoc;

import com.fasterxml.jackson.annotation.JacksonInject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.integration.config.EnableIntegration;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.PollableChannel;
import org.springframework.messaging.support.GenericMessage;
import org.springframework.util.Assert;

import java.util.Map;

@SpringBootApplication
@EnableIntegration
public class IntegrationPocApplication {


	public static void main(String[] args) {

		ApplicationContext context = SpringApplication.run(IntegrationPocApplication.class, args);

		System.out.println(context);
		Assert.notNull(context);
		MessageChannel inputChannel = context.getBean("inputChannel", MessageChannel.class);

		System.out.println(inputChannel.send(new GenericMessage<>("hello test")));

		PollableChannel outputChannel = context.getBean("outputChannel", PollableChannel.class);

		Message message = outputChannel.receive(0);
		for (Map.Entry<String, Object> entry :  message.getHeaders().entrySet()) {
			System.out.println(" [header '"+ entry.getKey() +"']: "+ entry.getValue());
		}
		System.out.println(" [x] Received: "+ message.getPayload());

	}
}
