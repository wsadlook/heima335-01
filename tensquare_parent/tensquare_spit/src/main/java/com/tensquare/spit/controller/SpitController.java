package com.tensquare.spit.controller;

import com.tensquare.spit.pojo.Spit;
import com.tensquare.spit.service.SpitService;
import entity.PageResult;
import entity.Result;
import entity.StatusCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("spit")
public class SpitController {

    @Autowired
    private SpitService spitService;

    @Autowired
    private RedisTemplate redisTemplate;

//    /spit/{spitId}根据ID查询吐槽
    @RequestMapping(value = "{spitId}",method = RequestMethod.GET)
    public Result findById(@PathVariable String spitId){
        Spit spit = spitService.findById(spitId);
        return new Result(true, StatusCode.OK,"查询成功",spit);
    }
//    /spitSpit全部列表
    @RequestMapping(method = RequestMethod.GET)
    public Result findAll(){
        List<Spit> list = spitService.findAll();
        return new Result(true,StatusCode.OK,"查询成功",list);
    }
//    /spit 增加吐槽
    @RequestMapping(method = RequestMethod.POST)
    public Result save(@RequestBody Spit spit){
        spitService.save(spit);
        return new Result(true,StatusCode.OK,"添加成功");
    }
//    /spit/{spitId}修改吐槽
    @RequestMapping(value = "{spitId}",method = RequestMethod.PUT)
    public Result update(@RequestBody Spit spit,
                         @PathVariable String spitId){
        spit.set_id(spitId);
        spitService.updateById(spit);
        return new Result(true,StatusCode.OK,"修改成功");
    }
//    DELETE/spit/{spitId}根据ID删除吐槽
    @RequestMapping(value = "{spitId}",method = RequestMethod.DELETE)
    public Result deleteById(@PathVariable String spitId){
        spitService.deleteById(spitId);
        return new Result(true,StatusCode.OK,"删除成功");
    }
//    GET/spit/comment/{parentid}/{page}/{size}根据上级ID查询吐槽数据（分页）
    @RequestMapping(value = "/comment/{parentid}/{page}/{size}",method = RequestMethod.GET)
    public Result comment(@PathVariable String parentid,
                          @PathVariable Integer page,
                          @PathVariable Integer size){
        Page<Spit> pageData = spitService.comment(parentid,page,size);
        PageResult<Spit> pageResult = new PageResult<>(pageData.getTotalElements(),pageData.getContent());
        return new Result(true,StatusCode.OK,"查询成功",pageResult);
    }
//    PUT/spit/thumbup/{spitId}吐槽点赞
    @RequestMapping(value = "/thumbup/{spitId}",method = RequestMethod.PUT)
    public Result thumbup(@PathVariable String spitId){

        String userid = "1013";
        Object result = redisTemplate.opsForValue().get("thumbup_" + userid + "_" + spitId);
        if(result != null){
            redisTemplate.delete("thumbup_" + userid + "_" + spitId);
            return new Result(true,StatusCode.REPERROR,"点赞已取消");

        }
        redisTemplate.opsForValue().set("thumbup_"+userid+"_"+spitId,1);

        spitService.thumbup(spitId);
        return new Result(true,StatusCode.OK,"点赞成功");
    }
}
