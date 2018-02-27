package com.share.pss.service;

import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @author MrZhang 
 * @date 2017年10月31日 下午4:30:09
 * @version V1.0 业务层测试类:1.防止抽象类被实例化；2.强制子类重载抽象方法； 
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext.xml")
public abstract class BaseServiceTest {
	
}
