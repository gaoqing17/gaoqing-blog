package com.gaoqing.gaoqingblog.service;

import com.gaoqing.gaoqingblog.pojo.Blog;
import com.gaoqing.gaoqingblog.pojo.TypePassing;

import java.util.List;


public interface TypesService {
    //检索博客描述，并且显示在首页
    List<Blog> getTypeList(int id);

    //博客数统计
    Integer getTypeTotal(int id);
    
    // 按条件检索出所有的使用到的分类与他的使用次数
    List<TypePassing> getTypeCollection();
}

