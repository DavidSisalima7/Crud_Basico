package com.david.sisalima.sqlite_productos.model;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class SQLHelper extends SQLiteOpenHelper {

    public static final String DBNAME="producto.bd";
    public static final String  TABLENAME="productos";
    public static final int VER=1;

    public SQLHelper(@Nullable Context context) {
        super(context, DBNAME, null, VER);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query="Create TABLE "+TABLENAME+" (" +
                "codigo_producto primary key, " +
                "nombre, " +
                "precio" +
                ")";
        db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String query= "drop table if exists "+TABLENAME;
        db.execSQL(query);
    }
}
