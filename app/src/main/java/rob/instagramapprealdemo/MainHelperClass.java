package rob.instagramapprealdemo;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.List;

import rob.instagramapprealdemo.TabLayoutPackage.FragmentCalling;
import rob.instagramapprealdemo.TabLayoutPackage.ViewPagerAdapter;
import rob.instagramapprealdemo.roomDatabase.InstaObj;
import rob.instagramapprealdemo.roomDatabase.InstaViewModel;
import rob.instagramapprealdemo.roomDatabase.MyInstaDatabase;

public class MainHelperClass extends AppCompatActivity {

    public static final String TAG = MainActivity.class.getSimpleName();
    List<String> usernameToInitRecycler;
    List<String> commentsToInitRecycler;
    List<String> postMessageToInitRecycler;
    List<String> dateTimeToInitRecycler;
    List<byte[]> imagePostToInitRecycler;
    InstaViewModel instaViewModel;





    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        initial();
        insertPostsFun();
        getAllPostsFun();





    }

    public void initial(){


        //initial the List Array for putting Value for RecyclerAdapter
        usernameToInitRecycler = new ArrayList<>();
        commentsToInitRecycler = new ArrayList<>();
        postMessageToInitRecycler = new ArrayList<>();
        dateTimeToInitRecycler = new ArrayList<>();
        imagePostToInitRecycler = new ArrayList<>();


    }

    public void insertPostsFun(){
        Resources res = getResources();
        Drawable drawable = res.getDrawable(R.drawable.sqlite_icon);
        Bitmap bitmap = ((BitmapDrawable)drawable).getBitmap();
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, stream);
        byte[] bitMapData = stream.toByteArray();

        //InstaObj(String username, String password, String comments, String postMessage,String dateTime, byte[] instaImage)
        InstaObj instaObj = new InstaObj("Mattheo", "123456", "Hi this is nice", "Today was cool", "11.12.2001", bitMapData);
        InsertAsynTask insertAsynTask = new InsertAsynTask();
        //insertAsynTask.execute(instaObj);



    }

    class InsertAsynTask extends AsyncTask<InstaObj, Void, Void> {
        @Override
        protected Void doInBackground(InstaObj... times) {
            MyInstaDatabase.getInstance(getApplicationContext())
                    .instaDao()
                    .insert(times[0]);
            return null;
        }
    }

    public void getAllPostsFun() {
        instaViewModel = ViewModelProviders.of(this).get(InstaViewModel.class);
        instaViewModel.getAllPosts().observe(this, new Observer<List<InstaObj>>() {
            @Override
            public void onChanged(List<InstaObj> instaObjs) {

                for (int i = 0; i < instaObjs.size(); i++){
                    usernameToInitRecycler.add(instaObjs.get(i).getUsername());
                    commentsToInitRecycler.add(instaObjs.get(i).getComments());
                    postMessageToInitRecycler.add(instaObjs.get(i).getPostMessage());
                    dateTimeToInitRecycler.add(instaObjs.get(i).getDateTime());
                    imagePostToInitRecycler.add(instaObjs.get(i).getInstaImage());
                }

                Log.i(TAG, "onChanged: "+ usernameToInitRecycler.toString());



            }
        });

    }




}
