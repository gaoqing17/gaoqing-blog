package com.gaoqing.gaoqingblog.service;

import com.gaoqing.gaoqingblog.pojo.Blog;

public interface BlogDetailedServiceImpl {
    //查询博客内容
    Blog GetBlog(int id);
}
