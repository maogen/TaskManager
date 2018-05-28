package com.tgram.android.task.bean_request;

import io.swagger.annotations.ApiParam;

/**
 * 项目名称：TaskManager
 * 类描述：
 * 创建人：mzgkq
 * 创建时间：18/3/26
 */
public class RecordUpdate {
    @ApiParam(value = "记录id")
    private Long recordId;
    @ApiParam(value = "自定义记录名")
    private String recordName;
    @ApiParam(value = "服务器ip地址，例如：192.168.0.37")
    private String mainIP;
    @ApiParam(value = "服务器端口，例如：8080")
    private String mainPort;
    @ApiParam(value = "映射后的ip地址，例如：10.10.0.1")
    private String mainMappingIP;
    @ApiParam(value = "映射后的端口，例如：17001")
    private String mainMappingPort;
    @ApiParam(value = "地图url，例如：http://192.168.20.90:17002/map/china/zhongshan/zhongshanpgissltoandroid.xml")
    private String mapUrl;
    @ApiParam(value = "记录描述")
    private String description;

    public Long getRecordId() {
        return recordId;
    }

    public void setRecordId(Long recordId) {
        this.recordId = recordId;
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
