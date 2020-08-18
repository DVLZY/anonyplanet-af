package com.anonyplanet.service;

public class PicUploadResult {
    //图片上传错误不能抛出，抛出就无法进行jsp页面回调，所以设置这个标识，0表示无异常，1代表异常
    private String imgUrlFull;
    //应该是浏览器能够解析的具体页面路径  相对路径http://image.jt/1212.jpg
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
