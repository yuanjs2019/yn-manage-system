package cn.yuan.blog.domain;

import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author yjs
 * @since 2020-11-06
 */
public class YnBlogClassify implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 分类主键id
     */
    private Long classifyId;

    /**
     * 博客文章id
     */
    private Long blogId;

    public Long getClassifyId() {
        return classifyId;
    }

    public void setClassifyId(Long classifyId) {
        this.classifyId = classifyId;
    }

    public Long getBlogId() {
        return blogId;
    }

    public void setBlogId(Long blogId) {
        this.blogId = blogId;
    }
}
