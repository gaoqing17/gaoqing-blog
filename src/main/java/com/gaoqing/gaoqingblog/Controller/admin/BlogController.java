package com.gaoqing.gaoqingblog.Controller.admin;

import com.gaoqing.gaoqingblog.pojo.Blog;
import com.gaoqing.gaoqingblog.pojo.QueryCriteria;
import com.gaoqing.gaoqingblog.pojo.Tag;
import com.gaoqing.gaoqingblog.pojo.Type;
import com.gaoqing.gaoqingblog.service.admin.BlogService;
import com.gaoqing.gaoqingblog.service.admin.TagService;
import com.gaoqing.gaoqingblog.service.admin.TypeService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/admin")
public class BlogController {

    @Autowired
    BlogService blogService;

    @Autowired
    TypeService typeService;

    @Autowired
    TagService tagService;


    //分页显示
    @RequestMapping("/blogs/blogidlist")
    public String ListBlogAnd(Model model,
                              QueryCriteria blog,
                         @RequestParam(defaultValue = "0",value = "pageNum") Integer pageNum){
        String orderBy = "t.id desc";
        System.out.println(blog);
        Integer typeId = blog.getTypeId().intValue();
        Integer tagId = blog.getTagId().intValue();
        PageHelper.startPage(pageNum,5,orderBy);
        List<Blog> blogs = blogService.ListBlogAnd(typeId,tagId);
        PageInfo<Blog> blogPageInfo = new PageInfo<Blog>(blogs);
        model.addAttribute("types",typeService.typeIFSelect());
        model.addAttribute("tags",tagService.TagIFSelect());
        model.addAttribute("blogPageInfo",blogPageInfo);
        return "admin/blogs :: blogList";
    }

    //分页显示
    @RequestMapping("/blog")
    public String ToBlog(Model model,
                         @RequestParam(defaultValue = "0",value = "pageNum") Integer pageNum){
        String orderBy = "t.id desc";
        PageHelper.startPage(pageNum,5,orderBy);
        List<Blog> blogs = blogService.ListBlog();
        PageInfo<Blog> blogPageInfo = new PageInfo<Blog>(blogs);
        model.addAttribute("types",typeService.typeIFSelect());
        model.addAttribute("tags",tagService.TagIFSelect());
        model.addAttribute("blogPageInfo",blogPageInfo);
        return "admin/blogs";
    }


    //去新增画面
    @RequestMapping("/blogs/input")
    public String ToBlogUp(Model model){
        model.addAttribute("types",typeService.typeIFSelect());
        model.addAttribute("tags",tagService.TagIFSelect());
        Blog blog = new Blog();
        model.addAttribute("blogs",blog);
        return "admin/blogs-input";
    }

    //验证新增信息
    @RequestMapping("/blogs/ToInput")
    public String InputBlog(Blog blog, RedirectAttributes model, HttpSession session){
        //存入分类信息
        blog.setType(typeService.SelectById(blog.getType().getId()));

        //设置用户id
        long a = 1;
        blog.setUserid(a);
        blog.setViews(0);
        if (blog.getCreateTime() == null){
            Date date = new Date();
            blog.setCreateTime(date);
            blog.setUpdateTime(date);
        }else {
            Date date = new Date();
            blog.setUpdateTime(date);
        }
        boolean b = blogService.InsertBlog(blog);

        if (b){
            model.addFlashAttribute("message", "新增失败");
        }else{
            model.addFlashAttribute("message", "新增成功");
        }
        return "redirect:/admin/blog";
    }

    //去博客修改画面
    @RequestMapping("/blogs/{id}/input")
    public String ToUpdate(@PathVariable Long id ,Model model){
        Blog blog = blogService.SlectBolgId(id);
        blog.setId(id);
        List<Type> allType = typeService.typeIFSelect();
        List<Tag> tags = tagService.TagIFSelect();
        model.addAttribute("blogs", blog);
        model.addAttribute("types", allType);
        model.addAttribute("tags", tags);
        return "admin/blogs-input";
    }

    //验证修改信息
    @RequestMapping("/blogs/ToInput/{id}")
    public String UpdateBlog(Blog blog, RedirectAttributes model){
        //博客文章内容更新
        Date date = new Date();
        System.out.println("---------------------------------------------------");

        System.out.println(blog.getCommentabled());

        System.out.println("---------------------------------------------------");
        blog.setUpdateTime(date);
        boolean b = blogService.UpdateBlog(blog);
        if (b){
            model.addFlashAttribute("message", "修改失败");
        }else{
            model.addFlashAttribute("message", "修改成功");
        }
        return "redirect:/admin/blog";
    }

    //按id删除文章信息
    @RequestMapping("/deleteBlog/{id}")
    public String deleteBlog(@PathVariable("id")Long id, Model model){
        boolean b = blogService.DeleteBlog(id);
        model.addAttribute("message","文章删除成功");
        return "redirect:/admin/blog";
    }


    //按id删除文章信息
    @RequestMapping("/blogs/logTitle")
    public String ListBlogTitle(@RequestParam String title,@RequestParam String name, Model model){
        List<Blog> blogs = blogService.ListBlogTitle(title, name);
        model.addAttribute("blogs",blogs);
        model.addAttribute("message","文章删除成功");
        return "redirect:/admin/blog";
    }

}
