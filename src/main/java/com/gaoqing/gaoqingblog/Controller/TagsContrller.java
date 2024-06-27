package com.gaoqing.gaoqingblog.Controller;

import com.gaoqing.gaoqingblog.pojo.Blog;
import com.gaoqing.gaoqingblog.pojo.TypePassing;
import com.gaoqing.gaoqingblog.service.TagsService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class TagsContrller {
    @Autowired
    TagsService tagsService;

    //去分类
    @RequestMapping("/tags/{id}")
    public String types(Model model,
                        @RequestParam(defaultValue = "0", value = "pageNum")
                                Integer pageNum,
                        @PathVariable int id) {

        //获取分类的全部种类和数量
        List<TypePassing> tagsCollection = tagsService.getTagCollection();
        //从导航栏分类进入到分类页面
        if (id == -1) {
            if (!tagsCollection.isEmpty()) {
                id = tagsCollection.get(0).getId();
            }
        }
        //获取分类后的博客数
        Integer tagTotal = tagsService.getTagTotal(id);

        PageHelper.startPage(pageNum,3);
        //按条件检索博客内容
        List<Blog> tagList = tagsService.getTagList(id);

        //分页以及博客内容显示

        PageInfo<Blog> blogPageInfo = new PageInfo<Blog>(tagList);

        //取分类后的博客数
        model.addAttribute("tagTotal",tagTotal);

        //分页以及博客内容显示
        model.addAttribute("blogPageInfo",blogPageInfo);

        //获取分类的全部种类和数量
        model.addAttribute("tagCcount",tagsCollection);

        //此次显示的内容的uid
        model.addAttribute("activeTagId",id);
        return "tags";
    }


}
