package app.integro.dioceseofbangalore.models;

import com.google.gson.annotations.SerializedName;

import java.util.List;


public class CircularList {

    private String message;

    @SerializedName("data")
    private List<Circular> circularList;

    public List<Circular> getCircularList() {
        return circularList;
    }

    public void setCircularList(List<Circular> circularList) {
        this.circularList = circularList;
    }

    private String success;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getSuccess() {
        return success;
    }

    public void setSuccess(String success) {
        this.success = success;
    }
}
