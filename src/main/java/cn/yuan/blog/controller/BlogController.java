package cn.yuan.blog.controller;

import cn.yuan.blog.domain.BaseBlog;
import cn.yuan.blog.domain.Blog;
import cn.yuan.blog.domain.SaveBlog;
import cn.yuan.blog.domain.SaveContent;
import cn.yuan.blog.service.BlogService;
import cn.yuan.blog.service.ClassifyService;
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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.*;
import java.util.stream.Collectors;

@Controller
public class BlogController extends BaseController {

    private Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private BlogService blogService;
    @Autowired
    private TagService tagService;
    @Autowired
    private ClassifyService classifyService;

    @Log("获取博客信息")
    @RequestMapping("blog")
    @RequiresPermissions("blog:list")
    public String index() {
        return "blog/blog/blog";
    }

    @RequestMapping("blog/list")
    @RequiresPermissions("blog:list")
    @ResponseBody
    public Map<String, Object> blogList(QueryRequest request, Blog blog) {
        Map<String, Object> dataMap = super.selectByPageNumSize(request, () -> this.blogService.findAllBlogs(blog, request));
            Object rows = dataMap.get("rows");
            if(rows!=null){
             List<Blog> blogs = (ArrayList<Blog>)rows;
                blogs = blogs.stream().map(x ->{
                      x.setTags(tagService.findTagsByBlogId(x.getId()));
                      x.setClassifies(classifyService.findClassifysByBlogId(x.getId()));
                    return x;
                }).collect(Collectors.toList());
                dataMap.put("rows", blogs);
            }
        return dataMap;
    }

    @RequestMapping("blog/excel")
    @ResponseBody
    public ResponseBo blogExcel(Blog blog) {
        try {
            List<Blog> list = this.blogService.findAllBlogs(blog, null);
            return FileUtil.createExcelByPOIKit("博客表", list, Blog.class);
        } catch (Exception e) {
            log.error("导出标信息Excel失败", e);
            return ResponseBo.error("导出Excel失败，请联系网站管理员！");
        }
    }

    @RequestMapping("blog/csv")
    @ResponseBody
    public ResponseBo blogCsv(Blog blog) {
        try {
            List<Blog> list = this.blogService.findAllBlogs(blog, null);
            return FileUtil.createCsv("博客表", list, Blog.class);
        } catch (Exception e) {
            log.error("导出博客信息Csv失败", e);
            return ResponseBo.error("导出Csv失败，请联系网站管理员！");
        }
    }

    @RequestMapping("blog/getBlog")
    @ResponseBody
    public ResponseBo getBlog(Long id) {
        try {
            Blog blog = this.blogService.findById(id);
            blog.setTags(tagService.findTagsByBlogId(id));
            blog.setClassifies(classifyService.findClassifysByBlogId(id));
            return ResponseBo.ok(blog);
        } catch (Exception e) {
            log.error("获取博客信息失败", e);
            return ResponseBo.error("获取博客信息失败，请联系网站管理员！");
        }
    }

    @Log("新增博客")
    @RequiresPermissions("blog:add")
    @RequestMapping("blog/add")
    @ResponseBody
    public ResponseBo addBlog(SaveBlog saveBlog) {
        String[] sericsArry = saveBlog.getSericsSelect();
        if(sericsArry.length>1){
            return ResponseBo.error("不能添加多个系列");
        }
        try {
           this.blogService.addBlog(saveBlog);
            return ResponseBo.ok("新增博客成功！");
        } catch (Exception e) {
            log.error("新增博客失败", e);
            return ResponseBo.error("新增博客失败，请联系网站管理员！");
        }
    }

    @Log("删除博客")
    @RequiresPermissions("blog:delete")
    @RequestMapping("blog/delete")
    @ResponseBody
    public ResponseBo deleteBlogs(String ids) {
        try {
            this.blogService.deleteBlogs(ids);
            return ResponseBo.ok("删除博客成功！");
        } catch (Exception e) {
            log.error("删除博客失败", e);
            return ResponseBo.error("删除博客失败，请联系网站管理员！");
        }
    }

    @Log("修改博客")
    @RequiresPermissions("blog:update")
    @RequestMapping("blog/update")
    @ResponseBody
    public ResponseBo updateBlog(SaveBlog blog) {
        String seriesCode = blog.getSeriesCode();
        if(seriesCode==null || seriesCode.contains(",")){
            return ResponseBo.error("不能添加多个系列");
        }
        try {
             this.blogService.updateBlog(blog);
            return ResponseBo.ok("修改博客成功！");
        } catch (Exception e) {
            log.error("修改博客失败", e);
            return ResponseBo.error("修改博客失败，请联系网站管理员！");
        }
    }


    @RequestMapping("blog/getContent")
    @ResponseBody
    public ResponseBo getContent(Long id) {
        try {
            BaseBlog blog = this.blogService.selectByKey(id);
            return ResponseBo.ok(blog);
        } catch (Exception e) {
            log.error("获取博客内容失败", e);
            return ResponseBo.error("获取博客内容失败，请联系网站管理员！");
        }
    }

    @RequestMapping("blog/saveContent")
    @ResponseBody
    public ResponseBo saveContent(Long id,String content,String textContent) {
        if(id ==null ){
            return ResponseBo.error("文章编号不存在!");
         }
        try {
            BaseBlog baseBlog  = new BaseBlog();
            baseBlog.setId(id);
            baseBlog.setTextContent(textContent );
            baseBlog.setContent(content);
            this.blogService.updateNotNull(baseBlog);
            return ResponseBo.ok();
        } catch (Exception e) {
            log.error("更新博客内容失败", e);
            return ResponseBo.error("更新博客内容失败，请联系网站管理员！");
        }
    }
}
