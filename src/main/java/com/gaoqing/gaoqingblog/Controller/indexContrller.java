package com.gaoqing.gaoqingblog.Controller;

import com.gaoqing.gaoqingblog.service.IndexService;
import com.gaoqing.gaoqingblog.pojo.Blog;
import com.gaoqing.gaoqingblog.service.admin.TypeService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Controller
public class indexContrller {
    @Autowired
    IndexService indexService;

    @Autowired
    TypeService typeService;

//    去首页
    @RequestMapping("/index")
    public String indexg(Model model,
                         @RequestParam(defaultValue = "0",value = "pageNum") Integer pageNum){
        String orderBy = "t.id desc";
        PageHelper.startPage(pageNum,4,orderBy);
        List<Blog> blogs = indexService.bolgList();
        int blogTotal = indexService.getBlogTotal();
        //查询最新推荐
        List<Blog> recommend = indexService.getRecommend();

        //分页以及博客内容显示
        PageInfo<Blog> blogPageInfo = new PageInfo<Blog>(blogs);
        //获取博客总数
        model.addAttribute("BlogTotal",blogTotal);
        //查询最新推荐
        model.addAttribute("getRecommend",recommend);
        //分页以及博客内容显示
        model.addAttribute("blogPageInfo",blogPageInfo);
        //拿到使用到的分类数量
        model.addAttribute("typeCcount",indexService.getTypeCcount());
        //拿到使用到的标签数量
        model.addAttribute("tagCcount",indexService.TagList());

        return "index";
    }


    //去关于我
    @RequestMapping("/about")
    public String about(){
        System.out.println("-----------about-----------");
        return "about";
    }


    //去归档
    @RequestMapping("/archives")
    public String archives(Model model){
        int blogTotal = indexService.getBlogTotal();
        List<Blog> recommend = indexService.getRecommend();
        //获取博客总数
        model.addAttribute("BlogTotal",blogTotal);
        //获取年份最近的四篇文章
        model.addAttribute("Archives",recommend);
        return "archives";
    }



}
