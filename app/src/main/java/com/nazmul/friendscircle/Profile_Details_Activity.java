package com.nazmul.friendscircle;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class Profile_Details_Activity extends AppCompatActivity {

    EditText txtName,txtCell,txtGender,txtDivisoin,txtPassword;
    Button btnUpdate;

    private ProgressDialog loading;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile__details_);


        txtName = findViewById(R.id.txt_name);
        txtCell = findViewById(R.id.txt_cell);
        txtGender = findViewById(R.id.txt_gender);
        txtDivisoin = findViewById(R.id.txt_division);
        txtPassword = findViewById(R.id.txt_password);

    }
}
