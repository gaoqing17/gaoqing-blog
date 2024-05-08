package com.gaoqing.gaoqingblog.service;

import com.gaoqing.gaoqingblog.dao.IndexMapper;
import com.gaoqing.gaoqingblog.pojo.Blog;
import com.gaoqing.gaoqingblog.service.IndexServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class IndexService implements IndexServiceImpl {

    @Autowired
    IndexMapper indexMapper;
    //检索博客描述，并且显示在首页
    @Override
    public List<Blog> bolgList() {
        List<Blog> blogs = indexMapper.bolgList();
        return blogs;
    }

    //博客数统计
    @Override
    public Integer  getBlogTotal() {
        Integer blogTotal = indexMapper.getBlogTotal();

        return blogTotal;
    }

    //检索出所有的使用到的标签与他的使用次数
    @Override
    public List<Map<Object, Object>> TagList() {
        List<Map<Object, Object>> maps = indexMapper.TagList();
        return maps;
    }

    //分类统计
    @Override
    public List<Map<Object, Object>> getTypeCcount() {
        List<Map<Object, Object>> blogTotal = indexMapper.getTypeCcount();
        return blogTotal;
    }


    //检索最新推荐
    @Override
    public List<Blog> getRecommend() {
        List<Blog> recommend = indexMapper.getRecommend();
        return recommend;
    }


}
