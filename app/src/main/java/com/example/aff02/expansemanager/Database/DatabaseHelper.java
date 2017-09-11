package com.example.aff02.expansemanager.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.aff02.expansemanager.Model.DataModel;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

/**
 * Created by AFF02 on 23-Aug-17.
 */

public class DatabaseHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "CUSTOMER_DETAILS.DB";
    public static final String TABLE_NAME = "CUSTOMER_DATA";
    public static final int DATABASE_VERSION = 1;

    private static final String COLUMN_ID = "_id";
    public static final String COL_NAME = "Name";
    public static final String COL_RUPEES = "Rupees";
    public static final String COL_DESC = "Desc";
    public static final String COL_PAYMENT = "Payment";
    public static final String COL_DATE = "Date";


    String br = "CREATE TABLE "+TABLE_NAME+"("+COLUMN_ID+ " INTEGER PRIMARY KEY,"+COL_NAME+ " TEXT, "+COL_RUPEES+ " INTEGER, "+COL_DESC+ " TEXT, "+COL_DATE+ " TEXT, "+COL_PAYMENT+" TEXT);";;

    DataModel dataModel = new DataModel();
    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(br);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        onCreate(db);
        db.execSQL("DROP TABLE IF EXISTS"+TABLE_NAME+" ;");
    }

    public long insertData (String name, String rupees, String desc, String date,String payment)
    {
        System.out.print("Hello "+br);
        SQLiteDatabase db =  this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_NAME,name);
        contentValues.put(String.valueOf(COL_RUPEES),rupees);
        contentValues.put(COL_DESC,desc);
        contentValues.put(COL_DATE,getDate());
        contentValues.put(COL_PAYMENT,payment);
        long result = db.insert(TABLE_NAME,null,contentValues);
        db.close();
        return result;
    }

    public String getDate()
    {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss", Locale.getDefault());
        Date date1 = new Date();
         return dateFormat.format(date1);
    }
    public List<DataModel> getData()
    {
        List<DataModel> dataModelList = new ArrayList<DataModel>();
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from "+TABLE_NAME+" ;",null);

        StringBuffer stringBuffer = new StringBuffer();
        DataModel dataModel = null;

        while (cursor.moveToNext())
        {
            dataModel = new DataModel();

            String name = cursor.getString(cursor.getColumnIndexOrThrow("Name"));
            String desc = cursor.getString(cursor.getColumnIndexOrThrow("Desc"));
            String  rupees = String.valueOf(cursor.getInt(cursor.getColumnIndexOrThrow("Rupees")));
            String date = cursor.getString(cursor.getColumnIndexOrThrow(COL_DATE));
            String payment = cursor.getString(cursor.getColumnIndexOrThrow(COL_PAYMENT));

            dataModel.setName(name);
            dataModel.setDesc(desc);
            dataModel.setRupees(Integer.parseInt(rupees));
            dataModel.setDate(date);
            dataModel.setPayment(payment);

            stringBuffer.append(dataModel);

            dataModelList.add(dataModel);
        }

        for (DataModel mo : dataModelList)
        {
            Log.i("Hellomo",""+mo.getName());
        }

        return dataModelList;
    }

    public void updateRow(DataModel dataModel)
    {
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_NAME,dataModel.getName());
        contentValues.put(COL_PAYMENT,dataModel.getPayment());
        contentValues.put(COL_DATE,dataModel.getDate());
        contentValues.put(COL_RUPEES,dataModel.getRupees());
        contentValues.put(COL_DESC,dataModel.getDesc());
        SQLiteDatabase database = this.getWritableDatabase();

        database.update(TABLE_NAME, contentValues, "_id="+COLUMN_ID, null);
    }

    public void deleteRow(String name)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("DELETE FROM " +TABLE_NAME+ " WHERE "+COL_NAME+"='"+name+"'");
        db.close();
        //return true;

    }
}
