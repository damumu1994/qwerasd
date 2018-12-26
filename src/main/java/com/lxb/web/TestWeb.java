package com.lxb.web;

import com.lxb.model.Family;
import com.lxb.service.FamilyService;
import com.lxb.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Auther: lxb
 * @Date: 2018/12/25 0025
 * @Description:
 */
@RestController
public class TestWeb {
    @Autowired
    private TestService testService;
    @Autowired
    private FamilyService familyService;
    @GetMapping("test")
    public void  test(){
        testService.getList();
    }
    @GetMapping("msg")
    public List<Family> getMsg(){
        return familyService.toId(1);
    }
}
