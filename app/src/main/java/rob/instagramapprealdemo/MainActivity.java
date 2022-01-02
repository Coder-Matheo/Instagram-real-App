package rob.instagramapprealdemo;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.viewpager.widget.ViewPager;
import androidx.viewpager2.widget.CompositePageTransformer;
import androidx.viewpager2.widget.MarginPageTransformer;
import androidx.viewpager2.widget.ViewPager2;

import com.google.android.material.tabs.TabLayout;

import rob.instagramapprealdemo.TabLayoutPackage.FragmentCalling;
import rob.instagramapprealdemo.TabLayoutPackage.FragmentPost;
import rob.instagramapprealdemo.TabLayoutPackage.ViewPagerAdapter;
import rob.instagramapprealdemo.storyPackage.StoryAdapter;

public class MainActivity extends MainHelperClass{


    private static final int REQUEST_CODE_GALLERY = 999;
    TabLayout tabLayout;
    ViewPagerAdapter viewPagerAdapter;
    ViewPager viewPager;

    StoryAdapter storyAdapter;
    ViewPager2 storyViewPager;
    int [] imagesStory = {R.drawable.fran1,R.drawable.fran2,R.drawable.fran3,R.drawable.fran4,
            R.drawable.fran1,R.drawable.fran2,R.drawable.fran3,R.drawable.fran4,
            R.drawable.fran1,R.drawable.fran2,R.drawable.fran3,R.drawable.fran4,
            R.drawable.fran1,R.drawable.fran2,R.drawable.fran3,R.drawable.fran4,
            R.drawable.fran1,R.drawable.fran2,R.drawable.fran3,R.drawable.fran4
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        startActivity(new Intent(MainActivity.this, LoginActivity.class));

        initialComponentLayout();
        initTabLayout_Adapter_viewPager();
        storyViewPager();
    }

    private void storyViewPager() {
        storyViewPager = findViewById(R.id.viewPager2);
        storyAdapter = new StoryAdapter(imagesStory);
        storyViewPager.setAdapter(storyAdapter);

        storyViewPager.setClipChildren(false);
        storyViewPager.setClipToPadding(false);
        storyViewPager.setOffscreenPageLimit(10);
        storyViewPager.getChildAt(0).setOverScrollMode(View.OVER_SCROLL_NEVER);

        storyViewPager.setAdapter(storyAdapter);

        CompositePageTransformer transformer = new CompositePageTransformer();
        transformer.addTransformer(new MarginPageTransformer(8));
        transformer.addTransformer(new ViewPager2.PageTransformer() {
            @Override
            public void transformPage(@NonNull View page, float position) {
                float v = 1 - Math.abs(position);
                //Set scale y
                page.setScaleY(0.8f + v * 0.4f);
                page.setScaleX(0.8f + v * 0.4f);
            }
        });
        storyViewPager.setPageTransformer(transformer);

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



