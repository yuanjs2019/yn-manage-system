package cn.yuan.blog.service;

import cn.yuan.blog.domain.Classify;
import cn.yuan.common.domain.QueryRequest;
import cn.yuan.common.service.IService;
import java.util.List;

public interface ClassifyService extends IService<Classify> {

	List<Classify> findAllClassifys(Classify classify, QueryRequest request);

	Classify findById(Long id);

	void addClassify(Classify classify);

	void deleteClassifys(String ids);

	void updateClassify(Classify classify);

}
