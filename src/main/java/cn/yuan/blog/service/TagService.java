package cn.yuan.blog.service;

import cn.yuan.blog.domain.Tag;
import cn.yuan.common.domain.QueryRequest;
import cn.yuan.common.service.IService;

import java.util.List;

public interface TagService extends IService<Tag> {

	List<Tag> findAllTags(Tag tag, QueryRequest request);

	Tag findById(Long id);

	void addTag(Tag tag);

	void deleteTags(String ids);

	void updateTag(Tag tag);

}
