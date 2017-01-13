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
    private int calcul = -1;
    private EditText edit_massa;
    private EditText edit_ref2;
    private EditText edit_ref3;
    private EditText resultat;
    private EditText edit_ref1;
    private EditText edit_densitat;


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
            calcul = intent.getIntExtra("calcul", -1);
        } else {

        }

        Button button = (Button)findViewById(R.id.btn_solve);
        edit_densitat =(EditText)findViewById(R.id.valor_densitat);
        edit_massa = (EditText)findViewById(R.id.valor_massa);
        edit_ref1 =(EditText)findViewById(R.id.valor_ref1);
        edit_ref2 = (EditText)findViewById(R.id.valor_ref2);
        edit_ref3 = (EditText)findViewById(R.id.valor_ref3);
        resultat = (EditText)findViewById(R.id.resultat);
        //final float densi = edit_densitat;




        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               // switch (calcul){
                   // case 0:
                     //   if (densi > 0){

                       // }

               // }
                float inercia = calculaInerciaCubMassa();
                String sinercia = String.format("%f",inercia);
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
    }

    private float calculaInerciaCubMassa() {
        //String sdensitat = valor_densitat.getText().toString();
        String smassa = edit_massa.getText().toString();
        //String sref1 = valor_ref1.getText().toString();
        String sref2 = edit_ref2.getText().toString();
        String sref3 = edit_ref3.getText().toString();
        //float densitat= Float.parseFloat(sdensitat);
        float massa = Float.parseFloat(smassa);
        //float ref1= Float.parseFloat(sref1);
        float ref2 = Float.parseFloat(sref2);
        float ref3 = Float.parseFloat(sref3);
        //densitat
        //float massa = ref2*ref3*ref1*densitat

        return (massa*((ref2*ref2)+(ref3*ref3)))/12000000;
    }
}

