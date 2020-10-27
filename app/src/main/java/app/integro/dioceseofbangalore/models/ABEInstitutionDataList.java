package app.integro.dioceseofbangalore.models;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class ABEInstitutionDataList {

    private String message;

    @SerializedName("data")
    private ArrayList<ABEInstitutionData> ABEInstitutionData;

    private String success;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public ArrayList<ABEInstitutionData> getABEInstitutionData() {
        return ABEInstitutionData;
    }

    public void setABEInstitutionData(ArrayList<ABEInstitutionData> ABEInstitutionData) {
        this.ABEInstitutionData = ABEInstitutionData;
    }

    public String getSuccess() {
        return success;
    }

    public void setSuccess(String success) {
        this.success = success;
    }
}
