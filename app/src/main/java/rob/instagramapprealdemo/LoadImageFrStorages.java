package rob.instagramapprealdemo;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
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
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

import rob.instagramapprealdemo.TabLayoutPackage.FragmentPost;
import rob.instagramapprealdemo.roomDatabase.InstaObj;
import rob.instagramapprealdemo.roomDatabase.MyInstaDatabase;

public class LoadImageFrStorages extends AppCompatActivity {

    private static final int REQUEST_CODE_GALLERY = 999;
    private static final String TAG = LoadImageFrStorages.class.getSimpleName();
    private ImageButton loadImageButton;
    private Button cancelButton;
    private Button postButton;
    private EditText loadEditTextMultiLine;
    private ImageButton imageCameraButton;
    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.load_image_storage);

        initial();

    }

    private void initial() {
        cancelButton = findViewById(R.id.cancelButton);
        postButton = findViewById(R.id.postButton);
        loadEditTextMultiLine = findViewById(R.id.loadEditTextMultiLine);

        loadImageButton = findViewById(R.id.imageButton);
        imageCameraButton = findViewById(R.id.imageCameraButton);

        loadImageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ActivityCompat.requestPermissions(LoadImageFrStorages.this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                        REQUEST_CODE_GALLERY);
            }
        });

        postButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                insertPostsFun();
            }
        });
        cancelButton.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                Log.i(TAG, "onClick: cancel Button");
                Intent intent = new Intent(LoadImageFrStorages.this, MainActivity.class);
                startActivity(intent);
            }
        });
        imageCameraButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.i(TAG, "onClick imageCamera: ");
            }
        });

    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == REQUEST_CODE_GALLERY){
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                Intent jumpToStorage = new Intent(Intent.ACTION_PICK);
                jumpToStorage.setType("image/*");
                startActivityForResult(jumpToStorage, REQUEST_CODE_GALLERY);
                Toast.makeText(this, " have Permission", Toast.LENGTH_LONG).show();
            }else {
                Toast.makeText(this, "don't have Permission", Toast.LENGTH_LONG).show();
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
                //Get Image from device and show it ImageView
                InputStream inputStream = getContentResolver().openInputStream(uri);
                Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
                Bitmap resizeBitmapImage = Bitmap.createScaledBitmap(bitmap, 800, 600, true);
                loadImageButton.setImageBitmap(resizeBitmapImage);

            }catch (FileNotFoundException e){
                e.printStackTrace();
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

   /* public byte[] intImageToImageByteArray(int intImage){
        Resources res = getResources();
        Drawable drawable = res.getDrawable(intImage);
        Bitmap bitmap = ((BitmapDrawable)drawable).getBitmap();
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, stream);
        byte[] bitMapData = stream.toByteArray();
        return bitMapData;
    }*/

    private byte[] imageViewToByte(ImageView image) {
        Bitmap bitmap = ((BitmapDrawable) image.getDrawable()).getBitmap();
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
        byte[] byteArray = stream.toByteArray();
        return byteArray;
    }

    public void insertPostsFun(){
        InstaObj instaObj = new InstaObj("Mattio", "123456", "Heute war sehr Cool", loadEditTextMultiLine.getText().toString(), "11.12.2001", imageViewToByte(loadImageButton));
        InsertAsynTask insertAsynTask = new InsertAsynTask();
        //insertAsynTask.execute(instaObj);

    }

    class InsertAsynTask extends AsyncTask<InstaObj, Void, Void> {
        @Override
        protected Void doInBackground(InstaObj... objs) {
            MyInstaDatabase.getInstance(context)
                    .instaDao()
                    .insert(objs[0]);
            return null;
        }
    }

}