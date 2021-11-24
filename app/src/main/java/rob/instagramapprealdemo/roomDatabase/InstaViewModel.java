package rob.instagramapprealdemo.roomDatabase;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class InstaViewModel extends AndroidViewModel {
    MyInstaDatabase myInstaDatabase;

    public InstaViewModel(@NonNull Application application) {
        super(application);

        myInstaDatabase = MyInstaDatabase.getInstance(application.getApplicationContext());

    }

    public LiveData<List<InstaObj>> getAllPosts(){
        return myInstaDatabase.instaDao().getAllPosts();
    }
}
