package app.integro.dioceseofbangalore.models;

import java.io.Serializable;

public class CuriaData implements Serializable {

    private String id;

    private String title;

    private String description;

    public String getDescription ()
    {
        return description;
    }

    public void setDescription (String description)
    {
        this.description = description;
    }

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

}
