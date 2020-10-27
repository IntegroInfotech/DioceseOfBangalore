package app.integro.dioceseofbangalore.models;

import java.io.Serializable;

public class Circular implements Serializable {

    private String id;

    private String name;

    private String link;

    private String updated_date;

    public String getId ()
    {
        return id;
    }

    public void setId (String id)
    {
        this.id = id;
    }

    public String getUpdated_date ()
    {
        return updated_date;
    }

    public void setUpdated_date (String updated_date)
    {
        this.updated_date = updated_date;
    }

    public String getLink ()
    {
        return link;
    }

    public void setLink (String link)
    {
        this.link = link;
    }

    public String getName ()
    {
        return name;
    }

    public void setName (String name)
    {
        this.name = name;
    }
}
