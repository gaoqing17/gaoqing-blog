package com.gaoqing.gaoqingblog.dao.admin;

import com.gaoqing.gaoqingblog.pojo.Tag;
import com.gaoqing.gaoqingblog.pojo.Type;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface TagDaoMapper {
    //保存新的标签
    boolean tagInsert(Tag type);

    //检索标签是否存在
    Tag tagSelect(String name);

    //检索所有标签
    List<Tag> tagIFSelect();

    //通过id去检索标签
    Tag SelectById(Long id);

    //删除标签
    boolean tagDelete(Long id);

    //修改标签
    boolean tagUpdate(Tag type);
}
