package com.gaoqing.gaoqingblog.Controller;


import com.gaoqing.gaoqingblog.pojo.Comment;
import com.gaoqing.gaoqingblog.service.CommentsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

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
//    @PostMapping("/comment/{blogId}/{id}/delete")
//    @ResponseBody
//    public Map<String, Object> getDeteleUP(@PathVariable int blogId, @PathVariable int id){
//        Map<String, Object> response = new HashMap<>();
//        boolean deteleUP = commentsService1.getDeteleUP(blogId, id, 0);
//       if (deteleUP){
//           response.put("success", true);
//           response.put("message", "Data deleted successfully");
//       }else {
//           response.put("success", false);
//           response.put("message", "Data not found");
//       }
//        return response;
//
//    }
}
