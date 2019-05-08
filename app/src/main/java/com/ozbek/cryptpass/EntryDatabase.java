package com.ozbek.cryptpass;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import io.sentry.Sentry;
import io.sentry.event.BreadcrumbBuilder;
import io.sentry.event.UserBuilder;

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

    void unsafeMethod() {throw new UnsupportedOperationException("You shouldn't call this!");}

    void logWithStaticAPI() {
        Sentry.getContext().recordBreadcrumb(new BreadcrumbBuilder().setMessage("User made an action").build());
        Sentry.getContext().setUser(new UserBuilder().setEmail("hello@sentry.io").build());
        Sentry.capture("This is a test");

        try {unsafeMethod();}
        catch (Exception e) {Sentry.capture(e);}
    }
}