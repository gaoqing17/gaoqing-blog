package com.gaoqing.gaoqingblog.Controller;


import com.gaoqing.gaoqingblog.pojo.Comment;
import com.gaoqing.gaoqingblog.service.CommentsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Controller
public class CommentsContrller {

    @Autowired
    CommentsService commentsService1;

    //查询博客评论
    @GetMapping("/comments/{id}")
    public String getComments(Model model, @PathVariable int id) {
        System.out.println(id + "-------------------------");
        List<Comment> comments = commentsService1.listCommentByBlogId(id);
        System.out.println(comments);
        model.addAttribute("comments", comments);
        return "blog :: commentList";
    }

    //删除评论
    @DeleteMapping("/comment/{id}/{blogId}/delete")
    @ResponseBody
    public String getDeteleUP(@PathVariable int blogId, @PathVariable int id, Model model, HttpServletRequest request, HttpServletResponse response) {
        boolean deteleUP = commentsService1.getDeteleUP(id, blogId, 0);
        if (deteleUP) {
            // 设置响应类型和字符编码
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            return "blog :: commentList";
        } else {
            model.addAttribute("mag", "不知道反正错了");
            return "blog :: commentList";
        }
    }
}
