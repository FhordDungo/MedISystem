package com.medisystem.app;

import static android.widget.Toast.LENGTH_LONG;

import android.content.Intent;
import android.net.Uri;
import android.os.Handler;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.HashMap;
import java.util.Map;

public class AddProduct extends AppCompatActivity {

    ImageButton homeBtn;
    ImageView imageView;
    Button picBtn, galleryBtn, saveBtn;

    TextInputLayout ID_LAY, PRODUCT_LAY, GENERIC_LAY, MANUF_LAY, DOE_LAY, DOMS_LAY, STOCK_LAY;
    TextInputEditText ID_EDT, PNAME_EDT, GNAME_EDT, MNAME_EDT, DOE_EDT, DOM_EDT, STOCK_EDT;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_product);

        /**--------- Button -----------**/

        picBtn = findViewById(R.id.takeBtn);
        galleryBtn = findViewById(R.id.openBtn);
        saveBtn = findViewById(R.id.saveDataBtn);
        homeBtn = findViewById(R.id.backaddBtn);

        /**--------- Button END -----------**/

        /**--------- Edit Text -----------**/
        ID_LAY = findViewById(R.id.idlay);
        ID_EDT = findViewById(R.id.ided);
        PRODUCT_LAY = findViewById(R.id.namelay);
        PNAME_EDT = findViewById(R.id.nameed);
        GENERIC_LAY = findViewById(R.id.genericlay);
        GNAME_EDT = findViewById(R.id.genericed);
        MANUF_LAY = findViewById(R.id.manlay);
        MNAME_EDT = findViewById(R.id.maned);
        DOE_LAY = findViewById(R.id.explay);
        DOE_EDT = findViewById(R.id.exped);
        DOMS_LAY = findViewById(R.id.makelays);
        DOM_EDT = findViewById(R.id.makeeds);
        STOCK_LAY = findViewById(R.id.stocklay);
        STOCK_EDT = findViewById(R.id.stocked);

        /**--------- Edit Text END -----------**/

        saveBtn.setOnClickListener(v ->{

            try {
                //TODO GET TEXT FROM FIELD THEN SAVE TO VARIABLES
                String PRODUCT_ID = ID_LAY.getEditText().getText().toString().trim();
                String PRODUCT_NAME = PRODUCT_LAY.getEditText().getText().toString().trim();
                String PRODUCT_GEN = GENERIC_LAY.getEditText().getText().toString().trim();
                String MANUFAC = MANUF_LAY.getEditText().getText().toString().trim();
                String DOExp = DOE_LAY.getEditText().getText().toString().trim();
                String DOMan = DOE_LAY.getEditText().getText().toString().trim();
                String STOCK = STOCK_LAY.getEditText().getText().toString().trim();

                uploader upload = new uploader(this);
                upload.execute(PRODUCT_ID, PRODUCT_NAME, PRODUCT_GEN, MANUFAC, DOExp, DOMan,STOCK);

            }catch(Exception e){
                    Toast.makeText(this, "Error Check Field's", Toast.LENGTH_LONG).show();
                }
        });

    }
}