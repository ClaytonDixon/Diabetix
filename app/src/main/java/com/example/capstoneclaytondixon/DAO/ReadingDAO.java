package com.example.capstoneclaytondixon.DAO;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.example.capstoneclaytondixon.Entity.ReadingEntity;

import java.util.List;

@Dao
public interface ReadingDAO {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insertReading(ReadingEntity readingEntity);

    @Update
    void updateReading(ReadingEntity readingEntity);

    @Delete
    void deleteReading(ReadingEntity readingEntity);

    @Query("SELECT * FROM readings WHERE readingID = :readingID")
    ReadingEntity getReadingByID(Integer readingID);

    @Query("SELECT * FROM readings ORDER BY readingDate ASC")
    List<ReadingEntity> getAllReadings();

    @Query("SELECT * FROM readings WHERE readingDate = :readingDate")
    List<ReadingEntity> getReadingsByDate(String readingDate);

    @Query("SELECT * FROM readings WHERE readingTOD = :readingTOD")
    List<ReadingEntity> getReadingsByTOD(String readingTOD);

    @Query("SELECT * FROM readings WHERE readingFeeling = :readingFeeling")
    List<ReadingEntity> getReadingsByFeeling(String readingFeeling);

    @Query("SELECT COUNT(*) FROM readings")
    int getReadingCount();
}
