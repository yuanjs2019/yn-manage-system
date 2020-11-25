package cn.yuan.blog.dao;

import cn.yuan.blog.domain.YnItems;
import cn.yuan.common.config.MyMapper;

import java.util.List;

public interface YnItemsMapper extends MyMapper<YnItems> {

    List<YnItems> findAllYnItems(YnItems items);

    YnItems findById(Long id);
}