package com.gaoqing.gaoqingblog.service.imp;

import com.gaoqing.gaoqingblog.dao.CommentsMapper;
import com.gaoqing.gaoqingblog.pojo.Comment;
import com.gaoqing.gaoqingblog.service.CommentsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CommentsServiceIpml implements CommentsService {

    @Autowired
    CommentsMapper commentsMapper;

    //存放迭代找出的所有子代的集合
    private List<Comment> tempReplys = new ArrayList<>();


    //削除
    @Override
    public boolean SaveComment() {
        boolean b = commentsMapper.SaveComment();
        return b;
    }

    //查询一级评论
    @Override
    public List<Comment> listCommentByBlogId(int blogId) {
        //查询评论
        List<Comment> comments = commentsMapper.findByBlogIdParentIdNull(blogId);
        for (Comment comment : comments) {
            //评论id
            int CommentId = comment.getId();
            // 查询一级回复,根据评论id查询
            List<Comment> childComments = commentsMapper.findByBlogIdParentIdNotNull(blogId, CommentId);
            //查询出子评论
            combineChildren(childComments);
            comment.setReplyComments(tempReplys);
            tempReplys = new ArrayList<>();
        }
        return comments;
    }

    /**
     * @Description: 查询出子评论
     * @Auther: ONESTAR
     * @Date: 10:43 2020/6/23
     * @Param: childComments：所有子评论
     * @Param: parentNickname1：父评论姓名
     * @Return:
     */
    private void combineChildren(List<Comment> childComments) {
        //判断是否有一级子评论
        if (childComments.size() > 0) {
            //循环找出子评论的id
            for (Comment childComment : childComments) {
                String nickname = childComment.getNickname();
                childComment.setNickname(nickname);
                tempReplys.add(childComment);
            }
        }
    }


    @Override
    public boolean getDeteleUP(int blogId, int id, int EthicalJudgment) {
        Comment comment = commentsMapper.getEmailByParentId(id, blogId);
        int ethicalJudgment = comment.getEthicalJudgment();
        boolean deteleUP = commentsMapper.getDeteleUP(blogId, id, ethicalJudgment);
        return deteleUP;
    }
}
