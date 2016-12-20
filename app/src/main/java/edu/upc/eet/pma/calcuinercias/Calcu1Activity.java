package edu.upc.eet.pma.calcuinercias;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatImageButton;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class Calcu1Activity extends AppCompatActivity {

    // Taula de ids dels botons (l'ordre ha de ser el mateix que el de 'all_titles'!!)
    private final int[] ids_botons = {
            R.id.btn_cil,    R.id.btn_cil_buit,
            R.id.btn_con,    R.id.btn_esfera,
            R.id.btn_basic,  R.id.btn_cub
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calcu1);

        String[] all_titles = getResources().getStringArray(R.array.all_titles);

        // Comprovem que les dues taules tenen la mateixa longitud
        assert(all_titles.length == ids_botons.length);

        for (int i = 0; i < ids_botons.length; i++) {
            ImageButton btn = (ImageButton) findViewById(ids_botons[i]);
            Click_Listener(btn, all_titles[i]);
        }
    }

    private void Click_Listener(final ImageButton btn, final String titol_pagina) {
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent calcular = new Intent (Calcu1Activity.this, CalculatorActivity.class);
                calcular.putExtra("tit_pag", titol_pagina);
                startActivity(calcular);
            }
        });
    }

}
