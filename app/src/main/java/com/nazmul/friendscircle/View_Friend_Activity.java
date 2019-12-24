package com.nazmul.friendscircle;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
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

import java.util.ArrayList;
import java.util.HashMap;

public class View_Friend_Activity extends AppCompatActivity {
    EditText txtSearch;
    Button btnSearch;
    ListView FriendsList;
    ImageView imgNoData;
    private ProgressDialog loading;
    String getCell;

//
//    int MAX_SIZE = 999;
//    public String userID[] = new String[MAX_SIZE];
//    public String userName[] = new String[MAX_SIZE];
//    public String userCell[] = new String[MAX_SIZE];
//    public String userEmail[] = new String[MAX_SIZE];


    int MAX_SIZE = 999;
    public String userID[] = new String[MAX_SIZE];
    public String userName[] = new String[MAX_SIZE];
    public String userCell[] = new String[MAX_SIZE];
    public String userEmail[] = new String[MAX_SIZE];
    public String userUniversity[] = new String[MAX_SIZE];
    public String userCompany[] = new String[MAX_SIZE];
    public String userPosition[] = new String[MAX_SIZE];
    public String user_User_cell[] = new String[MAX_SIZE];


    //private Object SharedPreferences;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view__friend_);


        txtSearch = findViewById(R.id.txt_search);
        btnSearch = findViewById(R.id.btn_search);
        FriendsList = findViewById(R.id.friends_list);




        getSupportActionBar().setHomeButtonEnabled(true); //for back button
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);//for back button
        getSupportActionBar().setTitle("Friends");

        SharedPreferences sharedPreferences;
        sharedPreferences = getSharedPreferences(Constant.SHARED_PREF_NAME, Context.MODE_PRIVATE);
        getCell = sharedPreferences.getString(Constant.CELL_SHARED_PREF,"Not availabe");

        //
        Log.d("LOG_USER_CELL",getCell);


        //call function to get data
        getData("");


        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String search = txtSearch.getText().toString().trim();

                if (search.isEmpty()) {
                    Toast.makeText(View_Friend_Activity.this, "Please enter name!", Toast.LENGTH_SHORT).show();
                }
                else {
                    getData(search);
                }


            }
        });

    }



    private void getData(String text){
        // for showing progress dialog

        loading = new ProgressDialog(View_Friend_Activity.this);
        loading.setIcon(R.drawable.wait_icon);
        loading.setTitle("Loading.");
        loading.setMessage("Please wait..");
        loading.show();


        String URL = Constant.CONTACT_VIEW_URL+getCell+"&text="+text;
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
                        loading.dismiss();
                        Toast.makeText(View_Friend_Activity.this,"network error!", Toast.LENGTH_SHORT).show();

                    }
                });


        RequestQueue requestQueue = Volley.newRequestQueue(View_Friend_Activity.this);
        requestQueue.add(stringRequest);


    }



    private  void showJSON(String response){

        JSONObject jsonObject = null;

        ArrayList<HashMap<String, String>> list = new ArrayList<HashMap<String, String>>();
        try {
            jsonObject = new JSONObject(response);
            JSONArray result = jsonObject.getJSONArray(Constant.JSON_ARRAY);

//            jsonObject = new JSONObject(response);
//            JSONArray result = jsonObject.getJSONArray(Constant.JSON_ARRAY);

            if (result.length()==0){
                Toast.makeText(View_Friend_Activity.this,"No Data Available",Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(View_Friend_Activity.this,HomeActivity.class);
                startActivity(intent);


            }

            else {
                for (int i = 0; i< result.length();i++){
                    JSONObject object = result.getJSONObject(i);
                    String id = object.getString(Constant.KEY_ID);
                    String name = object.getString(Constant.KEY_NAME);
                    String cell = object.getString(Constant.KEY_CELL);
                    String email = object.getString(Constant.KEY_EMAIL);
                    String university = object.getString(Constant.KEY_UNIVERSITY);

                    String position = object.getString(Constant.KEY_POSITION);
                    String company = object.getString(Constant.KEY_CURRENT_JOB);
                    String user_cell = object.getString(Constant.KEY_USER_CELL);




                    //insert  data into array for put extra


                    userID[i]=id;
                    userName[i]=name;
                    userCell[i]=cell;
                    userEmail[i]=email;
                    userUniversity[i]=university;
                    userCompany[i]=company;
                    userPosition[i]=position;
                    user_User_cell[i]= user_cell;





                    //put value into Map
                    HashMap<String, String> user_data = new HashMap<>();
                    user_data.put(Constant.KEY_NAME,name);
                    user_data.put(Constant.KEY_POSITION,position);
                    user_data.put(Constant.KEY_CURRENT_JOB,company);

                    list.add(user_data);


                }
            }

            }//try end and exception for JSON
        catch (JSONException e){
            e.printStackTrace();
        }

        ListAdapter adapter = new SimpleAdapter(
                View_Friend_Activity.this,list,R.layout.friends_list_items,
                new String[]{Constant.KEY_NAME,Constant.KEY_POSITION,Constant.KEY_CURRENT_JOB},
                new int[]{R.id.txt_name, R.id.txt_position,R.id.txt_company});

        // friend list hocce fiend view er list view
        FriendsList.setAdapter(adapter);


        FriendsList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(View_Friend_Activity.this,Friends_Details_Activity.class);
                intent.putExtra("id",userID);
                intent.putExtra("name",userName);
                intent.putExtra("cell",userCell);
                intent.putExtra("email",userEmail);
                intent.putExtra("universtiy",userUniversity);
                intent.putExtra("company",userCompany);
                intent.putExtra("position",userPosition);
                intent.putExtra("user_user_cell",user_User_cell);


                startActivity(intent);

            }
        });


    }










}
