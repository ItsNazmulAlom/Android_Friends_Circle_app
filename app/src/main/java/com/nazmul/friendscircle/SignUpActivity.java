package com.nazmul.friendscircle;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
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

public class SignUpActivity extends AppCompatActivity {

    EditText txtName,txtCell,txtGender,txtDivision,txtPassword;
    Button btnSignup;

    ProgressDialog loading;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);



        txtName = findViewById(R.id.txt_name);
        txtCell = findViewById(R.id.txt_cell);
        txtGender = findViewById(R.id.txt_gender);
        txtDivision = findViewById(R.id.txt_division);
        txtPassword = findViewById(R.id.txt_password);

        btnSignup = findViewById(R.id.btn_update);




        //For choosing account type and open alert dialog
        txtGender.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final String[] genderList = {"Male", "Female"};

                AlertDialog.Builder builder = new AlertDialog.Builder(SignUpActivity.this);
                builder.setTitle("SELECT GENDER");
                //builder.setIcon(R.drawable.ic_gender);


                builder.setCancelable(false);
                builder.setItems(genderList, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int position) {
                        switch (position) {
                            case 0:
                                txtGender.setText(genderList[position]);
                                break;

                            case 1:
                                txtGender.setText(genderList[position]);
                                break;


                        }
                    }
                });
                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int position) {
                        dialog.dismiss();
                    }
                });


                AlertDialog accountTypeDialog = builder.create();

                accountTypeDialog.show();
            }

        });/// gender function sesh


        //start division function



        //For choosing division and open alert dialog
        txtDivision.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final String[] cityList={"Dhaka","Chittagong","Sylhet","Rajshahi","Barishal","Khulna","Rangpur","Mymensingh"};

                AlertDialog.Builder builder = new AlertDialog.Builder(SignUpActivity.this);
                builder.setTitle("SELECT DIVISION");
                //builder.setIcon(R.drawable.ic_location);


                builder.setCancelable(false);
                builder.setItems(cityList, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int position) {
                        switch (position) {
                            case 0:

                                txtDivision.setText("Dhaka");
                                break;

                            case 1:

                                txtDivision.setText("Chittagong");
                                break;

                            case 2:

                                txtDivision.setText("Sylhet");
                                break;

                            case 3:

                                txtDivision.setText("Rajshahi");
                                break;

                            case 4:

                                txtDivision.setText("Barishal");
                                break;

                            case 5:

                                txtDivision.setText("Khulna");
                                break;

                            case 6:

                                txtDivision.setText("Rangpur");
                                break;

                            case 7:

                                txtDivision.setText("Mymensingh");
                                break;
                        }
                    }
                });
                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int position) {
                        dialog.dismiss();
                    }
                });


                AlertDialog locationTypeDialog = builder.create();

                locationTypeDialog.show();
            }

        });
        /// division selected








        btnSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sign_up();//call the signup function down below
            }
        });


    }


    private void sign_up(){


        final String name = txtName.getText().toString().trim();
        final  String cell = txtCell.getText().toString().trim();
        final  String gender = txtGender.getText().toString().trim();
        final String division = txtDivision.getText().toString().trim();
        final String password = txtPassword.getText().toString().trim();

        // check field validation


        if (name.isEmpty()){
            txtName.setError("Please enter name!");
            txtName.requestFocus();

        }
        else if (cell.length()!=11 || cell.charAt(0)!='0' || cell.charAt(1)!='1' || cell.contains(" ")){
            txtCell.setError("Invalid number!");
            txtCell.requestFocus();
        }
        else if (gender.isEmpty()){
            txtGender.setError("Please enter gender!");
            txtGender.requestFocus();
        }
        else if (division.isEmpty()){
            txtDivision.setError("please enter division!");
            txtDivision.requestFocus();
        }
        else if (password.isEmpty()){
            txtPassword.setError("plaease enter password!");
            txtPassword.requestFocus();
        }
        else {
//            loading = new ProgressDialog(this);
//            loading.setIcon(R.drawable.wait_icon);
//            loading.setTitle("Sign up");
//            loading.setMessage("please wait ...");
//            loading.show();

            loading = new ProgressDialog(this);
            loading.setIcon(R.drawable.wait_icon);
            loading.setTitle("Sign up");
            loading.setMessage("Please wait....");
            loading.show();

            // server a request pathabo

            final StringRequest stringRequest = new StringRequest(Request.Method.POST, Constant.SIGNUP_URL, new Response.Listener<String>() {
                @Override
                public void onResponse(String response){
//                    Log.d("Response", response);
//
//
////
////                    if (response.equals("success")) {
////                        loading.dismiss();
////                        Intent intent = new Intent(SignUpActivity.this,LoginActivity.class);
////                        Toast.makeText(SignUpActivity.this, "Signup Successfull", Toast.LENGTH_SHORT).show();
////                        startActivity(intent);
////                 }
//
//
//                    if(response.equals("success")) {
//                       // loading.dismiss();
//                        Intent intent = new Intent(SignUpActivity.this, LoginActivity.class);
//                        Toast.makeText(SignUpActivity.this, "Sign up successful", Toast.LENGTH_SHORT).show();
//                        startActivity(intent);
//
//                    } else if (response.equals("exists")) {
//                        Toast.makeText(SignUpActivity.this, "user already exists!", Toast.LENGTH_SHORT).show();
//                       // loading.dismiss();
//
//                    } else if (response.equals("failure")) {
//                        Toast.makeText(SignUpActivity.this, "Registration failed!", Toast.LENGTH_SHORT).show();
//                       // loading.dismiss();
//                    }



                    Log.d("RESPONSE", response);

                    if (response.equals("success")) {
                        loading.dismiss();
                        Intent intent = new Intent(SignUpActivity.this, LoginActivity.class);
                        Toast.makeText(SignUpActivity.this, "Sign up successful", Toast.LENGTH_SHORT).show();
                        startActivity(intent);
                    }
                    else if (response.equals("exists")) {

                        Toast.makeText(SignUpActivity.this, "User already exists!", Toast.LENGTH_SHORT).show();
                        loading.dismiss();

                    }

                    else if (response.equals("failure")) {

                        Toast.makeText(SignUpActivity.this, "Registration Failed!", Toast.LENGTH_SHORT).show();
                        loading.dismiss();

                    }


                }
            },
                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            Toast.makeText(SignUpActivity.this,"no internet connection!",Toast.LENGTH_LONG).show();
                          //  loading.dismiss();
                        }
                    }
            ) {
                //value pathabo

                @Override
                protected Map<String,String>getParams()throws AuthFailureError{
                    Map<String,String> params = new HashMap<>();

                    params.put(Constant.KEY_NAME,name);
                    params.put(Constant.KEY_CELL,cell);
                    params.put(Constant.KEY_GENDER,gender);
                    params.put(Constant.KEY_DIVISION,division);
                    params.put(Constant.KEY_PASSWORD,password);

                    Log.d("signup info",""+name+" "+cell+" "+gender+" "+division+" "+password);
                    return params;
                }

            };
            RequestQueue requestQueue = Volley.newRequestQueue(this);
            requestQueue.add(stringRequest);
        }

    }
// this mathod for request focus on field
    private void requestFocus(View view){
        if (view.requestFocus()){
            getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);

        }
    }


}
