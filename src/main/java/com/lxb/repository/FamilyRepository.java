package com.lxb.repository;

import com.lxb.model.Family;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Auther: lxb
 * @Date: 2018/12/26 0026
 * @Description:
 */
@Repository
public interface FamilyRepository  extends JpaRepository<Family,Integer>{
    List<Family> findAllByIdOrderByCreateDateDesc(int id);
}
