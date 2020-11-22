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
@Table(name = "yn_series")
public class Series implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(generator = "JDBC")
    @Column(name = "id")
    @ExportConfig(value = "主键id")
    private Long id;

    /**
     * 系列名称
     */
    @Column(name = "serics_name")
    @ExportConfig(value = "系列名称")
    private String sericsName;

    /**
     * 系列编码
     */
    @Column(name = "serics_code")
    @ExportConfig(value = "系列编码")
    private String sericsCode;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSericsName() {
        return sericsName;
    }

    public void setSericsName(String sericsName) {
        this.sericsName = sericsName;
    }

    public String getSericsCode() {
        return sericsCode;
    }

    public void setSericsCode(String sericsCode) {
        this.sericsCode = sericsCode;
    }
}
