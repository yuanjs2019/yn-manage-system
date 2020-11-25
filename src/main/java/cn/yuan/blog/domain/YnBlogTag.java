package cn.yuan.blog.domain;

import java.io.Serializable;

/**
 *
 * @author yjs
 * @since 2020-11-06
 */
public class YnBlogTag implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 标签主键id
     */
    private Long tagId;

    /**
     * 博客文章id
     */
    private Long blogId;

    public Long getTagId() {
        return tagId;
    }

    public void setTagId(Long tagId) {
        this.tagId = tagId;
    }

    public Long getBlogId() {
        return blogId;
    }

    public void setBlogId(Long blogId) {
        this.blogId = blogId;
    }
}
