package app.integro.dioceseofbangalore.models;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;


public class OrganisationList {

    private String message;

    @SerializedName("organisation")
    ArrayList<Organisation> organisationArrayList;

    public ArrayList<Organisation> getOrganisationArrayList() {
        return organisationArrayList;
    }

    public void setOrganisationArrayList(ArrayList<Organisation> organisationArrayList) {
        this.organisationArrayList = organisationArrayList;
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
