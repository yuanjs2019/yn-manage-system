package cn.yuan.blog.domain;

import cn.yuan.common.annotation.ExportConfig;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * <p>
 * 标签列表
 * </p>
 *
 * @author yjs
 * @since 2020-10-29
 */
@Table(name = "yn_tag")
public class Tag implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(generator = "JDBC")
    @Column(name = "id")
    @ExportConfig(value = "主键id")
    private Long id;

    /**
     * 标签名称
     */
    @Column(name = "tag_name")
    @ExportConfig(value = "标签名称")
    private String tagName;

    /**
     * 标签编码
     */
    @Column(name = "tag_code")
    @ExportConfig(value = "标签编码")
    private String tagCode;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTagName() {
        return tagName;
    }

    public void setTagName(String tagName) {
        this.tagName = tagName;
    }

    public String getTagCode() {
        return tagCode;
    }

    public void setTagCode(String tagCode) {
        this.tagCode = tagCode;
    }
}
