package app.integro.dioceseofbangalore.models;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class OtherInstitutionDataList {

    @SerializedName("data")
    private ArrayList<OtherInstitutionData> otherInstitutionDataArrayList;

    private String success;

    private String message;

    public ArrayList<OtherInstitutionData> getOtherInstitutionDataArrayList() {
        return otherInstitutionDataArrayList;
    }

    public void setOtherInstitutionDataArrayList(ArrayList<OtherInstitutionData> otherInstitutionDataArrayList) {
        this.otherInstitutionDataArrayList = otherInstitutionDataArrayList;
    }

    public String getSuccess() {
        return success;
    }

    public void setSuccess(String success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}
