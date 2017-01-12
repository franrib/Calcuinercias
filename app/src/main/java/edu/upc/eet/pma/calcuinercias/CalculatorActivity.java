package edu.upc.eet.pma.calcuinercias;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

public class CalculatorActivity extends AppCompatActivity {
    //Selector D'unitats
    Spinner unitats_resultat;
    String[] unires = {"Kgm2","Kgcm2","ozin2","Lbin2","lbft2"};
    Spinner unitats_densitat;
    String[] unidens = {"Kg/m3","Kg/cm3","oz/in3","lb/in3","lb/ft3"};
    Spinner unitats_massa;
    String[] unimassa = {"Kg","g","oz","lb"};
    Spinner unitats_referencia1;
    Spinner unitats_referencia2;
    Spinner unitats_referencia3;
    String[] unireff={"mm","m","in","ft"};



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculator_relative);

        TextView titol_pagina = (TextView) findViewById(R.id.titol_pagina);

        //Selector d'unitats
        unitats_resultat = (Spinner)findViewById(R.id.spinner_resultat);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,unires);
        unitats_resultat.setAdapter(adapter);

        unitats_densitat =(Spinner)findViewById(R.id.spinner_densitat);
        ArrayAdapter<String> adapter1 =new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,unidens);
        unitats_densitat.setAdapter(adapter1);

        unitats_massa=(Spinner)findViewById(R.id.spinner_massa);
        ArrayAdapter<String> adapter2=new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,unimassa);
        unitats_massa.setAdapter(adapter2);

        unitats_referencia1=(Spinner)findViewById(R.id.spinner_ref1);
        ArrayAdapter<String> adapter3=new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,unireff);
        unitats_referencia1.setAdapter(adapter3);
        unitats_referencia2=(Spinner)findViewById(R.id.spinner_ref2);
        unitats_referencia2.setAdapter(adapter3);
        unitats_referencia3=(Spinner)findViewById(R.id.spinner_ref3);
        unitats_referencia3.setAdapter(adapter3);


        Intent intent = getIntent();
        if (intent != null) {
            String titol_pag = intent.getStringExtra("tit_pag");
            if (titol_pag != null) {
                titol_pagina.setText(titol_pag);
            }
        }


    }
}

