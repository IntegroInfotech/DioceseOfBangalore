package app.integro.dioceseofbangalore.models;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;


public class PublicationList {
    private String message;

    @SerializedName("publications")
    private ArrayList<Publications> publicationsArrayList;

    private String success;

    public PublicationList(String message, ArrayList<Publications> publicationsArrayList, String success) {
        this.message = message;
        this.publicationsArrayList = publicationsArrayList;
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public ArrayList<Publications> getPublicationsArrayList() {
        return publicationsArrayList;
    }

    public String getSuccess() {
        return success;
    }
}
