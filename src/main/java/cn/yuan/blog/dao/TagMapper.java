package cn.yuan.blog.dao;

import cn.yuan.blog.domain.Tag;
import cn.yuan.blog.domain.YnBlogTag;
import cn.yuan.common.config.MyMapper;

import java.util.List;

public interface TagMapper extends MyMapper<Tag> {

    List<Tag> findTagsByBlogId(Long blogId);

    Integer addBlogTag(YnBlogTag blogTag);

    Integer deleteBlogTagByBlogId(Long blogId);
}