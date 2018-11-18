package com.arunika.arlingtonauto.DAO;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import com.arunika.arlingtonauto.DATABASE.DBHelper;
import com.arunika.arlingtonauto.model.Reservation;
import com.arunika.arlingtonauto.model.ReservationDetails;

import java.util.ArrayList;

public class ReservationDAO {
    private static Context context;
    private static SQLiteDatabase Database;
    private static DBHelper DBHelper;

    private static ReservationDAO instance;
    public static synchronized ReservationDAO getInstance(Context context) {
        if (instance == null) {
            instance = new ReservationDAO(context);
        }
        return instance;
    }
    private ReservationDAO(Context context) {
        DBHelper = DBHelper.getInstance(context);
        this.context = context;
        try {
            Database = DBHelper.getWritableDatabase(); // open the database
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public static long insertReservation(Reservation reservation) {
        ContentValues values = new ContentValues();
        values.put(DBHelper.COLUMN_RESERVATION_USER_ID, reservation.getUserId());
        values.put(DBHelper.COLUMN_RESERVATION_CAR_ID, reservation.getCarId());
        values.put(DBHelper.COLUMN_RESERVATION_CHECK_OUT, reservation.getEndTimeAsString());
        values.put(DBHelper.COLUMN_RESERVATION_RETURN, reservation.getEndTimeAsString());
        values.put(DBHelper.COLUMN_RESERVATION_HAS_GPS, reservation.getHasGps());
        values.put(DBHelper.COLUMN_RESERVATION_HAS_ONSTAR, reservation.getHasOnstar());
        values.put(DBHelper.COLUMN_RESERVATION_HAS_SIRIUS, reservation.getHasSirius());
        values.put(DBHelper.COLUMN_RESERVATION_TOTAL_PRICE, reservation.getTotalPrice());
        long insertId = Database.insert(DBHelper.TABLE_RESERVATION, null, values);
        return insertId;
    }
    public static ArrayList<ReservationDetails> getCustomerReservations(
            String start, String end, int userId) {

        ArrayList<ReservationDetails> myResList = new ArrayList<>();
        //do stuff here
        return myResList;
    }
}
