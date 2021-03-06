package biz.cosee.techtalks.springcloud.springbootforaws.order;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@ToString
public class Order {

    private String orderId;
    private List<OrderItem> orderItems;
}
