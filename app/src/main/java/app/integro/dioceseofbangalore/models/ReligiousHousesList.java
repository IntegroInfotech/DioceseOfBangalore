package app.integro.dioceseofbangalore.models;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class ReligiousHousesList {

    private String success;

    @SerializedName("religious_houses")
    ArrayList<ReligiousHouses> religiousHousesArrayList;


    private String message;

    public String getSuccess ()
    {
        return success;
    }

    public void setSuccess (String success)
    {
        this.success = success;
    }

    public ArrayList<ReligiousHouses> getReligiousHousesArrayList() {
        return religiousHousesArrayList;
    }

    public void setReligiousHousesArrayList(ArrayList<ReligiousHouses> religiousHousesArrayList) {
        this.religiousHousesArrayList = religiousHousesArrayList;
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
