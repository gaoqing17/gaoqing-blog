package com.gaoqing.gaoqingblog.service.imp;

import com.gaoqing.gaoqingblog.dao.TagsMapper;
import com.gaoqing.gaoqingblog.pojo.Blog;
import com.gaoqing.gaoqingblog.pojo.TypePassing;
import com.gaoqing.gaoqingblog.service.TagsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TagsServiceImpl implements TagsService {

    @Autowired
    TagsMapper tagsMapper;

    @Override
    public List<Blog> getTagList(int id) {
        List<Blog> TagList = tagsMapper.getTagList(id);
        return TagList;
    }

    @Override
    public Integer getTagTotal(int id) {
        Integer TagTotal = tagsMapper.getTagTotal(id);
        return TagTotal;
    }

    @Override
    public List<TypePassing> getTagCollection() {
        List<TypePassing> TagCollection = tagsMapper.getTagCollection();
        return TagCollection;
    }
}



