package com.example.quizgame;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

public class FlagsDAO {
    public ArrayList<FlagsModle> getRandomTenQuestion(FlagsDatabase fd) {
        ArrayList<FlagsModle> modleArrayList = new ArrayList<>();
        SQLiteDatabase liteDatabase = fd.getWritableDatabase();
        Cursor cursor = liteDatabase.rawQuery("SELECT * FROM flagquizgametable ORDER BY RANDOM () LIMIT 10", null);

        int flagIdIndex = cursor.getColumnIndex("flag_id");
        int flagNameIndex = cursor.getColumnIndex("flag_name");
        int flagImageIndex = cursor.getColumnIndex("flag_image");

        while (cursor.moveToNext())
        {
            FlagsModle modle = new FlagsModle(cursor.getInt(flagIdIndex),
                    cursor.getString(flagNameIndex),
                    cursor.getString(flagImageIndex));

            modleArrayList.add(modle);
        }
        return modleArrayList;

    }

    public ArrayList<FlagsModle> getRandomThreeOption(FlagsDatabase fd, int flag_id) {
        ArrayList<FlagsModle> modleArrayList = new ArrayList<>();
        SQLiteDatabase liteDatabase = fd.getWritableDatabase();
        Cursor cursor = liteDatabase.rawQuery("SELECT * FROM flagquizgametable WHERE flag_id != "+flag_id+" ORDER BY RANDOM () LIMIT 3", null);

        int flagIdIndex = cursor.getColumnIndex("flag_id");
        int flagNameIndex = cursor.getColumnIndex("flag_name");
        int flagImageIndex = cursor.getColumnIndex("flag_image");

        while (cursor.moveToNext())
        {
            FlagsModle modle = new FlagsModle(cursor.getInt(flagIdIndex),
                    cursor.getString(flagNameIndex),
                    cursor.getString(flagImageIndex));

            modleArrayList.add(modle);
        }
        return modleArrayList;

    }
}
