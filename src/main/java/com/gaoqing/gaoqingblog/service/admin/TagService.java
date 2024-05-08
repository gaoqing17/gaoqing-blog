package com.gaoqing.gaoqingblog.service.admin;

import com.gaoqing.gaoqingblog.pojo.Tag;

import java.util.List;

public interface TagService {


    //添加新的标签
    boolean TagInsert(Tag Tag);

    //查询该标签是否存在
    Tag TagSelect(String name);

    //检索所有标签
    List<Tag> TagIFSelect();

    //删除标签
    boolean TagDelete(Long id);

    //修改标签
    boolean TagUpdate(Tag Tag);

    //通过id去检索标签
    Tag SelectById(Long id);

}
