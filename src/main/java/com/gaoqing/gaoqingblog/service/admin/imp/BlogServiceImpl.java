package com.gaoqing.gaoqingblog.service.admin.imp;

import com.gaoqing.gaoqingblog.dao.admin.BlogDaoMapper;
import com.gaoqing.gaoqingblog.pojo.Blog;
import com.gaoqing.gaoqingblog.service.admin.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BlogServiceImpl implements BlogService {

    @Autowired
    BlogDaoMapper blogDaoMapper;

    @Override
    public List<Blog> ListBlogAnd(long typeId, long tagId) {
        List<Blog> blogs = blogDaoMapper.ListBlogAnd(typeId, tagId);
        return blogs;
    }

    @Override
    public List<Blog> ListBlog() {
        List<Blog> blogs = blogDaoMapper.ListBlog();
        return blogs;
    }

    @Override
    public boolean DeleteBlog(Long id) {
        boolean b = blogDaoMapper.DeleteBlog(id);
        return b;
    }

    @Override
    public List<Blog> ListBlogTitle(String title,String name) {
        List<Blog> blogs = blogDaoMapper.ListBlogTitle(title,name);
        return blogs;
    }

    @Override
    public boolean UpdateBlog(Blog blog) {
        boolean b = blogDaoMapper.UpdateBlog(blog);
        return b;
    }

    @Override
    public boolean InsertBlog(Blog blog) {
        boolean b = blogDaoMapper.InsertBlog(blog);
        return b;
    }

    //根据id查询博客
    @Override
    public Blog SlectBolgId(Long id){
        Blog blog = blogDaoMapper.SlectBolgId(id);
        return blog;
    }
}
