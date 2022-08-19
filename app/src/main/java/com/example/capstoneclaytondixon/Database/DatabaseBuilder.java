package com.example.capstoneclaytondixon.Database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.capstoneclaytondixon.DAO.ReadingDAO;
import com.example.capstoneclaytondixon.Entity.ReadingEntity;

@Database(entities = { ReadingEntity.class},version = 3, exportSchema = false )
public abstract class DatabaseBuilder extends RoomDatabase {


    public abstract ReadingDAO readingDAO();

    private static volatile DatabaseBuilder INSTANCE;

   public static DatabaseBuilder getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (DatabaseBuilder.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(), DatabaseBuilder.class
                            , "myDatabase.db").fallbackToDestructiveMigration().build();
                }
            }
        }
        return INSTANCE;
    }
}
