package app.integro.dioceseofbangalore.models;

import com.google.gson.annotations.SerializedName;

import java.util.List;


public class ParishesList {
    private String message;

    @SerializedName("mass")
    private List<Parishes> parishesList;

    public List<Parishes> getParishesList() {
        return parishesList;
    }

    public void setParishesList(List<Parishes> parishesList) {
        this.parishesList = parishesList;
    }

    private String success;

    public String getMessage ()
    {
        return message;
    }

    public void setMessage (String message)
    {
        this.message = message;
    }


    public String getSuccess ()
    {
        return success;
    }

    public void setSuccess (String success)
    {
        this.success = success;
    }
    }
