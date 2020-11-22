package cn.yuan.blog.controller;

import cn.yuan.blog.domain.Tag;
import cn.yuan.blog.service.TagService;
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
public class TagController extends BaseController {

    private Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private TagService tagService;

    @Log("获取标签信息")
    @RequestMapping("tag")
    @RequiresPermissions("tag:list")
    public String index() {
        return "blog/tag/tag";
    }

    @RequestMapping("tag/list")
    @RequiresPermissions("tag:list")
    @ResponseBody
    public Map<String, Object> tagList(QueryRequest request, Tag tag) {
        return super.selectByPageNumSize(request, () -> this.tagService.findAllTags(tag, request));
    }

    @RequestMapping("tag/excel")
    @ResponseBody
    public ResponseBo tagExcel(Tag tag) {
        try {
            List<Tag> list = this.tagService.findAllTags(tag, null);
            return FileUtil.createExcelByPOIKit("标签表", list, Tag.class);
        } catch (Exception e) {
            log.error("导出标信息Excel失败", e);
            return ResponseBo.error("导出Excel失败，请联系网站管理员！");
        }
    }

    @RequestMapping("tag/csv")
    @ResponseBody
    public ResponseBo tagCsv(Tag tag) {
        try {
            List<Tag> list = this.tagService.findAllTags(tag, null);
            return FileUtil.createCsv("标签表", list, Tag.class);
        } catch (Exception e) {
            log.error("导出标签信息Csv失败", e);
            return ResponseBo.error("导出Csv失败，请联系网站管理员！");
        }
    }
    @RequestMapping("tag/getTag")
    @ResponseBody
    public ResponseBo getTag(Long id) {
        try {
            Tag tag = this.tagService.findById(id);
            return ResponseBo.ok(tag);
        } catch (Exception e) {
            log.error("获取标签信息失败", e);
            return ResponseBo.error("获取标签信息失败，请联系网站管理员！");
        }
    }
    @Log("新增标签")
    @RequiresPermissions("tag:add")
    @RequestMapping("tag/add")
    @ResponseBody
    public ResponseBo addTag(Tag tag) {
        try {
            this.tagService.addTag(tag);
            return ResponseBo.ok("新增标签成功！");
        } catch (Exception e) {
            log.error("新增标签失败", e);
            return ResponseBo.error("新增标签失败，请联系网站管理员！");
        }
    }

    @Log("删除标签")
    @RequiresPermissions("tag:delete")
    @RequestMapping("tag/delete")
    @ResponseBody
    public ResponseBo deleteTags(String ids) {
        try {
            this.tagService.deleteTags(ids);
            return ResponseBo.ok("删除标签成功！");
        } catch (Exception e) {
            log.error("删除标签失败", e);
            return ResponseBo.error("删除标签失败，请联系网站管理员！");
        }
    }

    @Log("修改标签")
    @RequiresPermissions("tag:update")
    @RequestMapping("tag/update")
    @ResponseBody
    public ResponseBo updateTag(Tag tag) {
        try {
            this.tagService.updateTag(tag);
            return ResponseBo.ok("修改标签成功！");
        } catch (Exception e) {
            log.error("修改标签失败", e);
            return ResponseBo.error("修改标签失败，请联系网站管理员！");
        }
    }
}
