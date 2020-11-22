package cn.yuan.blog.domain;



import cn.yuan.common.annotation.ExportConfig;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.io.Serializable;

/**
 * <p>
 * 分类表
 * </p>
 *
 * @author yjs
 * @since 2020-10-29
 */
public class Classify implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(generator = "JDBC")
    @Column(name = "id")
    @ExportConfig(value = "主键id")
    private Long id;
    /**
     * 分类名称
     */
    private String classifyName;

    /**
     * 分类编号
     */
    private String classifyCode;


}
