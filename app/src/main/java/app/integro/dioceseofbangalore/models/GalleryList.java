package app.integro.dioceseofbangalore.models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class GalleryList {

    private String message;

    @SerializedName("gallery")
    private List<Gallery> galleryList;

    public List<Gallery> getGalleryList() {
        return galleryList;
    }

    public void setGalleryList(List<Gallery> galleryList) {
        this.galleryList = galleryList;
    }

    private String success;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getSuccess() {
        return success;
    }

    public void setSuccess(String success) {
        this.success = success;
    }
}
