package com.bigcake.a30daystransformbody.data;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Big Cake on 4/7/2017
 */

public class Exercise implements Serializable {
    private int id;
    private int categoryId;
    private String title;
    private String tag;
    private String images;
    private String descriptions;

    public Exercise() {
    }

    public Exercise(int id, int categoryId, String title, String tag, String images, String descriptions) {
        this.id = id;
        this.categoryId = categoryId;
        this.title = title;
        this.tag = tag;
        this.images = images;
        this.descriptions = descriptions;
    }

    public int getId() {
        return id;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public String getImages() {
        return images;
    }

    public void setImages(String images) {
        this.images = images;
    }

    public String getDescriptions() {
        return descriptions;
    }

    public void setDescriptions(String descriptions) {
        this.descriptions = descriptions;
    }
}
