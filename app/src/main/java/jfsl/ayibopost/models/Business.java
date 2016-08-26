package jfsl.ayibopost.models;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by carlybaja on 8/25/16.
 */
public class Business {

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

    public Business (JSONObject jsonObject) throws JSONException {
        this.postTitle = jsonObject.getString("title");
        this.content = jsonObject.getString("content");
        this.image = jsonObject.getJSONObject("featured_image").getString("source");


    }

    public static ArrayList<Business> fromJSONArray(JSONArray array) {
        ArrayList<Business> results = new ArrayList<>();

        for (int x = 0; x < array.length(); x++){
            try {
                results.add (new Business(array.getJSONObject(x)));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return results;

    }
}
