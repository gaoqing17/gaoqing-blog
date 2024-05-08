package com.gaoqing.gaoqingblog.service;

import com.gaoqing.gaoqingblog.pojo.Comment;

import java.util.List;

public interface CommentsServiceIpml {

    //新增评论
    boolean SaveComment();

    //查询评论
    List<Comment> getComment(int id);
}
