package com.lxb.repository;

import com.lxb.model.Test;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * @Auther: lxb
 * @Date: 2018/12/25 0025
 * @Description:
 */
@Repository
public interface TestRepository extends JpaRepository<Test,Integer> {

}
