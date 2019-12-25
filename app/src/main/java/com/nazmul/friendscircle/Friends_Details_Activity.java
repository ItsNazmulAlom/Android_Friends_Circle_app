package com.nazmul.friendscircle;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class Friends_Details_Activity extends AppCompatActivity {


    EditText txtName,txtCell,txtEmail,txtUniversity,txtCompany,txtPosition;
    TextView ProfileID;
    Button btnEdit,btnUpdate,btnDelete;

    private ProgressDialog loading;

    // for recived data from view friend activity
    String getID,getName,getCell,getEmail,getUniversity,getCompany,getPosition,getUser_user_cell,get_cell;
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

      ProfileID = findViewById(R.id.profile_ID);

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
       // Log.d("NEW_GETID",getID);
        getName= getIntent().getExtras().getString("name");
        getCell = getIntent().getExtras().getString("cell");
        getEmail = getIntent().getExtras().getString("email");
        getUniversity = getIntent().getExtras().getString("university");
        getCompany =getIntent().getExtras().getString("company");
        getPosition = getIntent().getExtras().getString("position");
        getUser_user_cell = getIntent().getExtras().getString("user_user_cell");
//         //
       Log.d("getID",getUser_user_cell);
//        Log.d("getName",getName);

        getSupportActionBar().setHomeButtonEnabled(true); //for back button
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);//for back button
        getSupportActionBar().setTitle("Friend Details");//for actionbar title

        SharedPreferences sharedPreferences;
        sharedPreferences = getSharedPreferences(Constant.SHARED_PREF_NAME, Context.MODE_PRIVATE);
        get_cell = sharedPreferences.getString(Constant.CELL_SHARED_PREF,"Not availabe");

        //ProfileID.setText(getID);
        txtName.setText(getName);
        txtCell.setText(getCell);
        txtEmail.setText(getEmail);
        txtUniversity.setText(getUniversity);
        txtCompany.setText(getCompany);
        txtPosition.setText(getPosition);



      btnEdit.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View view) {

              if (get_cell.equals(getUser_user_cell)){

                  txtName.setEnabled(true);
                  txtCell.setEnabled(true);
                  txtEmail.setEnabled(true);
                  txtUniversity.setEnabled(true);
                  txtCompany.setEnabled(true);
                  txtPosition.setEnabled(true);


                  txtName.setTextColor(Color.BLUE);
                  txtCell.setTextColor(Color.BLUE);
                  txtEmail.setTextColor(Color.BLUE);
                  txtUniversity.setTextColor(Color.BLUE);
                  txtCompany.setTextColor(Color.BLUE);
                  txtPosition.setTextColor(Color.BLUE);
              }
              else {
                  Toast.makeText(Friends_Details_Activity.this,"You are not add this friend.",Toast.LENGTH_SHORT).show();

              }



          }
      });


