package jfsl.ayibopost.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;

import org.json.JSONArray;

import java.util.ArrayList;
import java.util.List;

import cz.msebera.android.httpclient.Header;
import jfsl.ayibopost.models.Article;
import jfsl.ayibopost.adapters.ArticleArrayAdapter;
import jfsl.ayibopost.R;

public class BreakingFragment extends Fragment {

    ArrayList<Article> article;
    ArticleArrayAdapter articleAdapter;
    ListView lvItems;
    //private BreakingFragment fragmentBreaking;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragments_articles_list, parent, false);



        lvItems = (ListView) v.findViewById(R.id.lvArticles);
        lvItems.setAdapter(articleAdapter);

        String url = "http://ayibopost.com/wp-json/posts";

        AsyncHttpClient client = new AsyncHttpClient();


        client.get(url, new JsonHttpResponseHandler(){
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONArray response) {
                super.onSuccess(statusCode, headers, response);
                //Log.d("DEBUG", toString());
                JSONArray articleJsonPosts = null;

                articleJsonPosts = response;
                article.addAll( Article.fromJSONArray(articleJsonPosts));

                //articleAdapter.notifyDataSetChanged();

                Log.d("DEBUG", articleJsonPosts.toString() );


            }

            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONArray errorResponse) {
                super.onFailure(statusCode, headers, throwable, errorResponse);
            }
        } );



        return v;
    }

    //LifeCycle
    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);

        article = new ArrayList<>();
        articleAdapter = new ArticleArrayAdapter(getActivity(),article);

    }

    public void addAll(List<Article> articles){
        articleAdapter.addAll(articles);
    }



}


