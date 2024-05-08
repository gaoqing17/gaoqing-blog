package com.gaoqing.gaoqingblog.Controller.admin;

import com.gaoqing.gaoqingblog.pojo.Type;
import com.gaoqing.gaoqingblog.service.admin.TypeService;
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
public class TypeController {
    @Autowired
    TypeService typeService;

    //查询所有标签
    @RequestMapping("/typeList")
    public String Type(Model model,
                       @RequestParam(defaultValue = "0",value = "pageNum") Integer pageNum){
        String orderBy = "id desc";
        PageHelper.startPage(pageNum,5,orderBy);
        List<Type> typelist = typeService.typeIFSelect();
        PageInfo<Type> pageInfo = new PageInfo<Type>(typelist);
        model.addAttribute("typelist",pageInfo);
        return "admin/types";
    }


    //删除标签
    @RequestMapping("/typeDelete/{id}")
    public String typeDelete(@PathVariable("id")Long id,Model model){
        boolean delete = typeService.typeDelete(id);
        model.addAttribute("message","标签删除成功");
        return "redirect:/admin/typeList";
    }

    //去修改标签名字
    @RequestMapping("/type-update/{id}/update")
    public String ToUpdate(@PathVariable Long id ,Model model){
        model.addAttribute("type", typeService.SelectById(id));
        return "admin/type-input";
    }

    //验证修改信息
    @RequestMapping("/update/{id}")
    public String TypeUpdete(@Valid Type type, RedirectAttributes attributes){
        Type type1 = typeService.typeSelect(type.getName());
        if (type1 != null){
            attributes.addFlashAttribute("message","标签已存在");
            return "redirect:/admin/type-input";
        }else if (type.getName().isEmpty()) {
            attributes.addFlashAttribute("message","内容为空");
            return "redirect:/admin/type-input";
        }else {
            boolean b = typeService.typeUpdate(type);
            return "redirect:/admin/typeList";
        }
    }

    //去添加标签页面
    @RequestMapping("/type-input")
    public String Type(Model model){
        model.addAttribute("type", new Type());
        return "admin/type-input";
    }

    //验证新的标签
    @GetMapping("/insert")
    public String TypeInser(@RequestParam String name, RedirectAttributes attributes){
        Type type1 = typeService.typeSelect(name);
        System.out.println(type1+" ------------------------");
        if (type1 != null ){
            attributes.addFlashAttribute("message","标签已存在");
            return "redirect:/admin/type-input";
        }else if (name.isEmpty()) {
            attributes.addFlashAttribute("message","内容为空");
            return "redirect:/admin/type-input";
        }else{
            Type type2 = new Type();
            type2.setName(name);
            boolean b = typeService.typeInsert(type2);
            return "redirect:/admin/typeList";
        }
    }
}
