package com.ozbek.cryptpass;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class EntryViewModel extends AndroidViewModel {
    private EntryRepository repository;
    private LiveData<List<Entries>> allEntries;


    public EntryViewModel(@NonNull Application application) {
        super(application);
        repository = new EntryRepository(application);
        allEntries = repository.getAllEntries();
    }

    public void insert(Entries entries){repository.insert(entries);}

    public void update(Entries entries){repository.update(entries);}

    public void delete(Entries entries){repository.delete(entries);}

    public void deleteAll(){repository.deleteAllEntries();}

    public LiveData<List<Entries>> getAllEntries() {return allEntries;}
}
