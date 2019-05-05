package com.ozbek.cryptpass;

import android.app.Application;
import android.os.AsyncTask;
import androidx.lifecycle.LiveData;
import java.util.List;

public class GenerateRepository {
    private GenerateDAO generateDAO;
    private LiveData<List<Generate>> allEntries;

    public GenerateRepository(Application application){
        GenerateDatabase database = GenerateDatabase.getInstance(application);
        generateDAO = database.generateDao();
        allEntries = generateDAO.getAllEntries();
    }

    public void insert(Generate generate){new InsertEntryAsyncTask(generateDAO).execute(generate);}

    public void update(Generate generate){new UpdateEntryAsyncTask(generateDAO).execute(generate);}

    public void delete(Generate generate){new DeleteEntryAsyncTask(generateDAO).execute(generate);}

    public void deleteAllEntries(){new DeleteAllEntriesAsyncTask(generateDAO).execute();}

    public LiveData<List<Generate>> getAllEntries(){return allEntries;}


    public static class InsertEntryAsyncTask extends AsyncTask<Generate, Void, Void>{
        private GenerateDAO generateDAO;

        private InsertEntryAsyncTask(GenerateDAO generateDAO){this.generateDAO = generateDAO;}

        @Override
        protected Void doInBackground(Generate... generates) {
            generateDAO.insert(generates[0]);
            return null;
        }
    }

    public static class UpdateEntryAsyncTask extends AsyncTask<Generate, Void, Void>{
        private GenerateDAO generateDAO;

        private UpdateEntryAsyncTask(GenerateDAO generateDAO){
            this.generateDAO = generateDAO;
        }
        @Override
        protected Void doInBackground(Generate... generates) {
            generateDAO.update(generates[0]);
            return null;
        }
    }

    public static class DeleteEntryAsyncTask extends AsyncTask<Generate, Void, Void>{
        private GenerateDAO generateDAO;

        private DeleteEntryAsyncTask(GenerateDAO generateDAO){this.generateDAO = generateDAO;}

        @Override
        protected Void doInBackground(Generate... generates) {
            generateDAO.delete(generates[0]);
            return null;
        }
    }

    public static class DeleteAllEntriesAsyncTask extends AsyncTask<Void, Void, Void>{
        private GenerateDAO generateDAO;

        private DeleteAllEntriesAsyncTask(GenerateDAO generateDAO){this.generateDAO = generateDAO;}

        @Override
        protected Void doInBackground(Void... voids) {
            generateDAO.deleteAllEntries();
            return null;
        }
    }


}
