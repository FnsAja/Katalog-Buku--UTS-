package com.example.katalogbuku;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import java.util.List;

public class DetailActivity extends AppCompatActivity {

    TextView Judul, Author, Tahun, Deskripsi;
    ImageView Gambar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        SqliteHelper db = new SqliteHelper(getApplicationContext());
        List<Buku> booklist = db.getAllBook();
        initViews();

        Bundle bundle = getIntent().getExtras();
        int i = bundle.getInt("id");
        Judul.setText(booklist.get(i).getBookName());
        Author.setText(booklist.get(i).getAuthor());
        Tahun.setText(booklist.get(i).getYear());
        Deskripsi.setText(booklist.get(i).getDesc());
        Gambar.setImageResource(booklist.get(i).getImage());
    }

    private void initViews(){
        Judul = findViewById(R.id.textViewJudulDetail);
        Author = findViewById(R.id.textViewAuthorDetail);
        Tahun = findViewById(R.id.textViewTahunDetail);
        Deskripsi = findViewById(R.id.textViewDescDetail);
        Gambar = findViewById(R.id.imageViewDetail);
    }
}