package com.anonyplanet.service;

/**
 * @author 邓启航
 */
public class PicUploadResult {

    private String imgUrlFull;
    private String imgUrlSmall;
    private Integer error = 0;
    private String picId;

    public String getImgUrlSmall() {
        return imgUrlSmall;
    }

    public void setImgUrlSmall(String imgUrlSmall) {
        this.imgUrlSmall = imgUrlSmall;
    }

    public String getImgUrlFull() {
        return imgUrlFull;
    }

    public void setImgUrlFull(String imgUrlFull) {
        this.imgUrlFull = imgUrlFull;
    }

    public String getPicId() {
        return picId;
    }

    public void setPicId(String picId) {
        this.picId = picId;
    }

    public Integer getError() {
        return error;
    }

    public void setError(Integer error) {
        this.error = error;
    }

    public PicUploadResult() {
    }

    public PicUploadResult(Integer error, String imgUrlFull, String imgUrlSmall, String picId) {
        this.error = error;
        this.imgUrlFull = imgUrlFull;
        this.imgUrlSmall = imgUrlSmall;
        this.picId = picId;
    }
}
