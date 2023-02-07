package com.example.makeproject;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.junit4.SpringRunner;

/************
 * @info : Spring - Container관리 Beans 목록 확인
 * @name : ContainerNameTest
 * @date : 2023/02/07 7:05 PM
 * @author : SeokJun Kang(swings134@gmail.com)
 * @version : 1.0.0
 * @Description : Spring Container 구현체 2가지 : BeanFactory, ApplicationContext
 *
 *
 * - ApplicationContext는 BeanFactory를 구현하여 BeanFactory의 확장 버전.
 * - Spring 공식문서 상 : 특별한일이 아니면 ApplicationContext 사용할 것을 권장.
 *
 * - 목표: ApplicationContext 내부의 beans 확인.
 ************/
@RunWith(SpringRunner.class)
@SpringBootTest(classes = MakeProjectApplication.class)
public class ContainerNameTest {


    @Autowired
    ApplicationContext context;

    @Test
    public void contextCheck() throws Exception {

        if(context != null) {
            String[] beans = context.getBeanDefinitionNames();

            for (String bean: beans) {
                System.out.println("bean: " + bean);
            }
        }

    }


}
