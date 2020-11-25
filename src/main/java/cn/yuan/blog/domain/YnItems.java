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
 * @since 2020-11-09
 */
@Table(name = "yn_items")
public class YnItems implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(generator = "JDBC")
    @Column(name = "id")
    @ExportConfig(value = "主键id")
    private Long id;
    /**
     * 项目分类id
     */
    @Column(name = "item_classfy_id")
    @ExportConfig(value = "项目分类id")
    private Integer itemClassfyId;
    /**
     * 项目分类名称
     */
    @Column(name = "item_classfy_name")
    @ExportConfig(value = "项目分类名称")
    private String itemClassfyName;

    /**
     * 项目名称
     */
    @Column(name = "item_name")
    @ExportConfig(value = "项目名称")
    private String itemName;

    /**
     * 项目简介
     */
    @Column(name = "item_explain")
    @ExportConfig(value = "项目简介")
    private String itemExplain;

    /**
     * 项目图标地址
     */
    @Column(name = "item_picture")
    @ExportConfig(value = "项目图标地址")
    private String itemPicture;

    /**
     * 项目地址
     */
    @Column(name = "item_url")
    @ExportConfig(value = "项目地址")
    private String itemUrl;

    /**
     * 项目文档地址
     */
    @Column(name = "item_introduce_url")
    @ExportConfig(value = "项目文档地址")
    private String itemIntroduceUrl;

    /**
     * 是否存在说明文档：0-不存在，1-存在
     */
    @Column(name = "has_document")
    @ExportConfig(value = "是否存在说明文档：0-不存在，1-存在")
    private Integer hasDocument;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getItemClassfyId() {
        return itemClassfyId;
    }

    public void setItemClassfyId(Integer itemClassfyId) {
        this.itemClassfyId = itemClassfyId;
    }

    public String getItemClassfyName() {
        return itemClassfyName;
    }

    public void setItemClassfyName(String itemClassfyName) {
        this.itemClassfyName = itemClassfyName;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getItemExplain() {
        return itemExplain;
    }

    public void setItemExplain(String itemExplain) {
        this.itemExplain = itemExplain;
    }

    public String getItemPicture() {
        return itemPicture;
    }

    public void setItemPicture(String itemPicture) {
        this.itemPicture = itemPicture;
    }

    public String getItemUrl() {
        return itemUrl;
    }

    public void setItemUrl(String itemUrl) {
        this.itemUrl = itemUrl;
    }

    public String getItemIntroduceUrl() {
        return itemIntroduceUrl;
    }

    public void setItemIntroduceUrl(String itemIntroduceUrl) {
        this.itemIntroduceUrl = itemIntroduceUrl;
    }

    public Integer getHasDocument() {
        return hasDocument;
    }

    public void setHasDocument(Integer hasDocument) {
        this.hasDocument = hasDocument;
    }
}
