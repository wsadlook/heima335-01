package com.itheima.rabbitmq.consumer.listener;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@RabbitListener(queues = "itheima")
public class MyListener {

    @RabbitHandler
    public void handlerMessage(String message){
        System.out.println("itheima消费者2"+message);
    }
}
