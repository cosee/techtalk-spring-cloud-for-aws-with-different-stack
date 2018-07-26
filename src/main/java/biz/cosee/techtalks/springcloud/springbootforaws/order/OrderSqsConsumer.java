package biz.cosee.techtalks.springcloud.springbootforaws.order;

import com.amazonaws.services.sqs.AmazonSQS;
import com.amazonaws.services.sqs.model.Message;
import com.amazonaws.services.sqs.model.ReceiveMessageResult;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.List;

@Component
@Slf4j
public class OrderSqsConsumer {

    private final ObjectMapper mapper;
    private final AmazonSQS amazonSQS;

    @Value("${aws.sqs.name.order}")
    private String queueName;

    public OrderSqsConsumer(ObjectMapper mapper, AmazonSQS amazonSQS) {
        this.mapper = mapper;
        this.amazonSQS = amazonSQS;
    }

    @Scheduled(fixedRate = 5000, initialDelay = 2000)
    public void consumeOrderSqs() {
        log.info("Attempting to receive messages from SQS '"+ queueName + "'");

        ReceiveMessageResult result = amazonSQS.receiveMessage(queueName);

        List<Message> messages = result.getMessages();
        if(!messages.isEmpty()) {
            log.info("Received orders!");
            for (Message sqsMessage : messages) {
                Order order = mapPayloadToOrder(sqsMessage.getBody());
                System.out.println("Following order was consumed: " + order);
            }
        }
        else {
            log.info("No orders in queue.");
        }

    }

    private Order mapPayloadToOrder(String payload) {
        return mapToMessage(payload, Order.class);
    }

    private <T> T mapToMessage(String payload, Class<T> clazz) {
        try {
            return mapper.readValue(payload, clazz);
        } catch (IOException e) {
            throw new RuntimeException("Could not parse payload from SQS.", e);
        }
    }

}
