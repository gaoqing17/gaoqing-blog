package com.gaoqing.gaoqingblog.service;

import com.gaoqing.gaoqingblog.pojo.Blog;

import java.util.List;
import java.util.Map;

public interface IndexService {
    //检索博客描述，并且显示在首页
    List<Blog> bolgList();

    //博客数统计
    Integer getBlogTotal();

    //检索出所有的使用到的标签与他的使用次数
    List<Map<Object, Object>> TagList();

    //分类统计
    List<Map<Object, Object>> getTypeCcount();

    //检索最新推荐
    List<Blog> getRecommend();
}
