package com.syyjay.test;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.pool.DruidPooledConnection;
import com.mchange.v2.c3p0.ComboPooledDataSource;
import com.syyjay.test.config.SpringConfiguration;
import com.syyjay.test.dao.impl.UserDaoImpl;
import com.syyjay.test.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class dataSourceTest {

    @Test
    public  void testAnnoConfiguration() throws  Exception{
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(SpringConfiguration.class);
        UserService userService = (UserService)applicationContext.getBean("userService");
        userService.save();

        DataSource dataSource = (DataSource) applicationContext.getBean("dataSource");
        Connection connection = dataSource.getConnection();
        System.out.println(connection);
    }

    @Test
    //原始注解获取Bean对象并注入UserDao
    public void  test6() throws Exception {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("applicationContext.xml");
        UserService userService = (UserService) applicationContext.getBean("userService");
        userService.save();

        DataSource dataSource = (DataSource) applicationContext.getBean("dataSource");
        Connection connection = dataSource.getConnection();
        System.out.println(connection);
    }

    @Test
    //原始注解获取Bean对象
    public void  test5() throws Exception {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("applicationContext.xml");
        UserDaoImpl userDao = (UserDaoImpl) applicationContext.getBean("userDao");
        userDao.save();
    }

    @Test
    // 将DataSource交给Spring容器去创建
    public void  test4() throws Exception {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("applicationContext.xml");
        DataSource dataSource = (DataSource) applicationContext.getBean("dataSource");
        Connection connection = dataSource.getConnection();
        System.out.println(connection);
    }

    @Test
    //测试手动创建 c3p0 数据源(加载properties配置文件)
    public void  test3() throws Exception {
        //读取配置文件
        ResourceBundle resourceBundle = ResourceBundle.getBundle("jdbc");
        String driver = resourceBundle.getString("jdbc.driver");
        String url = resourceBundle.getString("jdbc.url");
        String username = resourceBundle.getString("jdbc.username");
        String password = resourceBundle.getString("jdbc.password");

        DruidDataSource dataSource = new DruidDataSource();
        dataSource.setDriverClassName(driver);
        dataSource.setUrl(url);
        dataSource.setUsername(username);
        dataSource.setPassword(password);
        DruidPooledConnection connection = dataSource.getConnection();
        System.out.println(connection);
        connection.close();
    }

    @Test
    //druid
    public void  test2() throws Exception {
        DruidDataSource dataSource = new DruidDataSource();
        dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
        dataSource.setUrl("jdbc:mysql://localhost:3306/suboDb");
        dataSource.setUsername("root");
        dataSource.setPassword("123456root");
        DruidPooledConnection connection = dataSource.getConnection();
        System.out.println(connection);
        connection.close();
    }

    @Test
    //c3p0
    public void  test1() throws Exception {
        ComboPooledDataSource dataSource = new ComboPooledDataSource();
        dataSource.setDriverClass("com.mysql.cj.jdbc.Driver");
        dataSource.setJdbcUrl("jdbc:mysql://localhost:3306/suboDb");
        dataSource.setUser("root");
        dataSource.setPassword("123456root");
        Connection connection = dataSource.getConnection();
        System.out.println(connection);
        connection.close();
    }

    @RunWith(SpringJUnit4ClassRunner.class)
    @ContextConfiguration(classes = {SpringConfiguration.class})
    public static class SpringJUnitTest {
        @Autowired
        private UserService userService;

        @Autowired
        private DataSource dataSource;

        @Test
        public void testUserService() throws SQLException {
    //        userService.save();
            System.out.println(dataSource.getConnection());
        }
    }
}
