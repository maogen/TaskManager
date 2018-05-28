package com.tgram.android.task.bean_request;

/**
 * 项目名称：TaskManager
 * 类描述：
 * 创建人：mzgkq
 * 创建时间：18/3/23
 */
public class ProjectInsert {
    private Long placeId;
    private String name;
    private String description;

    public Long getPlaceId() {
        return placeId;
    }

    public void setPlaceId(Long placeId) {
        this.placeId = placeId;
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
