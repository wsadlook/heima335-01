package com.tensquare.spit.service;

import com.mongodb.client.result.UpdateResult;
import com.tensquare.spit.dao.SpitDao;
import com.tensquare.spit.pojo.Spit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import util.IdWorker;

import javax.lang.model.element.VariableElement;
import java.util.Date;
import java.util.List;

@Service
public class SpitService {

    @Autowired
    private SpitDao spitDao;

    @Autowired
    private IdWorker idWorker;

    @Autowired
    private MongoTemplate mongoTemplate;

    // 根据id查询数据
    public Spit findById(String id){
        return spitDao.findById(id).get();
    }

    //查询所有
    public List<Spit> findAll(){
        return spitDao.findAll();
    }

    //新增
    @Transactional
    public void save(Spit spit){
        spit.set_id(idWorker.nextId()+"");

        spit.setPublishtime(new Date());//发布时间
        spit.setVisits(0);//浏览量
        spit.setShare(0);//分享数
        spit.setThumbup(0);//点赞数
        spit.setComment(0);//回复数
        spit.setState("1");//状态
        if (spit.getParentid() != null && !"".equals(spit.getParentid())) {

            Query query = new Query();
            query.addCriteria(Criteria.where("_id").is(spit.getParentid()));

            Update update = new Update();
            update.inc("comment",1);
            mongoTemplate.updateFirst(query,update,"spit");
        }

        spitDao.save(spit);
    }

    //修改
    public void updateById(Spit spit){
        spitDao.save(spit);
    }
    //根据id删除
    public void deleteById(String id){
        spitDao.deleteById(id);

    }

    public Page<Spit> comment(String parentid, Integer page, Integer size) {
        PageRequest pageRequest = PageRequest.of(page - 1, size);
        Page<Spit> pageData = spitDao.findByParentid(parentid, pageRequest);
        return pageData;
    }

    public void thumbup(String spitId) {
//        Spit spit = spitDao.findById(spitId).get();
//        spit.setThumbup(spit.getThumbup() + 1);
//        spitDao.save(spit);
//        以上的操作不建议使用，因为一次点赞功能需要操作两次数据库才能实现，性能不高

        Query query = new Query();
        query.addCriteria(Criteria.where("_id").is(spitId));
        Update update = new Update();
        update.inc("thumbup",1);
        UpdateResult spit = mongoTemplate.updateFirst(query, update, "spit");
    }
}
