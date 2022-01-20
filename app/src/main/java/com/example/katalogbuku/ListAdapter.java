package com.example.katalogbuku;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class ListAdapter extends CursorAdapter {
    public ListAdapter(Context context, Cursor c) {
        super(context, c, 0);
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        return LayoutInflater.from(context).inflate(R.layout.listview_adapter,parent,false);
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        TextView Judul = (TextView) view.findViewById(R.id.textViewJudulBuku);
        TextView Author = (TextView) view.findViewById(R.id.textViewAuthorBuku);
        ImageView Gambar = (ImageView) view.findViewById(R.id.imageViewListAdapter);

        String judul = cursor.getString(cursor.getColumnIndexOrThrow("bookname"));
        String author = cursor.getString(cursor.getColumnIndexOrThrow("bookauthor"));
        String gambar = cursor.getString(cursor.getColumnIndexOrThrow("bookimage"));

        Judul.setText(judul);
        Author.setText(author);
        Gambar.setImageResource(Integer.parseInt(gambar));
    }
}