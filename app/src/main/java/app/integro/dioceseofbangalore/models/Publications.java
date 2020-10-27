package app.integro.dioceseofbangalore.models;


import java.io.Serializable;

public class Publications implements Serializable {

    private String id;

    private String name;

    private String image;

    private String url_pdf;

    public Publications(String id, String name, String image, String url_pdf) {
        this.id = id;
        this.name = name;
        this.image = image;
        this.url_pdf = url_pdf;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getImage() {
        return image;
    }

    public String getUrl_pdf() {
        return url_pdf;
    }
}
