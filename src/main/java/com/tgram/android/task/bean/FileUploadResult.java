package com.tgram.android.task.bean;

/**
 * 项目名称：TaskManager
 * 类描述：文件上传结果
 * 创建人：mzgkq
 * 创建时间：2018/4/2
 */
public class FileUploadResult {
    private String id;
    private String name;
    private String url;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
