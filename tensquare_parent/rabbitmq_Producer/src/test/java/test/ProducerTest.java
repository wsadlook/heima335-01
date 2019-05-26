package test;

import com.itheima.rabbitmq.producer.ProducerApplication;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = ProducerApplication.class)
public class ProducerTest {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    //相当于ActiveMq点对点消息发送
    @Test
    public void test1(){
        for(int i=0;i<10;i++){

        rabbitTemplate.convertAndSend("chuanzhi","测试发送简单类型消息"+i);
        }
    }

    //分列模式，相当于ActiveMq的发布订阅模式
    @Test
    public void test2(){
        rabbitTemplate.convertAndSend("chuanzhi","","你们都能收到吧？");
    }
    //Routing路由模式
    @Test
    public void test3(){
        //第一个参数是交换机
        //第二个参数是路由键
        rabbitTemplate.convertAndSend("chuanzhi2","itcast","测试Routing模式的消息发送");
    }
    //主题模式
    //Topics
    @Test
    public void test4(){
        rabbitTemplate.convertAndSend("chuanzhi3","itheima.itcast","主题模式！");
    }
}
