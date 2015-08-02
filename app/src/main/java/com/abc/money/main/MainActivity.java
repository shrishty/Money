package com.abc.money.main;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;

import com.abc.money.tabs.SlidingTabLayout;


public class MainActivity extends AppCompatActivity {
//    private Toolbar toolbar;
    ViewPager pager;
    ViewPagerAdapter adapter;
    SlidingTabLayout tabs;
    CharSequence titles[] = {"Home", "Take", "Give"};
    int numberOfTabs = 3;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        SharedPreferences settings = getSharedPreferences(LoginActivity.PREFS_NAME, 0);

        boolean hasLoggedIn = settings.getBoolean("hasLoggedIn", false);

        if(!hasLoggedIn)
        {
            Intent activityChangeIntent = new Intent(MainActivity.                                                                                                      this, LoginActivity.class);
            MainActivity.this.startActivity(activityChangeIntent);
        }

        setContentView(R.layout.activity_main);

        adapter = new ViewPagerAdapter(getSupportFragmentManager(), titles, numberOfTabs);
        pager = (ViewPager) findViewById(R.id.pager);
        pager.setAdapter(adapter);

        tabs = (SlidingTabLayout) findViewById(R.id.tabs);
        tabs.setDistributeEvenly(true);

        tabs.setCustomTabColorizer(new SlidingTabLayout.TabColorizer(){
            @Override
            public int getIndicatorColor(int position) {
                return getResources().getColor(R.color.tabsScrollColor);
            }
        });
        tabs.setViewPager(pager);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}