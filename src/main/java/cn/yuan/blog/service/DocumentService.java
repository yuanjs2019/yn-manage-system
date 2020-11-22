package cn.yuan.blog.service;

import cn.yuan.blog.domain.Document;
import cn.yuan.common.domain.QueryRequest;
import cn.yuan.common.service.IService;
import java.util.List;

public interface DocumentService extends IService<Document> {

	List<Document> findAllDocuments(Document document, QueryRequest request);

	Document findById(Long id);

	void addDocument(Document document);

	void deleteDocuments(String ids);

	void updateDocument(Document document);

}
