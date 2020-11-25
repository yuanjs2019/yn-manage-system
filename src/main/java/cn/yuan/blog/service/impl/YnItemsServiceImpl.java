package cn.yuan.blog.service.impl;

import cn.yuan.blog.dao.YnItemsMapper;
import cn.yuan.blog.domain.Tag;
import cn.yuan.blog.domain.YnItems;
import cn.yuan.blog.service.YnItemsService;
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

@Service("ynItemsService")
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
public class YnItemsServiceImpl extends BaseService<YnItems> implements YnItemsService {

	private Logger log = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private YnItemsMapper ynItemsMapper;

	@Override
	public List<YnItems> findAllYnItems(YnItems items, QueryRequest request) {
		try {
			Example example = new Example(YnItems.class);
			if (StringUtils.isNotBlank(items.getItemName())) {
				example.createCriteria().andCondition("tag_name=", items.getItemName());
			}
			if (StringUtils.isNotBlank(items.getItemClassfyName())) {
				example.createCriteria().andCondition("tag_code=", items.getItemClassfyName());
			}
			example.setOrderByClause("id");
			return this.selectByExample(example);
		} catch (Exception e) {
			log.error("获取项目列表失败", e);
			return new ArrayList<>();
		}
	}

	@Override
	public YnItems findById(Long id) {
		return this.selectByKey(id);
	}

	@Override
	public void addYnItems(YnItems items) {
		this.save(items);
	}

	@Override
	public void deleteYnItems(String ids) {
		List<String> list = Arrays.asList(ids.split(","));
		this.batchDelete(list, "id", YnItems.class);
	}

	@Override
	public void updateYnItems(YnItems items) {
		this.updateNotNull(items);
	}
}
