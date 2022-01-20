package com.example.katalogbuku;

import android.os.Handler;
import android.os.Looper;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class RegisterActivity extends AppCompatActivity {

    EditText editTextUsername, editTextEmail, editTextPassword;
    TextInputLayout textInputLayoutUsername, textInputLayoutEmail, textInputLayoutPassword;
    Button buttonSignUp;
    SqliteHelper sqliteHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        sqliteHelper = new SqliteHelper(this);
        initViews();

        buttonSignUp.setOnClickListener(view -> {
            if(validateUsername() && validateEmail() && validatePassword()){
                String Username = editTextUsername.getText().toString();
                String Email = editTextEmail.getText().toString();
                String Password = editTextPassword.getText().toString();

                if(!sqliteHelper.isEmailExists(Email)){
                    sqliteHelper.addUser(new User(null, Username, Email, Password));
                    Snackbar.make(buttonSignUp, "User created successfully!", Snackbar.LENGTH_LONG).show();
                    new Handler(Looper.getMainLooper()).postDelayed(this::finish, Snackbar.LENGTH_LONG);
                }
                else{
                    Snackbar.make(buttonSignUp, "Email is already used, please try different Email", Snackbar.LENGTH_LONG).show();
                }
                finish();
            }
            else{
                Snackbar.make(buttonSignUp, "Register failed, please check Username, Email, or Password", Snackbar.LENGTH_LONG).show();
            }
        });
    }

    public void initViews(){
        textInputLayoutUsername = findViewById(R.id.textInputLayoutUsernameRegister);
        textInputLayoutEmail = findViewById(R.id.textInputLayoutEmailRegister);
        textInputLayoutPassword = findViewById(R.id.textInputLayoutPasswordRegister);
        editTextUsername = findViewById(R.id.editTextUsernameRegister);
        editTextEmail = findViewById(R.id.editTextEmailRegister);
        editTextPassword = findViewById(R.id.editTextPasswordRegister);
        buttonSignUp = findViewById(R.id.btnRegister);
    }

    private boolean validateUsername(){
        boolean validUser;

        String Username = editTextUsername.getText().toString();

        if(Username.isEmpty()){
            validUser = false;
            textInputLayoutUsername.setError("Please enter valid username!");
        }
        else{
            if(Username.length() >= 5){
                validUser = true;
                textInputLayoutUsername.setError(null);
            }
            else{
                validUser = false;
                textInputLayoutUsername.setError("Username is too short");
            }
        }
        return validUser;
    }

    private boolean validateEmail(){
        boolean validemail;

        String Email = editTextEmail.getText().toString();

        if(!Patterns.EMAIL_ADDRESS.matcher(Email).matches()){
            validemail = false;
            textInputLayoutEmail.setError("Please enter valid email!");
        }
        else{
            validemail = true;
            textInputLayoutEmail.setError(null);
        }
        return validemail;
    }

    private boolean validatePassword(){
        boolean validpass;

        String Username = editTextUsername.getText().toString();

        if(Username.isEmpty()){
            validpass = false;
            textInputLayoutUsername.setError("Please enter valid password!");
        }
        else{
            if(Username.length() >= 5){
                validpass = true;
                textInputLayoutUsername.setError(null);
            }
            else{
                validpass = false;
                textInputLayoutUsername.setError("Password is too short");
            }
        }
        return validpass;
    }

    public void onClick(View view) {
        finish();
    }
}