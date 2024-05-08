package com.gaoqing.gaoqingblog.service;

import com.gaoqing.gaoqingblog.dao.CommentsMapper;
import com.gaoqing.gaoqingblog.pojo.Comment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentsService implements CommentsServiceIpml{

    @Autowired
    CommentsMapper commentsMapper;
    //削除
    @Override
    public boolean SaveComment() {
        boolean b = commentsMapper.SaveComment();
        return b;
    }
    //查询评论
    @Override
    public List<Comment> getComment(int id) {
        List<Comment> comments = commentsMapper.getComment(id);
//        for (Comment comment : comments) {
//            if (comment.getParentComment())
//        }
        return comments;
    }
}
