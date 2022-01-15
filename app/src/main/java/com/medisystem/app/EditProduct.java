package com.medisystem.app;

import android.content.Intent;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageButton;

public class EditProduct extends AppCompatActivity {

    ImageButton edBackBtn;
    Button edSubmitBtn;

    TextInputLayout edID_F;
    TextInputEditText edID_EDT;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_product);

        edBackBtn = findViewById(R.id.edBackBtn);
        edSubmitBtn = findViewById(R.id.edSubmitBtn);

        edID_F = findViewById(R.id.edID_F);
        edID_EDT = findViewById(R.id.edID_edt);

        edBackBtn.setOnClickListener(v ->{
            Intent i = new Intent(this, Dashboard.class);
            startActivity(i);

        });


    }
}