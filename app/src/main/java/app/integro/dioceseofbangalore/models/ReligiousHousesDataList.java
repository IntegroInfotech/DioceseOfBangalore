package app.integro.dioceseofbangalore.models;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class ReligiousHousesDataList {

    @SerializedName("data")
    private ArrayList<ReligiousHousesData> religiousHousesData;

    private String success;

    private String message;

    public ArrayList<ReligiousHousesData> getReligiousHousesData() {
        return religiousHousesData;
    }

    public void setReligiousHousesData(ArrayList<ReligiousHousesData> religiousHousesData) {
        this.religiousHousesData = religiousHousesData;
    }

    public String getSuccess ()
    {
        return success;
    }

    public void setSuccess (String success)
    {
        this.success = success;
    }

    public String getMessage ()
    {
        return message;
    }

    public void setMessage (String message)
    {
        this.message = message;
    }

}
