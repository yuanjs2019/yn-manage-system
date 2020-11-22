package cn.yuan.blog.domain;



import cn.yuan.common.annotation.ExportConfig;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * <p>
 * 分类表
 * </p>
 *
 * @author yjs
 * @since 2020-10-29
 */
@Table(name = "yn_classify")
public class Classify implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(generator = "JDBC")
    @Column(name = "id")
    @ExportConfig(value = "主键id")
    private Long id;
    /**
     * 分类名称
     */@Column(name = "classify_name")
    @ExportConfig(value = "分类名称")
    private String classifyName;

    /**
     * 分类编号
     */
    @Column(name = "classify_code")
    @ExportConfig(value = "分类编号")
    private String classifyCode;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getClassifyName() {
        return classifyName;
    }

    public void setClassifyName(String classifyName) {
        this.classifyName = classifyName;
    }

    public String getClassifyCode() {
        return classifyCode;
    }

    public void setClassifyCode(String classifyCode) {
        this.classifyCode = classifyCode;
    }
}
