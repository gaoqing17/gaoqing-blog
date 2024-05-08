package com.gaoqing.gaoqingblog.service.admin;
import com.gaoqing.gaoqingblog.dao.admin.UserDaoMapper;
import com.gaoqing.gaoqingblog.pojo.User;
import com.gaoqing.gaoqingblog.service.admin.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDaoMapper userDao;

    @Override
    public User selectDao(String userName ) {
        User user = userDao.selectDao(userName);
        return user;
    }
}
