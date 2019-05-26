package com.tensquare.recruit.service;

import com.tensquare.recruit.dao.RecruitDao;
import com.tensquare.recruit.pojo.Recruit;
import entity.PageResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Service;
import util.IdWorker;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
public class RecruitService {

    @Autowired
    private RecruitDao recruitDao;

    @Autowired
    private IdWorker idWorker;

    /**
     * 查询全部列表
     */
    public List<Recruit> findAll(){
        return recruitDao.findAll();
    }

    /**
     * 条件查询+分页
     */
    public Page<Recruit> findSearch(Recruit recruit,int page, int size){
        Specification<Recruit> specification = getSpecification(recruit);
        PageRequest pageRequest = PageRequest.of(page-1,size);
        return recruitDao.findAll(specification,pageRequest);
    }

    /**
     * 条件查询
     * @param recruit
     * @return
     */
    public List<Recruit> findSearch(Recruit recruit){
        Specification<Recruit> specification = getSpecification(recruit);
        return recruitDao.findAll(specification);
    }

    /**
     * 根据id查询实体
     * @return
     */

    public Recruit findByid(String id){
        return recruitDao.findById(id).get();
    }


    /**
     * 增加
     * @return
     */
    @Modifying
   public void add(Recruit recruit){
       recruit.setId(idWorker.nextId()+"");
       recruitDao.save(recruit);
   }

    /**
     * 修改
     * @param recruit
     */
     public void update(Recruit recruit){
       recruitDao.save(recruit);
     }

    /**
     * 删除
     * @param id
     */
    public void deleteById(String id){
         recruitDao.deleteById(id);
     }

    /**
     * 查询state状态为2的所有数据，并根据时间排序
     * @return
     */
     public List<Recruit> recomment(){
        return recruitDao.getByStateOrderByCreatetimeDesc("2");
     }

    /**
     * 查询state状态不为0的所有数据，并根据创建时间排序
     * @return
     */
     public List<Recruit> newlist(){
         return recruitDao.findByStateNotOrderByCreatetimeDesc("0");
     }



    private Specification<Recruit> getSpecification(Recruit recruit) {
            Specification<Recruit> specification = new Specification<Recruit>() {
                @Override
                public Predicate toPredicate(Root<Recruit> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder cb) {
                    List<Predicate> predicateList = new ArrayList<>();
                    if(recruit.getId() != null && !"".equals(recruit.getId())){
                        Predicate id = cb.like(root.get("id").as(String.class),"%"+ recruit.getId()+"%");
                        predicateList.add(id);
                    }

                    if(recruit.getJobname() != null && !"".equals(recruit.getJobname())){
                        Predicate jobname = cb.like(root.get("jobname").as(String.class),"%"+ recruit.getJobname()+"%");
                        predicateList.add(jobname);
                    }

                    if(recruit.getSalary() != null && !"".equals(recruit.getSalary())){
                        Predicate salary = cb.like(root.get("jobname").as(String.class),"%"+ recruit.getSalary()+"%");
                        predicateList.add(salary);
                    }

                    if(recruit.getCondition() != null && !"".equals(recruit.getCondition())){
                        Predicate condition = cb.like(root.get("jobname").as(String.class),"%"+ recruit.getCondition()+"%");
                        predicateList.add(condition);
                    }

                    if(recruit.getEducation() != null && !"".equals(recruit.getEducation())){
                        Predicate education = cb.like(root.get("jobname").as(String.class),"%"+ recruit.getEducation()+"%");
                        predicateList.add(education);
                    }

                    if(recruit.getType() != null && !"".equals(recruit.getType())){
                        Predicate type = cb.like(root.get("jobname").as(String.class),"%"+ recruit.getType()+"%");
                        predicateList.add(type);
                    }

                    if(recruit.getAddress() != null && !"".equals(recruit.getAddress())){
                        Predicate address = cb.like(root.get("jobname").as(String.class),"%"+ recruit.getAddress()+"%");
                        predicateList.add(address);
                    }

                    if(recruit.getEid() != null && !"".equals(recruit.getEid())){
                        Predicate eid = cb.like(root.get("jobname").as(String.class),"%"+ recruit.getEid()+"%");
                        predicateList.add(eid);
                    }

                    if(recruit.getCreatetime() != null && !"".equals(recruit.getCreatetime())){
                        Predicate createtime = cb.like(root.get("jobname").as(String.class),"%"+ recruit.getCreatetime()+"%");
                        predicateList.add(createtime);
                    }

                    if(recruit.getState() != null && !"".equals(recruit.getState())){
                        Predicate state = cb.like(root.get("jobname").as(String.class),"%"+ recruit.getState()+"%");
                        predicateList.add(state);
                    }

                    if(recruit.getUrl() != null && !"".equals(recruit.getUrl())){
                        Predicate url = cb.like(root.get("jobname").as(String.class),"%"+ recruit.getUrl()+"%");
                        predicateList.add(url);
                    }


                    if(recruit.getLabel() != null && !"".equals(recruit.getLabel())){
                        Predicate label = cb.like(root.get("jobname").as(String.class),"%"+ recruit.getLabel()+"%");
                        predicateList.add(label);
                    }

                    if(recruit.getContent1() != null && !"".equals(recruit.getContent1())){
                        Predicate content1 = cb.like(root.get("jobname").as(String.class),"%"+ recruit.getContent1()+"%");
                        predicateList.add(content1);
                    }

                    if(recruit.getContent2() != null && !"".equals(recruit.getContent2())){
                        Predicate content2 = cb.like(root.get("jobname").as(String.class),"%"+ recruit.getContent2()+"%");
                        predicateList.add(content2);
                    }

                    Predicate[] predicates = new Predicate[predicateList.size()];
                    predicates = predicateList.toArray(predicates);


                    return cb.and(predicates);
                }
            };
                return specification;
    }
}

