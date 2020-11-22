package cn.yuan.blog.domain;

import cn.yuan.common.annotation.ExportConfig;


import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.io.Serializable;

/**
 * <p>
 * 标签列表
 * </p>
 *
 * @author yjs
 * @since 2020-10-29
 */
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
    private String sericsName;

    /**
     * 系列编码
     */
    private String sericsCode;


}
