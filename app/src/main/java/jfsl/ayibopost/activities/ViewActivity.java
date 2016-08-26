package jfsl.ayibopost.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;

import org.json.JSONArray;

import java.util.ArrayList;

import cz.msebera.android.httpclient.Header;
import jfsl.ayibopost.R;
import jfsl.ayibopost.adapters.ArticleArrayAdapter;
import jfsl.ayibopost.models.Article;

public class ViewActivity extends AppCompatActivity {


    ListView lvArticles;


    ArrayList<Article> articles;
    ArticleArrayAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view);


    }

    public void setupViews(){

        lvArticles = (ListView) findViewById(R.id.lvArticles);

        articles = new ArrayList<>();
       adapter =new ArticleArrayAdapter(this , articles);
        lvArticles.setAdapter(adapter);

        lvArticles.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // create an intent to display the article
                Intent i = new Intent(getApplicationContext(), ViewActivity.class);
                //get the article to display

                Article article = articles.get(position);
                // pass in that article into intent
                i.putExtra("url", article.getLink_url());
                // launch the activity
                startActivity(i);
            }

        });
    }




    public void onArticleView(View view) {


        AsyncHttpClient client = new AsyncHttpClient();
        String url = "http://ayibopost.com/wp-json/posts";

        client.get(url, new JsonHttpResponseHandler(){
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONArray response) {
                super.onSuccess(statusCode, headers, response);
                //Log.d("DEBUG", toString());
                JSONArray articleJsonPosts = null;

                articleJsonPosts = response;
                articles.addAll( Article.fromJSONArray(articleJsonPosts));

                //articleAdapter.notifyDataSetChanged();

                Log.d("DEBUG", articleJsonPosts.toString() );


            }
        });
    }
}
