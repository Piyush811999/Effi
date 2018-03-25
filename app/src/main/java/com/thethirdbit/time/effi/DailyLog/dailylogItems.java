package com.thethirdbit.time.effi.DailyLog;

/**
 * Created by piyush on 7/2/18.
 */

public class dailylogItems {
    private String date;
    private String notes;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public dailylogItems(String date, String notes) {
        this.date = date;
        this.notes = notes;
    }
}
