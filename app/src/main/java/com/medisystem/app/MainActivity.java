package com.medisystem.app;
//
//import android.app.ProgressDialog;
//import android.content.Context;
//import android.content.Intent;
//import android.content.SharedPreferences;
//import android.support.design.widget.TextInputLayout;
//import android.support.v7.app.AppCompatActivity;
//import android.os.Bundle;
//import android.text.TextUtils;
//import android.view.View;
//import android.view.inputmethod.InputMethodManager;
//import android.widget.Button;
//import android.widget.EditText;
//import android.widget.Toast;
//
//import java.util.Objects;
//import java.util.concurrent.ExecutionException;
//
//import retrofit2.http.Field;
//import retrofit2.http.FormUrlEncoded;
//import retrofit2.http.POST;
//
//public class MainActivity extends AppCompatActivity {
//
//    public static final String URL_LOGIN="http://a2e5-152-32-107-72.ngrok.io/login.php";
//    SharedPreferences sharedPreferences;
//    public static final String MY_PREFERENCES = "MyPrefs";
//    private boolean status;
//
//    private TextInputLayout UID_FIELD, PASS_FIELD;
//    private EditText UID_EDIT, PASS_EDIT;
//    private Button LOGIN_BUTTON;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
//
//
//        UID_FIELD = findViewById(R.id.idField);
//        UID_EDIT = findViewById(R.id.idEdit);
//
//        PASS_FIELD = findViewById(R.id.passField);
//        PASS_EDIT = findViewById(R.id.passEdit);
//
//        LOGIN_BUTTON = findViewById(R.id.btnLogin);
//
//        //TODO BUTTON LOGIN
//    }
//
//    public void loginLast (View v) {
//        try{
//            String UID_VALUE = UID_FIELD.getEditText().getText().toString().trim();
//            String PASS_VALUE = PASS_FIELD.getEditText().getText().toString().trim();
//
//            InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
//            imm.hideSoftInputFromWindow(UID_EDIT.getWindowToken(), 0);
//
//            if (TextUtils.isEmpty(UID_VALUE)) {
//                UID_FIELD.setError("Username is Required!");
//                return;
//            }
//
//            if (TextUtils.isEmpty(PASS_VALUE)) {
//                PASS_FIELD.setError("Password is Required!");
//                return;
//            }
//
//            if (PASS_VALUE.length() < 5) {
//                PASS_FIELD.setError("Password must be 6 Character Long");
//                return;
//            }
//
//            background bg = new background(this);
//            bg.execute(UID_VALUE,PASS_VALUE);
//
//            String message = bg.getStatus().toString();
//
//            Toast.makeText(this, message, Toast.LENGTH_LONG).show();
//
//        }catch (Exception e){
//            Toast.makeText(this, "Error on Button Click..!", Toast.LENGTH_LONG).show();
//        }
//    }
//}

//TODO OTHERS

        import android.app.ProgressDialog;
        import android.content.Context;
        import android.content.Intent;
        import android.content.SharedPreferences;
        import android.os.AsyncTask;
        import android.support.design.widget.TextInputEditText;
        import android.support.design.widget.TextInputLayout;
        import android.support.v7.app.AppCompatActivity;
        import android.os.Bundle;
        import android.view.View;
        import android.widget.Button;
        import android.widget.EditText;
        import android.widget.Toast;

        import org.json.JSONException;
        import org.json.JSONObject;

        import java.util.HashMap;

public class MainActivity extends AppCompatActivity {

    public static final String URL_LOGIN = "http://a2e5-152-32-107-72.ngrok.io/login.php";
    TextInputEditText ed_email, ed_password;
    SharedPreferences sharedPreferences;
    Button LOGIN_BUTTON;
    public static final String MY_PREFERENCES = "MyPrefs";
    public static final String STATUS = "status";
    public static final String USERNAME = "USERNAME";
    //public static final String PASSWORD = "password";
    private boolean status;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ed_email = findViewById(R.id.idEdit);
        ed_password = findViewById(R.id.passEdit);
        LOGIN_BUTTON = findViewById(R.id.btnLogin);

        sharedPreferences = getSharedPreferences(MY_PREFERENCES, Context.MODE_PRIVATE);

        status = sharedPreferences.getBoolean(STATUS, false);

        if (status){
            finish();
            Intent intent = new Intent(MainActivity.this, Dashboard.class);
            startActivity(intent);
        }
    }


    //TODO on login button click

    public void loginLast(View view){
         final String username = ed_email.getText().toString().trim();
         final String password = ed_password.getText().toString().trim();

        if(username.isEmpty()|| password.isEmpty()){
            Toast.makeText(this, "Please fill all the fields", Toast.LENGTH_SHORT).show();
        }

        else {
            class Login extends AsyncTask<Void, Void, String> {
                ProgressDialog pdLoading = new ProgressDialog(MainActivity.this);

                @Override
                protected void onPreExecute() {
                    super.onPreExecute();

                    //this method will be running on UI thread
                    pdLoading.setMessage("\tLoading...");
                    pdLoading.setCancelable(false);
                    pdLoading.show();
                }

                @Override
                protected String doInBackground(Void... voids) {
                    //creating request handler object
                    RequestHandler requestHandler = new RequestHandler();

                    //creating request parameters
                    HashMap<String, String> params = new HashMap<>();
                    params.put("username", username);
                    params.put("password", password);

                    //returing the response
                    return requestHandler.sendPostRequest(URL_LOGIN, params);
                }

                @Override
                protected void onPostExecute(String s) {
                    super.onPostExecute(s);
                    pdLoading.dismiss();

                    try {
                        //converting response to json object
                        JSONObject obj = new JSONObject(s);
                        //if no error in response
                        if (!obj.getBoolean("error")) {
                            String username = obj.getString("username");

                            SharedPreferences.Editor editor = sharedPreferences.edit();
                            editor.putString(USERNAME, username);
                            editor.putBoolean(STATUS, true);
                            editor.apply();

                            finish();
                            Toast.makeText(getApplicationContext(), obj.getString("message"), Toast.LENGTH_LONG).show();
                            Intent intent = new Intent(MainActivity.this, Dashboard.class);
                            startActivity(intent);
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                        Toast.makeText(MainActivity.this, "Exception: " + e, Toast.LENGTH_LONG).show();
                    }
                }
            }

            Login login = new Login();
            login.execute();
        }
    }
        //TODO +OTHER FUNCTION FOR BUTTON
}