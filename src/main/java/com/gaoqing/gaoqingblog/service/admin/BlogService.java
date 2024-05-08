package com.gaoqing.gaoqingblog.service.admin;

import com.gaoqing.gaoqingblog.pojo.Blog;

import java.util.List;

public interface BlogService {
    //按条件查找博客信息
    List<Blog> ListBlogAnd(long typeId,long tagId);

    //查询所有博客信息
    List<Blog> ListBlog();

    //根据id删除博客信息
    boolean DeleteBlog(Long id);

    //根据标签查询博客
    List<Blog> ListBlogTitle(String title,String name);

    //修改博客内容
    boolean UpdateBlog(Blog blog);

    //添加新的博客内容
    boolean InsertBlog(Blog blog);

    //根据id查询博客
    Blog SlectBolgId(Long id);
}