// when update button press

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (get_cell.equals(getUser_user_cell)){

                    if (txtName.isEnabled()){
                        AlertDialog.Builder builder= new AlertDialog.Builder(Friends_Details_Activity.this);
                        builder.setIcon(R.drawable.wait_icon)
                                .setMessage("want to update friend?")
                                .setCancelable(false)
                                .setPositiveButton("YES", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialogInterface, int i) {
                                        UpdateFriend();
                                        dialogInterface.cancel();

                                    }
                                })
                                .setNegativeButton("NO", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialogInterface, int i) {
                                        dialogInterface.cancel();
                                    }
                                }).show();


                    } else {
                        Toast.makeText(Friends_Details_Activity.this,"please edit data",Toast.LENGTH_SHORT).show();
                    }

                }
                else {
                    Toast.makeText(Friends_Details_Activity.this,"You are not add this friend.",Toast.LENGTH_SHORT).show();

                }


            }
        });




        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (get_cell.equals(getUser_user_cell)){




                    if (txtName.isEnabled()){
                        AlertDialog.Builder builder= new AlertDialog.Builder(Friends_Details_Activity.this);
                        builder.setIcon(R.drawable.wait_icon)
                                .setMessage("want to Delete friend?")
                                .setCancelable(false)
                                .setPositiveButton("YES", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialogInterface, int i) {
                                        FriendDeleteFromServer();
                                        dialogInterface.cancel();

                                    }
                                })
                                .setNegativeButton("NO", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialogInterface, int i) {
                                        dialogInterface.cancel();
                                    }
                                }).show();


                    } else {
                        Toast.makeText(Friends_Details_Activity.this,"please edit data",Toast.LENGTH_SHORT).show();
                    }




                }else {
                    Toast.makeText(Friends_Details_Activity.this,"You are not add this friend.",Toast.LENGTH_SHORT).show();

                }

            }
        });



    }//on create method end





      public void UpdateFriend(){
//          txtName = findViewById(R.id.txt_name);
//          txtCell = findViewById(R.id.txt_cell);
//          txtEmail = findViewById(R.id.txt_email);
//          txtUniversity = findViewById(R.id.txt_university);
//          txtCompany = findViewById(R.id.txt_current_job);
//          txtPosition = findViewById(R.id.txt_position);

          final String name = txtName.getText().toString().trim();
          final String cell = txtCell.getText().toString().trim();
          final String email = txtEmail.getText().toString().trim();
          final String university = txtUniversity.getText().toString().trim();
          final String company = txtCompany.getText().toString().trim();
          final String position = txtPosition.getText().toString().trim();


          if(name.isEmpty()){
              txtName.setError("Please enter name!");
              txtName.requestFocus();
          }

          else if (cell.length()!=11 || cell.charAt(0)!='0' || cell.charAt(1)!='1' || cell.contains(" ")){
              txtCell.setError("Invalid number!");
              txtCell.requestFocus();
          }
          else if (email.isEmpty()){
              txtEmail.setError("Invalid email");
              txtEmail.requestFocus();
          }
          else if (university.isEmpty()){
              txtUniversity.setError("Enter universtiy name!");
              txtUniversity.requestFocus();
          }
          else if (company.isEmpty()){
              txtCompany.setError("Enter Current job");
              txtCompany.requestFocus();
          }
          else if (position.isEmpty()){
              txtPosition.setError("enter job position!");
              txtPosition.requestFocus();
          }
          else {

              loading = new ProgressDialog(this);
              loading.setIcon(R.drawable.wait_icon);
              loading.setTitle("Updating");
              loading.setMessage("Please wait....");
              loading.show();

              String URL = Constant.FRIEND_UPDATE;

              StringRequest stringRequest = new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {
                  @Override
                  public void onResponse(String response) {

                      Log.d("Response", response);

                      if (response.equals("success")) {
                          loading.dismiss();
                          Intent intent = new Intent(Friends_Details_Activity.this, View_Friend_Activity.class);
                          Toast.makeText(Friends_Details_Activity.this, "Update sucessfully", Toast.LENGTH_SHORT).show();
                          startActivity(intent);

                      } else if (response.equals("failure")) {
                          loading.dismiss();
                          Intent intent = new Intent(Friends_Details_Activity.this, View_Friend_Activity.class);
                          Toast.makeText(Friends_Details_Activity.this, "Update faile!", Toast.LENGTH_SHORT).show();
                          startActivity(intent);

                      } else {
                          loading.dismiss();
                          Toast.makeText(Friends_Details_Activity.this, "Network Error", Toast.LENGTH_SHORT).show();

                      }

                  }
              },


                      new Response.ErrorListener() {
                          @Override
                          public void onErrorResponse(VolleyError error) {
                              Toast.makeText(Friends_Details_Activity.this,"Internet Error",Toast.LENGTH_SHORT).show();
                              loading.dismiss();
                          }
                      })// String request sesh
                         {
                             @Override
                             protected Map<String,String> getParams() throws AuthFailureError{
                                 Map<String,String> perams = new HashMap<>();
                                 perams.put(Constant.KEY_ID,getID);
                                 perams.put(Constant.KEY_NAME,name);
                                 perams.put(Constant.KEY_CELL,cell);
                                 perams.put(Constant.KEY_EMAIL,email);
                                 perams.put(Constant.KEY_UNIVERSITY,university);
                                 perams.put(Constant.KEY_CURRENT_JOB,company);
                                 perams.put(Constant.KEY_POSITION,position);

                                 Log.d("GET_ID",getID);
                                 Log.d("Name",name);
                                 Log.d("CELL",cell);
                                 return perams;

                             }


                         };

              //Adding the string request to the queue
              RequestQueue requestQueue = Volley.newRequestQueue(Friends_Details_Activity.this);
              requestQueue.add(stringRequest);


          }//else sesh


          }//update sesh





      public void  FriendDeleteFromServer(){


      }




}
