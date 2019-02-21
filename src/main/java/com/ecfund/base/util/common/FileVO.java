package com.ecfund.base.util.common;


import java.io.Serializable;

public class FileVO implements Serializable {
    /**
     *
     */
    private static final long serialVersionUID = 1L;
    private String filePath;//文件路径 相对路径，不带根路径
    private String fileUrl;//文件URL路径
    private String type;// 1 文件目录 2 文件

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public String getFileUrl() {
        return fileUrl;
    }

    public void setFileUrl(String fileUrl) {
        this.fileUrl = fileUrl;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

}