package com.mad.moviemobileapplication;

import static androidx.core.content.ContextCompat.startActivity;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class DBHandler extends SQLiteOpenHelper {

    public static final String DATABASE_NAME ="GameInfo.db";
    List<String> games=new ArrayList<String>();
    List<String> years=new ArrayList<String>();
    List<String> comments=new ArrayList<String>();
    public static DBHandler instance;


    public DBHandler(Context context) {
        super(context, DATABASE_NAME, null, 1);

    }

   public List getGames(){
        return games;
   }
   public List getYears(){
        return years;
   }
   public List getComments(){
        return comments;
   }

   public boolean addGame(String gameName,String gameYear){

        SQLiteDatabase db =getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(DatabaseMaster.Game.COLUMN_NAME_GAMENAME,gameName);
        values.put(DatabaseMaster.Game.COLUMN_NAME_YEAR,gameYear);

        long newRowID =db.insert(DatabaseMaster.Game.TABLE_NAME,null,values);

        if(newRowID> 0){

            return true;
        }else{
            return false;
        }
   }


   public List viewGames(){

        SQLiteDatabase db=getReadableDatabase();

        String[] projection = {
                DatabaseMaster.Game._ID,
                DatabaseMaster.Game.COLUMN_NAME_GAMENAME,
                DatabaseMaster.Game.COLUMN_NAME_YEAR
        };
        String sortOrder =DatabaseMaster.Game.COLUMN_NAME_YEAR + " DESC";

       Cursor cursor = db.query(
         DatabaseMaster.Game.TABLE_NAME,
         projection,
         null, null, null, null, sortOrder

       );

       while (cursor.moveToNext()){
           String gameName=cursor.getString(cursor.getColumnIndexOrThrow(DatabaseMaster.Game.COLUMN_NAME_GAMENAME));
           String gameYear=cursor.getString(cursor.getColumnIndexOrThrow(DatabaseMaster.Game.COLUMN_NAME_YEAR));

           games.add(gameName);
           years.add(gameYear);
       }
       cursor.close();
        return games;
   }

   public void insertComments(String comments,String gameName,String rating){

        SQLiteDatabase db = getWritableDatabase();

        ContentValues contentValues =new ContentValues();
       contentValues.put(DatabaseMaster.Comments.COLUMN_NAME_COMMENTS,comments);
       contentValues.put(DatabaseMaster.Comments.COLUMN_NAME_GAMENAME,gameName);
       contentValues.put(DatabaseMaster.Comments.COLUMN_NAME_RATING,rating);

       long newRowID=db.insert(DatabaseMaster.Comments.TABLE_NAME,null,contentValues);


   }
   public List viewComments(){


        SQLiteDatabase db =getReadableDatabase();
        String[] projection ={
          DatabaseMaster.Comments._ID,
          DatabaseMaster.Comments.COLUMN_NAME_COMMENTS,
          DatabaseMaster.Comments.COLUMN_NAME_GAMENAME,
          DatabaseMaster.Comments.COLUMN_NAME_RATING
        };

        String sortOrder =DatabaseMaster.Comments.COLUMN_NAME_COMMENTS+" Desc";

        Cursor cursor =db.query(
                DatabaseMaster.Comments.TABLE_NAME,
                projection,
                null,
                null,
                null,
                null,
                sortOrder
        );
        while (cursor.moveToNext()){
            String comment =cursor.getString(cursor.getColumnIndexOrThrow(DatabaseMaster.Comments.COLUMN_NAME_COMMENTS));
            String gameName =cursor.getString(cursor.getColumnIndexOrThrow(DatabaseMaster.Comments.COLUMN_NAME_GAMENAME));
            String rating =cursor.getString(cursor.getColumnIndexOrThrow(DatabaseMaster.Comments.COLUMN_NAME_RATING));

            comments.add(comment);
            games.add(gameName);

        }
        cursor.close();

        return comments;

   }




    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String SQL_CREATE_ENTRIES =
                "CREATE TABLE " + DatabaseMaster.Users.TABLE_NAME + " ("+
                        DatabaseMaster.Users._ID+ " INTEGER PRIMARY KEY," +
                        DatabaseMaster.Users.COLUMN_NAME_USERNAME+ " TEXT," +
                        DatabaseMaster.Users.COLUMN_NAME_PASSWORD+ " TEXT,"+
                        DatabaseMaster.Users.COLUMN_NAME_USERTYPE+ " Text)";
        sqLiteDatabase.execSQL(SQL_CREATE_ENTRIES);


        String SQL_CREATE_ENTRIES2 =
                "CREATE TABLE " + DatabaseMaster.Game.TABLE_NAME + " ("+
                        DatabaseMaster.Game._ID+ " INTEGER PRIMARY KEY," +
                        DatabaseMaster.Game.COLUMN_NAME_GAMENAME+ " TEXT," +
                        DatabaseMaster.Game.COLUMN_NAME_YEAR+ " TEXT)";
        sqLiteDatabase.execSQL(SQL_CREATE_ENTRIES2);


        String SQL_CREATE_ENTRIES3 =
                "CREATE TABLE " + DatabaseMaster.Comments.TABLE_NAME + " ("+
                        DatabaseMaster.Comments._ID+ " INTEGER PRIMARY KEY," +
                        DatabaseMaster.Comments.COLUMN_NAME_GAMENAME+ " TEXT," +
                        DatabaseMaster.Comments.COLUMN_NAME_RATING+ " TEXT,"+
                        DatabaseMaster.Comments.COLUMN_NAME_COMMENTS+ " Text)";
        sqLiteDatabase.execSQL(SQL_CREATE_ENTRIES3);


    }

    public  void RegisterUser(String userName, String password){

        SQLiteDatabase sqLiteDatabase =getWritableDatabase();

        ContentValues values =new ContentValues();
        values.put(DatabaseMaster.Users.COLUMN_NAME_USERNAME,userName);
        values.put(DatabaseMaster.Users.COLUMN_NAME_PASSWORD,password);


        long newRowID =sqLiteDatabase.insert(DatabaseMaster.Users.TABLE_NAME,null,values);


    }
    public String loginUser(String userName,String password){

        if(userName=="admin" && password=="admin"){
          return "admin";


        }
        else if(userName==DatabaseMaster.Users.COLUMN_NAME_USERNAME && password == DatabaseMaster.Users.COLUMN_NAME_PASSWORD){

            return "normalUser";

        }
        else{
            return "error";
        }

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
