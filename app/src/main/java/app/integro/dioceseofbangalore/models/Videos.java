package app.integro.dioceseofbangalore.models;

import java.io.Serializable;

public class Videos implements Serializable{

    private String id;

    private String v_id;

    private String name;

    private String description;

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

    public String getV_id ()
    {
        return v_id;
    }

    public void setV_id (String v_id)
    {
        this.v_id = v_id;
    }

    public String getDescription ()
    {
        return description;
    }

    public void setDescription (String description)
    {
        this.description = description;
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
