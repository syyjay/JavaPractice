package com.syyjay.test.service.impl;

import com.syyjay.test.dao.UserDao;
import com.syyjay.test.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.ImportResource;
import org.springframework.stereotype.Service;

@Service("userService")
public class UserServiceImpl implements UserService {
    @Autowired
//    @Resource(name="userDao")
//    @Qualifier("userDao")
    private UserDao userDao;

    @Override
    public void save() {
        userDao.save();
    }
}
