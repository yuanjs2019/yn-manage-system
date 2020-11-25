package cn.yuan.blog.service.impl;

import cn.yuan.blog.dao.BlogMapper;
import cn.yuan.blog.dao.ClassifyMapper;
import cn.yuan.blog.dao.TagMapper;
import cn.yuan.blog.domain.*;
import cn.yuan.blog.service.BlogService;
import cn.yuan.common.domain.QueryRequest;
import cn.yuan.common.service.impl.BaseService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Service("blogService")
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
public class BlogServiceImpl extends BaseService<BaseBlog> implements BlogService {

    private Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private BlogMapper blogMapper;
    @Autowired
    private TagMapper tagMapper;
    @Autowired
    private ClassifyMapper classifyMapper;


    @Override
    public List<Blog> findAllBlogs(Blog blog, QueryRequest request) {
        if (blog.getTitle() != null && !"".equals(blog.getTitle())) {
            blog.setTitle("%" + blog.getTitle() + "%");
        }
        List<Blog> allBlogs = blogMapper.findAllBlogs(blog);
        return allBlogs;
    }

    @Override
    public Blog findById(Long id) {
        return blogMapper.findById(id);
    }

    @Override
    public void addBlog(SaveBlog blog) {
        BaseBlog baseBlog = ConvertBlog(blog, new BaseBlog(), "save");
        this.save(baseBlog);
        //删除分类和标签
        //tagMapper.deleteBlogTagByBlogId(baseBlog.getId());
        //classifyMapper.deleteBlogClassifyByBlogId(blog.getId());
        //更新
        Long[] classifySelect = blog.getClassifySelect();
        if(classifySelect != null && classifySelect.length !=0 ) {
            Arrays.stream(classifySelect).map(x -> {
                YnBlogTag blogTag = new YnBlogTag();
                blogTag.setBlogId(baseBlog.getId());
                blogTag.setTagId(x);
                tagMapper.addBlogTag(blogTag);
                return x;
            }).count();
        }
        Long[] tagSelect = blog.getTagSelect();
        if(tagSelect != null && tagSelect.length !=0 ){
            Arrays.stream(tagSelect).map(x -> {
                YnBlogClassify blogClassify = new YnBlogClassify();
                blogClassify.setBlogId(baseBlog.getId());
                blogClassify.setClassifyId(x);
                classifyMapper.addBlogClassify(blogClassify);
                return x;
            }).count();
        }

    }

    @Override
    public void deleteBlogs(String ids) {
        List<String> list = Arrays.asList(ids.split(","));
        this.batchDelete(list, "id", BaseBlog.class);
        //删除对应的关联标签与分类和统计文章访量表数据。
		list.stream().map(x->{
			tagMapper.deleteBlogTagByBlogId(Long.valueOf(x));
			classifyMapper.deleteBlogClassifyByBlogId(Long.valueOf(x));
			return x;
		}).count();

    }

    @Override
    public void updateBlog(SaveBlog blog) {
        BaseBlog baseBlog = ConvertBlog(blog, new BaseBlog(), "update");
        this.updateNotNull(baseBlog);
		//删除分类和标签
		tagMapper.deleteBlogTagByBlogId(blog.getId());
		classifyMapper.deleteBlogClassifyByBlogId(blog.getId());
		//更新
        Long[] classifySelect = blog.getClassifySelect();
        if(classifySelect != null && classifySelect.length !=0 ) {
            Arrays.stream(classifySelect).map(x -> {
                YnBlogTag blogTag = new YnBlogTag();
                blogTag.setBlogId(baseBlog.getId());
                blogTag.setTagId(x);
                tagMapper.addBlogTag(blogTag);
                return x;
            }).count();
        }
        Long[] tagSelect = blog.getTagSelect();
        if(tagSelect != null && tagSelect.length !=0 ){
            Arrays.stream(tagSelect).map(x -> {
                YnBlogClassify blogClassify = new YnBlogClassify();
                blogClassify.setBlogId(baseBlog.getId());
                blogClassify.setClassifyId(x);
                classifyMapper.addBlogClassify(blogClassify);
                return x;
            }).count();
        }

    }


    private BaseBlog ConvertBlog(SaveBlog saveBlog, BaseBlog blog, String method) {
        blog.setId(saveBlog.getId());
        blog.setTitle(saveBlog.getTitle());
        blog.setAuthor(saveBlog.getAuthor());
        blog.setDescription(saveBlog.getDescription());
        blog.setSeriesCode(saveBlog.getSeriesCode());
        blog.setType(saveBlog.getType());
        blog.setMainPicture(saveBlog.getMainPicture());
        if ("save".equals(method)) {
            Calendar now = Calendar.getInstance();
            blog.setMonth(now.get(Calendar.MONTH) + 1);
            blog.setYear(now.get(Calendar.YEAR));
            blog.setCreateTime(new Date());
        }
        blog.setUpdateTime(new Date());
        return blog;
    }
}
