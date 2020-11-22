package cn.yuan.blog.domain;

import cn.yuan.common.annotation.ExportConfig;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author yjs
 * @since 2020-11-17
 */
@Table(name = "yn_document")
public class Document implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(generator = "JDBC")
    @Column(name = "id")
    @ExportConfig(value = "主键id")
    private Long id;
    /**
     * 文档名称
     */
    @Column(name = "title")
    @ExportConfig(value = "文档名称")
    private String title;

    /**
     * 封面
     */
    @Column(name = "cover")
    @ExportConfig(value = "封面地址")
    private String cover;

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

    public String getCover() {
        return cover;
    }

    public void setCover(String cover) {
        this.cover = cover;
    }
}
