package com.gaoqing.gaoqingblog.dao;

import com.gaoqing.gaoqingblog.pojo.Blog;
import com.gaoqing.gaoqingblog.pojo.TypePassing;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface TagsMapper {
    //检索博客描述，并且显示在首页
    List<Blog> getTagList(int id);

    //博客数统计
    Integer getTagTotal(int id);

    // 按条件检索出所有的使用到的分类与他的使用次数
    List<TypePassing>  getTagCollection();
}

