package com.medisystem.app;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.ImageButton;

public class Dashboard extends AppCompatActivity {

    SharedPreferences sharedPreferences;
    public static final String MY_PREFERENCES = "MyPrefs";
    public static final String USERNAME = "username";

    Button addBtn, removeBtn, editBtn, aboutBtn;
    ImageButton logout;
    TextInputLayout searchF;
    TextInputEditText searchEdt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        /**---------------Button & Edit Text--------------------**/
        addBtn = findViewById(R.id.btnAdd);
        removeBtn = findViewById(R.id.btnRemove);
        editBtn = findViewById(R.id.btnEdit);
        aboutBtn = findViewById(R.id.btnAbout);

        searchF = findViewById(R.id.searchField);
        searchEdt = findViewById(R.id.searchEdit);

        logout = findViewById(R.id.btnLogout);
        /**---------------Button & Edit Text--------------------**/

        searchF.setOnClickListener(v ->{
            try{
                View view = this.getCurrentFocus();
                InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
                view.clearFocus();
            }catch (Exception e){
                //Do Nothing
            }
        });

        addBtn.setOnClickListener(v ->{
            Intent i = new Intent(this, AddProduct.class);
            startActivity(i);
        });

        removeBtn.setOnClickListener(v ->{
            Intent i = new Intent(this, RemoveProduct.class);
            startActivity(i);
        });
        editBtn.setOnClickListener(v ->{
            Intent i = new Intent(this, EditProduct.class);
            startActivity(i);
        });
        aboutBtn.setOnClickListener(v ->{
            Intent i = new Intent(this, AboutUs.class);
            startActivity(i);
        });

        logout.setOnClickListener(v ->{
            //TODO SIGN OUT FUNCTION

            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.clear();
            editor.apply();
            finish();
            Intent intent = new Intent(Dashboard.this, MainActivity.class);
            startActivity(intent);

        });

    }

}