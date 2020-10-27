package app.integro.dioceseofbangalore.models;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class CuriaList {

    @SerializedName("curia")
    private ArrayList<Curia> curiaArrayList;

    private String success;

    private String message;

    public ArrayList<Curia> getCuriaArrayList() {
        return curiaArrayList;
    }

    public void setCuriaArrayList(ArrayList<Curia> curiaArrayList) {
        this.curiaArrayList = curiaArrayList;
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
