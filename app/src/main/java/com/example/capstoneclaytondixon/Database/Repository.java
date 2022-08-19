package com.example.capstoneclaytondixon.Database;

import android.app.Application;

import com.example.capstoneclaytondixon.DAO.ReadingDAO;
import com.example.capstoneclaytondixon.Entity.ReadingEntity;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Repository {


    private ReadingDAO mReadingDAO;

    private List<ReadingEntity> mAllReadings;

    private ReadingEntity mReadingEntity;
    private int count;

    private static int NUMBER_OF_THREADS=4;
    static final ExecutorService databaseExecutor = Executors.newFixedThreadPool(NUMBER_OF_THREADS);

    public Repository(Application application) {
        DatabaseBuilder db=DatabaseBuilder.getDatabase(application);

        mReadingDAO = db.readingDAO();
    }


    public ReadingEntity getReadingByID(Integer readingID) {
        databaseExecutor.execute(() ->{
            mReadingEntity = mReadingDAO.getReadingByID(readingID);
        });
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e){
            e.printStackTrace();
        }
        return mReadingEntity;
    }

    public List<ReadingEntity> getAllReadings() {
        databaseExecutor.execute(() ->{
            mAllReadings = mReadingDAO.getAllReadings();
        });
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e){
            e.printStackTrace();
        }
        return mAllReadings;
    }

    public List<ReadingEntity> getReadingsByDate(String readingDate) {
        databaseExecutor.execute(() ->{
            mAllReadings = mReadingDAO.getReadingsByDate(readingDate);
        });
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e){
            e.printStackTrace();
        }
        return mAllReadings;
    }

    public List<ReadingEntity> getReadingsByTOD(String readingTOD) {
        databaseExecutor.execute(() ->{
            mAllReadings = mReadingDAO.getReadingsByTOD(readingTOD);
        });
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e){
            e.printStackTrace();
        }
        return mAllReadings;
    }

    public List<ReadingEntity> getReadingsByFeeling(String readingFeeling) {
        databaseExecutor.execute(() ->{
            mAllReadings = mReadingDAO.getReadingsByFeeling(readingFeeling);
        });
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e){
            e.printStackTrace();
        }
        return mAllReadings;
    }

    public int getReadingCount() {
        databaseExecutor.execute(() ->{
            count = mReadingDAO.getReadingCount();
        });
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e){
            e.printStackTrace();
        }
        return count;
    }

    public void insertReading(ReadingEntity readingEntity) {
        databaseExecutor.execute(() ->{
            mReadingDAO.insertReading(readingEntity);
        });
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e){
            e.printStackTrace();
        }
    }


    public void updateReading(ReadingEntity readingEntity) {
        databaseExecutor.execute(() ->{
            mReadingDAO.updateReading(readingEntity);
        });
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e){
            e.printStackTrace();
        }
    }

    public void deleteReading(ReadingEntity readingEntity) {
        databaseExecutor.execute(() ->{
            mReadingDAO.deleteReading(readingEntity);
        });
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e){
            e.printStackTrace();
        }
    }


}
