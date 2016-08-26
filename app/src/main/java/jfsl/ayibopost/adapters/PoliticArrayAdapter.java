package jfsl.ayibopost.adapters;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import jfsl.ayibopost.R;
import jfsl.ayibopost.models.Politic;

public class PoliticArrayAdapter extends ArrayAdapter<Politic> {

public PoliticArrayAdapter (Context context, ArrayList<Politic> politic) {
        super(context, android.R.layout.simple_list_item_1, politic);
        }

// Model=> view
@Override
public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data item for this position
        Politic politics = getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {

        LayoutInflater inflater = LayoutInflater.from(getContext());
        convertView= inflater.inflate(R.layout.item_menu,parent,false);
        }

        //find the image view
        ImageView ivArticleImage = (ImageView) convertView.findViewById(R.id.ivArticleImage);
        // clear out image from convert view
        ivArticleImage.setImageResource(0);

        TextView tvTitle = (TextView) convertView.findViewById(R.id.tvArticleTitle);
        TextView tvContenu = (TextView) convertView.findViewById(R.id.tvArticleContent);
        //TextView tvAuteur = (TextView) convertView.findViewById(R.id.tvAuteur);

        // populate data
        tvTitle.setText(politics.getPostTitle());
        tvContenu.setText(politics.getContent());

        Picasso.with(getContext()).load(politics.getImage()).into(ivArticleImage);

        // return the view
        return convertView;

        }
        }

