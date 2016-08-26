package jfsl.ayibopost.models;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;


public class Politic {

    public String postTitle;

    public String image;
    public String content;

    public String getPostTitle() {
        return postTitle;
    }



    public String getImage() {
        return image;
    }

    public String getContent() {
        return content;
    }

    public Politic (JSONObject jsonObject) throws JSONException {
        this.postTitle = jsonObject.getString("title");
        this.content = jsonObject.getString("content");
        this.image = jsonObject.getJSONObject("featured_image").getString("source");


    }

    public static ArrayList<Politic> fromJSONArray(JSONArray array) {
        ArrayList<Politic> results = new ArrayList<>();

        for (int x = 0; x < array.length(); x++){
            try {
                results.add (new Politic(array.getJSONObject(x)));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return results;

    }
}
