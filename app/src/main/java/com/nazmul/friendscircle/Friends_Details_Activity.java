package com.nazmul.friendscircle;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class Friends_Details_Activity extends AppCompatActivity {


    EditText txtName,txtCell,txtEmail,txtUniversity,txtCompany,txtPosition;
    Button btnEdit,btnUpdate,btnDelete;

    // for recived data from view friend activity
    String getID,getName,getCell,getEmail,getUniversity,getCompany,getPosition,getUser_user_cell;
//                intent.putExtra("id",userID);
//                intent.putExtra("name",userName);
//                intent.putExtra("cell",userCell);
//                intent.putExtra("email",userEmail);
//                intent.putExtra("universtiy",userUniversity);
//                intent.putExtra("company",userCompany);
//                intent.putExtra("position",userPosition);
//                intent.putExtra("user_user_cell",user_User_cell);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_friends__details_);
//initialized for friend details form field.
      txtName = findViewById(R.id.txt_name);
      txtCell = findViewById(R.id.txt_cell);
      txtEmail = findViewById(R.id.txt_email);
      txtUniversity = findViewById(R.id.txt_university);
      txtCompany = findViewById(R.id.txt_current_job);
      txtPosition = findViewById(R.id.txt_position);

      //button initialized

        btnEdit = findViewById(R.id.btn_edit);
        btnUpdate = findViewById(R.id.btn_update);
        btnDelete = findViewById(R.id.btn_delete);


      //for desable edit text

        txtName.setEnabled(false);
        txtCell.setEnabled(false);
        txtEmail.setEnabled(false);
        txtUniversity.setEnabled(false);
        txtCompany.setEnabled(false);
        txtPosition.setEnabled(false);

        getID = getIntent().getExtras().getString("id");
        getName= getIntent().getExtras().getString("name");
        getCell = getIntent().getExtras().getString("cell");
        getEmail = getIntent().getExtras().getString("email");
        getUniversity = getIntent().getExtras().getString("university");
        getCompany =getIntent().getExtras().getString("company");
        getPosition = getIntent().getExtras().getString("position");
        getUser_user_cell = getIntent().getExtras().getString("user_user_cell");
//         //
//        Log.d("getID",getID);
//        Log.d("getName",getName);

        getSupportActionBar().setHomeButtonEnabled(true); //for back button
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);//for back button
        getSupportActionBar().setTitle("Friend Details");//for actionbar title



        txtName.setText(getName);
        txtCell.setText(getCell);
        txtEmail.setText(getEmail);
        txtUniversity.setText(getUniversity);
        txtCompany.setText(getCompany);
        txtPosition.setText(getPosition);







    }
}
