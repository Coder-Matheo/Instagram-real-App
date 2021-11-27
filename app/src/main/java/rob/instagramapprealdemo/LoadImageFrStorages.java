package rob.instagramapprealdemo;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import java.io.FileNotFoundException;
import java.io.InputStream;

public class LoadImageFrStorages extends AppCompatActivity {

    private static final int REQUEST_CODE_GALLERY = 999;
    private static final String TAG = LoadImageFrStorages.class.getSimpleName();
    private ImageButton loadImageButton;
    private Button cancelButton;
    private Button postButton;
    private EditText loadEditTextMultiLine;

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

        loadImageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ActivityCompat.requestPermissions(LoadImageFrStorages.this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                        REQUEST_CODE_GALLERY);

                Log.i(TAG, "onClick: "+ loadEditTextMultiLine.getText());
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
}