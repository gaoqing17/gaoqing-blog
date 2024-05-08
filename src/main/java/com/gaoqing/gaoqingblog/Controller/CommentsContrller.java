package com.gaoqing.gaoqingblog.Controller;


import com.gaoqing.gaoqingblog.pojo.Blog;
import com.gaoqing.gaoqingblog.pojo.Comment;
import com.gaoqing.gaoqingblog.service.BlogDetailedService;
import com.gaoqing.gaoqingblog.service.CommentsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class CommentsContrller {

    @Autowired
    CommentsService commentsService;

    //查询博客评论
    @GetMapping("/comments/{id}")
    public String getComments(Model model, @PathVariable int id){
        System.out.println(id+"-------------------------");
        List<Comment> comments = commentsService.getComment(id);

        model.addAttribute("comments",comments);
        return "blog :: commentList";
    }

    //删除评论
    @GetMapping("/comment/{blogId}/{id}/delete")
    public String delete(@PathVariable Long blogId, @PathVariable int id, Comment comment, HttpSession session, Model model){
//        List<Comment> comment = commentsService.getComment(id);
//        model.addAttribute("comments", comment);
        return "blog";
    }
}
