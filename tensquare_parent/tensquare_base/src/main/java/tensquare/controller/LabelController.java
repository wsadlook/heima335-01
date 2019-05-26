package tensquare.controller;

import entity.PageResult;
import entity.Result;
import entity.StatusCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;
import tensquare.pojo.Label;
import tensquare.service.Labelservice;

import java.util.List;

@RestController
@RequestMapping("label")
public class LabelController {
    @Autowired
    private Labelservice labelservice;



    @RequestMapping(value = "search/{page}/{size}",method = RequestMethod.POST)
    public Result search(@RequestBody Label label,
                         @PathVariable Integer page,
                         @PathVariable Integer size){
        Page<Label> pageData = labelservice.search(label,page,size);
        PageResult<Label> pageResult = new PageResult<>(
                pageData.getTotalElements(),pageData.getContent()
        );
        return new Result(true,StatusCode.OK,"查询成功",pageResult);
    }

//    /label/search标签条件查询
    @RequestMapping(value = "{search}",method = RequestMethod.POST)
    public Result search(@RequestBody Label label){
        List<Label> list = labelservice.search(label);
        return new Result(true,StatusCode.OK,"查询成功",list);
    }

    @RequestMapping(value = "{labelId}",method = RequestMethod.DELETE)
    public Result deleteById(@PathVariable String labelId){
        labelservice.deleteById(labelId);
        return new Result(true, StatusCode.OK,"删除成功");
    }

    @RequestMapping(value = "{labelId}",method = RequestMethod.PUT)
    public Result updateById(@PathVariable String labelId, @RequestBody Label label){
        label.setId(labelId);

        labelservice.updateById(label);
        return new Result(true,StatusCode.OK,"修改成功");
    }

    @RequestMapping(method = RequestMethod.POST)
    public Result save(@RequestBody Label label){
        labelservice.save(label);
        return new Result(true,StatusCode.OK,"新增成功");
    }

    @RequestMapping(method = RequestMethod.GET)
    public Result findAll(){
        List<Label> list = labelservice.findAll();

        return new Result(true,StatusCode.OK,"查询成功",list);

    }

    @RequestMapping(value = "{labelId}",method = RequestMethod.GET)
    public Result findById(@PathVariable String labelId){
        Label label = labelservice.findById(labelId);

        return new Result(true,StatusCode.OK,"查询成功",label);
    }


}
