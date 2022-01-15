package com.medisystem.app;

import android.content.Intent;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import java.util.Objects;

public class RemoveProduct extends AppCompatActivity {

    ImageButton rmHomeBtn;
    Button rmBtn;

    TextInputLayout rmID_F;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_remove_product);

        rmBtn = findViewById(R.id.deleteBtn);
        rmHomeBtn = findViewById(R.id.rmBackBtn);



        //TODO BACK BUTTON
        rmHomeBtn.setOnClickListener(v ->{
            Intent i = new Intent(this, Dashboard.class);
            startActivity(i);

        });

        //TODO EXTRACT TEXT FROM FIELD
        rmBtn.setOnClickListener(v ->{
            String product_id = rmID_F.getEditText().getText().toString().trim();

        });

    }
}