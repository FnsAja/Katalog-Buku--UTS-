package com.example.katalogbuku;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;


public class LoginActivity extends AppCompatActivity {

    EditText editTextUsername, editTextPassword;
    TextInputLayout textInputLayoutUsername, textInputLayoutPassword;
    Button buttonLogin;
    SqliteHelper sqliteHelper;
    Book book = new Book();

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        sqliteHelper = new SqliteHelper(this);
        sqliteHelper.addBook(book);
        initViews();

        buttonLogin.setOnClickListener(view -> {
            if(validateUsername()&&validatePassword()){
                String Username = editTextUsername.getText().toString();
                String Password = editTextPassword.getText().toString();

                User currUser = sqliteHelper.Authenticate(new User(null,Username, null, Password));

                if(currUser != null){
                    Snackbar.make(buttonLogin, "Successfully Logged In", Snackbar.LENGTH_LONG).show();

                    Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                    intent.putExtra("us", Username);
                    startActivity(intent);
                }else{
                    Snackbar.make(buttonLogin, "Failed to log in, please try again later", Snackbar.LENGTH_LONG).show();
                }
            }
        });
    }

    private void initViews(){
        editTextUsername = findViewById(R.id.editTextUsernameLogin);
        editTextPassword = findViewById(R.id.editTextPasswordLogin);
        textInputLayoutUsername = findViewById(R.id.textInputLayoutUsernameLogin);
        textInputLayoutPassword = findViewById(R.id.textInputLayoutPasswordLogin);
        buttonLogin = findViewById(R.id.btnLogin);
    }

    private boolean validateUsername(){
        boolean valid;

        String Username = editTextUsername.getText().toString();

        if(Username.isEmpty()){
            valid = false;
            textInputLayoutUsername.setError("Please enter a valid username!");
        }else{
            if(Username.length() >= 5){
                valid = true;
                textInputLayoutUsername.setError(null);
            }else{
                valid = false;
                textInputLayoutUsername.setError("Username is too short!");
            }
        }

        return valid;
    }

    private boolean validatePassword(){
        boolean valid;

        String Password = editTextPassword.getText().toString();

        if(Password.isEmpty()){
            valid = false;
            textInputLayoutUsername.setError("Please enter a valid password!");
        }else{
            if(Password.length() >= 5){
                valid = true;
                textInputLayoutUsername.setError(null);
            }else{
                valid = false;
                textInputLayoutUsername.setError("Password is too short!");
            }
        }

        return valid;
    }

    public void onClick(View view) {
        Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
        startActivity(intent);
    }
}