package com.ozbek.cryptpass;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class GenerateViewModel extends AndroidViewModel {
    private GenerateRepository repository;
    private LiveData<List<Generate>> allEntries;


    public GenerateViewModel(@NonNull Application application) {
        super(application);
        repository = new GenerateRepository(application);
        allEntries = repository.getAllEntries();
    }

    public void insert(Generate generate){repository.insert(generate);}

    public void update(Generate generate){repository.update(generate);}

    public void delete(Generate generate){repository.delete(generate);}

    public void deleteAll(){repository.deleteAllEntries();}

    public LiveData<List<Generate>> getAllEntries() {return allEntries;}
}
