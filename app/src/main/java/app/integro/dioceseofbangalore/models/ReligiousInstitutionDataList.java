package app.integro.dioceseofbangalore.models;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class ReligiousInstitutionDataList {

    @SerializedName("data")
    ArrayList<ReligiousInstitutionData> religiousInstitutionDataArrayList;

    private String success;

    private String message;

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

    public ArrayList<ReligiousInstitutionData> getReligiousInstitutionDataArrayList() {
        return religiousInstitutionDataArrayList;
    }

    public void setReligiousInstitutionDataArrayList(ArrayList<ReligiousInstitutionData> religiousInstitutionDataArrayList) {
        this.religiousInstitutionDataArrayList = religiousInstitutionDataArrayList;
    }
}
