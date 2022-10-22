package com.borg.model;


import androidx.room.TypeConverter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TimestampConverter {

    static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

    @TypeConverter
    public static Date fromTimestamp(String value) {
        if (value != null) {
            try {
                return sdf.parse(value);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            return null;
        } else {
            return null;
        }
    }

    @TypeConverter
    public static String fromDate(Date value) {
        return sdf.format(value);
    }

}