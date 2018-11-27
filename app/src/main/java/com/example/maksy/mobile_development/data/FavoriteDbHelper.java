package com.example.maksy.mobile_development.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import static com.example.maksy.mobile_development.data.FavoriteContract.FavoriteEntry.TABLE_NAME;

public class FavoriteDbHelper extends SQLiteOpenHelper {

    SQLiteDatabase mDatabase;

    public FavoriteDbHelper(Context context) {
        super(context, TABLE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase mDatabase) {
        String createTable = "CREATE TABLE " +  TABLE_NAME+ " " +
                "(ID INTEGER PRIMARY KEY AUTOINCREMENT, " +
                FavoriteContract.FavoriteEntry.COLUMN_TITLE +" TEXT)";
        mDatabase.execSQL(createTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        mDatabase.execSQL("DROP TABLE  " + TABLE_NAME);
        onCreate(db);
    }

    public boolean addToFavorites(String title) {
        SQLiteDatabase mDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(FavoriteContract.FavoriteEntry.COLUMN_TITLE, title);
        mDatabase.insert(TABLE_NAME, null, contentValues);
        return true;
    }

    public Cursor getData(){
        SQLiteDatabase mDatabase = this.getWritableDatabase();
        String query = "SELECT * FROM " + TABLE_NAME;
        return mDatabase.rawQuery(query, null);
    }

    public void deleteFromFavourite( String title){
        SQLiteDatabase mDatabase = this.getWritableDatabase();
        mDatabase.delete(TABLE_NAME,
                FavoriteContract.FavoriteEntry.COLUMN_TITLE + " = '" + title + "'",
                null);
    }

}
