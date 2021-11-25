package rob.instagramapprealdemo.TabLayoutPackage;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;

import androidx.fragment.app.Fragment;

import java.io.ByteArrayOutputStream;

import rob.instagramapprealdemo.R;
import rob.instagramapprealdemo.roomDatabase.InstaObj;
import rob.instagramapprealdemo.roomDatabase.MyInstaDatabase;

public class HelperFragmentPost extends Fragment {
    public void insertPostsFun(){


        //InstaObj(String username, String password, String comments, String postMessage,String dateTime, byte[] instaImage)
        InstaObj instaObj = new InstaObj("Mattio", "123456", "Heute war sehr Cool", "Today was cool", "11.12.2001", intImageToImageByteArray(R.drawable.fran1));
        InsertAsynTask insertAsynTask = new InsertAsynTask();
        insertAsynTask.execute(instaObj);



    }

    class InsertAsynTask extends AsyncTask<InstaObj, Void, Void> {
        @Override
        protected Void doInBackground(InstaObj... objs) {
            MyInstaDatabase.getInstance(getContext())
                    .instaDao()
                    .insert(objs[0]);
            return null;
        }
    }

    public byte[] intImageToImageByteArray(int intImage){
        Resources res = getResources();
        Drawable drawable = res.getDrawable(intImage);
        Bitmap bitmap = ((BitmapDrawable)drawable).getBitmap();
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, stream);
        byte[] bitMapData = stream.toByteArray();
        return bitMapData;
    }
}





