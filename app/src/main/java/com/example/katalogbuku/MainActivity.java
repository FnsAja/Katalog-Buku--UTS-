package com.example.katalogbuku;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView namaUser;
    ListView listView;
    Button help;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SqliteHelper sqliteHelper = new SqliteHelper(getApplicationContext());
        SQLiteDatabase db = sqliteHelper.getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM book", null);

        namaUser = findViewById(R.id.textViewUser);
        Intent getdata = getIntent();
        Bundle bundle = getdata.getExtras();

        if(bundle!=null){
            namaUser.setText("Hello, " + bundle.getString("us"));
        }

        listView = (ListView) findViewById(R.id.listView);
        ListAdapter listAdapter = new ListAdapter(this, cursor);
        listView.setAdapter(listAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(MainActivity.this, DetailActivity.class);
                intent.putExtra("id", i);
                startActivity(intent);
            }
        });

        help = findViewById(R.id.btnHelp);
        help.setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity.this, HelpActivity.class);
            startActivity(intent);
        });
    }
}