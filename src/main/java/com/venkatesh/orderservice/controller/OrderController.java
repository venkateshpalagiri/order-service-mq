package com.venkatesh.orderservice.controller;

import com.venkatesh.orderservice.dto.Order;
import com.venkatesh.orderservice.dto.OrderEvent;
import com.venkatesh.orderservice.publisher.OrderProducer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1")
public class OrderController {
    private OrderProducer orderProducer;
    public OrderController(OrderProducer orderProducer){
        this.orderProducer=orderProducer;
    }
    private Logger LOGGER= LoggerFactory.getLogger(OrderController.class);

    @PostMapping("/orders")
    public String placeOrder(@RequestBody Order order){
        LOGGER.info("placeOrder method executing...");
        order.setOrderId(UUID.randomUUID().toString());
        OrderEvent orderEvent=new OrderEvent();
        orderEvent.setStatus("PENDING");
        orderEvent.setMessage("Order is in pending status");
        orderEvent.setOrder(order);

        orderProducer.sendMessage(orderEvent);

        return "Order sent to the RabbitMQ...";

    }
}
