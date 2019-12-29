package com.nazmul.friendscircle;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Profile_Activity extends AppCompatActivity {

    EditText txtName,txtCell,txtGender,txtDivisoin,txtPassword;
    Button btnUpdate;
    String UserCell;
    SharedPreferences sharedPreferences;

    private ProgressDialog loading;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_);


        txtName = findViewById(R.id.txt_name);
        txtCell = findViewById(R.id.txt_cell);
        txtGender = findViewById(R.id.txt_gender);
        txtDivisoin = findViewById(R.id.txt_division);
        txtPassword = findViewById(R.id.txt_password);

        btnUpdate = findViewById(R.id.btn_update);

        sharedPreferences = getSharedPreferences(Constant.SHARED_PREF_NAME, Context.MODE_PRIVATE);
        String cell = sharedPreferences.getString(Constant.CELL_SHARED_PREF,"not available");
        UserCell = cell;


        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Profile");

        //call function

        getData();



    }

    private void getData(){
        //loading progress dialog
        loading = new ProgressDialog(Profile_Activity.this);
        loading.setMessage("Please wait...");
        loading.show();


        String URL = Constant.PROFILE_VIEW_URL+UserCell;

        Log.d("URL",URL);

        StringRequest stringRequest = new StringRequest(URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                loading.dismiss();
                showJSON(response);
            }
        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.d("Volley error ","Error"+error);
                        Toast.makeText(Profile_Activity.this,"No internet connection",Toast.LENGTH_SHORT).show();
                        loading.dismiss();
                    }
                });

        RequestQueue requestQueue = Volley.newRequestQueue(Profile_Activity.this);
        requestQueue.add(stringRequest);

    }// get data function sesh


    private void showJSON(String response){
        Log.d("RESPONSE",response);

//        txtName = findViewById(R.id.txt_name);
//        txtCell = findViewById(R.id.txt_cell);
//        txtGender = findViewById(R.id.txt_gender);
//        txtDivisoin = findViewById(R.id.txt_division);
//        txtPassword = findViewById(R.id.txt_password);
        String id = "";
        String name = "";
        String cell = "";
        String gender = "";
        String division = "";
        String password = "";

        try {
            JSONObject jsonObject = new JSONObject(response);
            JSONArray result = jsonObject.getJSONArray(Constant.JSON_ARRAY);
            JSONObject ProfileData = result.getJSONObject(0);
            //id = ProfileData.getString(Constant.KEY_ID);
            name = ProfileData.getString(Constant.KEY_NAME);
            cell = ProfileData.getString(Constant.KEY_CELL);
            gender = ProfileData.getString(Constant.KEY_GENDER);
            division = ProfileData.getString(Constant.KEY_DIVISION);
            password = ProfileData.getString(Constant.KEY_PASSWORD);





        }catch (JSONException e){
            e.printStackTrace();
        }


        txtName.setText(name);
        txtCell.setText(cell);
        txtGender.setText(gender);
        txtDivisoin.setText(division);
        txtPassword.setText(password);


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
