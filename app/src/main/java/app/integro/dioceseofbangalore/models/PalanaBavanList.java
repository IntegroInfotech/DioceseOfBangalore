package app.integro.dioceseofbangalore.models;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class PalanaBavanList {

    private String success;

    @SerializedName("palanaa")
    private ArrayList<PalanaBavan> palanaBavanArrayList;

    private String message;

    public String getSuccess ()
    {
        return success;
    }

    public void setSuccess (String success)
    {
        this.success = success;
    }

    public ArrayList<PalanaBavan> getPalanaBavanArrayList() {
        return palanaBavanArrayList;
    }

    public void setPalanaBavanArrayList(ArrayList<PalanaBavan> palanaBavanArrayList) {
        this.palanaBavanArrayList = palanaBavanArrayList;
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
