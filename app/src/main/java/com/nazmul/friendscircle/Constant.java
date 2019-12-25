package com.nazmul.friendscircle;

public class Constant {




// main url

    public  static  final String MAIN_URL= "https://android.nkrit.com";



/// url for signup
    public static final String SIGNUP_URL = MAIN_URL+"/friendCircle/signup.php";

   // key value for signup

    public static final String KEY_NAME = "name";
    public static final String KEY_CELL = "cell";
    public static final String KEY_GENDER = "gender";
    public static final String KEY_DIVISION = "division";
    public static final String KEY_PASSWORD = "password";


// url for add friend

    public static final String FRIEND_ADD_URL = MAIN_URL+"/friendCircle/save_friend.php";


    public static final String FRIEND_UPDATE = MAIN_URL+"/friendCircle/update_friend.php";

//    txtName = findViewById(R.id.txt_name);
//    txtCell = findViewById(R.id.txt_cell);
//    txtEmail = findViewById(R.id.txt_email);
//    txtUniversity= findViewById(R.id.txt_university);
//    txtCurrentJob = findViewById(R.id.txt_current_job);
//    txtPosition = findViewById(R.id.txt_position);
    public static final String KEY_EMAIL = "email";
    public static final String KEY_UNIVERSITY = "university";
    public static final String KEY_CURRENT_JOB = "current_job";
    public static final String KEY_POSITION = "position";
    public static final String KEY_USER_CELL = "user_cell";

    //url for sign in


    public static  final String SIGNIN_URL = MAIN_URL+"/friendCircle/login.php";


    //url for CONTACT_VIEW_URL
    //url for contacts view

    public static final String CONTACT_VIEW_URL= MAIN_URL+"/friendCircle/view_friend.php?cell=";

    public static final String KEY_ID = "id";
    public static final String JSON_ARRAY = "result";


    // Share preference

    // we will use this to store he cell number into shared preference

    public  static final String SHARED_PREF_NAME = "com.nazmul.friendscircle.user_login";

    // this would to be use to store the cell of current logged in user
    public  static final String CELL_SHARED_PREF = "cell";

















}
