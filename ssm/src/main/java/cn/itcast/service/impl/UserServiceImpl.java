package cn.itcast.service.impl;

import cn.itcast.dao.UserDao;
import cn.itcast.domain.User;
import cn.itcast.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service("userService")
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDao userDao;
    @Override
    public boolean findByOne(User user) {

        List<User> userDaoAll = userDao.findByOne(user);
        if (userDaoAll !=null && userDaoAll.size() >= 1 ){
            return true;
        }
            return false;
    }

    @Override
    public boolean findByname(String username) {
        List<User> userDaoAll = userDao.findByname(username);
        if (userDaoAll !=null && userDaoAll.size() >= 1 ){
            return true;
        }
           return false;
         }

    @Override
    public void saveUser(User user) {
        System.out.println("业务层，注册用户...");
        userDao.saveUser(user);

    }
}

