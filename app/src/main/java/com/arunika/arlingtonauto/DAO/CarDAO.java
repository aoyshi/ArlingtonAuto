package com.arunika.arlingtonauto.DAO;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import com.arunika.arlingtonauto.DATABASE.DBHelper;
import com.arunika.arlingtonauto.model.Car;
import java.util.ArrayList;

public class CarDAO {
    private static Context context;
    private static SQLiteDatabase Database;
    private static DBHelper DBHelper;

    private static CarDAO instance;
    public static synchronized CarDAO getInstance(Context context) {
        if (instance == null) {
            instance = new CarDAO(context);
        }
        return instance;
    }
    private CarDAO(Context context) {
        DBHelper = DBHelper.getInstance(context);
        this.context = context;
        try {
            Database = DBHelper.getWritableDatabase(); // open the database
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static ArrayList<Car> getAvailableCarsForCustomer(String searchStart, String searchEnd, int capacity) {
        String query = "SELECT * FROM " + DBHelper.TABLE_CAR
                + " WHERE "+DBHelper.COLUMN_CAR_CAPACITY+">="+capacity
                + " AND "+DBHelper.COLUMN_CAR_ID + " NOT IN ( SELECT "
                + DBHelper.COLUMN_CAR_ID+" FROM " + DBHelper.TABLE_RESERVATION
                + " WHERE "+DBHelper.COLUMN_RESERVATION_CHECK_OUT+" <= '"+searchEnd
                + "' AND '"+searchStart+"' <= "+DBHelper.COLUMN_RESERVATION_RETURN+"  )"
                + " ORDER BY " + DBHelper.COLUMN_CAR_CAPACITY;
        return getCarList(query);
    }

    public static ArrayList<Car> getAvailableCarsForManager(String searchStart, String searchEnd) {
        String query = "SELECT * FROM " + DBHelper.TABLE_CAR
                + " WHERE " + DBHelper.COLUMN_CAR_ID + " NOT IN ( SELECT "
                + DBHelper.COLUMN_CAR_ID + " FROM " + DBHelper.TABLE_RESERVATION
                + " WHERE " + DBHelper.COLUMN_RESERVATION_CHECK_OUT + " <= '" + searchEnd
                + "' AND '" + searchStart+"' <= " + DBHelper.COLUMN_RESERVATION_RETURN + "  )"
                + " ORDER BY " + DBHelper.COLUMN_CAR_CAPACITY;
        return getCarList(query);
    }

    private static ArrayList<Car> getCarList(String query) {
        ArrayList<Car> carList = new ArrayList<>();
        Cursor cursor = Database.rawQuery(query, null);
        Car car = new Car();
        try {
            // looping through all rows and adding car to list
            if (cursor.moveToFirst()) {
                do {
                    //set car attributes from db
                    car.setId(cursor.getInt(cursor.getColumnIndex(DBHelper.COLUMN_CAR_ID)));
                    car.setName(cursor.getString(cursor.getColumnIndex(DBHelper.COLUMN_CAR_NAME)));
                    car.setCapacity(cursor.getInt(cursor.getColumnIndex(DBHelper.COLUMN_CAR_CAPACITY)));
                    car.setWeekdayRate(cursor.getDouble(cursor.getColumnIndex(DBHelper.COLUMN_CAR_WEEKDAY_RATE)));
                    car.setWeekendRate(cursor.getDouble(cursor.getColumnIndex(DBHelper.COLUMN_CAR_WEEKEND_RATE)));
                    car.setWeeklyRate(cursor.getDouble(cursor.getColumnIndex(DBHelper.COLUMN_CAR_WEEKLY_RATE)));
                    car.setDailyGps(cursor.getDouble(cursor.getColumnIndex(DBHelper.COLUMN_CAR_DAILY_GPS)));
                    car.setDailyOnstar(cursor.getDouble(cursor.getColumnIndex(DBHelper.COLUMN_CAR_DAILY_ONSTAR)));
                    car.setDailySirius(cursor.getDouble(cursor.getColumnIndex(DBHelper.COLUMN_CAR_DAILY_SIRIUS)));
                    //add car to list
                    carList.add(car);
                } while (cursor.moveToNext());
            }
        } finally {
            try {
                cursor.close(); //always close cursor!
            }
            catch (Exception e) {
            }
        }
        return carList;
    }

}
