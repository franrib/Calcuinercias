package edu.upc.eet.pma.calcuinercias;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class CalculatorActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculator_relative);

        TextView titol_pagina = (TextView) findViewById(R.id.titol_pagina);

        //String title = intent.getStringExtra("tit_pag");

        Intent intent=getIntent();
        Bundle title =intent.getExtras();

        if (title != null) {
            String titol_pag = (String) title.get("tit_pag");
            titol_pagina.setText(titol_pag);
        }



    }

    }

