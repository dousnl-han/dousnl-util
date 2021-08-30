package com.user.test;

import com.dousnl.DousnlUtilApplication;
import com.dousnl.domain.User;
import com.dousnl.mapper.UserEntityMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Description: 对象引用传递说明-实测，讲的比较清楚的
 *
 * https://blog.csdn.net/q5706503/article/details/82910428
 *
 * Company    : 上海黄豆网络科技有限公司
 *
 * @author : hanliangliang
 * Date       : 2020/12/28
 * Modify     : 修改日期          修改人员        修改说明          JIRA编号
 * v1.0.0       2020/12/28       hanliangliang     新增              1001
 ********************************************************************/
// 获取启动类，加载配置，确定装载 Spring 程序的装载方法，它回去寻找 主配置启动类（被 @SpringBootApplication 注解的）
//@SpringBootTest
// 让 JUnit 运行 Spring 的测试环境， 获得 Spring 环境的上下文的支持

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {DousnlUtilApplication.class})
public class UserTest {

    @Autowired
    private UserEntityMapper userEntityMapper;


    @Test
    public void testUpdateUser(){
        //int updateUser = userEntityMapper.updateUser(5);
        //System.out.println(updateUser);
        User u1=saveU1();
        u1.setName("22");
        if (u1!=null){
            System.out.println(u1.getAge());
            saveU12(u1);
        }
        System.out.println(u1);
    }

    private User saveU12(User u1) {
        u1.setName("111");
        u1=null;
        return null;
    }

    private User saveU1() {
        User u1=new User();
        u1.setAge(1);
        return u1;
    }
}


