package com.mogul.gec.dao;

import com.mogul.gec.pojo.AnnouncementInfo;
import com.mogul.gec.pojo.UserInf;
import com.mogul.gec.vo.UserVo;

import java.util.List;

public interface UserDao {

    UserInf loginUser(String username);

    List<UserInf> userByList(UserVo userVo);

    int count(UserVo userVo);

    int countUser(String username);

    void insert(UserInf userInf);

    void delete(long id);

    UserInf get(long id);

    void update(UserInf userInf);
}
