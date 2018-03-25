package com.thethirdbit.time.effi.TimeTable;

/**
 * Created by piyush on 7/2/18.
 */

public class DayItems {

    private String Day;
    private String FreeHoursCount;
    private String ActivitiesCount;

    public DayItems(String day, String freeHoursCount, String activitiesCount) {
        Day = day;
        FreeHoursCount = freeHoursCount;
        ActivitiesCount = activitiesCount;
    }

    public String getDay() {
        return Day;
    }

    public void setDay(String day) {
        Day = day;
    }

    public String getFreeHoursCount() {
        return FreeHoursCount;
    }

    public void setFreeHoursCount(String freeHoursCount) {
        FreeHoursCount = freeHoursCount;
    }

    public String getActivitiesCount() {
        return ActivitiesCount;
    }

    public void setActivitiesCount(String activitiesCount) {
        ActivitiesCount = activitiesCount;
    }
}

