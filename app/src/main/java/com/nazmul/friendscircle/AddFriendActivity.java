package com.nazmul.friendscircle;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

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

public class AddFriendActivity extends AppCompatActivity {

    EditText txtName,txtCell,txtEmail,txtUniversity,txtCurrentJob,txtPosition;

    Button btnAdd;

    private ProgressDialog loading;

    String getUserCell;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_friend);


        txtName = findViewById(R.id.txt_name);
        txtCell = findViewById(R.id.txt_cell);
        txtEmail = findViewById(R.id.txt_email);
        txtUniversity= findViewById(R.id.txt_university);
        txtCurrentJob = findViewById(R.id.txt_current_job);
        txtPosition = findViewById(R.id.txt_position);

        btnAdd = findViewById(R.id.btn_edit);


        getSupportActionBar().setHomeButtonEnabled(true); //for back button
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);//for back button
        getSupportActionBar().setTitle("Add Friend");//for actionbar title

        //Fetching cell from shared preferences
        SharedPreferences sharedPreferences;
        sharedPreferences =getSharedPreferences(Constant.SHARED_PREF_NAME, Context.MODE_PRIVATE);
        getUserCell = sharedPreferences.getString(Constant.CELL_SHARED_PREF, "Not Available");


        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(AddFriendActivity.this);
                builder.setIcon(R.mipmap.ic_launcher)
                        .setMessage("want to save friend?")
                        .setCancelable(false)
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                SaveFriend();
                                dialogInterface.cancel();


                            }
                        })

                        .setNegativeButton("No", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                dialogInterface.cancel();
                            }
                        }).show();

            }
        });

    }

    private void SaveFriend(){

        final String user_cell = getUserCell;

        final String name = txtName.getText().toString().trim();
        final String cell = txtCell.getText().toString().trim();
        final String email = txtEmail.getText().toString().trim();
        final String university = txtUniversity.getText().toString().trim();
        final String currentjob = txtCurrentJob.getText().toString().trim();
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
        else if (currentjob.isEmpty()){
            txtCurrentJob.setError("Enter Current job");
            txtCurrentJob.requestFocus();
        }
        else if (position.isEmpty()){
            txtPosition.setError("enter job position!");
            txtPosition.requestFocus();
        }
        else {

            loading = new ProgressDialog(this);
            loading.setIcon(R.drawable.wait_icon);
            loading.setTitle("Adding");
            loading.setMessage("Please wait....");
            loading.show();


            final StringRequest stringRequest = new StringRequest(Request.Method.POST, Constant.FRIEND_ADD_URL, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    //for track response
                    Log.d("RESPONSE", response);

                    if (response.equals("success")) {
                        loading.dismiss();
                        Intent intent = new Intent(AddFriendActivity.this, HomeActivity.class);
                        Toast.makeText(AddFriendActivity.this, "Successfully Saved!", Toast.LENGTH_SHORT).show();
                        startActivity(intent);

                    }


                    else if (response.equals("failure")) {
                        loading.dismiss();
                        Toast.makeText(AddFriendActivity.this, " Save fail!", Toast.LENGTH_SHORT).show();
                    }



                    else {
                        loading.dismiss();
                        Toast.makeText(AddFriendActivity.this, "Network Error", Toast.LENGTH_SHORT).show();
                    }

                }
            },
                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            Toast.makeText(AddFriendActivity.this, "No Internet Connection or \nThere is an error !!!", Toast.LENGTH_LONG).show();
                            loading.dismiss();
                        }
                    }) {


                @Override// Map use for assign value to key
                protected Map<String,String> getParams()throws AuthFailureError{
                    Map<String,String> params = new HashMap<>();

                    params.put(Constant.KEY_USER_CELL, user_cell);
                    params.put(Constant.KEY_NAME, name);
                    params.put(Constant.KEY_CELL, cell);
                    params.put(Constant.KEY_EMAIL, email);
                    params.put(Constant.KEY_UNIVERSITY,university);
                    params.put(Constant.KEY_CURRENT_JOB,currentjob);
                    params.put(Constant.KEY_POSITION,position);


                    Log.d("KEYUserCEll",user_cell);
                    Log.d("NAME",name);


                    return params;
                        }
                       };

            //Adding the string request to the queue
            RequestQueue requestQueue = Volley.newRequestQueue(AddFriendActivity.this);
            requestQueue.add(stringRequest);


        }

    }






    // this mathod for request focus on field
    private void requestFocus(View view){
        if (view.requestFocus()){
            getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);

        }
    }


    //for back button
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                // app icon in action bar clicked; goto parent activity.
                this.finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }


}
