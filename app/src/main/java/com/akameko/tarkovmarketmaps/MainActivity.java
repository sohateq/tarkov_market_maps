package com.akameko.tarkovmarketmaps;

import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.akameko.tarkovmarketmaps.databinding.ActivityMainBinding;
import com.akameko.tarkovmarketmaps.tabs.market.MarketFragment;
import com.akameko.tarkovmarketmaps.tabs.home.HomeFragment;
import com.akameko.tarkovmarketmaps.tabs.map.MapFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener {

    private final static String TAG_HOME = "home";
    private final static String TAG_MARKET = "market";
    private final static String TAG_MAP = "map";

    //ActivityMainBinding binding;
    private HomeFragment homeFragment;
    private MarketFragment marketFragment;
    private MapFragment mapFragment;

    FragmentManager fm;
    ArrayList<Fragment> fragmentList;
    BottomNavigationView navView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //binding = ActivityMainBinding.inflate(getLayoutInflater());
        //setContentView(binding.getRoot());


        fm = getSupportFragmentManager();


        navView = findViewById(R.id.nav_view);
        navView.setOnNavigationItemSelectedListener(this);


        initFragments();


    }

    private void initFragments() {


        homeFragment = (HomeFragment) fm.findFragmentByTag(TAG_HOME);
        marketFragment = (MarketFragment) fm.findFragmentByTag(TAG_MARKET);
        mapFragment = (MapFragment) fm.findFragmentByTag(TAG_MAP);

        if (homeFragment == null) {
            homeFragment = new HomeFragment();
            fm.beginTransaction().add(R.id.nav_host_fragment, homeFragment, TAG_HOME).hide(homeFragment).commit();
        }
        if (marketFragment == null) {
            marketFragment = new MarketFragment();
            fm.beginTransaction().add(R.id.nav_host_fragment, marketFragment, TAG_MARKET).commit();
            navView.setSelectedItemId(R.id.navigation_market);
        }
        if (mapFragment == null) {
            mapFragment = new MapFragment();
            fm.beginTransaction().add(R.id.nav_host_fragment, mapFragment, TAG_MAP).hide(mapFragment).commit();
        }

        fragmentList = new ArrayList<>();
        fragmentList.add(homeFragment);
        fragmentList.add(marketFragment);
        fragmentList.add(mapFragment);


    }


    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()) {
            case R.id.navigation_home:

                if (homeFragment.isHidden()) {
                    hideAll();
                    fm.beginTransaction()

                            .show(homeFragment)
                            .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
                            .commit();

                }
                return true;

            case R.id.navigation_market:
                if (marketFragment.isHidden()) {
                    hideAll();
                    fm.beginTransaction()

                            .show(marketFragment)
                            .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
                            .commit();

                }
                return true;
            case R.id.navigation_map:
                if (mapFragment.isHidden()) {
                    hideAll();
                    fm.beginTransaction()
                            .show(mapFragment)
                            .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
                            .commit();

                }
                return true;
        }

        return false;
    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    private void hideAll() {

        for (Fragment fragment : fragmentList) {
            fm.beginTransaction()
                    .hide(fragment)
                    .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
                    .commit();
        }
    }

    @Override
    protected void onDestroy() {
        //binding = null;
        super.onDestroy();
    }
}
