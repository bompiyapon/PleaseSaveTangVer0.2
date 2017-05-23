package com.example.armfluke.pleasesavetang;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.ArrayAdapter;
import android.view.View;
import android.content.SharedPreferences;
import android.content.Context;

public class AddActivity extends AppCompatActivity {
    private String[] state= {"Deposit","Withdraw"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        Spinner dropdown = (Spinner)findViewById(R.id.Type);
        String[] items = new String[]{"Deposit","Withdraw"};
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, items);
        dropdown.setAdapter(adapter);

    }

    public void back(View view){
        Intent intent = new Intent(AddActivity.this, MainActivity.class);
        startActivity(intent);
    }

    public void addActivity(View view){
        SharedPreferences sharedpreferences = getSharedPreferences("ID", Context.MODE_PRIVATE);
        String Username = sharedpreferences.getString("Username",null);
        EditText Amount = (EditText)findViewById(R.id.Amount);
        EditText Note = (EditText)findViewById(R.id.Note);
        EditText Date = (EditText)findViewById(R.id.Date);
        Spinner Type = (Spinner) findViewById(R.id.Type);
        DatabaseHandler db = new DatabaseHandler(this);
        db.addActivity(Username,Amount.getText().toString(),Note.getText().toString(),Date.getText().toString(),Type.getSelectedItem().toString());


        AlertDialog alertDialog = new AlertDialog.Builder(AddActivity.this).create();
        alertDialog.setTitle("Alert");
        alertDialog.setMessage("New activity added");
        alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        Intent intent = new Intent(AddActivity.this, MainActivity.class);
                        startActivity(intent);
                    }
                });
        alertDialog.show();
    }
}
