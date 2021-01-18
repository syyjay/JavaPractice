package com.syyjay.test;

import com.syyjay.domain.User;
import com.syyjay.mapper.UserMapper;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class MyBatisTest {

    @Test
    public void  test0() throws IOException {
        //加载配置文件
        InputStream resourceAsStream = Resources.getResourceAsStream("sqlMapConfig.xml");
        //获取sqlSession 工厂对象
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(resourceAsStream);
        //获取sqlSession对象
        SqlSession sqlSession = sqlSessionFactory.openSession();
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
        List<User> userList = userMapper.findAll();
        System.out.printf(String.valueOf(userList));
        sqlSession.close();;
    }


    @Test
    public void  test1() throws IOException {
        //加载配置文件
        InputStream resourceAsStream = Resources.getResourceAsStream("sqlMapConfig.xml");
        //获取sqlSession 工厂对象
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(resourceAsStream);
        //获取sqlSession对象
        SqlSession sqlSession =sqlSessionFactory.openSession();
        List<User> userList = sqlSession.selectList("userMapper.findAll");
        System.out.printf(String.valueOf(userList));
        sqlSession.close();;
    }

    @Test
    public void test2() throws IOException {
        //加载配置文件
        InputStream resourceAsStream = Resources.getResourceAsStream("sqlMapConfig.xml");
        //获取sqlSession 工厂对象
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(resourceAsStream);
        //获取sqlSession对象
        SqlSession sqlSession =sqlSessionFactory.openSession();
        User user= sqlSession.selectOne("userMapper.findOne",2);
        System.out.printf(String.valueOf(user));
        sqlSession.close();;
    }

    @Test
    public void test3() throws IOException {

        User user = new User();
        user.setUsername("wangwu");
        user.setPassword("w123456");

        //加载配置文件
        InputStream resourceAsStream = Resources.getResourceAsStream("sqlMapConfig.xml");
        //获取sqlSession 工厂对象
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(resourceAsStream);
        //获取sqlSession对象
        SqlSession sqlSession =sqlSessionFactory.openSession();
        sqlSession.insert("userMapper.insert",user);
        sqlSession.commit();
        sqlSession.close();;
    }
}
