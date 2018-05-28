package com.tgram.android.task.bean;

/**
 * 项目名称：TaskManager
 * 类描述：地市，地方类
 * 创建人：mzgkq
 * 创建时间：18/3/22
 */
public class Project {

    /**
     * 项目id
     */
    private long projectId;
    /**
     * 项目关联的地市id
     */
    private long placeId;
    private String projectName;
    private String description;
    private String createTime;
    private String updateTime;

    public long getProjectId() {
        return projectId;
    }

    public void setProjectId(long projectId) {
        this.projectId = projectId;
    }

    public long getPlaceId() {
        return placeId;
    }

    public void setPlaceId(long placeId) {
        this.placeId = placeId;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }
}
