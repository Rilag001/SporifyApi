package com.example.rickylagerkvist.sporifyapi.models;

import java.util.List;

/**
 * Created by Ricky on 2016-11-20.
 */
public class Album {

    private String id;
    private List<Image> images;
    private String name;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<Image> getImages() {
        return images;
    }

    public void setImages(List<Image> images) {
        this.images = images;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
