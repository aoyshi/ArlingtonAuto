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
        values.put(DBHelper.COLUMN_RESERVATION_CHECK_OUT, reservation.getStartTimeAsString());
        values.put(DBHelper.COLUMN_RESERVATION_RETURN, reservation.getEndTimeAsString());
        values.put(DBHelper.COLUMN_RESERVATION_HAS_GPS, reservation.getHasGps());
        values.put(DBHelper.COLUMN_RESERVATION_HAS_ONSTAR, reservation.getHasOnstar());
        values.put(DBHelper.COLUMN_RESERVATION_HAS_SIRIUS, reservation.getHasSirius());
        values.put(DBHelper.COLUMN_RESERVATION_TOTAL_PRICE, reservation.getTotalPrice());
        long insertId = Database.insert(DBHelper.TABLE_RESERVATION, null, values);
        return insertId;
    }

    public static ArrayList<ReservationDetails> getCustomerReservations
            (String searchStart, String searchEnd, int userId) {
        String query = "SELECT * FROM CAR,USER,RESERVATION "
                + " WHERE carId = rCarId AND userId=rUserId "
                + " AND userId="+userId+" AND checkOut <= '"+searchEnd+"' and '"+searchStart+"' <= return"
                + " ORDER BY checkOut DESC";
        return getResList(query);
    }

    public static ArrayList<ReservationDetails> getManagerReservations
            (String searchStart, String searchEnd) {
        String query = "";
        /*
        String query = " SELECT * "
				+ " FROM reservation,user,car,payment"
				+ " WHERE payment.id=reservation.payment_id AND payment.user_id=user.id AND car.id=reservation.car_id"
				+ " AND iscanceled!=1 AND isdeleted !=1 AND start_time <= '"+searchEnd+"' and '"+searchStart+"' <= end_time"
				+ " ORDER BY start_time DESC";
    	*/
        return getResList(query);
    }

    private static ArrayList<ReservationDetails> getResList(String query) {
        ArrayList<ReservationDetails> resList = null;
        Cursor cursor = Database.rawQuery(query, null);
        ReservationDetails reservationDetails;
        try {
            // looping through all rows and adding res to list
            if (cursor.moveToFirst()) {
                do {
                    resList = new ArrayList<>();
                    reservationDetails = new ReservationDetails();
                    //set res attributes from db
                    reservationDetails.setId(cursor.getInt(cursor.getColumnIndex(DBHelper.COLUMN_RESERVATION_ID)));
                    reservationDetails.setRenterUsername(cursor.getString(cursor.getColumnIndex(DBHelper.COLUMN_USER_USERNAME)));
                    reservationDetails.setStartAndEndTimes(
                            cursor.getString(cursor.getColumnIndex(DBHelper.COLUMN_RESERVATION_CHECK_OUT)),
                            cursor.getString(cursor.getColumnIndex(DBHelper.COLUMN_RESERVATION_RETURN)));
                    reservationDetails.setCarName(cursor.getString(cursor.getColumnIndex(DBHelper.COLUMN_CAR_NAME)));
                    reservationDetails.setCapacity(cursor.getInt(cursor.getColumnIndex(DBHelper.COLUMN_CAR_CAPACITY)));
                    reservationDetails.setHasSirius(cursor.getInt(cursor.getColumnIndex(DBHelper.COLUMN_RESERVATION_HAS_GPS)));
                    reservationDetails.setHasOnstar(cursor.getInt(cursor.getColumnIndex(DBHelper.COLUMN_RESERVATION_HAS_ONSTAR)));
                    reservationDetails.setHasSirius(cursor.getInt(cursor.getColumnIndex(DBHelper.COLUMN_RESERVATION_HAS_SIRIUS)));
                    reservationDetails.setTotalPrice(cursor.getDouble(cursor.getColumnIndex(DBHelper.COLUMN_RESERVATION_TOTAL_PRICE)));
                    //add res to list
                    resList.add(reservationDetails);
                } while (cursor.moveToNext());
            }
        } finally {
            try {
                cursor.close(); //always close cursor!
            }
            catch (Exception e) {
            }
        }
        return resList;
    }

}
