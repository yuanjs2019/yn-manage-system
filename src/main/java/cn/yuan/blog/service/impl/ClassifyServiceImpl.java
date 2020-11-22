package cn.yuan.blog.service.impl;

import cn.yuan.blog.dao.ClassifyMapper;
import cn.yuan.blog.domain.Classify;
import cn.yuan.blog.domain.Tag;
import cn.yuan.blog.service.ClassifyService;
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

@Service("cassifyService")
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
public class ClassifyServiceImpl extends BaseService<Classify> implements ClassifyService {
    private Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private ClassifyMapper classifyMapper;

    @Override
    public List<Classify> findAllClassifys(Classify classify, QueryRequest request) {
        try {
            Example example = new Example(Classify.class);
            if (StringUtils.isNotBlank(classify.getClassifyName())) {
                example.createCriteria().andCondition("classify_name=", classify.getClassifyName());
            }
            if (StringUtils.isNotBlank(classify.getClassifyCode())) {
                example.createCriteria().andCondition("classify_code=", classify.getClassifyCode());
            }
            example.setOrderByClause("id");
            return this.selectByExample(example);
        } catch (Exception e) {
            log.error("获取分类列表失败", e);
            return new ArrayList<>();
        }
    }

    @Override
    public Classify findById(Long id) {
        return this.selectByKey(id);
    }

    @Override
    public void addClassify(Classify classify) {
        this.save(classify);
    }

    @Override
    public void deleteClassifys(String ids) {
        List<String> list = Arrays.asList(ids.split(","));
        this.batchDelete(list, "id", Classify.class);
    }

    @Override
    public void updateClassify(Classify classify) {
        this.updateNotNull(classify);
    }
}
