package cn.yuan.blog.controller;

import cn.yuan.blog.domain.Document;
import cn.yuan.blog.service.DocumentService;
import cn.yuan.common.annotation.Log;
import cn.yuan.common.controller.BaseController;
import cn.yuan.common.domain.QueryRequest;
import cn.yuan.common.domain.ResponseBo;
import cn.yuan.common.util.FileUtil;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

@Controller
public class DoucmentController extends BaseController {

    private Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private DocumentService documentService;

    @Log("获取文档信息")
    @RequestMapping("document")
    @RequiresPermissions("document:list")
    public String index() {
        return "blog/document/document";
    }

    @RequestMapping("document/list")
    @RequiresPermissions("document:list")
    @ResponseBody
    public Map<String, Object> documentList(QueryRequest request, Document document) {
        return super.selectByPageNumSize(request, () -> this.documentService.findAllDocuments(document, request));
    }

    @RequestMapping("document/excel")
    @ResponseBody
    public ResponseBo documentExcel(Document document) {
        try {
            List<Document> list = this.documentService.findAllDocuments(document, null);
            return FileUtil.createExcelByPOIKit("文档表", list, Document.class);
        } catch (Exception e) {
            log.error("导出标信息Excel失败", e);
            return ResponseBo.error("导出Excel失败，请联系网站管理员！");
        }
    }

    @RequestMapping("document/csv")
    @ResponseBody
    public ResponseBo documentCsv(Document document) {
        try {
            List<Document> list = this.documentService.findAllDocuments(document, null);
            return FileUtil.createCsv("文档表", list, Document.class);
        } catch (Exception e) {
            log.error("导出文档信息Csv失败", e);
            return ResponseBo.error("导出Csv失败，请联系网站管理员！");
        }
    }
    @RequestMapping("document/getDocument")
    @ResponseBody
    public ResponseBo getDocument(Long id) {
        try {
            Document document = this.documentService.findById(id);
            return ResponseBo.ok(document);
        } catch (Exception e) {
            log.error("获取文档信息失败", e);
            return ResponseBo.error("获取文档信息失败，请联系网站管理员！");
        }
    }
    @Log("新增文档")
    @RequiresPermissions("document:add")
    @RequestMapping("document/add")
    @ResponseBody
    public ResponseBo addDocument(Document document) {
        try {
            this.documentService.addDocument(document);
            return ResponseBo.ok("新增文档成功！");
        } catch (Exception e) {
            log.error("新增文档失败", e);
            return ResponseBo.error("新增文档失败，请联系网站管理员！");
        }
    }

    @Log("删除文档")
    @RequiresPermissions("document:delete")
    @RequestMapping("document/delete")
    @ResponseBody
    public ResponseBo deleteDocuments(String ids) {
        try {
            this.documentService.deleteDocuments(ids);
            return ResponseBo.ok("删除文档成功！");
        } catch (Exception e) {
            log.error("删除文档失败", e);
            return ResponseBo.error("删除文档失败，请联系网站管理员！");
        }
    }

    @Log("修改文档")
    @RequiresPermissions("document:update")
    @RequestMapping("document/update")
    @ResponseBody
    public ResponseBo updateDocument(Document document) {
        try {
            this.documentService.updateDocument(document);
            return ResponseBo.ok("修改文档成功！");
        } catch (Exception e) {
            log.error("修改文档失败", e);
            return ResponseBo.error("修改文档失败，请联系网站管理员！");
        }
    }
}
