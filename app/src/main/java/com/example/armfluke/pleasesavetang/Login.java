package com.example.armfluke.pleasesavetang;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.content.Context;



public class Login extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        SharedPreferences sharedpreferences = getSharedPreferences("ID", Context.MODE_PRIVATE);
        if(sharedpreferences.contains("Username")){
            Intent intent = new Intent(Login.this, MainActivity.class);
            startActivity(intent);
        }
    }

    public void login(View v){
        DatabaseHandler db = new DatabaseHandler(this);
        EditText username = (EditText)findViewById(R.id.Username);
        EditText password = (EditText)findViewById(R.id.PasswordText);

        Contact information = db.getContact(username.getText().toString());
        if(information.getUsername().equals(username.getText().toString()) && information.getPassword().equals(password.getText().toString())){
            SharedPreferences sharedpreferences = getSharedPreferences("ID", Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedpreferences.edit();
            editor.putString("Username",information.getUsername());
            editor.putString("Password",information.getPassword());
            editor.commit();

            Intent intent = new Intent(Login.this, MainActivity.class);
            startActivity(intent);

        }else{
            AlertDialog alertDialog = new AlertDialog.Builder(Login.this).create();
            alertDialog.setTitle("Alert");
            alertDialog.setMessage("Your username or password is incorrect");
            alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    });
            alertDialog.show();
        }

    }

    public void register(View view){
        Intent intent = new Intent(Login.this, Register.class);
        startActivity(intent);
    }

}
