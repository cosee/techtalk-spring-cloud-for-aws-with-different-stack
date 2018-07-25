package biz.cosee.techtalks.springcloud.springbootforaws.order;

import com.amazonaws.services.sqs.AmazonSQSAsync;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.aws.messaging.core.QueueMessagingTemplate;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

@Service
public class OrderSqsProducer {

    private final QueueMessagingTemplate queueMessagingTemplate;

    @Value("${aws.sqs.name.order}")
    private String QUEUE_NAME;

    public OrderSqsProducer(AmazonSQSAsync amazonSQSAsync) {
        this.queueMessagingTemplate = new QueueMessagingTemplate(amazonSQSAsync);
    }

    public void sendOutOrder(Order order) {
        queueMessagingTemplate.send(
                QUEUE_NAME,
                MessageBuilder.withPayload(order.toString()).build());
    }
}
