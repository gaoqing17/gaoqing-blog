package com.gaoqing.gaoqingblog.pojo;

public class QueryCriteria {
    private Long tagId;
    private Long typeId;

    public QueryCriteria() {
    }

    public QueryCriteria(Long tagId, Long typeId) {
        this.tagId = tagId;
        this.typeId = typeId;
    }

    public Long getTagId() {
        return tagId;
    }

    public void setTagId(Long tagId) {
        this.tagId = tagId;
    }

    public Long getTypeId() {
        return typeId;
    }

    public void setTypeId(Long typeId) {
        this.typeId = typeId;
    }

    @Override
    public String toString() {
        return "QueryCriteria{" +
                "tagId=" + tagId +
                ", typeId=" + typeId +
                '}';
    }
}



