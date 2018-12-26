package com.lxb.model;

import lombok.Data;
import org.springframework.stereotype.Repository;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @Auther: lxb
 * @Date: 2018/12/25 0025
 * @Description:
 */
@Entity
@Data
@Table(name = "t_table")
public class Test {
    @Id
    @GeneratedValue
    private int id;
    private String msg;
}
