package cn.yuan.blog.controller;

import cn.yuan.blog.domain.Series;
import cn.yuan.blog.service.SeriesService;
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
public class SeriesController extends BaseController {

    private Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private SeriesService seriesService;

    @Log("获取系列信息")
    @RequestMapping("series")
    @RequiresPermissions("series:list")
    public String index() {
        return "blog/series/series";
    }

    @RequestMapping("series/list")
    @RequiresPermissions("series:list")
    @ResponseBody
    public Map<String, Object> seriesList(QueryRequest request, Series series) {
        return super.selectByPageNumSize(request, () -> this.seriesService.findAllSeries(series, request));
    }

    @RequestMapping("series/excel")
    @ResponseBody
    public ResponseBo seriesExcel(Series series) {
        try {
            List<Series> list = this.seriesService.findAllSeries(series, null);
            return FileUtil.createExcelByPOIKit("系列表", list, Series.class);
        } catch (Exception e) {
            log.error("导出标信息Excel失败", e);
            return ResponseBo.error("导出Excel失败，请联系网站管理员！");
        }
    }

    @RequestMapping("series/csv")
    @ResponseBody
    public ResponseBo seriesCsv(Series series) {
        try {
            List<Series> list = this.seriesService.findAllSeries(series, null);
            return FileUtil.createCsv("系列表", list, Series.class);
        } catch (Exception e) {
            log.error("导出系列信息Csv失败", e);
            return ResponseBo.error("导出Csv失败，请联系网站管理员！");
        }
    }
    @RequestMapping("series/getseries")
    @ResponseBody
    public ResponseBo getSeries(Long id) {
        try {
            Series series = this.seriesService.findById(id);
            return ResponseBo.ok(series);
        } catch (Exception e) {
            log.error("获取系列信息失败", e);
            return ResponseBo.error("获取系列信息失败，请联系网站管理员！");
        }
    }
    @Log("新增系列")
    @RequiresPermissions("series:add")
    @RequestMapping("series/add")
    @ResponseBody
    public ResponseBo addSeries(Series series) {
        try {
            this.seriesService.addSeries(series);
            return ResponseBo.ok("新增系列成功！");
        } catch (Exception e) {
            log.error("新增系列失败", e);
            return ResponseBo.error("新增系列失败，请联系网站管理员！");
        }
    }

    @Log("删除系列")
    @RequiresPermissions("series:delete")
    @RequestMapping("series/delete")
    @ResponseBody
    public ResponseBo deleteSeriess(String ids) {
        try {
            this.seriesService.deleteSeries(ids);
            return ResponseBo.ok("删除系列成功！");
        } catch (Exception e) {
            log.error("删除系列失败", e);
            return ResponseBo.error("删除系列失败，请联系网站管理员！");
        }
    }

    @Log("修改系列")
    @RequiresPermissions("series:update")
    @RequestMapping("series/update")
    @ResponseBody
    public ResponseBo updateSeries(Series series) {
        try {
            this.seriesService.updateSeries(series);
            return ResponseBo.ok("修改系列成功！");
        } catch (Exception e) {
            log.error("修改系列失败", e);
            return ResponseBo.error("修改系列失败，请联系网站管理员！");
        }
    }
}
