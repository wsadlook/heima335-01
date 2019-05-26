package com.tensquare.article.service;

import com.tensquare.article.dao.CommentDao;
import com.tensquare.article.pojo.Comment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import util.IdWorker;

import java.util.Date;
import java.util.List;

@Service
public class CommentService {

    @Autowired
    private CommentDao commentDao;

    @Autowired
    private IdWorker idWorker;
    public void save(Comment comment) {
        comment.set_id(idWorker.nextId()+"");
        comment.setPublishdate(new Date());
        commentDao.save(comment);
    }

    public List<Comment> findAll() {
        List<Comment> list = commentDao.findAll();
        return list;
    }

    public Comment findById(String commentId) {

        Comment comment = commentDao.findById(commentId).get();
        return comment;
    }

    @Transactional
    public void deleteById(String articleId) {
        commentDao.deleteByArticleid(articleId);

    }
}
