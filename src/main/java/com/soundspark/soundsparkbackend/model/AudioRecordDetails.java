package com.soundspark.soundsparkbackend.model;

public class AudioRecordDetails {
    private Integer size;
    private String type;

    public AudioRecordDetails(Integer size, String type) {
        this.size = size;
        this.type = type;
    }

    public Integer getSize() {
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
