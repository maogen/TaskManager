package com.tgram.android.task.bean;

/**
 * 项目名称：TaskManager
 * 类描述：ip记录
 * 创建人：mzgkq
 * 创建时间：18/3/22
 */
public class IPRecord {
    /**
     * 记录id
     */
    private long recordId;
    /**
     * 项目id
     */
    private long projectId;
    /**
     * 记录名
     */
    private String recordName;
    /**
     * 程序ip
     */
    private String mainIP;
    /**
     * 程序端口
     */
    private String mainPort;
    /**
     * 程序映射后的ip
     */
    private String mainMappingIP;
    /**
     * 程序映射后的端口
     */
    private String mainMappingPort;
    /**
     * 地图url
     */
    private String mapUrl;
    /**
     * 说明
     */
    private String description;

    private String createTime;
    private String updateTime;

    public long getRecordId() {
        return recordId;
    }

    public void setRecordId(long recordId) {
        this.recordId = recordId;
    }

    public long getProjectId() {
        return projectId;
    }

    public void setProjectId(long projectId) {
        this.projectId = projectId;
    }

    public String getRecordName() {
        return recordName;
    }

    public void setRecordName(String recordName) {
        this.recordName = recordName;
    }

    public String getMainIP() {
        return mainIP;
    }

    public void setMainIP(String mainIP) {
        this.mainIP = mainIP;
    }

    public String getMainPort() {
        return mainPort;
    }

    public void setMainPort(String mainPort) {
        this.mainPort = mainPort;
    }

    public String getMainMappingIP() {
        return mainMappingIP;
    }

    public void setMainMappingIP(String mainMappingIP) {
        this.mainMappingIP = mainMappingIP;
    }

    public String getMainMappingPort() {
        return mainMappingPort;
    }

    public void setMainMappingPort(String mainMappingPort) {
        this.mainMappingPort = mainMappingPort;
    }

    public String getMapUrl() {
        return mapUrl;
    }

    public void setMapUrl(String mapUrl) {
        this.mapUrl = mapUrl;
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
