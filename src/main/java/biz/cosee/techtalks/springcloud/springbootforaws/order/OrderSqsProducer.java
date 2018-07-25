package biz.cosee.techtalks.springcloud.springbootforaws.order;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class OrderSqsProducer {


    @Value("${aws.sqs.name.order}")
    private String QUEUE_NAME;

    public OrderSqsProducer() {
    }

    public void sendOutOrder(Order order) {

    }
}
