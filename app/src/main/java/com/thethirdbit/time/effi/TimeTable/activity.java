package com.thethirdbit.time.effi.TimeTable;

import android.provider.BaseColumns;

/**
 * Created by piyush on 7/2/18.
 */

public class activity {
    public static final int DB_VERSION = 1;
    public static final String TABLE_NAME ="activity_table";

    //columns

    public class activityEntery implements BaseColumns {

        public static final String COL_activity_TITLE = "Activity";
        public static final String COL_activity_location = "Location";
        public static final String COL_from_hour = "FromHour";
        public static final String COL_from_min = "FromMin";
        public static final String COL_to_hour = "ToHour";
        public static final String COL_to_min = "ToMin";
        public static final String COL_duration = "duration";
        public static final String COL_day_name = "dayname";


    }


}
