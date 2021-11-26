package rob.instagramapprealdemo;

import androidx.appcompat.app.ActionBar;
import androidx.viewpager.widget.ViewPager;

import android.content.Context;
import android.os.Bundle;

import com.google.android.material.tabs.TabLayout;

import rob.instagramapprealdemo.TabLayoutPackage.FragmentCalling;
import rob.instagramapprealdemo.TabLayoutPackage.FragmentPost;
import rob.instagramapprealdemo.TabLayoutPackage.ViewPagerAdapter;

public class MainActivity extends MainHelperClass {


    TabLayout tabLayout;
    ViewPagerAdapter viewPagerAdapter;
    ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        initialComponentLayout();
        initTabLayout_Adapter_viewPager();
    }

    private void initialComponentLayout() {
        tabLayout = findViewById(R.id.tabLayout);
        viewPager = findViewById(R.id.view_pager);
    }

    private void initTabLayout_Adapter_viewPager() {
        //initial Layout Component

        viewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager());


        viewPagerAdapter.AddFragment(new FragmentPost(this), "Call");
        viewPagerAdapter.AddFragment(new FragmentCalling(), "Post");
        viewPagerAdapter.AddFragment(new FragmentPost(this), "Photo");



        viewPager.setAdapter(viewPagerAdapter);
        tabLayout.setupWithViewPager(viewPager);

        tabLayout.getTabAt(0).setIcon(R.drawable.ic_camera);
        tabLayout.getTabAt(1).setIcon(R.drawable.ic_post_message);
        tabLayout.getTabAt(2).setIcon(R.drawable.ic_add);


        ActionBar actionBar = getSupportActionBar();
        actionBar.setElevation(0);

    }


}