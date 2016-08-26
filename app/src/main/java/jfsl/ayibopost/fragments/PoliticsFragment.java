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
import jfsl.ayibopost.R;
import jfsl.ayibopost.adapters.PoliticArrayAdapter;
import jfsl.ayibopost.models.Politic;


public class PoliticsFragment extends Fragment {

    ArrayList<Politic> politic;
    PoliticArrayAdapter politicAdapter;
    ListView lvBusiness;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        // Inflate the layout for this fragment
        View v= inflater.inflate(R.layout.fragment_business, container, false);

        lvBusiness = (ListView) v.findViewById(R.id.lvBusiness);
        lvBusiness.setAdapter(politicAdapter);

        String url = "http://ayibopost.com/wp-json/posts?filter[category_name]=POLITICS";

        AsyncHttpClient client = new AsyncHttpClient();


        client.get(url, new JsonHttpResponseHandler(){
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONArray response) {
                super.onSuccess(statusCode, headers, response);
                //Log.d("DEBUG", toString());
                JSONArray politicJsonPosts = null;

                politicJsonPosts = response;
                politic.addAll(Politic.fromJSONArray(politicJsonPosts));

                //articleAdapter.notifyDataSetChanged();

                Log.d("DEBUG", politicJsonPosts.toString() );


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

        politic = new ArrayList<>();
        politicAdapter = new PoliticArrayAdapter(getActivity(),politic);

    }

    public void addAll(List<Politic> politics){ politicAdapter.addAll(politics);
    }



}
