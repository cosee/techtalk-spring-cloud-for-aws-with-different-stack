package com.cosee.springbootforaws;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.cloud.aws.messaging.listener.annotation.SqsListener;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class OrderSqsConsumer {

    private final ObjectMapper mapper;

    public OrderSqsConsumer(ObjectMapper mapper) {
        this.mapper = mapper;
    }

    protected Message mapPayloadToMessage(String payload) {
        return mapToMessage(payload, Message.class);
    }

    protected <T> T mapToMessage(String payload, Class<T> clazz) {
        try {
            return mapper.readValue(payload, clazz);
        } catch (IOException e) {
            throw new RuntimeException("Could not parse payload from SQS.", e);
        }
    }

    @SqsListener(value = "${aws.sqs.name.order}")
    public void consumeOrderSqs(String payload) {
        System.out.println("Received order!");

        Message message = mapPayloadToMessage(payload);

        System.out.println("Following order was consumed: " + message.getMessage());
    }

}
