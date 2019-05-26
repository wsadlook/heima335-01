package com.tensquare.recruit.controller;

import com.tensquare.recruit.dao.RecruitDao;
import com.tensquare.recruit.pojo.Recruit;
import com.tensquare.recruit.service.RecruitService;
import entity.PageResult;
import entity.Result;
import entity.StatusCode;
import org.aspectj.weaver.ast.Var;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/recruit")
@CrossOrigin // 跨域解决
public class RecruitController {

    @Autowired
    private RecruitService recruitService;


    //GET /recruit/search/newlist 最新职位
    @RequestMapping(value = "search/newlist",method = RequestMethod.GET)
    public Result newlist(){
        List<Recruit> list = recruitService.newlist();
        return new Result(true, StatusCode.OK,"查询成功",list);
    }

    //GET /recruit/search/recommend 推荐职位
    @RequestMapping(value = "search/recomment",method = RequestMethod.GET)
    public Result recomment(){
        List<Recruit> list = recruitService.recomment();
        return new Result(true,StatusCode.OK,"查询成功",list);
    }

    /**
     * 查询全部数据
     * @return
     */
    @RequestMapping(method = RequestMethod.GET)
    public Result findAll(){
        return new Result(true,StatusCode.OK,"查询成功",recruitService.findAll());
    }

    /**
     *根据id查询
     * @param id
     * @return
     */
    @RequestMapping(value = "/{id}",method = RequestMethod.GET)
    public Result findById(@PathVariable String id){
        return new Result(true,StatusCode.OK,"查询成功",recruitService.findByid(id));
    }

    /**
     * 分页+多条件查询
     * @param recruit
     * @param page
     * @param size
     * @return
     */
    @RequestMapping(value = "/search/{page}/{size}",method = RequestMethod.POST)
    public Result findSearch(@RequestBody Recruit recruit,@PathVariable int page, @PathVariable int size){
        Page<Recruit> pageList = recruitService.findSearch(recruit,page,size);
        return new Result(true,StatusCode.OK,"查询成功",new PageResult(pageList.getTotalElements(),pageList.getContent()));
    }

    /**
     * 根据条件查询
     * @param recruit
     * @return
     */
    @RequestMapping(value = "/search", method = RequestMethod.POST)
    public Result findSearch(@RequestBody Recruit recruit){
        return new Result(true,StatusCode.OK,"查询成功",recruitService.findSearch(recruit));

    }

    /**
     * 增加
     * @param recruit
     * @return
     */
    @RequestMapping(method = RequestMethod.POST)
    @Modifying
    public Result add(@RequestBody Recruit recruit){
        recruitService.add(recruit);
        return new Result(true,StatusCode.OK,"增加成功");
    }

    /**
     * 修改
     * @param recruit
     * @param
     * @return
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    //@Modifying
    public Result update(@RequestBody Recruit recruit,@PathVariable String id){
        recruit.setId(id);
        recruitService.update(recruit);
        return new Result(true,StatusCode.OK,"修改成功");
    }

    /**
     * 删除
     * @param id
     * @return
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public Result delete(@PathVariable String id){
        recruitService.deleteById(id);
        return new Result(true,StatusCode.OK,"删除成功");
    }
}
