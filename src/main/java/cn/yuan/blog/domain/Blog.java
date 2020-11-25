package cn.yuan.blog.domain;

import java.io.Serializable;
import java.util.List;

/**
 * 实体对象BLOG.
 *
 * @author yjs
 * @since 2020-10-26
 */
public class Blog extends BaseBlog implements Serializable {

    private static final long serialVersionUID = 1L;

    private String seriesName;

    /**
     * 查询时返回的标签
     */
    private List<Tag> tags;
    /**
     * 查询时返回的分类
     */
    private List<Classify> classifies;

    public String getSeriesName() {
        return seriesName;
    }

    public void setSeriesName(String seriesName) {
        this.seriesName = seriesName;
    }

    public List<Tag> getTags() {
        return tags;
    }

    public void setTags(List<Tag> tags) {
        this.tags = tags;
    }

    public List<Classify> getClassifies() {
        return classifies;
    }

    public void setClassifies(List<Classify> classifies) {
        this.classifies = classifies;
    }
}
