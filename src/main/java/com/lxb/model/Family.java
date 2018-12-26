package com.lxb.model;

import lombok.Data;
import org.hibernate.exception.DataException;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Date;

/**
 * @Auther: lxb
 * @Date: 2018/12/26 0026
 * @Description:
 */
@Entity
@Data
public class Family {
    @Id@GeneratedValue
    private Integer id;
    private String familyName;
    private Date createDate;
}
