package edu.upc.eet.pma.calcuinercias;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
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
        }

        Button button = (Button)findViewById(R.id.btn_solve);
        edit_densitat =(EditText)findViewById(R.id.valor_densitat);
        edit_massa = (EditText)findViewById(R.id.valor_massa);
        edit_ref1 =(EditText)findViewById(R.id.valor_ref1);
        edit_ref2 = (EditText)findViewById(R.id.valor_ref2);
        edit_ref3 = (EditText)findViewById(R.id.valor_ref3);
        resultat = (EditText)findViewById(R.id.resultat);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    String smassa = edit_massa.getText().toString();
                    String sdensitat = edit_densitat.getText().toString();

                    float mass  = Float.parseFloat(smassa);
                    float densi = Float.parseFloat(sdensitat);

                    switch (calcul){
                        case 0:
                            if (densi > 0){
                            float inercia = calculaInerciaCubdens();
                            String sinercia = String.format("%f",inercia);
                            resultat.setText(sinercia);
                            }
                            if (mass > 0){
                             float inercia = calculaInerciaCubMassa();
                             String sinercia = String.format("%f",inercia);
                             resultat.setText(sinercia);
                            }
                        break;







                    }
                } catch (NumberFormatException e) {
                    Log.e("calcuinercia", "Error en algun float.");
                    resultat.setText("ERROR");
                }
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





    //METODES DE CALCUL
    private float calculaInerciaCilindremassa() {
        String smassa = edit_massa.getText().toString();
        float massa = Float.parseFloat(smassa);
        String sref1 = edit_ref1.getText().toString();
        float ref1= Float.parseFloat(sref1);

        return (massa*((ref1/2f)*(ref1/2f)))/2;
    }
    private float calculaInerciaCilindredens() {
        String sdensitat = edit_densitat.getText().toString();
        float densitat= Float.parseFloat(sdensitat);
        String sref1 = edit_ref1.getText().toString();
        float ref1= Float.parseFloat(sref1);
        String sref2 = edit_ref2.getText().toString();
        float ref2 = Float.parseFloat(sref2);

        return (3.1416f*densitat*ref2*((ref1/2f)*(ref1/2f)*(ref1/2f)*(ref1/2f)))/2f;
    }

    private float calculaInerciaCilindrebuitmassa() {
        String smassa = edit_massa.getText().toString();
        float massa = Float.parseFloat(smassa);
        String sref1 = edit_ref1.getText().toString();
        float ref1= Float.parseFloat(sref1);
        String sref2 = edit_ref2.getText().toString();
        float ref2 = Float.parseFloat(sref2);

        return (massa*(((ref1/2f)*(ref1/2f))+(((ref2/2f)*(ref2/2f)))))/2f;

    }
    private float calculaInerciaCilindrebuitdens() {
        String sdensitat = edit_densitat.getText().toString();
        float densitat= Float.parseFloat(sdensitat);
        String sref1 = edit_ref1.getText().toString();
        float ref1= Float.parseFloat(sref1);
        String sref2 = edit_ref2.getText().toString();
        float ref2 = Float.parseFloat(sref2);
        String sref3 = edit_ref3.getText().toString();
        float ref3 = Float.parseFloat(sref3);

        return ((3.1416f*densitat*ref3)/2)*(((ref1/2f)*(ref1/2f)*(ref1/2f)*(ref1/2f))-((ref2/2f)*(ref2/2f)*(ref2/2f)*(ref2/2f)));

    }

    private float calculaInerciaConmassa() {
        String sdensitat = edit_densitat.getText().toString();
        float densitat= Float.parseFloat(sdensitat);
        String smassa = edit_massa.getText().toString();
        float massa = Float.parseFloat(smassa);
        String sref1 = edit_ref1.getText().toString();
        float ref1= Float.parseFloat(sref1);
        String sref2 = edit_ref2.getText().toString();
        float ref2 = Float.parseFloat(sref2);
        String sref3 = edit_ref3.getText().toString();
        float ref3 = Float.parseFloat(sref3);

        return (3f*massa*((ref1/2)*(ref1/2)))/10;
    }
    private float calculaInerciaCondens(){
        String sdensitat = edit_densitat.getText().toString();
        float densitat= Float.parseFloat(sdensitat);
        String sref1 = edit_ref1.getText().toString();
        float ref1= Float.parseFloat(sref1);
        String sref2 = edit_ref2.getText().toString();
        float ref2 = Float.parseFloat(sref2);
        float massa = ((3.1416f*(ref1*ref1)*ref2)/12)*densitat;

        return (3f*massa*((ref1/2)*(ref1/2)))/10;
    }

    private float calculaInerciaEsferamassa() {
        String smassa = edit_massa.getText().toString();
        float massa = Float.parseFloat(smassa);
        String sref1 = edit_ref1.getText().toString();
        float ref1= Float.parseFloat(sref1);

        return ((2/5)*massa*((ref1/2f)*(ref1/2)));
    }
    private float calculaInerciaEsferadens() {
        String sdensitat = edit_densitat.getText().toString();
        float densitat= Float.parseFloat(sdensitat);
        String sref1 = edit_ref1.getText().toString();
        float ref1= Float.parseFloat(sref1);
        float massa = (3.146f*((ref1/2)*(ref1/2)*(ref1/2)))*densitat;

        return ((2/5)*massa*((ref1/2f)*(ref1/2)));

    }

    private float calculaInerciaBasicmassa() {
        String smassa = edit_massa.getText().toString();
        float massa = Float.parseFloat(smassa);
        String sref1 = edit_ref1.getText().toString();
        float ref1= Float.parseFloat(sref1);

        return (massa*ref1);

    }

    private float calculaInerciaCubMassa() {
        String smassa = edit_massa.getText().toString();
        String sref2 = edit_ref2.getText().toString();
        String sref3 = edit_ref3.getText().toString();
        float massa = Float.parseFloat(smassa);
        float ref2 = Float.parseFloat(sref2);
        float ref3 = Float.parseFloat(sref3);
        return (massa*((ref2*ref2)+(ref3*ref3)))/12000000;
    }
    private float calculaInerciaCubdens(){
        String sdensitat = edit_densitat.getText().toString();
        float densitat= Float.parseFloat(sdensitat);
        String sref1 = edit_ref1.getText().toString();
        float ref1= Float.parseFloat(sref1);
        String sref2 = edit_ref2.getText().toString();
        String sref3 = edit_ref3.getText().toString();
        float ref2 = Float.parseFloat(sref2);
        float ref3 = Float.parseFloat(sref3);
        float massa = ref2*ref3*ref1*densitat;
        return (massa*((ref2*ref2)+(ref3*ref3)))/12000000;
    }
}

