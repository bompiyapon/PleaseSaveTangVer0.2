package com.example.armfluke.pleasesavetang;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.content.Intent;
import android.app.AlertDialog;
import android.content.DialogInterface;

public class Register extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
    }

    public void register(View view){
        DatabaseHandler db = new DatabaseHandler(this);
        EditText username = (EditText)findViewById(R.id.Username);
        EditText password = (EditText)findViewById(R.id.Password);
        EditText passwordRe = (EditText)findViewById(R.id.PasswordRe);
        Contact qusername = db.getContact(username.getText().toString());

        if(password.getText().toString().equals(passwordRe.getText().toString())) {
            if (qusername.getUsername().equals(username.getText().toString())) {
                AlertDialog alertDialog = new AlertDialog.Builder(Register.this).create();
                alertDialog.setTitle("Alert");
                alertDialog.setMessage("Your username is already existed");
                alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        });
                alertDialog.show();
            }else{
                Contact contact = new Contact(username.getText().toString(),password.getText().toString());
                db.addContact(contact);
                AlertDialog alertDialog = new AlertDialog.Builder(Register.this).create();
                alertDialog.setTitle("Alert");
                alertDialog.setMessage("Register complete. Redirected to Login");
                alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                                Intent intent = new Intent(Register.this, Login.class);
                                startActivity(intent);
                            }
                        });
                alertDialog.show();
            }
        }else{
            AlertDialog alertDialog = new AlertDialog.Builder(Register.this).create();
            alertDialog.setTitle("Alert");
            alertDialog.setMessage("Your password is not match");
            alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    });
            alertDialog.show();
        }
    }

    public void back(View view){
        Intent intent = new Intent(Register.this, Login.class);
        startActivity(intent);
    }
}
