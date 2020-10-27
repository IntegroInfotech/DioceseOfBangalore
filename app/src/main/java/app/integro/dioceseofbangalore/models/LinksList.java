package app.integro.dioceseofbangalore.models;

import com.google.gson.annotations.SerializedName;

import java.util.List;


public class LinksList {

    private String message;

    @SerializedName("links")
    private List<Links> linksList;

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

    public List<Links> getLinksList() {
        return linksList;
    }

    public void setLinksList(List<Links> linksList) {
        this.linksList = linksList;
    }
}
