package rob.instagramapprealdemo.TabLayoutPackage;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import com.getbase.floatingactionbutton.FloatingActionButton;
import java.io.ByteArrayOutputStream;

import rob.instagramapprealdemo.LoadImageFrStorages;
import rob.instagramapprealdemo.R;
import rob.instagramapprealdemo.roomDatabase.InstaObj;
import rob.instagramapprealdemo.roomDatabase.MyInstaDatabase;

public class HelperFragmentPost extends Fragment {
    private static final String TAG = HelperFragmentPost.class.getSimpleName();
    private static final int REQUEST_CODE_GALLERY = 999;

    FloatingActionButton floatingActionButton;
    Context context;
    ImageView dialogLoadImage;
    Button loadButtonImageDialog;
    View view;
    AlertDialog.Builder dialog;




    public HelperFragmentPost(Context context) {
        this.context = context;

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = getLayoutInflater().inflate(R.layout.dialog_load_image, container, false);



        return super.onCreateView(inflater, container, savedInstanceState);
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



                Intent intent = new Intent(context, LoadImageFrStorages.class);
                startActivity(intent);
                //popUpLoadImageFromStorages();




            }
        });
    }

    public void popUpLoadImageFromStorages() {
        dialog = new AlertDialog.Builder(context);
        view = getLayoutInflater().inflate(R.layout.dialog_load_image, null);
        loadButtonImageDialog = view.findViewById(R.id.loadImageButtonDialog);
        dialogLoadImage = (ImageView) view.findViewById(R.id.dialogLoadImageView);
        dialogLoadImage.setImageResource(R.drawable.fran1);
        dialog.setView(view);
        AlertDialog dialog1 = dialog.create();
        dialog1.show();

    }


}





