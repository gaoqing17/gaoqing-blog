package com.gaoqing.gaoqingblog.service;

import com.gaoqing.gaoqingblog.pojo.Comment;

import java.util.List;

public interface CommentsService {

    //新增评论
    boolean SaveComment();

    //查询评论
    List<Comment> listCommentByBlogId(int id);

    // 删除评论
    boolean getDeteleUP(int blogId, int id, int EthicalJudgment);
}
