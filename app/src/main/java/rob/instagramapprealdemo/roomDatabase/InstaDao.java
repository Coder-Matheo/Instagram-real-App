package rob.instagramapprealdemo.roomDatabase;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface InstaDao {
    @Insert
    void insert(InstaObj instaObj);

    @Query("SELECT * FROM InstaObj")
    LiveData<List<InstaObj>> getAllPosts();


}
