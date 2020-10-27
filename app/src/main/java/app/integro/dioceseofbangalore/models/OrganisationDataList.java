package app.integro.dioceseofbangalore.models;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;



public class OrganisationDataList {

    private String message;

    @SerializedName("data")
    private ArrayList<OrganisationData> organisationDataArrayList;

    public ArrayList<OrganisationData> getOrganisationDataArrayList() {
        return organisationDataArrayList;
    }

    public void setOrganisationDataArrayList(ArrayList<OrganisationData> organisationDataArrayList) {
        this.organisationDataArrayList = organisationDataArrayList;
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
