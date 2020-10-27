package app.integro.dioceseofbangalore.models;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;


public class NewsImagesList {

    private String message;

    @SerializedName("news_images")
    private ArrayList <NewsImages> newsImagesArrayList;

    private String success;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public ArrayList <NewsImages> getNewsImagesArrayList() {
        return newsImagesArrayList;
    }

    public void setNewsImagesArrayList(ArrayList <NewsImages> newsImagesArrayList) {
        this.newsImagesArrayList = newsImagesArrayList;
    }

    public String getSuccess() {
        return success;
    }

    public void setSuccess(String success) {
        this.success = success;
    }

}
