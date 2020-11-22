package cn.yuan.blog.service.impl;

import cn.yuan.blog.dao.SeriesMapper;
import cn.yuan.blog.domain.Series;
import cn.yuan.blog.domain.Tag;
import cn.yuan.blog.service.SeriesService;
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

@Service("seriesService")
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
public class SeriesServiceImpl extends BaseService<Series> implements SeriesService {
    private Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private SeriesMapper seriesMapper;

    @Override
    public List<Series> findAllSeries(Series series, QueryRequest request) {
        try {
            Example example = new Example(Series.class);
            if (StringUtils.isNotBlank(series.getSericsName())) {
                example.createCriteria().andCondition("serics_name=", series.getSericsName());
            }
            if (StringUtils.isNotBlank(series.getSericsCode())) {
                example.createCriteria().andCondition("serics_code=", series.getSericsCode());
            }
            example.setOrderByClause("id");
            return this.selectByExample(example);
        } catch (Exception e) {
            log.error("获取系列列表失败", e);
            return new ArrayList<>();
        }
    }

    @Override
    public Series findById(Long id) {
        return this.selectByKey(id);
    }

    @Override
    public void addSeries(Series series) {
        this.save(series);
    }

    @Override
    public void deleteSeries(String ids) {
        List<String> list = Arrays.asList(ids.split(","));
        this.batchDelete(list, "id", Series.class);
    }

    @Override
    public void updateSeries(Series series) {
        this.updateNotNull(series);
    }
}
