package jfsl.ayibopost.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import jfsl.ayibopost.R;
import jfsl.ayibopost.fragments.BusinessFragment;

public class BusinessActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_business);

        BusinessFragment businessFragment = new BusinessFragment();
        getSupportFragmentManager().beginTransaction().replace(R.id.businessContainer,businessFragment).commit();


    }
}
