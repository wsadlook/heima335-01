package com.tensquare.article.controller;

import com.tensquare.article.pojo.Comment;
import com.tensquare.article.service.CommentService;
import entity.Result;
import entity.StatusCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("comment")
public class CommentController {

    @Autowired
    private CommentService commentService;

    //POST/comment 新增
    @RequestMapping(method = RequestMethod.POST)
    public Result save(@RequestBody Comment comment){
        commentService.save(comment);
        return new Result(true, StatusCode.OK,"保存成功");
    }

    //GET/comment
    @RequestMapping(method = RequestMethod.GET)
    public Result findAll(){
        List<Comment> list = commentService.findAll();
        return new Result(true,StatusCode.OK,"查询成功",list);
    }

    //GET/{commentId}
    @RequestMapping(value = "{commentId}",method = RequestMethod.GET)
    public Result findById(@PathVariable String commentId){
        Comment comment = commentService.findById(commentId);
        return new Result(true,StatusCode.OK,"查询成功",comment);
    }

    //DELETE{articleid}
    @RequestMapping(value = "{articleId}",method = RequestMethod.DELETE)
    public Result deleteById(@PathVariable String articleId){
        commentService.deleteById(articleId);
        return new Result(true,StatusCode.OK,"删除成功");
    }
}
