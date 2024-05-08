package com.gaoqing.gaoqingblog.service;

import com.gaoqing.gaoqingblog.config.MarkdownConfig;
import com.gaoqing.gaoqingblog.config.NotFoundException;
import com.gaoqing.gaoqingblog.dao.BlogDetailedMapper;
import com.gaoqing.gaoqingblog.pojo.Blog;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BlogDetailedService implements BlogDetailedServiceImpl {
    @Autowired
    BlogDetailedMapper blogDetailedMapper;

    @Override
    public Blog GetBlog(@Param("id") int id) {
        Blog blog = blogDetailedMapper.GetBlog(id);
        if (blog == null){
            throw new NotFoundException("该博客不存在");
        }
        Integer Views = blog.getViews()+1;

        blogDetailedMapper.AddViews(id,Views);
        String content = blog.getContent();
        blog.setContent(MarkdownConfig.markdownToHtmlExtrnsions(content));
        return blog;
    }
}
