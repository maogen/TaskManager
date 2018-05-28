package com.tgram.android.task.bean;

/**
 * 项目名称：TaskManager
 * 类描述：地市，地方类
 * 创建人：mzgkq
 * 创建时间：18/3/22
 */
public class Place {

    private long placeId;
    private String placeName;
    private String description;
    private String createTime;
    private String updateTime;

    public long getPlaceId() {
        return placeId;
    }

    public void setPlaceId(long placeId) {
        this.placeId = placeId;
    }

    public String getPlaceName() {
        return placeName;
    }

    public void setPlaceName(String placeName) {
        this.placeName = placeName;
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
