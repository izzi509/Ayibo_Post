package jfsl.ayibopost.activities;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.widget.Toast;

import com.astuetz.PagerSlidingTabStrip;

import jfsl.ayibopost.R;
import jfsl.ayibopost.fragments.AyiboTalkFragment;
import jfsl.ayibopost.fragments.BreakingFragment;
import jfsl.ayibopost.fragments.BusinessFragment;
import jfsl.ayibopost.fragments.LifestyleFragment;
import jfsl.ayibopost.fragments.PoliticsFragment;
import jfsl.ayibopost.fragments.SportsFragment;

public class ArticlesActivity extends AppCompatActivity {

    /*ArrayList<Article> article;
    ArticleArrayAdapter articleAdapter;
    ListView lvItems;*/

    private BreakingFragment fragmentBreaking;






    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_articles);



        ViewPager vpPager = (ViewPager) findViewById(R.id.viewpager);

        vpPager.setAdapter(new ArticlesPagerAdapter(getSupportFragmentManager()));

        PagerSlidingTabStrip tabStrip = (PagerSlidingTabStrip) findViewById(R.id.tabs);

        tabStrip.setViewPager(vpPager);


        String url = "http://ayibopost.com/wp-json/posts";

        if (savedInstanceState == null){

            fragmentBreaking = (BreakingFragment) getSupportFragmentManager().findFragmentById(R.id.fragment_articles);

        }



        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ActionBar actionBar = getSupportActionBar();
        getSupportActionBar().setTitle("");







    }






    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_articles, menu);
        return true;
    }


    public class ArticlesPagerAdapter extends FragmentPagerAdapter {
        final int PAGE_COUNT = 7;
        private String tabTitles[] = new String[]{"Breaking", "Business", "Social", "Sports", "Politics", "LifeStyle","AyiboTalk"};

        public ArticlesPagerAdapter(FragmentManager fm) {
            super(fm);
        }


        @Override
        public int getCount() {
            return PAGE_COUNT;
        }

        @Override
        public Fragment getItem(int position) {
            if (position == 0) {
                return new BreakingFragment();
            } else if (position == 1) {
                Toast.makeText(getApplicationContext(),"test 2",Toast.LENGTH_LONG).show();
                return new BusinessFragment();
            } else if (position == 2) {
                Toast.makeText(getApplicationContext(),"test 3",Toast.LENGTH_LONG).show();
                return new BusinessFragment();
            } else if (position == 3) {
                return new SportsFragment();
            } else if (position == 4) {
                return new PoliticsFragment();
            } else if (position == 5) {
                return new LifestyleFragment();
            }else if (position == 6) {
                return new AyiboTalkFragment();
            }
            else {
                return null;
            }
        }

        @Override
        public CharSequence getPageTitle(int position) {
            // Generate title based on item position
            return tabTitles[position];
        }
    }
}
