package com.example.katalogbuku;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class HelpActivity extends AppCompatActivity implements View.OnClickListener{

    Button Email, Telepon, Web;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help);
        Email = (Button) findViewById(R.id.btnEmailHelp);
        Telepon = (Button) findViewById(R.id.btnTeleponHelp);
        Web = (Button) findViewById(R.id.btnWebHelp);

        Email.setOnClickListener(this);
        Telepon.setOnClickListener(this);
        Web.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btnEmailHelp:
                Intent email = new Intent(Intent.ACTION_SEND);
                email.setType("text/plain");
                email.putExtra(email.EXTRA_EMAIL, new String[] {"s32190039@student.ubm.ac.id"});
                email.putExtra(email.EXTRA_SUBJECT, "KATALOG BUKU APP - HELP");
                email.putExtra(email.EXTRA_TEXT, "Hai, Ada sedikit masalah disini. Bisa reply?");
                try {
                    startActivity(Intent.createChooser(email, "Choose..."));
                }catch (Exception e){
                    Toast.makeText(HelpActivity.this, "There is no email client installed", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.btnTeleponHelp:
                String nomor = "085863599547";
                Intent tlp = new Intent(Intent.ACTION_DIAL);
                tlp.setData(Uri.fromParts("tel",nomor,null));
                startActivity(tlp);
                break;
            case R.id.btnWebHelp:
                String url = "https://www.ubm.ac.id/jurusan-ubm/teknik-informatika/";
                Intent web = new Intent(Intent.ACTION_VIEW);
                web.setData(Uri.parse(url));
                startActivity(web);
                break;
        }
    }
}