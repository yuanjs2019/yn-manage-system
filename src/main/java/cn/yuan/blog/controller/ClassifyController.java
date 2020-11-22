package cn.yuan.blog.controller;

import cn.yuan.blog.domain.Classify;
import cn.yuan.blog.service.ClassifyService;
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
public class ClassifyController extends BaseController {

    private Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private ClassifyService classifyService;

    @Log("获取分类信息")
    @RequestMapping("classify")
    @RequiresPermissions("classify:list")
    public String index() {
        return "blog/classify/classify";
    }

    @RequestMapping("classify/list")
    @RequiresPermissions("classify:list")
    @ResponseBody
    public Map<String, Object> classifyList(QueryRequest request, Classify classify) {
        return super.selectByPageNumSize(request, () -> this.classifyService.findAllClassifys(classify, request));
    }

    @RequestMapping("classify/excel")
    @ResponseBody
    public ResponseBo classifyExcel(Classify classify) {
        try {
            List<Classify> list = this.classifyService.findAllClassifys(classify, null);
            return FileUtil.createExcelByPOIKit("分类表", list, Classify.class);
        } catch (Exception e) {
            log.error("导出标信息Excel失败", e);
            return ResponseBo.error("导出Excel失败，请联系网站管理员！");
        }
    }

    @RequestMapping("classify/csv")
    @ResponseBody
    public ResponseBo classifyCsv(Classify classify) {
        try {
            List<Classify> list = this.classifyService.findAllClassifys(classify, null);
            return FileUtil.createCsv("分类表", list, Classify.class);
        } catch (Exception e) {
            log.error("导出分类信息Csv失败", e);
            return ResponseBo.error("导出Csv失败，请联系网站管理员！");
        }
    }
    @RequestMapping("classify/getClassify")
    @ResponseBody
    public ResponseBo getClassify(Long id) {
        try {
            Classify classify = this.classifyService.findById(id);
            return ResponseBo.ok(classify);
        } catch (Exception e) {
            log.error("获取分类信息失败", e);
            return ResponseBo.error("获取分类信息失败，请联系网站管理员！");
        }
    }
    @Log("新增分类")
    @RequiresPermissions("classify:add")
    @RequestMapping("classify/add")
    @ResponseBody
    public ResponseBo addClassify(Classify classify) {
        try {
            this.classifyService.addClassify(classify);
            return ResponseBo.ok("新增分类成功！");
        } catch (Exception e) {
            log.error("新增分类失败", e);
            return ResponseBo.error("新增分类失败，请联系网站管理员！");
        }
    }

    @Log("删除分类")
    @RequiresPermissions("classify:delete")
    @RequestMapping("classify/delete")
    @ResponseBody
    public ResponseBo deleteClassifys(String ids) {
        try {
            this.classifyService.deleteClassifys(ids);
            return ResponseBo.ok("删除分类成功！");
        } catch (Exception e) {
            log.error("删除分类失败", e);
            return ResponseBo.error("删除分类失败，请联系网站管理员！");
        }
    }

    @Log("修改分类")
    @RequiresPermissions("classify:update")
    @RequestMapping("classify/update")
    @ResponseBody
    public ResponseBo updateClassify(Classify classify) {
        try {
            this.classifyService.updateClassify(classify);
            return ResponseBo.ok("修改分类成功！");
        } catch (Exception e) {
            log.error("修改分类失败", e);
            return ResponseBo.error("修改分类失败，请联系网站管理员！");
        }
    }
}
