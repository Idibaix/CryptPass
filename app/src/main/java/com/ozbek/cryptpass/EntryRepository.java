package com.ozbek.cryptpass;

import android.app.Application;
import android.os.AsyncTask;
import androidx.lifecycle.LiveData;
import java.util.List;

import io.sentry.Sentry;
import io.sentry.event.BreadcrumbBuilder;
import io.sentry.event.UserBuilder;

public class EntryRepository {
    private EntryDAO entryDAO;
    private LiveData<List<Entries>> allEntries;

    public EntryRepository(Application application){
        EntryDatabase database = EntryDatabase.getInstance(application);
        entryDAO = database.generateDao();
        allEntries = entryDAO.getAllEntries();
    }

    public void insert(Entries entries){new InsertEntryAsyncTask(entryDAO).execute(entries);}

    public void update(Entries entries){new UpdateEntryAsyncTask(entryDAO).execute(entries);}

    public void delete(Entries entries){new DeleteEntryAsyncTask(entryDAO).execute(entries);}

    public void deleteAllEntries(){new DeleteAllEntriesAsyncTask(entryDAO).execute();}

    public LiveData<List<Entries>> getAllEntries(){return allEntries;}


    public static class InsertEntryAsyncTask extends AsyncTask<Entries, Void, Void>{
        private EntryDAO entryDAO;

        private InsertEntryAsyncTask(EntryDAO entryDAO){this.entryDAO = entryDAO;}

        @Override
        protected Void doInBackground(Entries... entries) {
            entryDAO.insert(entries[0]);
            return null;
        }
    }

    public static class UpdateEntryAsyncTask extends AsyncTask<Entries, Void, Void>{
        private EntryDAO entryDAO;

        private UpdateEntryAsyncTask(EntryDAO entryDAO){
            this.entryDAO = entryDAO;
        }
        @Override
        protected Void doInBackground(Entries... entries) {
            entryDAO.update(entries[0]);
            return null;
        }
    }

    public static class DeleteEntryAsyncTask extends AsyncTask<Entries, Void, Void>{
        private EntryDAO entryDAO;

        private DeleteEntryAsyncTask(EntryDAO entryDAO){this.entryDAO = entryDAO;}

        @Override
        protected Void doInBackground(Entries... entries) {
            entryDAO.delete(entries[0]);
            return null;
        }
    }

    public static class DeleteAllEntriesAsyncTask extends AsyncTask<Void, Void, Void>{
        private EntryDAO entryDAO;

        private DeleteAllEntriesAsyncTask(EntryDAO entryDAO){this.entryDAO = entryDAO;}

        @Override
        protected Void doInBackground(Void... voids) {
            entryDAO.deleteAllEntries();
            return null;
        }
    }
}
