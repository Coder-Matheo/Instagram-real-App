package rob.instagramapprealdemo.roomDatabase;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {InstaObj.class}, version = 1)
public abstract class MyInstaDatabase extends RoomDatabase {

    private static final String INSTA_DB = "INSTA_DB";
    //invoke InstaDao
    public abstract InstaDao instaDao();
    //Singleton database
    private static volatile MyInstaDatabase INSTANCE;


    //Build Database and getInstance from Database
    public static MyInstaDatabase getInstance(Context context) {
        if (INSTANCE == null) {
            synchronized (MyInstaDatabase.class){
                if (INSTANCE == null){
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(), 
                            MyInstaDatabase.class, INSTA_DB).build();
                }
            }
        }
        return INSTANCE;
    }


}
