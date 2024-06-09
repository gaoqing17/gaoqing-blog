package com.gaoqing.gaoqingblog.service.admin.imp;

import com.gaoqing.gaoqingblog.dao.admin.TagDaoMapper;
import com.gaoqing.gaoqingblog.pojo.Tag;
import com.gaoqing.gaoqingblog.service.admin.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TagServiceImpl implements TagService {

    @Autowired
    TagDaoMapper tagDaoMapper;

    @Override
    public boolean TagInsert(Tag tag) {
        boolean TagInsert = tagDaoMapper.tagInsert(tag);
        return TagInsert;
    }

    @Override
    public Tag TagSelect(String name) {
        Tag Tag = tagDaoMapper.tagSelect(name);
        return Tag;
    }

    @Override
    public List<Tag> TagIFSelect() {
        List<Tag> tag = tagDaoMapper.tagIFSelect();
        return tag;
    }

    @Override
    public boolean TagDelete(Long id) {
        boolean Delete= tagDaoMapper.tagDelete(id);
        return Delete;
    }

    @Override
    public boolean TagUpdate(Tag tag) {
        boolean Update = tagDaoMapper.tagUpdate(tag);
        return Update;
    }

    @Override
    public Tag SelectById(Long id) {
        Tag tag = tagDaoMapper.SelectById(id);
        return tag;
    }


}
