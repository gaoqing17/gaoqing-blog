package com.gaoqing.gaoqingblog.service.admin;

import com.gaoqing.gaoqingblog.dao.admin.TypeDaoMapper;
import com.gaoqing.gaoqingblog.pojo.Type;
import com.gaoqing.gaoqingblog.service.admin.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TypeServiceImpl implements TypeService {

    @Autowired
    TypeDaoMapper typeDaoMapper;

    @Override
    public boolean typeInsert(Type type) {
        boolean typeInsert = typeDaoMapper.typeInsert(type);
        return typeInsert;
    }

    @Override
    public Type typeSelect(String name) {
        Type type = typeDaoMapper.typeSelect(name);
        return type;
    }

    @Override
    public List<Type> typeIFSelect() {
        List<Type> type = typeDaoMapper.typeIFSelect();
        return type;
    }

    @Override
    public boolean typeDelete(Long id) {
        boolean Delete= typeDaoMapper.typeDelete(id);
        return Delete;
    }

    @Override
    public boolean typeUpdate(Type type) {
        boolean Update = typeDaoMapper.typeUpdate(type);
        return Update;
    }

    @Override
    public Type SelectById(Long id) {
        Type type = typeDaoMapper.SelectById(id);
        return type;
    }


}
