package cn.yuan.blog.dao;

import cn.yuan.blog.domain.BaseBlog;
import cn.yuan.blog.domain.Blog;
import cn.yuan.common.config.MyMapper;

import java.util.List;

public interface BlogMapper extends MyMapper<BaseBlog> {

    List<Blog> findAllBlogs(Blog blog);

    Blog findById(Long id);
}