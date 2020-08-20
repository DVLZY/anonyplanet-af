package com.anonyplanet.pojo;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @author 谭应有
 */
public class Works implements Serializable {
    private static final long serialVersionUID = 6728793652658664707L;
    private String works;
    private String image;
    private String thumbnail;
    private String description;
    private Double longitude;
    private Double latitude;
    private LocalDateTime time;

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getWorks() {
        return works;
    }

    public void setWorks(String works) {
        this.works = works;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public LocalDateTime getTime() {
        return time;
    }

    public void setTime(LocalDateTime time) {
        this.time = time;
    }

    public Works() {
    }


    @Override
    public String toString() {
        return "{" +
                "works='" + works + '\'' +
                ", image='" + image + '\'' +
                ", thumbnail='" + thumbnail + '\'' +
                ", description='" + description + '\'' +
                ", longitude=" + longitude +
                ", latitude=" + latitude +
                ", time=" + time +
                '}';
    }
}
