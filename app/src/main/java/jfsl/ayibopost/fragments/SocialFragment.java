package jfsl.ayibopost.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;

import org.json.JSONArray;

import java.util.ArrayList;
import java.util.List;

import cz.msebera.android.httpclient.Header;
import jfsl.ayibopost.R;
import jfsl.ayibopost.adapters.SocialArrayAdapter;
import jfsl.ayibopost.models.Social;

/**
 * Created by kayla2 on 8/19/2016.
 */
public class SocialFragment extends Fragment {

    ArrayList<Social> social;
    SocialArrayAdapter socialAdapter;
    ListView lvBusiness;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        // Inflate the layout for this fragment
        View v= inflater.inflate(R.layout.fragment_business, container, false);

        lvBusiness = (ListView) v.findViewById(R.id.lvBusiness);
        lvBusiness.setAdapter(socialAdapter);

        String url = "http://ayibopost.com/wp-json/posts?filter[category_name]=SOCIAL";

        AsyncHttpClient client = new AsyncHttpClient();


        client.get(url, new JsonHttpResponseHandler(){
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONArray response) {
                super.onSuccess(statusCode, headers, response);
                //Log.d("DEBUG", toString());
                JSONArray socialJsonPosts = null;

                socialJsonPosts = response;
                social.addAll(Social.fromJSONArray(socialJsonPosts));

                //articleAdapter.notifyDataSetChanged();

                Log.d("DEBUG", socialJsonPosts.toString() );


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

        social = new ArrayList<>();
        socialAdapter = new SocialArrayAdapter(getActivity(),social);

    }

    public void addAll(List<Social> socials){
        socialAdapter.addAll(socials);
    }



}

