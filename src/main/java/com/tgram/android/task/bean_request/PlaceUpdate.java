package com.tgram.android.task.bean_request;

/**
 * 项目名称：TaskManager
 * 类描述：
 * 创建人：mzgkq
 * 创建时间：18/3/23
 */
public class PlaceUpdate {
    private Long id;
    private String name;
    private String description;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
