package app.integro.dioceseofbangalore.models;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity
public class Parishes implements Serializable {

    @PrimaryKey(autoGenerate = true)
    private int id;

    @ColumnInfo(name = "name")
    private String name;

    @ColumnInfo(name = "timing")
    private String timing;

    @ColumnInfo(name = "suntiming")
    private String suntiming;

    @ColumnInfo(name = "adoration")
    private String adoration;

    @ColumnInfo(name = "address")
    private String address;

    @ColumnInfo(name = "contact")
    private String contact;

    @ColumnInfo(name = "priest")
    private String priest;

    @ColumnInfo(name = "apriest")
    private String apriest;

    @ColumnInfo(name = "email")
    private String email;

    @ColumnInfo(name = "website")
    private String website;

    @ColumnInfo(name = "updated_date")
    private String updated_date;

    public String getApriest() {
        return apriest;
    }

    public void setApriest(String apriest) {
        this.apriest = apriest;
    }

    private String image;

    public String getPriest() {
        return priest;
    }

    public void setPriest(String priest) {
        this.priest = priest;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUpdated_date() {
        return updated_date;
    }

    public void setUpdated_date(String updated_date) {
        this.updated_date = updated_date;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSuntiming() {
        return suntiming;
    }

    public void setSuntiming(String suntiming) {
        this.suntiming = suntiming;
    }

    public String getAdoration() {
        return adoration;
    }

    public void setAdoration(String adoration) {
        this.adoration = adoration;
    }

    public String getTiming() {
        return timing;
    }

    public void setTiming(String timing) {
        this.timing = timing;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }
}
