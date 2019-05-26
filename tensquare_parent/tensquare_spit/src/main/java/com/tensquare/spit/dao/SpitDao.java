package com.tensquare.spit.dao;

import com.tensquare.spit.pojo.Spit;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface SpitDao extends MongoRepository<Spit,String> {
    //SpringDataJap有五种方式查询
    //1. JpaRepository提供的方法
    //2. 设置方法名的方式
    //3. 使用jpqa语句查询  @query注解
    //4. 使用sql语句查询  @query注解
    //5. 使用Specification进行复杂查询


    //这里使用类型与SpringDataJpa的第2中方式,方法名命名的方式

    //根据上级id查询数据,进行分页操作
    Page<Spit> findByParentid(String parentid,Pageable pageable);


}
