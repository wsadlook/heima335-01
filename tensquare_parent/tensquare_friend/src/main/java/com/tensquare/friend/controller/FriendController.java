package com.tensquare.friend.controller;

import com.tensquare.friend.client.UserClient;
import com.tensquare.friend.dao.NoFriendDao;
import com.tensquare.friend.service.FriendService;
import entity.Result;
import entity.StatusCode;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;

@RestController
@RequestMapping("friend")
@CrossOrigin
public class FriendController {

    @Autowired
    private FriendService friendService;

    @Autowired
    private UserClient userClient;


    //    PUT/friend/like/{friendid}/{type}添加好友或非好友
    @RequestMapping(value = "like/{friendid}/{type}", method = RequestMethod.PUT)
    public Result like(@PathVariable String friendid,
                       @PathVariable String type,
                       HttpServletRequest request) {
        Claims claims = (Claims) request.getAttribute("claims");
        if (claims == null || "".equals(claims.getId())) {
            new Result(false, StatusCode.LOGINERROR, "用户未登录");
        }

        String userid = claims.getId();
        if ("1".equals(type)) {
            //如果类型为1，就是加好友
            boolean nofriend = friendService.like(userid, friendid);
            if(!nofriend){
                return new Result(false,StatusCode.REPERROR,"已经为好友，不能继续添加");
            }
            userClient.followcount(userid,1);
            userClient.fanscount(friendid,1);
        } else {
            //如果类型不为1，就是加非好友
            friendService.addNoFriend(userid,friendid);
            return new Result(true,StatusCode.OK,"添加no好友成功");
        }
        return new Result(true, StatusCode.OK, "添加成功");
    }

//    DELETE/friend/{friendid}删除好友
    @RequestMapping(value = "{friendid}",method = RequestMethod.DELETE)
    public Result noLike(@PathVariable String friendid,
                         HttpServletRequest request){
         Claims claims = (Claims) request.getAttribute("claims");
         if(claims ==null || "".equals(claims.getId())){
             return new Result(false,StatusCode.ACCESSERROR,"没有权限");
         }

        String userid = claims.getId();
         friendService.delete(userid,friendid);
        userClient.followcount(userid,-1);
        userClient.fanscount(friendid,-1);


        return new Result(true,StatusCode.OK,"删除好友成功");
    }
}
