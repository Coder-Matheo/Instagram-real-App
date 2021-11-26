package rob.instagramapprealdemo.TabLayoutPackage;

import static android.app.Activity.RESULT_OK;

import android.Manifest;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.AsyncTask;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;

import com.getbase.floatingactionbutton.FloatingActionButton;
import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import rob.instagramapprealdemo.R;
import rob.instagramapprealdemo.roomDatabase.InstaObj;
import rob.instagramapprealdemo.roomDatabase.MyInstaDatabase;

public class HelperFragmentPost extends Fragment {
    private static final String TAG = HelperFragmentPost.class.getSimpleName();
    private static final int REQUEST_CODE_GALLERY = 999;

    FloatingActionButton floatingActionButton;
    Context context;



    public HelperFragmentPost(Context context) {
        this.context = context;
    }

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




    void initFlaotActionButton(View view, FragmentActivity context) {
        floatingActionButton = view.findViewById(R.id.fab_button_id);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Log.i(TAG, "Clicked: ");
                ActivityCompat.requestPermissions(context, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                        REQUEST_CODE_GALLERY);

                popUpLoadImageFromStorages();

            }
        });
    }

    private void popUpLoadImageFromStorages() {
        AlertDialog.Builder dialog = new AlertDialog.Builder(context);
        View view = getLayoutInflater().inflate(R.layout.dialog_load_image, null);

        dialog.setView(view);
        AlertDialog dialog1 = dialog.create();
        dialog1.show();

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == REQUEST_CODE_GALLERY){
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                Intent jumpToStorage = new Intent(Intent.ACTION_PICK);
                jumpToStorage.setType("image/*");
                startActivityForResult(jumpToStorage, REQUEST_CODE_GALLERY);
                Toast.makeText(context, " have Permission", Toast.LENGTH_LONG).show();
            }else {
                Toast.makeText(context, "don't have Permission", Toast.LENGTH_LONG).show();
            }
            return;
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (requestCode == REQUEST_CODE_GALLERY && resultCode == RESULT_OK && data != null) {
            Uri uri = data.getData();
            try {
                InputStream inputStream = context.getContentResolver().openInputStream(uri);
                Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
                Log.i(TAG, "onActivityResult: "+ bitmap);
            }catch (FileNotFoundException e){
                e.printStackTrace();
            }

        }
        super.onActivityResult(requestCode, resultCode, data);
    }
}





