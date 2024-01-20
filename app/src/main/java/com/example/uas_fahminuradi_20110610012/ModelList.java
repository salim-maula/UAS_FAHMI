package com.example.uas_fahminuradi_20110610012;

public class ModelList {
    private String id, title, keterangan, image;


    public ModelList(String id, String title, String keterangan, String img) {
        this.title = title;
        this.keterangan = keterangan;
        this.image = img;
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getKeterangan() {
        return keterangan;
    }

    public void setKeterangan(String keterangan) {
        this.keterangan = keterangan;
    }

    public String getImg() {
        return image;
    }

    public void setImg(String img) {
        this.image = img;
    }
}

