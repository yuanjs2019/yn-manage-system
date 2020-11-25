package cn.yuan.blog.service;

import cn.yuan.blog.domain.BaseBlog;
import cn.yuan.blog.domain.Blog;
import cn.yuan.blog.domain.SaveBlog;
import cn.yuan.common.domain.QueryRequest;
import cn.yuan.common.service.IService;

import java.util.List;

public interface BlogService extends IService<BaseBlog> {

	List<Blog> findAllBlogs(Blog blog, QueryRequest request);

	Blog findById(Long id);

	void addBlog(SaveBlog blog);

	void deleteBlogs(String ids);

	void updateBlog(SaveBlog blog);

}
