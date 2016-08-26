package jfsl.ayibopost.models;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by kayla2 on 8/19/2016.
 */
public class Article {

    public String getLink_url() {
        return link_url;
    }

    public String link_url;
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

    public Article (JSONObject jsonObject) throws JSONException {
        this.postTitle = jsonObject.getString("title");
        this.content = jsonObject.getString("content");
        this.link_url = jsonObject.getString("link");
        this.image = jsonObject.getJSONObject("featured_image").getString("source");


    }

    public static ArrayList<Article> fromJSONArray(JSONArray array) {
        ArrayList<Article> results = new ArrayList<>();

        for (int x = 0; x < array.length(); x++){
            try {
                results.add (new Article(array.getJSONObject(x)));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return results;

    }




}
