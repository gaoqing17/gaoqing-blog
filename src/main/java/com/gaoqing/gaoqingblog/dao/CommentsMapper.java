package com.gaoqing.gaoqingblog.dao;

import com.gaoqing.gaoqingblog.pojo.Comment;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface CommentsMapper {
    //新增评论
    boolean SaveComment();

    //查询评论
    List<Comment> getComment(int id);
}
