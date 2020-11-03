package com.syyjay.test.dao.impl;

import com.syyjay.test.dao.UserDao;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

//@Service("userDao")
//@Component("userDao")
@Repository("userDao")
@PropertySource("classpath:jdbc.properties")
public class UserDaoImpl implements UserDao {
    @Value("注入普通数据")
    private String str;
    @Value("${jdbc.driver}")
    private String driver;

    @Override
    public void save() {
        System.out.println(str);
        System.out.println(driver);
        System.out.println("UserDao Save Runing");
    }
}

