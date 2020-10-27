package app.integro.dioceseofbangalore.models;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class PalanaBavanaList2 {

    @SerializedName("data")
    private ArrayList<PalanaBavana2> bavana2ArrayList;

    private String success;

    private String message;

    public ArrayList<PalanaBavana2> getBavana2ArrayList() {
        return bavana2ArrayList;
    }

    public void setBavana2ArrayList(ArrayList<PalanaBavana2> bavana2ArrayList) {
        this.bavana2ArrayList = bavana2ArrayList;
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
