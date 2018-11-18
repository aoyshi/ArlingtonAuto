package com.arunika.arlingtonauto.DAO;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import com.arunika.arlingtonauto.DATABASE.DBHelper;
import com.arunika.arlingtonauto.model.Reservation;
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
}
