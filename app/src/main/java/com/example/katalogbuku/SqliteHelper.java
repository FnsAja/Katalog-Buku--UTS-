package com.example.katalogbuku;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;

public class SqliteHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "katalogbukudb";
    public static final int DATABASE_VERSION = 1;
    public static final String TABLE_USER = "user";
    public static final String TABLE_BUKU = "book";

    public static final String KEY_ID = "_id";
    public static final String KEY_USER_NAME = "username";
    public static final String KEY_EMAIL = "email";
    public static final String KEY_PASSWORD = "password";

    public static final String KEY_NAMA_BUKU = "bookname";
    public static final String KEY_AUTHOR_BUKU = "bookauthor";
    public static final String KEY_DESC_BUKU = "bookdesc";
    public static final String KEY_TAHUN_BUKU = "bookyear";
    public static final String KEY_GAMBAR_BUKU = "bookimage";

    public static final String SQL_TABLE_USERS = "CREATE TABLE " + TABLE_USER
            + " ( "
            + KEY_ID + " INTEGER PRIMARY KEY,"
            + KEY_USER_NAME + " TEXT, "
            + KEY_EMAIL + " TEXT, "
            + KEY_PASSWORD + " TEXT " + " )";

    public static final String SQL_TABLE_BOOK = "CREATE TABLE " + TABLE_BUKU
            + " ( "
            + " _id INTEGER PRIMARY KEY AUTOINCREMENT,"
            + KEY_NAMA_BUKU + " TEXT, "
            + KEY_AUTHOR_BUKU + " TEXT, "
            + KEY_TAHUN_BUKU + " TEXT, "
            + KEY_DESC_BUKU + " TEXT, "
            + KEY_GAMBAR_BUKU + " INTEGER" + " )";

    public SqliteHelper(@Nullable Context context){
        super (context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(SQL_TABLE_USERS);
        sqLiteDatabase.execSQL(SQL_TABLE_BOOK);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL(" DROP TABLE IF EXISTS " + TABLE_USER);
        sqLiteDatabase.execSQL(" DROP TABLE IF EXISTS " + TABLE_BUKU);

        onCreate(sqLiteDatabase);
    }

    public void addBook(Book book){

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();

        for (int i = 0; i < book.nama.length; i++){
            values.put(KEY_ID, i);
            values.put(KEY_NAMA_BUKU, book.nama[i]);
            values.put(KEY_AUTHOR_BUKU, book.penulis[i]);
            values.put(KEY_TAHUN_BUKU, book.tahun[i]);
            values.put(KEY_DESC_BUKU, book.deskripsi[i]);
            values.put(KEY_GAMBAR_BUKU, book.gambar[i]);

            db.insert(TABLE_BUKU, null, values);
        }

    }

    public List<Buku> getAllBook() {
        List<Buku> bookList = new ArrayList<Buku>();
        String query = "SELECT  * FROM " + TABLE_BUKU;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);

        Buku buku;

        if (cursor.moveToFirst()) {
            do {
                buku = new Buku(null,null,null,null,null,null);
                buku.setId(cursor.getString(0));
                buku.setBookName(cursor.getString(1));
                buku.setAuthor(cursor.getString(2));
                buku.setYear(cursor.getString(3));
                buku.setDesc(cursor.getString(4));
                buku.setImage(cursor.getInt(5));
                bookList.add(buku);
            } while (cursor.moveToNext());
        }
        return bookList;
    }

    public void addUser(User user){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put(KEY_USER_NAME, user.userName);
        values.put(KEY_EMAIL, user.email);
        values.put(KEY_PASSWORD, user.password);

        db.insert(TABLE_USER, null, values);
    }

    public User Authenticate(User user){
        SQLiteDatabase db = this.getReadableDatabase();
        @SuppressLint("Recycle")Cursor cursor = db.query(TABLE_USER, //select table
                new String[]{KEY_ID, KEY_USER_NAME, KEY_EMAIL, KEY_PASSWORD}, //select kolom
                KEY_USER_NAME + "=?",
                new String[]{user.userName},//Where Clause
                null, null, null);

        if(cursor != null && cursor.moveToFirst() && cursor.getCount()>0){
            User user1 = new User(cursor.getString(0), cursor.getString(1),
                    cursor.getString(2), cursor.getString(3));

            if(user.password.equalsIgnoreCase(user1.password)){
                return user1;
            }
        }

        return null;
    }

    public boolean isEmailExists(String email){
        SQLiteDatabase db = this.getReadableDatabase();
        @SuppressLint("Recycle")Cursor cursor = db.query(TABLE_USER, //select table
                new String[]{KEY_ID, KEY_USER_NAME, KEY_EMAIL, KEY_PASSWORD}, //select kolom
                KEY_EMAIL + "=?",
                new String[]{email},//Where Clause
                null, null, null);

        return cursor != null && cursor.moveToFirst() && cursor.getCount() > 0;
    }
}