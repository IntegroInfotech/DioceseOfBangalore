package app.integro.dioceseofbangalore.models;

import java.io.Serializable;

public class News implements Serializable{
    private String id;

    private String title;

    private String updated_date;

    private String description;

    private String l_img;

    private String date;

    public String getId ()
    {
        return id;
    }

    public void setId (String id)
    {
        this.id = id;
    }

    public String getTitle ()
    {
        return title;
    }

    public void setTitle (String title)
    {
        this.title = title;
    }

    public String getUpdated_date ()
    {
        return updated_date;
    }

    public void setUpdated_date (String updated_date)
    {
        this.updated_date = updated_date;
    }

    public String getL_img() {
        return l_img;
    }

    public void setL_img(String l_img) {
        this.l_img = l_img;
    }

    public String getDescription ()
    {
        return description;
    }

    public void setDescription (String description)
    {
        this.description = description;
    }


    public String getDate ()
    {
        return date;
    }

    public void setDate (String date)
    {
        this.date = date;
    }
}
