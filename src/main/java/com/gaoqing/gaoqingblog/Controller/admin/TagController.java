package com.gaoqing.gaoqingblog.Controller.admin;

import com.gaoqing.gaoqingblog.pojo.Tag;
import com.gaoqing.gaoqingblog.service.admin.TagService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/admin")
public class TagController {
    @Autowired
    TagService tagService;

    //查询所有标签
    @RequestMapping("/tagList")
    public String Tag(Model model,
                       @RequestParam(defaultValue = "0",value = "pageNum") Integer pageNum){
        String orderBy = "id desc";
        PageHelper.startPage(pageNum,5,orderBy);
        List<Tag> taglist = tagService.TagIFSelect();
        PageInfo<Tag> pageInfo = new PageInfo<Tag>(taglist);
        model.addAttribute("taglist",pageInfo);
        return "admin/tags";
    }


    //删除标签
    @RequestMapping("/tagDelete/{id}")
    public String tagDelete(@PathVariable("id")Long id,Model model){
        boolean delete = tagService.TagDelete(id);
        model.addAttribute("message","标签删除成功");
        return "redirect:/admin/tagList";
    }

    //去修改标签名字
    @RequestMapping("/tag-update/{id}/update")
    public String ToUpdate(@PathVariable Long id ,Model model){
        model.addAttribute("tag", tagService.SelectById(id));
        return "admin/tag-input";
    }

    //验证修改信息
    @RequestMapping("/tagupdate/{id}")
    public String TagUpdete(@Valid Tag tag, RedirectAttributes attributes){
        Tag tag1 = tagService.TagSelect(tag.getName());
        if (tag1 != null){
            attributes.addFlashAttribute("message","标签已存在");
            return "redirect:/admin/tag-input";
        }else if (tag.getName().isEmpty()) {
            attributes.addFlashAttribute("message","内容为空");
            return "redirect:/admin/tag-input";
        }else {
            boolean b = tagService.TagUpdate(tag);
            return "redirect:/admin/tagList";
        }
    }

    //去添加标签页面
    @RequestMapping("/tag-input")
    public String Tag(Model model){
        model.addAttribute("tag", new Tag());
        return "admin/tag-input";
    }

    //验证新的标签
    @PostMapping("/taginsert")
    public String TagInser(@RequestParam String name, RedirectAttributes attributes){
        Tag tag1 = tagService.TagSelect(name);
        System.out.println(tag1+" ------------------------");
        if (tag1 != null ){
            attributes.addFlashAttribute("message","标签已存在");
            return "redirect:/admin/tag-input";
        }else if (name.isEmpty()) {
            attributes.addFlashAttribute("message","内容为空");
            return "redirect:/admin/tag-input";
        }else{
            Tag tag2 = new Tag();
            tag2.setName(name);
            boolean b = tagService.TagInsert(tag2);
            return "redirect:/admin/tagList";
        }
    }
}
