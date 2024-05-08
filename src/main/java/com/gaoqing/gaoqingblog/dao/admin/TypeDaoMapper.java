package com.gaoqing.gaoqingblog.dao.admin;

import com.gaoqing.gaoqingblog.pojo.Type;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface TypeDaoMapper {
    //保存新的标签
    boolean typeInsert(Type type);

    //检索标签是否存在
    Type typeSelect(String name);

    //检索所有标签
    List<Type> typeIFSelect();

    //通过id去检索标签
    Type SelectById(Long id);

    //删除标签
    boolean typeDelete(Long id);

    //修改标签
    boolean typeUpdate(Type type);
}
