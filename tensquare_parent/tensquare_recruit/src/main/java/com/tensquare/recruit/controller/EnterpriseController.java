package com.tensquare.recruit.controller;

import com.tensquare.recruit.pojo.Enterprise;
import com.tensquare.recruit.service.EnterpriseService;
import entity.PageResult;
import entity.Result;
import entity.StatusCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/enterprise")
@CrossOrigin
public class EnterpriseController {

    @Autowired
    private EnterpriseService enterpriseService;

    //GET /enterprise/search/hotlist 热门企业列表
    @RequestMapping(value = "search/hotlist",method = RequestMethod.GET)
    public Result hotlist(){
        List<Enterprise> hotlist = enterpriseService.hotlist();
        return new Result(true, StatusCode.OK,"查询成功",hotlist);
    }

    /**
     * 查询全部数据
     * @return
     */
    @RequestMapping(method = RequestMethod.GET)
    public Result findAll(){
        return new Result(true,StatusCode.OK,"查询成功",enterpriseService.findAll());
    }

    /**
     * 根据id查询
     * @param id
     * @return
     */
    @RequestMapping(value = "/{id}",method = RequestMethod.GET)
    public Result findById(@PathVariable String id){
        return new Result(true,StatusCode.OK,"查询成功",enterpriseService.findById(id));
    }

    /**
     * 分页+多条件查询
     * @param enterprise
     * @param page
     * @param size
     * @return
     */
    @RequestMapping(value = "/search/{page}/{size}",method = RequestMethod.POST)
    public Result findSearch(@RequestBody Enterprise enterprise,
                             @PathVariable Integer page,
                             @PathVariable Integer size){
        Page<Enterprise> search = enterpriseService.findSearch(enterprise, page, size);
        return new Result(true,StatusCode.OK,"查询成功",new PageResult<Enterprise>(search.getTotalElements(),search.getContent()));
    }

    /**
     * 根据条件查询
     * @param enterprise
     * @return
     */
    @RequestMapping(value = "/search",method = RequestMethod.POST)
    public Result findSearch(@RequestBody Enterprise enterprise){
        return new Result(true,StatusCode.OK,"查询成功",enterpriseService.findSearch(enterprise));
    }

    /**
     * 增加
     * @param enterprise
     * @return
     */
    @RequestMapping(method = RequestMethod.POST)
    public Result add(@RequestBody Enterprise enterprise){
        enterpriseService.add(enterprise);
        return new Result(true,StatusCode.OK,"增加成功");
    }


    /**
     * 修改
     * @param enterprise
     * @param id
     * @return
     */
    @RequestMapping(value = "/{id}",method = RequestMethod.PUT)
    public Result update(@RequestBody Enterprise enterprise,@PathVariable String id){
        enterprise.setId(id);
        enterpriseService.update(enterprise);
        return new Result(true,StatusCode.OK,"修改成功");
    }

    /**
     * 删除
     * @param id
     * @return
     */
    @RequestMapping(value = "/{id}",method = RequestMethod.DELETE)
    public Result delete(@PathVariable String id){
        enterpriseService.deleteById(id);
        return new Result(true,StatusCode.OK,"删除成功");
    }




}
