package dk.nemprogrammering.android.listeapp;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

@Entity(tableName = "list_elements")
public class ListElementEntity {
    @PrimaryKey
    public int uid;

    public String header;
    public String desc;
    public String img;
}
