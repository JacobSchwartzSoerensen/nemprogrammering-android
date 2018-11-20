package dk.nemprogrammering.android.listeapp;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

@Database(entities = {ListElementEntity.class}, version = 1)
public abstract class ListDatabase extends RoomDatabase {
    public abstract ListElementDao listElementDao();

    private static ListDatabase INSTANCE;

    public static ListDatabase getInstance(Context context)
    {
        if (INSTANCE == null)
        {
            INSTANCE = Room.databaseBuilder(context, ListDatabase.class, "list-database").build();
        }

        return INSTANCE;
    }
}
