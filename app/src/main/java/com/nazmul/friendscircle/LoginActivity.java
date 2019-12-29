package com.nazmul.friendscircle;

import android.app.ProgressDialog;
import android.content.Context;
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

public class LoginActivity extends AppCompatActivity {

    EditText txtCell,txtPassword;
    Button btnSignup,btnSinin;

    private ProgressDialog loading;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        txtCell = findViewById(R.id.txt_cell);
        txtPassword = findViewById(R.id.txt_password);
        btnSinin = findViewById(R.id.btn_signin);
        btnSignup = findViewById(R.id.btn_update);


        btnSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this,SignUpActivity.class);
                startActivity(intent);
            }
        });


        btnSinin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Sign_in();
            }
        });
    }

    private void Sign_in(){

        final  String cell = txtCell.getText().toString().trim();
        final  String password = txtPassword.getText().toString().trim();

// check cell field validatoin
        if (cell.isEmpty()){
            txtCell.setError("Please enter Cell number");
            txtCell.requestFocus();
        }
        // check password field validation
        else if (password.isEmpty()){
            txtPassword.setError("please enter password!");
            txtPassword.requestFocus();
        }

        else {
            loading = new ProgressDialog(this);
            loading.setIcon(R.drawable.wait_icon);
            loading.setTitle("login");
            loading.setMessage("please wait..");
            loading.show();

            final StringRequest stringRequest = new StringRequest(Request.Method.POST, Constant.SIGNIN_URL, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    Log.d("RESPONSE", response);


                    // if get success message from server than
                    if (response.equals("success")) {
                        //create sharePreferance

                        SharedPreferences sharedPreferences = LoginActivity.this.getSharedPreferences(Constant.SHARED_PREF_NAME, Context.MODE_PRIVATE);
                        //create editor to store the value to shared preference
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.putString(Constant.CELL_SHARED_PREF, cell);

                        // save value to shared preference
                        editor.apply();
                        loading.dismiss();

                        // starting home activity

                        Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
                        startActivity(intent);
                        Toast.makeText(LoginActivity.this,"Login successfull",Toast.LENGTH_SHORT).show();
                        Log.d("CellPHOne",cell);


                    } else if (response.equals("failure")) {
                        //if the server response not success

                        Toast.makeText(LoginActivity.this, "Invalid login ", Toast.LENGTH_SHORT).show();
                        loading.dismiss();

                    } else {
                        Toast.makeText(LoginActivity.this, "Invalid user cell or Password", Toast.LENGTH_SHORT).show();
                        loading.dismiss();
                    }


                }
            },

                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            Toast.makeText(LoginActivity.this,"There is an Error!!",Toast.LENGTH_SHORT).show();
                            loading.dismiss();
                        }
                    })


            {

                @Override
                protected Map<String,String>getParams()throws AuthFailureError{
                    Map<String, String> params = new HashMap<>();
                    params.put(Constant.KEY_CELL,cell);
                    params.put(Constant.KEY_PASSWORD,password);
                    return params;
                }


            };

            RequestQueue requestQueue = Volley.newRequestQueue(this);
            requestQueue.add(stringRequest);



        }

    }




    //for request focus
    private void requestFocus(View view) {
        if (view.requestFocus()) {
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
