package edu.upc.eet.pma.calcuinercias;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
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


        Button button = (Button)findViewById(R.id.btn_solve);
        final EditText valor_densitat =(EditText)findViewById(R.id.valor_densitat);
        final EditText valor_massa=(EditText)findViewById(R.id.valor_massa);
        final EditText valor_ref1=(EditText)findViewById(R.id.valor_ref1);
        final EditText valor_ref2=(EditText)findViewById(R.id.valor_ref2);
        final EditText valor_ref3=(EditText)findViewById(R.id.valor_ref3);
        final EditText resultat =(EditText)findViewById(R.id.resultat);


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //String sdensitat = valor_densitat.getText().toString();
                String smassa = valor_massa.getText().toString();
                //String sref1 = valor_ref1.getText().toString();
                String sref2 = valor_ref2.getText().toString();
                String sref3 = valor_ref3.getText().toString();
                //float densitat= Float.parseFloat(sdensitat);
                float massa= Float.parseFloat(smassa);
                //float ref1= Float.parseFloat(sref1);
                float ref2= Float.parseFloat(sref2);
                float ref3= Float.parseFloat(sref3);
                //densitat
                //float massa = ref2*ref3*ref1*densitat
                float inercia=(massa*((ref2*ref2)+(ref3*ref3)))/12000000;


                String sinercia=String.format("%f",inercia);
                resultat.setText(sinercia);

            }
        });


        //ALTRES FORMULES


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

