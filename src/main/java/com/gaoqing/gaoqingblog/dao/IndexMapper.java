package com.gaoqing.gaoqingblog.dao;

import com.gaoqing.gaoqingblog.pojo.Blog;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Mapper
@Repository
public interface IndexMapper {
    //检索博客描述，并且显示在首页
    List<Blog> bolgList();

    //博客数统计
    Integer getBlogTotal();

    //检索出所有的使用到的标签与他的使用次数
    List<Map<Object,Object>> TagList();

    //检索出所有的使用到的分类 与他的使用次数
    List<Map<Object,Object>> getTypeCcount();

    //检索最新推荐
    List<Blog> getRecommend();

}

