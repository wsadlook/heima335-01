package com.tensquare.recruit.service;

import com.tensquare.recruit.dao.EnterpriseDao;
import com.tensquare.recruit.pojo.Enterprise;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import util.IdWorker;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

@Service
public class EnterpriseService  {
    @Autowired
    private EnterpriseDao enterpriseDao;

    @Autowired
    private IdWorker idWorker;

    /**
     * 查询全部列表
     * @return
     */
    public List<Enterprise> findAll(){
        return enterpriseDao.findAll();
    }

    /**
     * 条件查询+分页
     * @param enterprise
     * @param page
     * @param size
     * @return
     */
    public Page<Enterprise> findSearch(Enterprise enterprise,int page,int size){
        Specification<Enterprise> specification = getSpecification(enterprise);
        return enterpriseDao.findAll(specification, PageRequest.of(page -1,size));
    }

    /**
     * 条件查询
     * @param enterprise
     * @return
     */
    public List<Enterprise> findSearch(Enterprise enterprise){
        return enterpriseDao.findAll(getSpecification(enterprise));
    }


    /**
     * 根据id查询实体
     * @param id
     * @return
     */
    public Enterprise findById(String id){
        return enterpriseDao.findById(id).get();
    }

    /**
     * 增加
     * @param enterprise
     */
    public void add(Enterprise enterprise){
        enterprise.setId(idWorker.nextId()+"");
        enterpriseDao.save(enterprise);
    }

    /**
     * 修改
     * @param enterprise
     */
    public void update(Enterprise enterprise){
        enterpriseDao.save(enterprise);
    }

    /**
     * 根据id删除一条数据
     * @param id
     */
    public void deleteById(String id){
        enterpriseDao.deleteById(id);
    }

    /**
     * 查询所有热度状态为1的数据
     * @return
     */
    public List<Enterprise> hotlist(){
        return enterpriseDao.queryByIshot("1");
    }

    private Specification<Enterprise> getSpecification(Enterprise enterprise) {
        Specification<Enterprise> specification = new Specification<Enterprise>() {
            @Override
            public Predicate toPredicate(Root<Enterprise> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder cb) {

                List<Predicate> predicateList = new ArrayList<>();

                if(enterprise.getId() != null && !"".equals(enterprise.getId())){
                    Predicate id = cb.like(root.get("id").as(String.class), "%" + enterprise.getId() + "%");
                    predicateList.add(id);
                }

                if(enterprise.getName() != null && !"".equals(enterprise.getName())){
                    Predicate name = cb.like(root.get("name").as(String.class), "%" + enterprise.getName() + "%");
                    predicateList.add(name);
                }

                if(enterprise.getSummary() != null && !"".equals(enterprise.getSummary())){
                    Predicate summary = cb.like(root.get("summary").as(String.class), "%" + enterprise.getSummary() + "%");
                    predicateList.add(summary);
                }

                if(enterprise.getAddress() != null && !"".equals(enterprise.getAddress())){
                    Predicate getAddress = cb.like(root.get("address").as(String.class), "%" + enterprise.getAddress() + "%");
                    predicateList.add(getAddress);
                }

                if(enterprise.getLabels() != null && !"".equals(enterprise.getLabels())){
                    Predicate labels = cb.like(root.get("labels").as(String.class), "%" + enterprise.getLabels() + "%");
                    predicateList.add(labels);
                }

                if(enterprise.getCoordinate() != null && !"".equals(enterprise.getCoordinate())){
                    Predicate coordinate = cb.like(root.get("coordinate").as(String.class), "%" + enterprise.getCoordinate() + "%");
                    predicateList.add(coordinate);
                }

                if(enterprise.getIshot() != null && !"".equals(enterprise.getIshot())){
                    Predicate ishot = cb.like(root.get("ishot").as(String.class), "%" + enterprise.getIshot() + "%");
                    predicateList.add(ishot);
                }

                if(enterprise.getLogo() != null && !"".equals(enterprise.getLogo())){
                    Predicate logo = cb.like(root.get("logo").as(String.class), "%" + enterprise.getLogo() + "%");
                    predicateList.add(logo);
                }



                if(enterprise.getUrl() != null && !"".equals(enterprise.getUrl())){
                    Predicate url = cb.like(root.get("url").as(String.class), "%" + enterprise.getUrl() + "%");
                    predicateList.add(url);
                }
                return cb.and(predicateList.toArray(new Predicate[predicateList.size()]));
            }
        };
        return specification;
    }
}
