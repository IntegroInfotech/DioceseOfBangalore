package app.integro.dioceseofbangalore.models;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;

public class InstitutionList implements Serializable {
    private String success;

    @SerializedName("data")
    private ArrayList<Institutions> institutionArrayList;

    private String message;

    public InstitutionList(String success, ArrayList<Institutions> institutionArrayList, String message) {
        this.success = success;
        this.institutionArrayList = institutionArrayList;
        this.message = message;
    }

    public String getSuccess() {
        return success;
    }

    public ArrayList<Institutions> getInstitutionArrayList() {
        return institutionArrayList;
    }

    public String getMessage() {
        return message;
    }

    @Override
    public String toString() {
        return "InstitutionList{" +
                "success='" + success + '\'' +
                ", institutionArrayList=" + institutionArrayList +
                ", message='" + message + '\'' +
                '}';
    }
}
