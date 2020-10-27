package app.integro.dioceseofbangalore.models;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity(tableName = "Institutions")
public class Institutions implements Serializable {

    @PrimaryKey
    private int id;
    private String name;
    private String tag;

    public Institutions(int id, String name, String tag) {
        this.id = id;
        this.name = name;
        this.tag = tag;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getTag() {
        return tag;
    }
}
