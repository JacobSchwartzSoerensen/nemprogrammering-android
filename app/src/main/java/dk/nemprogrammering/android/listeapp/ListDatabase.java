package dk.nemprogrammering.android.listeapp;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

@Database(entities = {ListElementEntity.class}, version = 1)
public abstract class ListDatabase extends RoomDatabase {
    public abstract ListElementDao listElementDao();
}
