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

        Intent intent = getIntent();
        if (intent != null) {
            String titol_pag = intent.getStringExtra("tit_pag");
            if (titol_pag != null) {
                titol_pagina.setText(titol_pag);
            }
        }
    }
}

