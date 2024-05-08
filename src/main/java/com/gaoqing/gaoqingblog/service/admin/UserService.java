package com.gaoqing.gaoqingblog.service.admin;

import com.gaoqing.gaoqingblog.pojo.User;

public interface UserService {

    User selectDao(String userName);
}
