package cn.yuan.blog.domain;

/**
 * 内容保存数据.
 *
 * @author : YJS
 * @date: 2020-11-25 16:00
 */
public class SaveContent {

    private String id;
    /**
     * 内容md格式
     */
    private String textContent;

    /**
     * 内容(带有样式）
     */
    private String content;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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
}
