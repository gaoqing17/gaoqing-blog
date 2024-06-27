package com.gaoqing.gaoqingblog.pojo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class Comment {
    //评论id
    private int id;
    //评论的用户名字
    private String nickname;
    //评论的用户邮箱
    private String email;
    //评论内容
    private String content;
    //博客主人头像
    private String avatar;
    //评论时间
    private Date createTime;
    //评论的博客
    private int blogId;
    //是否开启了评论
    private boolean adminComment;
    //回复评论
    private List<Comment> replyComments = new ArrayList<>();
    //回复评论
    private Comment parentComment;

    //回复评论ID
    private int parentCommentId;

    //评论状态 1为正常显示 0 为删除
    private int EthicalJudgment;

    public int getParentCommentId() {
        return parentCommentId;
    }

    public void setParentCommentId(int parentCommentId) {
        this.parentCommentId = parentCommentId;
    }

    public int getEthicalJudgment() {
        return EthicalJudgment;
    }

    public void setEthicalJudgment(int ethicealJudgment) {
        EthicalJudgment = ethicealJudgment;
    }

    public Comment() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public int getBlogId() {
        return blogId;
    }

    public void setBlogId(int blogId) {
        this.blogId = blogId;
    }

    public List<Comment> getReplyComments() {
        return replyComments;
    }

    public void setReplyComments(List<Comment> replyComments) {
        this.replyComments = replyComments;
    }

    public Comment getParentComment() {
        return parentComment;
    }

    public void setParentComment(Comment parentComment) {
        this.parentComment = parentComment;
    }

    public boolean isAdminComment() {
        return adminComment;
    }

    public void setAdminComment(boolean adminComment) {
        this.adminComment = adminComment;
    }

    @Override
    public String toString() {
        return "Comment{" +
                "id=" + id +
                ", nickname='" + nickname + '\'' +
                ", email='" + email + '\'' +
                ", content='" + content + '\'' +
                ", avatar='" + avatar + '\'' +
                ", createTime=" + createTime +
                ", blogId=" + blogId +
                ", adminComment=" + adminComment +
                ", replyComments=" + replyComments +
                ", parentComment=" + parentComment +
                ", parentCommentId=" + parentCommentId +
                ", EthicalJudgment=" + EthicalJudgment +
                '}';
    }
}
