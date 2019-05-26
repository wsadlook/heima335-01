package com.tenquare.sms;

import com.tenquare.sms.util.SmsUtil;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class SmsApplication {
    public static void main(String[] args) {
        SpringApplication.run(SmsApplication.class,args);
    }

/*    @Bean
    public SmsUtil createSmsUtil(){
        return new SmsUtil();
    }*/
}
