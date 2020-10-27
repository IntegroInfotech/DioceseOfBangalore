package app.integro.dioceseofbangalore.models;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class PrincipalMessageList {

    private String message;

    @SerializedName("principal_message")
    ArrayList <PrincipalMessage> principalMessageArrayList;
    private String success;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public ArrayList <PrincipalMessage> getPrincipalMessageArrayList() {
        return principalMessageArrayList;
    }

    public void setPrincipalMessageArrayList(ArrayList <PrincipalMessage> principalMessageArrayList) {
        this.principalMessageArrayList = principalMessageArrayList;
    }

    public String getSuccess() {
        return success;
    }

    public void setSuccess(String success) {
        this.success = success;
    }

}
