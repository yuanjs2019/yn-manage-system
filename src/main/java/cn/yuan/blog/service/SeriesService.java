package cn.yuan.blog.service;

import cn.yuan.blog.domain.Series;
import cn.yuan.common.domain.QueryRequest;
import cn.yuan.common.service.IService;

import java.util.List;

public interface SeriesService extends IService<Series> {

	List<Series> findAllSeries(Series series, QueryRequest request);

	Series findById(Long id);

	void addSeries(Series series);

	void deleteSeries(String ids);

	void updateSeries(Series series);

}
