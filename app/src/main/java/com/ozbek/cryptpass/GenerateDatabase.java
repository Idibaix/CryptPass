package com.ozbek.cryptpass;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

@Database(entities = {Generate.class}, version = 1)
public abstract class GenerateDatabase extends RoomDatabase {

    private static GenerateDatabase instance;

    public abstract GenerateDAO generateDao();

    public static synchronized GenerateDatabase getInstance(Context context){
        if(instance == null){instance = Room.databaseBuilder(context.getApplicationContext(), GenerateDatabase.class, "generate_database").fallbackToDestructiveMigration().addCallback(roomCallBack).build();}
        return instance;
    }

    private static RoomDatabase.Callback roomCallBack= new RoomDatabase.Callback(){
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            new PopulateDbAsynctask(instance).execute();
        }
    };

    private static class PopulateDbAsynctask extends AsyncTask<Void, Void, Void>{
        private GenerateDAO generateDAO;

        private PopulateDbAsynctask(GenerateDatabase database){generateDAO = database.generateDao();}

        @Override
        protected Void doInBackground(Void... voids) {
            generateDAO.insert(new Generate("oo92", "GitHub", "Ab422ix0"));
            generateDAO.insert(new Generate("1916", "Pillar", "1916"));
            generateDAO.insert(new Generate("omar.humber", "outlook", "Ab422ix0!"));
            generateDAO.insert(new Generate("onur.toronto", "Gmail", "De644ix0"));
            generateDAO.insert(new Generate("onur.toronto@outlook", "Facebook", "Hi866ix0!"));
            return null;
        }
    }
}
