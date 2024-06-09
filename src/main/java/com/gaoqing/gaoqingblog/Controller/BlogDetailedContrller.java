package com.gaoqing.gaoqingblog.Controller;


import com.gaoqing.gaoqingblog.pojo.Blog;
import com.gaoqing.gaoqingblog.service.imp.BlogDetailedServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class BlogDetailedContrller {

    @Autowired
    BlogDetailedServiceImpl blogDetailedService;

    //去详细
    @RequestMapping("/blog/{id}")
    public String blog(Model model, @PathVariable int id){
        //按条件检索博客内容
        Blog blogDetailed = blogDetailedService.GetBlog(id);

        //分页以及博客内容显示
        model.addAttribute("blogPageInfo",blogDetailed);
        return "blog";
    }
}
