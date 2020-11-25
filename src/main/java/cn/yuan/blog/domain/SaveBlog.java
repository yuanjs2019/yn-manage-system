package cn.yuan.blog.domain;


import cn.yuan.common.annotation.ExportConfig;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * 提价文章保存的数据(不包含内容数据)
 *
 * @author yjs
 * @since 2020-10-26
 */
@Table(name = "blog")
public class SaveBlog implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(generator = "JDBC")
    @Column(name = "id")
    @ExportConfig(value = "主键id")
    private Long id;

    /**
     * 文章标题
     */
    @Column(name = "title")
    @ExportConfig(value = "文章标题")
    private String title;

    /**
     * 文章描述
     */
    @Column(name = "description")
    @ExportConfig(value = "文章描述")
    private String description;


    /**
     * 文章头图片
     */
    @Column(name = "main_picture")
    @ExportConfig(value = "文章头图片")
    private String mainPicture;

    /**
     * 作者
     */
    @Column(name = "author")
    @ExportConfig(value = "作者")
    private String author;

    /**
     * 系列code
     */
    @Column(name = "series_code")
    @ExportConfig(value = "系列code")
    private String seriesCode;


    /**
     * 文章类型：1-转载，2-原创
     */
    @Column(name = "type")
    @ExportConfig(value = "文章类型：1-转载，2-原创")
    private Integer type;


    private Long[] classifySelect;

    private Long[] tagSelect;

    private String[] sericsSelect;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getMainPicture() {
        return mainPicture;
    }

    public void setMainPicture(String mainPicture) {
        this.mainPicture = mainPicture;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getSeriesCode() {
        return seriesCode;
    }

    public void setSeriesCode(String seriesCode) {
        this.seriesCode = seriesCode;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Long[] getClassifySelect() {
        return classifySelect;
    }

    public void setClassifySelect(Long[] classifySelect) {
        this.classifySelect = classifySelect;
    }

    public Long[] getTagSelect() {
        return tagSelect;
    }

    public void setTagSelect(Long[] tagSelect) {
        this.tagSelect = tagSelect;
    }

    public String[] getSericsSelect() {
        return sericsSelect;
    }

    public void setSericsSelect(String[] sericsSelect) {
        this.sericsSelect = sericsSelect;
    }

    @Override
    public String toString() {
        return "SaveBlog{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", mainPicture='" + mainPicture + '\'' +
                ", author='" + author + '\'' +
                ", seriesCode='" + seriesCode + '\'' +
                ", type=" + type +
                ", classifySelect=" + Arrays.toString(classifySelect) +
                ", tagSelect=" + Arrays.toString(tagSelect) +
                ", sericsSelect=" + Arrays.toString(sericsSelect) +
                '}';
    }
}
