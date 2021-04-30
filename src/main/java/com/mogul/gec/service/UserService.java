package com.mogul.gec.service;

import com.mogul.gec.pojo.UserInf;
import com.mogul.gec.utils.Page;
import com.mogul.gec.vo.UserVo;

public interface UserService {

    UserInf loginUser(String username);

    Page getUserList(UserVo userVo);

    void addUserInfo(UserInf userInf);

    void delete(long id);

    UserInf getUserInfo(long id);

    void updateUserInfo(UserInf userInf);
}
