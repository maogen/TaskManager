package com.tgram.android.task.bean_request;

/**
 * 项目名称：TaskManager
 * 类描述：
 * 创建人：mzgkq
 * 创建时间：18/3/23
 */
public class ProjectUpdate {
    private Long projectId;
    private String name;
    private String description;

    public Long getProjectId() {
        return projectId;
    }

    public void setProjectId(Long projectId) {
        this.projectId = projectId;
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
