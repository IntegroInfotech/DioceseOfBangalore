package app.integro.dioceseofbangalore.models;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class ADSList {

    @SerializedName("ad")
    private ArrayList<ADS> adsArrayList;

    private String success;

    private String message;

    public ArrayList<ADS> getAdsArrayList() {
        return adsArrayList;
    }

    public void setAdsArrayList(ArrayList<ADS> adsArrayList) {
        this.adsArrayList = adsArrayList;
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
