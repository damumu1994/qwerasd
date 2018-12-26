package com.lxb;

import junit.framework.TestCase;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class FamilyApplicationTests {

	@Test
	public void contextLoads() {
		System.out.printf("大傻妞");
	}
	@Test
	public void testOne(){
		System.out.println("test hello 1");
	}

	@Test
	public void testTwo(){
		System.out.println("test hello 2");
		TestCase.assertEquals(1, 1);
	}

	@Before
	public void testBefore(){
		System.out.println("before");
	}

	@After
	public void testAfter(){
		System.out.println("after");
	}

}

