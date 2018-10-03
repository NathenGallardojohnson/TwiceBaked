package com.example.nathengallardojohnson.twicebaked.model;

public class Steps {
    private int id;
    private String shortDescription = " ";
    private String description = " ";
    private String videoURL = " ";
    private String thumbnailURL = " ";

    public Steps() {
    }

    public Steps(int id, String shortDescription, String description, String videoURL, String thumbnailURL) {
        super();
        this.id = id;
        this.shortDescription = shortDescription;
        this.description = description;
        this.videoURL = videoURL;
        this.thumbnailURL = thumbnailURL;
    }

    public Steps(Steps steps) {
        super();
        this.id = steps.getId();
        this.shortDescription = steps.getShortDescription();
        this.description = steps.getDescription();
        this.videoURL = steps.getVideoURL();
        this.thumbnailURL = steps.getThumbnailURL();
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getShortDescription() {
        return this.shortDescription;
    }

    public void setShortDescription(String shortDescription) { this.shortDescription = shortDescription; }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) { this.description = description; }

    public String getVideoURL() {
        return this.videoURL;
    }

    public void setVideoURL(String videoURL) { this.videoURL = videoURL; }

    public String getThumbnailURL() {
        return this.thumbnailURL;
    }

    public void setThumbnailURL(String thumbnailURL) {
        this.thumbnailURL = thumbnailURL;
    }
}
