package cn.yuan.blog.service;

import cn.yuan.blog.domain.YnItems;
import cn.yuan.common.domain.QueryRequest;
import cn.yuan.common.service.IService;
import java.util.List;

public interface YnItemsService extends IService<YnItems> {

	List<YnItems> findAllYnItems(YnItems items, QueryRequest request);

	YnItems findById(Long id);

	void addYnItems(YnItems items);

	void deleteYnItems(String ids);

	void updateYnItems(YnItems items);

}
