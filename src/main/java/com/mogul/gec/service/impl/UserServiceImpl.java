package com.mogul.gec.service.impl;

import com.mogul.gec.dao.UserDao;
import com.mogul.gec.dao.impl.UserDaoImpl;
import com.mogul.gec.pojo.UserInf;
import com.mogul.gec.service.UserService;
import com.mogul.gec.utils.Page;
import com.mogul.gec.vo.UserVo;

import java.util.List;

public class UserServiceImpl implements UserService {
    private UserDao userDao=new UserDaoImpl();


    @Override
    public Page getUserList(UserVo userVo) {
        int count = userDao.count(userVo);
        Page page=new Page(null,count,userVo.getSize(),userVo.getLimit());
        userVo.setLimit((userVo.getLimit()-1)*userVo.getSize());
        List<UserInf> users=userDao.userByList(userVo);
        page.setList(users);
        return page;
    }

    @Override
    public UserInf loginUser(String username) {
        UserInf user = userDao.loginUser(username);
        return user;
    }

    @Override
    public void addUserInfo(UserInf userInf) {
        userDao.insert(userInf);
    }

    @Override
    public void delete(long id) {
        userDao.delete(id);
    }

    @Override
    public UserInf getUserInfo(long id) {
        return userDao.get(id);
    }

    @Override
    public void updateUserInfo(UserInf userInf) {
        userDao.update(userInf);
    }
}
