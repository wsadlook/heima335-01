package com.tensquare.user.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.tensquare.user.util.ThreadLocalUtil;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwt;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.tensquare.user.pojo.User;
import com.tensquare.user.service.UserService;

import entity.PageResult;
import entity.Result;
import entity.StatusCode;
import util.JwtUtil;

import javax.servlet.http.HttpServletRequest;

/**
 * 控制器层
 *
 * @author Administrator
 */
@RestController
@CrossOrigin
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private JwtUtil jwtUtil;

    //PUT/user/fanscount/{userid}/{x}
    @RequestMapping(value = "fanscount/{userid}/{x}",method = RequestMethod.PUT)
    public Result fanscount(@PathVariable String userid,
                            @PathVariable Integer x){
        userService.fanscount(userid,x);
        return new Result(true,StatusCode.OK,"粉丝数增加成功");
    }
    //PUT/user/followcount/userid/{x}

    @RequestMapping(value = "followcount/{userid}/{x}")
    public Result followcount(@PathVariable String userid,
                              @PathVariable Integer x){
        userService.followcount(userid,x);
        return new Result(true,StatusCode.OK,"关注数累加成功");
    }

    //	POST/user/register/{code}注册用户
    @RequestMapping(value = "register/{code}", method = RequestMethod.POST)
    public Result register(@PathVariable String code,
                           @RequestBody User user) {
        if (code == null || "".equals(code.trim())) {
            return new Result(false, StatusCode.ERROR, "请您输入验证码");
        }
        Object codee = redisTemplate.opsForValue().get("sms_" + user.getMobile());
        if (!code.equals(codee)) {
            return new Result(false, StatusCode.ERROR, "验证码错误，请从新输入");
        }

        userService.register(user);
        redisTemplate.delete("sms_" + user.getMobile());
        return new Result(true, StatusCode.OK, "注册成功");

    }

    //	POST/user/sendsms/{mobile}发送手机验证码
    @RequestMapping(value = "sendsms/{mobile}", method = RequestMethod.POST)
    public Result sendsms(@PathVariable String mobile) {
        userService.sendsms(mobile);
        return new Result(true, StatusCode.OK, "短信验证码发送成功");

    }

    /**
     * 查询全部数据
     *
     * @return
     */
    @RequestMapping(method = RequestMethod.GET)
    public Result findAll() {
        return new Result(true, StatusCode.OK, "查询成功", userService.findAll());
    }

    /**
     * 根据ID查询
     *
     * @param id ID
     * @return
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Result findById(@PathVariable String id) {
        return new Result(true, StatusCode.OK, "查询成功", userService.findById(id));
    }


    /**
     * 分页+多条件查询
     *
     * @param searchMap 查询条件封装
     * @param page      页码
     * @param size      页大小
     * @return 分页结果
     */
    @RequestMapping(value = "/search/{page}/{size}", method = RequestMethod.POST)
    public Result findSearch(@RequestBody Map searchMap, @PathVariable int page, @PathVariable int size) {
        Page<User> pageList = userService.findSearch(searchMap, page, size);
        return new Result(true, StatusCode.OK, "查询成功", new PageResult<User>(pageList.getTotalElements(), pageList.getContent()));
    }

    /**
     * 根据条件查询
     *
     * @param searchMap
     * @return
     */
    @RequestMapping(value = "/search", method = RequestMethod.POST)
    public Result findSearch(@RequestBody Map searchMap) {
        return new Result(true, StatusCode.OK, "查询成功", userService.findSearch(searchMap));
    }

    /**
     * 增加
     *
     * @param user
     */
    @RequestMapping(method = RequestMethod.POST)
    public Result add(@RequestBody User user) {
        userService.add(user);
        return new Result(true, StatusCode.OK, "增加成功");
    }

    /**
     * 修改
     *
     * @param user
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public Result update(@RequestBody User user, @PathVariable String id) {
        user.setId(id);
        userService.update(user);
        return new Result(true, StatusCode.OK, "修改成功");
    }

    /**
     * 删除
     *
     * @param id
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public Result delete(@PathVariable String id, HttpServletRequest request) {

       /* String hearder = request.getHeader("Authorization");

        if (hearder == null || "".equals(hearder.trim())) {
            return new Result(false, StatusCode.ACCESSERROR, "用户未登录");
        }
        if (!hearder.startsWith("Bearer ")) {
            return new Result(false, StatusCode.ACCESSERROR, "token不合法，请重新操作");
        }
        try {
            String token = hearder.substring(7);

            Claims claims = jwtUtil.parseJWT(token);*/
//        Claims claims = (Claims) request.getAttribute("claims");
        Claims claims = ThreadLocalUtil.get();
        if (claims == null) {
            return new Result(false, StatusCode.ACCESSERROR, "token不合法，请重新登陆");
        }
        String roles = (String) claims.get("roles");
        if ("admin".equals(roles)) {
            userService.deleteById(id);
            return new Result(true, StatusCode.OK, "操作成功");

        } else {
            return new Result(false, StatusCode.ACCESSERROR, "token不合法，请重新登陆");
        }


       /* } catch (Exception e) {
            e.printStackTrace();
        }*/

    }

    @RequestMapping(value = "/login/{mobile}/{password}", method = RequestMethod.POST)
    public Result login(@PathVariable String mobile, @PathVariable String password) {
        User user = userService.findByMobileAndPassword(mobile, password);
        if (user != null) {
            String token = jwtUtil.createJWT(user.getId(), user.getMobile(), "user");
            Map<String,String> map = new HashMap<>();
            map.put("mobile",user.getMobile());
            map.put("token",token);
            return new Result(true, StatusCode.OK, "登陆成功",map);
        } else {
            return new Result(false, StatusCode.LOGINERROR, "用户名或密码错误");
        }
    }

}
