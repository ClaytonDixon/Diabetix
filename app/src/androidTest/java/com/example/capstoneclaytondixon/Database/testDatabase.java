package com.example.capstoneclaytondixon.Database;

import static org.junit.Assert.assertEquals;
import android.content.Context;
import androidx.room.Room;
import androidx.test.core.app.ApplicationProvider;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import com.example.capstoneclaytondixon.DAO.ReadingDAO;
import com.example.capstoneclaytondixon.Entity.ReadingEntity;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import java.io.IOException;
import java.util.List;

@RunWith(AndroidJUnit4.class)
public class testDatabase {

    private ReadingDAO readingDAO;
    private DatabaseBuilder db;

    @Before
    public void createDb() {
        Context context = ApplicationProvider.getApplicationContext();
        db = Room.inMemoryDatabaseBuilder(context, DatabaseBuilder.class).allowMainThreadQueries().build();
        readingDAO = db.readingDAO();
    }

    @After
    public void closeDb() throws IOException {
        db.close();
    }

    @Test
    public void insertReadingAndGetInList() throws Exception {
        ReadingEntity readingEntity = new ReadingEntity(1, 120, "07/23/22"
                , "2:30pm", 78, 80,
                "yes", "Feel Fine", "After Lunch");
        readingDAO.insertReading(readingEntity);
        List<ReadingEntity> readingEntityList = readingDAO.getAllReadings();
        assertEquals(readingEntity.getReadingID(), readingEntityList.get(0).getReadingID());
    }

    @Test
    public void insertReadingThenDelete() throws Exception {
        ReadingEntity readingEntity = new ReadingEntity(1, 240, "07/25/22"
                , "6:30pm", 53, 34,
                "no", "Stressed Out", "Before Dinner");
        readingDAO.insertReading(readingEntity);
        List<ReadingEntity> readingEntityList = readingDAO.getAllReadings();
        assertEquals(1, readingDAO.getReadingCount());
        readingDAO.deleteReading(readingEntity);
        readingEntityList = readingDAO.getAllReadings();
        assertEquals(0, readingDAO.getReadingCount());
    }

}
