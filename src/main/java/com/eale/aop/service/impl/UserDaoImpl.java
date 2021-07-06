package com.eale.aop.service.impl;

import com.eale.aop.service.UserDao;
import org.springframework.stereotype.Repository;

/**
 * @Author HLD
 * @Date 2021/7/6 0006
 **/
@Repository
public class UserDaoImpl implements UserDao {
    @Override
    public int addUser() {
        System.out.println("add user ......");
        return 6666;
    }

    @Override
    public void updateUser() {
        System.out.println("update user ......");
    }

    @Override
    public void deleteUser() {
        System.out.println("delete user ......");
    }

    @Override
    public void findUser() {
        System.out.println("find user ......");
    }
}
