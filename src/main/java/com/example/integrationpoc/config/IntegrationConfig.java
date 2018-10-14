package com.example.integrationpoc.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.annotation.IntegrationComponentScan;
import org.springframework.integration.channel.DirectChannel;
import org.springframework.integration.channel.QueueChannel;
import org.springframework.integration.dsl.IntegrationFlow;
import org.springframework.integration.dsl.IntegrationFlows;
import org.springframework.messaging.MessageChannel;

@Configuration
@IntegrationComponentScan
public class IntegrationConfig {

    @Bean
    public MessageChannel inputChannel() {
        return new DirectChannel();
    }

    @Bean
    public MessageChannel outputChannel() {
        return new QueueChannel(10);
    }

    @Bean
    public IntegrationFlow flow() {
        return IntegrationFlows.from(inputChannel())
                .transform(m -> ((String) m).toUpperCase())
                .channel(outputChannel())
                .get();
    }
}
