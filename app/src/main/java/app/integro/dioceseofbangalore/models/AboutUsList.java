package app.integro.dioceseofbangalore.models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class AboutUsList {
    private String message;

   @SerializedName("aboutus")
   private List<AboutUs>aboutUsList;

    public List<AboutUs> getAboutUsList() {
        return aboutUsList;
    }

    public void setAboutUsList(List<AboutUs> aboutUsList) {
        this.aboutUsList = aboutUsList;
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
