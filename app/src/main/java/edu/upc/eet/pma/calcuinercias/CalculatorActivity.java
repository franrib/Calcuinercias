package edu.upc.eet.pma.calcuinercias;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import static edu.upc.eet.pma.calcuinercias.R.id.spinner_densitat;
import static edu.upc.eet.pma.calcuinercias.R.id.spinner_massa;
import static edu.upc.eet.pma.calcuinercias.R.id.spinner_ref1;
import static edu.upc.eet.pma.calcuinercias.R.id.spinner_resultat;

public class CalculatorActivity extends AppCompatActivity {

    //Selector D'unitats
    Spinner unitats_resultat;
    String[] unires = {"Kgm2", "Kgcm2", "ozin2", "Lbin2", "lbft2"};
    Spinner unitats_densitat;
    String[] unidens = {"Kg/m3", "Kg/cm3", "oz/in3", "lb/in3", "lb/ft3"};
    Spinner unitats_massa;
    String[] unimassa = {"Kg", "g", "oz", "lb"};
    Spinner unitats_referencia1;
    Spinner unitats_referencia2;
    Spinner unitats_referencia3;
    String[] unireff = {"mm", "m", "in", "ft"};
    private int calcul = -1;
    private EditText edit_massa;
    private EditText edit_ref2;
    private EditText edit_ref3;
    private EditText resultat;
    private EditText edit_ref1;
    private EditText edit_densitat;
    private Spinner spinner_resulta;
    private Spinner spinner_densita;
    private Spinner spinner_mass;
    private Spinner spinner_ref11;
    private Spinner spinner_ref22;
    private Spinner spinner_ref33;


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

        Button button = (Button) findViewById(R.id.btn_solve);
        edit_densitat = (EditText) findViewById(R.id.valor_densitat);
        edit_massa = (EditText) findViewById(R.id.valor_massa);
        edit_ref1 = (EditText) findViewById(R.id.valor_ref1);
        edit_ref2 = (EditText) findViewById(R.id.valor_ref2);
        edit_ref3 = (EditText) findViewById(R.id.valor_ref3);
        resultat = (EditText) findViewById(R.id.resultat);
        spinner_resulta = (Spinner) findViewById(R.id.spinner_resultat);
        spinner_densita = (Spinner) findViewById(R.id.spinner_densitat);
        spinner_mass = (Spinner) findViewById(R.id.spinner_massa);
        spinner_ref11 = (Spinner) findViewById(R.id.spinner_ref1);
        spinner_ref22 = (Spinner) findViewById(R.id.spinner_ref2);
        spinner_ref33 = (Spinner) findViewById(R.id.spinner_ref3);

        final ImageView diagrama=(ImageView)findViewById(R.id.diagrama);
        final ImageView im_ref1=(ImageView)findViewById(R.id.im_ref1);
        final ImageView im_ref2=(ImageView)findViewById(R.id.im_ref2);
        final ImageView im_ref3=(ImageView)findViewById(R.id.im_ref3);
        final TextView titol_ref1=(TextView) findViewById(R.id.titol_ref1);
        final TextView titol_ref2=(TextView) findViewById(R.id.titol_ref2);
        final TextView titol_ref3=(TextView) findViewById(R.id.titol_ref3);


        //Adaptació del layout en funció del cas a calcular
        switch (calcul){
            case 0:
                diagrama.setImageResource(R.mipmap.dia_cilinder);
                edit_ref3.setVisibility(View.INVISIBLE);
                spinner_ref33.setVisibility(View.INVISIBLE);
                im_ref3.setVisibility(View.INVISIBLE);
                titol_ref3.setVisibility(View.INVISIBLE);
                titol_ref1.setText(R.string.Radius);
                titol_ref2.setText(R.string.Length);
                break;
            case 1:
                diagrama.setImageResource(R.mipmap.dia_cil_buit);
                titol_ref1.setText(R.string.Radius_ext);
                titol_ref2.setText(R.string.Radius_in);
                titol_ref3.setText(R.string.Length);
                break;
            case 2:
                diagrama.setImageResource(R.mipmap.dia_cono);
                edit_ref3.setVisibility(View.INVISIBLE);
                spinner_ref33.setVisibility(View.INVISIBLE);
                im_ref3.setVisibility(View.INVISIBLE);
                titol_ref3.setVisibility(View.INVISIBLE);
                titol_ref1.setText(R.string.Radius);
                break;
            case 3:
                diagrama.setImageResource(R.mipmap.dia_bola);
                edit_ref3.setVisibility(View.INVISIBLE);
                spinner_ref33.setVisibility(View.INVISIBLE);
                im_ref3.setVisibility(View.INVISIBLE);
                titol_ref3.setVisibility(View.INVISIBLE);
                edit_ref2.setVisibility(View.INVISIBLE);
                spinner_ref22.setVisibility(View.INVISIBLE);
                im_ref2.setVisibility(View.INVISIBLE);
                titol_ref2.setVisibility(View.INVISIBLE);
                titol_ref1.setText(R.string.Radius);
                break;
            case 4:
                diagrama.setImageResource(R.mipmap.dia_basic);
                edit_ref3.setVisibility(View.INVISIBLE);
                spinner_ref33.setVisibility(View.INVISIBLE);
                im_ref3.setVisibility(View.INVISIBLE);
                titol_ref3.setVisibility(View.INVISIBLE);
                edit_ref2.setVisibility(View.INVISIBLE);
                spinner_ref22.setVisibility(View.INVISIBLE);
                im_ref2.setVisibility(View.INVISIBLE);
                titol_ref2.setVisibility(View.INVISIBLE);
                titol_ref1.setText(R.string.Distance);
                break;
            case 5:
                diagrama.setImageResource(R.mipmap.dia_cub);
                break;

        }

