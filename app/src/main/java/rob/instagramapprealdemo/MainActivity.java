package rob.instagramapprealdemo;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.ActionBar;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;

import rob.instagramapprealdemo.TabLayoutPackage.FragmentCalling;
import rob.instagramapprealdemo.TabLayoutPackage.FragmentPost;
import rob.instagramapprealdemo.TabLayoutPackage.ViewPagerAdapter;

public class MainActivity extends MainHelperClass{


    private static final int REQUEST_CODE_GALLERY = 999;
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

        viewPagerAdapter.AddFragment(new FragmentPost(this), "Post");
        viewPagerAdapter.AddFragment(new FragmentCalling(), "Call");
        //viewPagerAdapter.AddFragment(new FragmentPost(this), "Setting");

        viewPager.setAdapter(viewPagerAdapter);
        tabLayout.setupWithViewPager(viewPager);

        tabLayout.getTabAt(0).setIcon(R.drawable.ic_camera);
        tabLayout.getTabAt(1).setIcon(R.drawable.ic_post_message);
        //tabLayout.getTabAt(2).setIcon(R.drawable.ic_add);




        ActionBar actionBar = getSupportActionBar();
        actionBar.setElevation(0);

    }



}



