package app.integro.dioceseofbangalore.models;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class CuriaDataList {

    @SerializedName("data")
    private ArrayList<CuriaData> curiaDataArrayList;

    private String success;

    private String message;

    public ArrayList<CuriaData> getCuriaDataArrayList() {
        return curiaDataArrayList;
    }

    public void setCuriaDataArrayList(ArrayList<CuriaData> curiaDataArrayList) {
        this.curiaDataArrayList = curiaDataArrayList;
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
