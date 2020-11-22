package cn.yuan.blog.service.impl;

import cn.yuan.blog.dao.TagMapper;
import cn.yuan.blog.domain.Tag;
import cn.yuan.blog.service.TagService;
import cn.yuan.common.domain.QueryRequest;
import cn.yuan.common.service.impl.BaseService;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service("tagService")
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
public class TagServiceImpl extends BaseService<Tag> implements TagService {

	private Logger log = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private TagMapper tagMapper;

	@Override
	public List<Tag> findAllTags(Tag tag, QueryRequest request) {
		try {
			Example example = new Example(Tag.class);
			if (StringUtils.isNotBlank(tag.getTagName())) {
				example.createCriteria().andCondition("tag_name=", tag.getTagName());
			}
			if (StringUtils.isNotBlank(tag.getTagCode())) {
				example.createCriteria().andCondition("tag_code=", tag.getTagCode());
			}
			example.setOrderByClause("id");
			return this.selectByExample(example);
		} catch (Exception e) {
			log.error("获取标签列表失败", e);
			return new ArrayList<>();
		}
	}

	@Override
	public Tag findById(Long id) {
	  return this.selectByKey(id);
	}

	@Override
	public void addTag(Tag tag) {
		this.save(tag);
	}

	@Override
	public void deleteTags(String ids) {
		List<String> list = Arrays.asList(ids.split(","));
		this.batchDelete(list, "id", Tag.class);
	}

	@Override
	public void updateTag(Tag tag) {
		this.updateNotNull(tag);
	}
}
