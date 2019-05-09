package com.ozbek.cryptpass;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

@Database(entities = {Entries.class}, version = 2)
public abstract class EntryDatabase extends RoomDatabase {

    private static EntryDatabase instance;

    public abstract EntryDAO generateDao();

    public static synchronized EntryDatabase getInstance(Context context){
        if(instance == null){instance = Room.databaseBuilder(context.getApplicationContext(), EntryDatabase.class, "generate_database").fallbackToDestructiveMigration().addCallback(roomCallBack).build();}
        return instance;
    }

    private static RoomDatabase.Callback roomCallBack= new RoomDatabase.Callback(){
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {super.onCreate(db);}
    };
}