        button.setOnClickListener(new View.OnClickListener() {
            @Override

            public void onClick(View view) {
                try {
                    String smassa = edit_massa.getText().toString();
                    String sdensitat = edit_densitat.getText().toString();

                    float mass = Float.parseFloat(smassa);
                    float densi = Float.parseFloat(sdensitat);

                    switch (calcul) {
                        case 0:

                            if (densi > 0) {
                                float inercia = calculaInerciaCilindredens();
                                String sinercia = String.format("%f", inercia);
                                resultat.setText(sinercia);
                            }
                            if (mass > 0) {
                                float inercia = calculaInerciaCilindremassa();
                                String sinercia = String.format("%f", inercia);
                                resultat.setText(sinercia);
                            }
                            break;
                        case 1:

                            if (densi > 0) {
                                float inercia = calculaInerciaCilindrebuitdens();
                                String sinercia = String.format("%f", inercia);
                                resultat.setText(sinercia);
                            }
                            if (mass > 0) {
                                float inercia = calculaInerciaCilindrebuitmassa();
                                String sinercia = String.format("%f", inercia);
                                resultat.setText(sinercia);
                            }
                            break;
                        case 2:

                            if (densi > 0) {
                                float inercia = calculaInerciaCondens();
                                String sinercia = String.format("%f", inercia);
                                resultat.setText(sinercia);
                            }
                            if (mass > 0) {
                                float inercia = calculaInerciaConmassa();
                                String sinercia = String.format("%f", inercia);
                                resultat.setText(sinercia);
                            }
                            break;
                        case 3:

                            if (densi > 0) {
                                float inercia = calculaInerciaEsferadens();
                                String sinercia = String.format("%f", inercia);
                                resultat.setText(sinercia);
                            }
                            if (mass > 0) {
                                float inercia = calculaInerciaEsferamassa();
                                String sinercia = String.format("%f", inercia);
                                resultat.setText(sinercia);
                            }
                            break;
                        case 4:

                            if (mass > 0) {
                                float inercia = calculaInerciaBasicmassa();
                                String sinercia = String.format("%f", inercia);
                                resultat.setText(sinercia);
                            }
                            break;
                        case 5:

                            if (densi > 0) {
                                float inercia = calculaInerciaCubdens();
                                String sinercia = String.format("%f", inercia);
                                resultat.setText(sinercia);
                            }
                            if (mass > 0) {
                                float inercia = calculaInerciaCubMassa();
                                String sinercia = String.format("%f", inercia);
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
        unitats_resultat = (Spinner) findViewById(spinner_resultat);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, unires);
        unitats_resultat.setAdapter(adapter);

        unitats_densitat = (Spinner) findViewById(spinner_densitat);
        ArrayAdapter<String> adapter1 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, unidens);
        unitats_densitat.setAdapter(adapter1);

        unitats_massa = (Spinner) findViewById(spinner_massa);
        ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, unimassa);
        unitats_massa.setAdapter(adapter2);

        unitats_referencia1 = (Spinner) findViewById(R.id.spinner_ref1);
        ArrayAdapter<String> adapter3 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, unireff);
        unitats_referencia1.setAdapter(adapter3);
        unitats_referencia2 = (Spinner) findViewById(R.id.spinner_ref2);
        unitats_referencia2.setAdapter(adapter3);
        unitats_referencia3 = (Spinner) findViewById(R.id.spinner_ref3);
        unitats_referencia3.setAdapter(adapter3);
    }


    //METODES DE CALCUL
    private float calculaInerciaCilindremassa() {
        String smassa = edit_massa.getText().toString();
        float massa = Float.parseFloat(smassa)*unimassa();
        String sref1 = edit_ref1.getText().toString();
        float ref1 = Float.parseFloat(sref1)*unidist1();

        return ((massa * ((ref1 / 2f) * (ref1 / 2f))) / 2f)* unitsresu();
    }
    private float calculaInerciaCilindredens() {
        String sdensitat = edit_densitat.getText().toString();
        float densitat = Float.parseFloat(sdensitat)*unidensi();
        String sref1 = edit_ref1.getText().toString();
        float ref1 = Float.parseFloat(sref1)*unidist1();
        String sref2 = edit_ref2.getText().toString();
        float ref2 = Float.parseFloat(sref2)*unidist2();

        return ((3.1416f * densitat * ref2 * ((ref1 / 2f) * (ref1 / 2f) * (ref1 / 2f) * (ref1 / 2f))) / 2f)* unitsresu();
    }

    private float calculaInerciaCilindrebuitmassa() {
        String smassa = edit_massa.getText().toString();
        float massa = Float.parseFloat(smassa)*unimassa();
        String sref1 = edit_ref1.getText().toString();
        float ref1 = Float.parseFloat(sref1)*unidist1();
        String sref2 = edit_ref2.getText().toString();
        float ref2 = Float.parseFloat(sref2)*unidist2();

        return ((massa * (((ref1 / 2f) * (ref1 / 2f)) + (((ref2 / 2f) * (ref2 / 2f))))) / 2f)* unitsresu();

    }
    private float calculaInerciaCilindrebuitdens() {
        String sdensitat = edit_densitat.getText().toString();
        float densitat = Float.parseFloat(sdensitat)*unidensi();
        String sref1 = edit_ref1.getText().toString();
        float ref1 = Float.parseFloat(sref1)*unidist1();
        String sref2 = edit_ref2.getText().toString();
        float ref2 = Float.parseFloat(sref2)*unidist2();
        String sref3 = edit_ref3.getText().toString();
        float ref3 = Float.parseFloat(sref3)*unidist3();

        return ((3.1416f * densitat * ref3) / 2) * (((ref1 / 2f) * (ref1 / 2f) * (ref1 / 2f) * (ref1 / 2f)) - ((ref2 / 2f) * (ref2 / 2f) * (ref2 / 2f) * (ref2 / 2f)))* unitsresu();

    }

    private float calculaInerciaConmassa() {
        String smassa = edit_massa.getText().toString();
        float massa = Float.parseFloat(smassa)*unimassa();
        String sref1 = edit_ref1.getText().toString();
        float ref1 = Float.parseFloat(sref1)*unidist1();

        return ((3f * massa * ((ref1 / 2f) * (ref1 / 2f))) / 10f)* unitsresu();
    }
    private float calculaInerciaCondens() {
        String sdensitat = edit_densitat.getText().toString();
        float densitat = Float.parseFloat(sdensitat)*unidensi();
        String sref1 = edit_ref1.getText().toString();
        float ref1 = Float.parseFloat(sref1)*unidist1();
        String sref2 = edit_ref2.getText().toString();
        float ref2 = Float.parseFloat(sref2)*unidist2();
        float massa = ((3.1416f * (ref1 * ref1) * ref2) / 12f) * densitat;

        return ((3f * massa * ((ref1 / 2f) * (ref1 / 2f))) / 10f)* unitsresu();
    }

    private float calculaInerciaEsferamassa() {
        String smassa = edit_massa.getText().toString();
        float massa = Float.parseFloat(smassa)*unimassa();
        String sref1 = edit_ref1.getText().toString();
        float ref1 = Float.parseFloat(sref1)*unidist1();

        return ((2f / 5f) * massa * ((ref1 / 2f) * (ref1 / 2f)))* unitsresu();
    }
    private float calculaInerciaEsferadens() {
        String sdensitat = edit_densitat.getText().toString();
        float densitat = Float.parseFloat(sdensitat)*unidensi();
        String sref1 = edit_ref1.getText().toString();
        float ref1 = Float.parseFloat(sref1)*unidist1();
        float massa = ((3.1416f * (ref1*ref1*ref1))/6) * densitat;

        return ((2f / 5f) * massa * ((ref1 / 2f) * (ref1 / 2f)))* unitsresu();

    }

    private float calculaInerciaBasicmassa() {
        String smassa = edit_massa.getText().toString();
        float massa = Float.parseFloat(smassa)*unimassa();
        String sref1 = edit_ref1.getText().toString();
        float ref1 = Float.parseFloat(sref1)*unidist1();

        return ((massa * ref1)/10f)* unitsresu();

    }

    private float calculaInerciaCubMassa() {
        String smassa = edit_massa.getText().toString();
        String sref1 = edit_ref1.getText().toString();
        String sref2 = edit_ref2.getText().toString();
        float massa = Float.parseFloat(smassa)*unimassa();
        float ref1 = Float.parseFloat(sref1)*unidist1();
        float ref2 = Float.parseFloat(sref2)*unidist2();
        return ((massa * ((ref1 * ref2) + (ref2 * ref2))) / 12f) * unitsresu();
    }
    private float calculaInerciaCubdens() {
        String sdensitat = edit_densitat.getText().toString();
        float densitat = Float.parseFloat(sdensitat)*unidensi();
        String sref1 = edit_ref1.getText().toString();
        float ref1 = Float.parseFloat(sref1)*unidist1();
        String sref2 = edit_ref2.getText().toString();
        String sref3 = edit_ref3.getText().toString();
        float ref2 = Float.parseFloat(sref2)*unidist2();
        float ref3 = Float.parseFloat(sref3)*unidist3();
        float massa = ref2 * ref3 * ref1 * densitat;
        return ((massa * ((ref2 * ref2) + (ref3 * ref3))) / 12)* unitsresu();
    }

    //CANVI D'UNITATS
    private float unitsresu() {
        String Textresultat = spinner_resulta.getSelectedItem().toString();
        float res = 0;
        if (Textresultat.equals("Kgm2")) {
            res = 1f;
        } else if (Textresultat.equals("Kgcm2")) {
            res = 10000f;
        } else if (Textresultat.equals("ozin2")) {
            res = 54674.8f;
        } else if (Textresultat.equals("Lbin2")) {
            res = 3417.17f;
        } else if (Textresultat.equals("lbft2")) {
            res = 23.7304f;
        }
        return res;
    }
    private float unidensi() {
        String Textresultat = spinner_densita.getSelectedItem().toString();
        float res = 0;
        if (Textresultat.equals("Kg/m3")) {
            res = 1f;
        } else if (Textresultat.equals("Kg/cm3")) {
            res = 1000000f;
        } else if (Textresultat.equals("oz/in3")) {
            res = 1729.99f;
        } else if (Textresultat.equals("lb/in3")) {
            res = 27679.9f;
        } else if (Textresultat.equals("lb/ft3")) {
            res = 16.018463f;
        }
        return res;
    }
    private float unimassa() {
        String Textresultat = spinner_mass.getSelectedItem().toString();
        float res = 0;
        if (Textresultat.equals("Kg")) {
            res = 1f;
        } else if (Textresultat.equals("g")) {
            res = 0.001f;
        } else if (Textresultat.equals("oz")) {
            res = 0.0283495f;
        } else if (Textresultat.equals("lb")) {
            res = 0.453592f;
        }
        return res;
    }
    private float unidist1() {
        String Textresultat = spinner_ref11.getSelectedItem().toString();
        float res = 0;
        if (Textresultat.equals("mm")) {
            res = 0.001f;
        } else if (Textresultat.equals("m")) {
            res = 1f;
        } else if (Textresultat.equals("in")) {
            res = 0.0254f;
        } else if (Textresultat.equals("ft")) {
            res = 0.3048f;
        }
        return res;
    }
    private float unidist2() {
        String Textresultat = spinner_ref22.getSelectedItem().toString();
        float res = 0;
        if (Textresultat.equals("mm")) {
            res = 0.001f;
        } else if (Textresultat.equals("m")) {
            res = 1f;
        } else if (Textresultat.equals("in")) {
            res = 0.0254f;
        } else if (Textresultat.equals("ft")) {
            res = 0.3048f;
        }
        return res;
    }
    private float unidist3() {
        String Textresultat = spinner_ref33.getSelectedItem().toString();
        float res = 0;
        if (Textresultat.equals("mm")) {
            res = 0.001f;
        } else if (Textresultat.equals("m")) {
            res = 1f;
        } else if (Textresultat.equals("in")) {
            res = 0.0254f;
        } else if (Textresultat.equals("ft")) {
            res = 0.3048f;
        }
        return res;
    }


}
