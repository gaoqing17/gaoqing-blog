package com.gaoqing.gaoqingblog.service.admin;

import com.gaoqing.gaoqingblog.pojo.Type;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface TypeService {

    //添加新的标签
    boolean typeInsert(Type type);

    //查询该标签是否存在
    Type typeSelect(String name);

    //检索所有标签
    List<Type> typeIFSelect();

    //删除标签
    boolean typeDelete(Long id);

    //修改标签
    boolean typeUpdate(Type type);

    //通过id去检索标签
    Type SelectById(Long id);

}
