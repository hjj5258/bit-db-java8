package com.varun.db.util;

public class DiskWriterResponse {
    private String fileName;
    private Integer valuePosition;

    public DiskWriterResponse(String fileName, Integer valuePosition) {
        this.fileName = fileName;
        this.valuePosition = valuePosition;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public Integer getValuePosition() {
        return valuePosition;
    }

    public void setValuePosition(Integer valuePosition) {
        this.valuePosition = valuePosition;
    }
}
