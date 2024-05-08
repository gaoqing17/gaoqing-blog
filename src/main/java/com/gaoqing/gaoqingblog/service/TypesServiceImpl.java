package com.gaoqing.gaoqingblog.service;

import com.gaoqing.gaoqingblog.dao.TypesMapper;
import com.gaoqing.gaoqingblog.pojo.Blog;
import com.gaoqing.gaoqingblog.pojo.TypePassing;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TypesServiceImpl implements TypesService {

    @Autowired
    TypesMapper typeMapper;

    @Override
    public List<Blog> getTypeList(int id) {
        List<Blog> typeList = typeMapper.getTypeList(id);
        return typeList;
    }

    @Override
    public Integer getTypeTotal(int id) {
        Integer typeTotal = typeMapper.getTypeTotal(id);
        return typeTotal;
    }

    @Override
    public List<TypePassing> getTypeCollection() {
        List<TypePassing> typeCollection = typeMapper.getTypeCollection();
        return typeCollection;
    }
}



