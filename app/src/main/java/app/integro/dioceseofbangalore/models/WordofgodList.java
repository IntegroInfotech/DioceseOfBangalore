package app.integro.dioceseofbangalore.models;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class WordofgodList {
    private String message;

    @SerializedName("data")
    private ArrayList<WordOfGod> wordofgod;

    private String success;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public ArrayList<WordOfGod> getWordofgod() {
        return wordofgod;
    }

    public void setWordofgod(ArrayList<WordOfGod> wordofgod) {
        this.wordofgod = wordofgod;
    }

    public String getSuccess() {
        return success;
    }

    public void setSuccess(String success) {
        this.success = success;
    }
}
