package com.tenquare.sms.listener;

import com.aliyuncs.exceptions.ClientException;
import com.tenquare.sms.util.SmsUtil;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
@RabbitListener(queues = "sms")
public class MsgListener {
 

    @Autowired
    private SmsUtil smsUtil;

    @RabbitHandler
    public void handlerMsg(Map<String,String> map){
        String mobile = map.get("mobile");
        System.out.println("电话"+mobile);
        String code = map.get("code");
        System.out.println("验证码"+code);
        try {
            smsUtil.sendSms(mobile,code);
        } catch (ClientException e) {
            e.printStackTrace();
        }
    }
}
