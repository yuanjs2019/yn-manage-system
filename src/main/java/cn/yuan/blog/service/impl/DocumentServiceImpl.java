package cn.yuan.blog.service.impl;

import cn.yuan.blog.dao.DocumentMapper;
import cn.yuan.blog.dao.TagMapper;
import cn.yuan.blog.domain.Document;
import cn.yuan.blog.domain.Tag;
import cn.yuan.blog.service.DocumentService;
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

@Service("documentService")
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
public class DocumentServiceImpl extends BaseService<Document> implements DocumentService {
    private Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private DocumentMapper documentMapper;

    @Override
    public List<Document> findAllDocuments(Document document, QueryRequest request) {
        try {
            Example example = new Example(Document.class);
            if (StringUtils.isNotBlank(document.getTitle())) {
                example.createCriteria().andCondition("title=", document.getTitle());
            }
            if (StringUtils.isNotBlank(document.getCover())) {
                example.createCriteria().andCondition("cover=", document.getCover());
            }
            example.setOrderByClause("id");
            return this.selectByExample(example);
        } catch (Exception e) {
            log.error("获取标签列表失败", e);
            return new ArrayList<>();
        }
    }

    @Override
    public Document findById(Long id) {
        return this.selectByKey(id);
    }

    @Override
    public void addDocument(Document document) {
        this.save(document);
    }

    @Override
    public void deleteDocuments(String ids) {
        List<String> list = Arrays.asList(ids.split(","));
        this.batchDelete(list, "id", Document.class);
    }

    @Override
    public void updateDocument(Document document) {
        this.updateNotNull(document);
    }
}
