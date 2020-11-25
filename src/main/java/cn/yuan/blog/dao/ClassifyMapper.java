package cn.yuan.blog.dao;

import cn.yuan.blog.domain.Classify;
import cn.yuan.blog.domain.YnBlogClassify;
import cn.yuan.blog.domain.YnBlogTag;
import cn.yuan.common.config.MyMapper;

import java.util.List;

public interface ClassifyMapper extends MyMapper<Classify> {

    List<Classify> findClassifysByBlogId(Long blogId);

    Integer addBlogClassify(YnBlogClassify blogTag);

    Integer deleteBlogClassifyByBlogId(Long blogId);
}