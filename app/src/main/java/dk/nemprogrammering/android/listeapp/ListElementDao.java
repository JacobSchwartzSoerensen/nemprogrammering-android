package dk.nemprogrammering.android.listeapp;
import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

@Dao
public interface ListElementDao {

    @Query("SELECT * FROM list_elements")
    LiveData<java.util.List<ListElementEntity>> getAllListElements();

    @Query("SELECT * FROM list_elements WHERE uid = :id")
    LiveData<ListElementEntity> getListElementById(int id);

    @Delete
    void deleteListElements(ListElementEntity... listElementEntities);

    @Update
    void updateListElements(ListElementEntity... listElementEntities);

    @Insert
    void insertListElements(ListElementEntity... listElementEntities);
}
