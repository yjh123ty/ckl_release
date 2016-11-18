/**
 * @Title: AbstractSpringTest.java
 * @Package tech.youmu.ckl
 * Copyright: Copyright (c) 2016
 * Company:成都友木科技有限公司
 * 
 * @author youmu-zh
 * @date 2016年8月15日 下午3:40:51
 * @version V1.0
 */

package tech.youmu.ckl;

import java.util.List;

import javax.sql.DataSource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
  * @ClassName: AbstractSpringTest
  * @Description: 基于spring的测试
  * @author youmu-zh
  * @date 2016年8月15日 下午3:40:51
  *
  */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/applicationContext.xml")
public  class AbstractSpringTest {
	
	@Autowired
	private DataSource dataSource;
	
	@Autowired
	private MessageSource messageSource;
	
	@Test
    public void testContext() throws Exception {
        System.out.println(dataSource);
    }
	
	public static void printList(List<?> list){
		for (Object object : list) {
			System.out.println(object);
		}
	}
	
}
