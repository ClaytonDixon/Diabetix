package com.example.capstoneclaytondixon;

import static org.junit.Assert.assertEquals;

import com.example.capstoneclaytondixon.Entity.ReadingEntity;

import org.junit.Before;
import org.junit.Test;



public class GettersTest {

    ReadingEntity readingEntity;


    @Before
    public void before() {
        readingEntity = new ReadingEntity(1, 120, "07/23/22", "2:30pm", 78, 80,
                "yes", "Feel Fine", "After Lunch");
    }

    @Test
    public void testReadingGetters() {
        assertEquals(Integer.valueOf(1), readingEntity.getReadingID());
        assertEquals(Integer.valueOf(120), readingEntity.getReadingSugar());
        assertEquals("07/23/22", readingEntity.getReadingDate());
        assertEquals("2:30pm", readingEntity.getReadingTime());
        assertEquals(Integer.valueOf(78), readingEntity.getReadingCarbs());
        assertEquals(Integer.valueOf(80), readingEntity.getReadingInsulin());
        assertEquals("yes", readingEntity.getMedicineBoolean());
        assertEquals("Feel Fine", readingEntity.getReadingFeeling());
        assertEquals("After Lunch", readingEntity.getReadingTOD());
    }

}