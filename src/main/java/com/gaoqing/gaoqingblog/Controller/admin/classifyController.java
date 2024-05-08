package com.gaoqing.gaoqingblog.Controller.admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
public class classifyController {


    @RequestMapping("/types")
    public String ToCategorize(){

        return "admin/types";
    }
}
