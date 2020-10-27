package app.integro.dioceseofbangalore.models;

import java.io.Serializable;

public class Notification implements Serializable{
    private String id;

    private String title;

    private String description;

    private String date;

    private String topicname;

    private String link;

    private String updated_date;

    public Notification(String id, String title, String description, String date, String topicname, String link, String updated_date) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.date = date;
        this.topicname = topicname;
        this.link = link;
        this.updated_date = updated_date;
    }

    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getDate() {
        return date;
    }

    public String getTopicname() {
        return topicname;
    }

    public String getLink() {
        return link;
    }

    public String getUpdated_date() {
        return updated_date;
    }
}
