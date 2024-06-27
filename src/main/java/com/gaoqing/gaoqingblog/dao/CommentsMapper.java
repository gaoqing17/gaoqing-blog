package com.gaoqing.gaoqingblog.dao;

import com.gaoqing.gaoqingblog.pojo.Comment;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface CommentsMapper {
    //新增评论
    boolean SaveComment();

    //查询父级评论
    List<Comment> findByBlogIdParentIdNull(@Param("blogId") int blogId);

    // 查询一级回复
    List<Comment> findByBlogIdParentIdNotNull(@Param("blogId") int blogId, @Param("id") int CommentId);

    // 根据父评论id查询留言信息
    Comment getEmailByParentId(int id, int blogId);

    // 删除评论
    boolean getDeteleUP(@Param("blogId") int blogId, @Param("id") int id, @Param("parentCommentId") int parentCommentId);


}
