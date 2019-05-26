package com.tensquare.qa.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.tensquare.qa.pojo.Problem;
import org.springframework.data.jpa.repository.Query;

/**
 * 数据访问接口
 * @author Administrator
 *
 */
public interface ProblemDao extends JpaRepository<Problem,String>,JpaSpecificationExecutor<Problem>{
    //根据标签id,分页查询最新问题数据,
    // 第二个参数就是分页数据
    @Query(value = "SELECT * FROM tb_problem WHERE id in (SELECT problemid FROM tb_pl WHERE labelid=?)" +
            " ORDER BY createtime DESC", nativeQuery = true)
    Page<Problem> newlist(String labelid, Pageable pageable);

    //根据标签id,分页查询热门问题数据
    @Query(value = "SELECT * FROM tb_problem WHERE id in (SELECT problemid FROM tb_pl WHERE labelid=?)" +
            " ORDER BY reply DESC", nativeQuery = true)
    Page<Problem> hotlist(String label, Pageable pageable);

    //根据标签id,分页查询等待问题数据
    @Query(value = "SELECT * FROM tb_problem WHERE id in (SELECT problemid FROM tb_pl WHERE labelid=?)" +
            " AND reply=0 ORDER BY createtime DESC", nativeQuery = true)
    Page<Problem> waitlist(String label, Pageable pageable);
}
