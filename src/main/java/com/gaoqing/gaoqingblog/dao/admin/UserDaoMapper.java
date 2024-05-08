package com.gaoqing.gaoqingblog.dao.admin;

import com.gaoqing.gaoqingblog.pojo.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface UserDaoMapper {
    User selectDao(@Param("username")  String username);
}
