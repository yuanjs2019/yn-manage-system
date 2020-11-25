package cn.yuan.blog.domain;

import cn.yuan.common.annotation.ExportConfig;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * 实体对象BLOG.
 *
 * @author yjs
 * @since 2020-10-26
 */
@Table(name = "blog")
public class BaseBlog implements Serializable {

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
     * 内容md格式
     */
    @Column(name = "text_content")
    @ExportConfig(value = "内容md格式")
    private String textContent;

    /**
     * 内容(带有样式）
     */
    @Column(name = "content")
    @ExportConfig(value = "内容(带有样式）")
    private String content;

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

    /**
     * 年份
     */
    @Column(name = "year")
    @ExportConfig(value = "年份")
    private Integer year;

    /**
     * 月份
     */
    @Column(name = "month")
    @ExportConfig(value = "月份")
    private Integer month;

    /**
     * 创建时间
     */
    @Column(name = "create_time")
    @ExportConfig(value = "创建时间")
    private Date createTime;

    /**
     * 更新时间
     */
    @Column(name = "update_time")
    @ExportConfig(value = "更新时间")
    private Date updateTime;


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

    public String getTextContent() {
        return textContent;
    }

    public void setTextContent(String textContent) {
        this.textContent = textContent;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
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

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public Integer getMonth() {
        return month;
    }

    public void setMonth(Integer month) {
        this.month = month;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}
