package dk.nemprogrammering.android.listeapp;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Room;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ListViewModel extends AndroidViewModel
{
    private ListDatabase db;
    private ListElementDao listElementDao;
    private ExecutorService executorService;

    public ListViewModel(Application application)
    {
        super(application);

        this.db = ListDatabase.getInstance(application);
        this.listElementDao = db.listElementDao();
        this.executorService = Executors.newSingleThreadExecutor();
    }

    LiveData<List<ListElementEntity>> getAllListElements()
    {
        return this.listElementDao.getAllListElements();
    }

    LiveData<ListElementEntity> getListElementById(int id)
    {
        return this.listElementDao.getListElementById(id);
    }

    void deleteListElements(ListElementEntity... entity)
    {
        this.executorService.execute(() -> this.listElementDao.deleteListElements(entity));
    }

    void updateListElements(ListElementEntity... entity)
    {
        this.executorService.execute(() -> this.listElementDao.updateListElements(entity));
    }

    void insertListElements(ListElementEntity... entity)
    {
        this.executorService.execute(() -> this.listElementDao.insertListElements(entity));
    }

    protected void onCleared()
    {
        super.onCleared();
        this.executorService.shutdown();
    }

}
