package com.appants.adspro;

import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.transition.Explode;
import android.transition.Transition;
import android.util.Log;
import android.view.MenuItem;
import android.view.Window;
import android.widget.TextView;

public class HomeActivity extends AppCompatActivity implements DashboardFragment.OnFragmentInteractionListener{


    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            Fragment selectedFragment=null;
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    selectedFragment=DashboardFragment.newInstance("","");
                    break;
                case R.id.navigation_dashboard:
                    selectedFragment=new MyNetworkFragment();//MyNetworkFragment.newInstance("","");
                    break;
                case R.id.navigation_notifications:
                    selectedFragment=DealsFragment.newInstance("","");
                    break;
                case R.id.navigation_settings:
                    selectedFragment=SettingsFragment.newInstance("","");
                    break;
            }

            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            transaction.replace(R.id.content, selectedFragment);
            transaction.commit();
            return true;
        }

    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().requestFeature(Window.FEATURE_CONTENT_TRANSITIONS);

            //set the transition
            Transition ts = new Explode();
            ts.setDuration(5000);
            getWindow().setEnterTransition(ts);
            getWindow().setExitTransition(ts);
        }
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);


        //Manually displaying the first fragment - one time only
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.content, DashboardFragment.newInstance("",""));
        transaction.commit();
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

        Log.i("onFragmentInteraction",":"+uri);
    }
}
