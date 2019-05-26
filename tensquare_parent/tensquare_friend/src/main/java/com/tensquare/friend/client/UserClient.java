package com.tensquare.friend.client;

import entity.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient("tensquare-user")
public interface UserClient {


    @RequestMapping(value = "user/fanscount/{userid}/{x}",method = RequestMethod.PUT)
    public Result fanscount(@PathVariable("userid") String userid,
                            @PathVariable("x") Integer x);


    @RequestMapping(value = "user/followcount/{userid}/{x}",method = RequestMethod.PUT)
    public Result followcount(@PathVariable("userid") String userid,
                              @PathVariable("x") Integer x);
}
