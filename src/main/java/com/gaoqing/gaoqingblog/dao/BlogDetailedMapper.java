package com.gaoqing.gaoqingblog.dao;

import com.gaoqing.gaoqingblog.pojo.Blog;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface BlogDetailedMapper {
    //查询博客内容
    Blog GetBlog(int id);

    //添加浏览次数
    boolean AddViews(int id,Integer views);

}
