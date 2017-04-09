package com.bigcake.a30daystransformbody.data;

import java.util.List;

/**
 * Created by Big Cake on 4/7/2017
 */

public class Exercise {
    private int id;
    private int categoryId;
    private String title;
    private String tag;
    private int type;
    private List<Integer> images;
    private List<String> descriptions;

    public Exercise() {
    }

    public Exercise(int id, int categoryId, String title, String tag, int type, List<Integer> images, List<String> descriptions) {
        this.id = id;
        this.categoryId = categoryId;
        this.title = title;
        this.tag = tag;
        this.type = type;
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

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public List<Integer> getImages() {
        return images;
    }

    public void setImages(List<Integer> images) {
        this.images = images;
    }

    public List<String> getDescriptions() {
        return descriptions;
    }

    public void setDescriptions(List<String> descriptions) {
        this.descriptions = descriptions;
    }
}
