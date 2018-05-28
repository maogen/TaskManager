package com.tgram.android.task.bean_request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 项目名称：TaskManager
 * 类描述：
 * 创建人：mzgkq
 * 创建时间：18/3/26
 */
@ApiModel(value = "新增记录实体类", description = "新增记录实体类json数据")
public class RecordInsert {
    @ApiModelProperty(value = "项目id")
    private Long projectId;
    @ApiModelProperty(value = "自定义记录名")
    private String recordName;
    @ApiModelProperty(value = "服务器ip地址", example = "192.168.0.37")
    private String mainIP;
    @ApiModelProperty(value = "服务器端口", example = "8080")
    private String mainPort;
    @ApiModelProperty(value = "映射后的ip地址", example = "10.10.0.1")
    private String mainMappingIP;
    @ApiModelProperty(value = "映射后的端口", example = "17001")
    private String mainMappingPort;
    @ApiModelProperty(value = "地图url", example = "http://192.168.20.90:17002/map/china/zhongshan/zhongshanpgissltoandroid.xml")
    private String mapUrl;
    @ApiModelProperty(value = "记录描述")
    private String description;

    public Long getProjectId() {
        return projectId;
    }

    public void setProjectId(Long projectId) {
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
}
