package com.example.capstoneclaytondixon.Entity;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "readings")
public class ReadingEntity {

    @PrimaryKey(autoGenerate = true)
    private Integer readingID;

    private Integer readingSugar;
    private String readingDate;
    private String readingTime;
    private Integer readingCarbs;
    private Integer readingInsulin;
    private String medicineBoolean;
    private String readingFeeling;
    private String readingTOD;

    public ReadingEntity(Integer readingID, Integer readingSugar, String readingDate, String readingTime,
        Integer readingCarbs, Integer readingInsulin, String medicineBoolean, String readingFeeling, String readingTOD) {
        this.readingID = readingID;
        this.readingSugar= readingSugar;
        this.readingDate = readingDate;
        this.readingTime = readingTime;
        this.readingCarbs = readingCarbs;
        this.readingInsulin = readingInsulin;
        this.medicineBoolean = medicineBoolean;
        this.readingFeeling = readingFeeling;
        this.readingTOD = readingTOD;
    }

    @Override
    public String toString() {
        return "ID = " + readingID + "\n" +
                "Sugar = " + readingSugar + "\n" +
                "Date = " + readingDate + "\n" +
                "Time = " + readingTime + "\n" +
                "Carbs = " + readingCarbs + "\n" +
                "Insulin = " + readingInsulin + "\n" +
                "Medicine = " + medicineBoolean + "\n" +
                "Feeling = " + readingFeeling + "\n" +
                "Time of Day = " + readingTOD;
    }

//    @Override
//    public String toString() {
//        return " ID = " + readingID +
//                ", Sugar = " + readingSugar +
//                ", Date = '" + readingDate + '\'' +
//                ", Time = '" + readingTime + '\'' +
//                ", Carbs = " + readingCarbs +
//                ", Insulin = " + readingInsulin +
//                ", Medicine = '" + medicineBoolean + '\'' +
//                ", Feeling = '" + readingFeeling + '\'' +
//                ", Time of Day = '" + readingTOD + '\'';
//    }

    public String getReadingTOD() {
        return readingTOD;
    }

    public void setReadingTOD(String readingTOD) {
        this.readingTOD = readingTOD;
    }

    public String getReadingDate() {
        return readingDate;
    }

    public void setReadingDate(String readingDate) {
        this.readingDate = readingDate;
    }

    public String getReadingTime() {
        return readingTime;
    }

    public void setReadingTime(String readingTime) {
        this.readingTime = readingTime;
    }

    public Integer getReadingSugar() {
        return readingSugar;
    }

    public void setReadingSugar(Integer readingSugar) {
        this.readingSugar = readingSugar;
    }

    public Integer getReadingID() {
        return readingID;
    }

    public void setReadingID(Integer readingID) {
        this.readingID = readingID;
    }

    public Integer getReadingCarbs() {
        return readingCarbs;
    }

    public void setReadingCarbs(Integer readingCarbs) {
        this.readingCarbs = readingCarbs;
    }

    public Integer getReadingInsulin() {
        return readingInsulin;
    }

    public void setReadingInsulin(Integer readingInsulin) {
        this.readingInsulin = readingInsulin;
    }

    public String getMedicineBoolean() {
        return medicineBoolean;
    }

    public void setMedicineBoolean(String medicineBoolean) {
        this.medicineBoolean = medicineBoolean;
    }

    public String getReadingFeeling() {
        return readingFeeling;
    }

    public void setReadingFeeling(String readingFeeling) {
        this.readingFeeling = readingFeeling;
    }
}
