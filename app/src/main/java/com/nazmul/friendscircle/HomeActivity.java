package com.nazmul.friendscircle;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class HomeActivity extends AppCompatActivity {

    Button btnProfile,btnAddFriend,btnViewFriend,btnLogout;

    //for double back press to exit
//    private static final int TIME_DELAY = 2000;
//    private static long back_pressed;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);


        btnProfile = findViewById(R.id.btn_profile);
        btnAddFriend = findViewById(R.id.btn_add_friend);
        btnViewFriend = findViewById(R.id.btn_view_friends);
        btnLogout = findViewById(R.id.btn_logout);


        btnProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HomeActivity.this, Profile_Activity.class);
                startActivity(intent);
            }
        });// gto to profile details


        btnAddFriend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HomeActivity.this, AddFriendActivity.class);
                startActivity(intent);
            }
        });


        btnViewFriend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HomeActivity.this, View_Friend_Activity.class);
                startActivity(intent);
            }
        });


        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HomeActivity.this, LoginActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);


            }
        });


        //double backpress to exit
//        @Override
//        public void onBackPressed () {
//            if (back_pressed + TIME_DELAY > System.currentTimeMillis()) {
//
//                Intent intent = new Intent(HomeActivity.this, LoginActivity.class);
//                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
//                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//                startActivity(intent);
//
//            } else {
//                Toast.makeText(this, "Press once again to exit!",
//                        Toast.LENGTH_SHORT).show();
//            }
//            back_pressed = System.currentTimeMillis();
//        }
    }

    }
