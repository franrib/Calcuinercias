package edu.upc.eet.pma.calcuinercias;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatImageButton;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;



public class Calcu1Activity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calcu1);

        ImageButton btn_cilindre = (ImageButton)findViewById(R.id.btn_cil);
        ImageButton btn_cil_buit = (ImageButton)findViewById(R.id.btn_cil_buit);
        ImageButton btn_basic = (ImageButton)findViewById(R.id.btn_basic);
        ImageButton btn_con = (ImageButton) findViewById(R.id.btn_con);
        ImageButton btn_esfera = (ImageButton) findViewById(R.id.btn_esfera);
        ImageButton btn_cub = (ImageButton) findViewById(R.id.btn_cub);

        Click_Listener(btn_cilindre);
        Click_Listener(btn_basic);
        Click_Listener(btn_cil_buit);
        Click_Listener(btn_con);
        Click_Listener(btn_cub);
        Click_Listener(btn_esfera);



    }

    private void Click_Listener(final ImageButton btn_type) {
        btn_type.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent calcular = new Intent (Calcu1Activity.this, CalculatorActivity.class);


                for(int i=0;i<5;i++) {
                    String[] all_titles = getResources().getStringArray(R.array.all_titles);
                    String t0 = all_titles[i];
                    String[] parts = t0.split(";");
                    String btn = parts[1];
                    String boto = btn_type.getResources().getResourceName(v.getId());

                    if (parts[1]== boto){
                        String patata = parts [0];
                        i=5;
                        calcular.putExtra("tit_pag",patata);
                    }
                }


                startActivity(calcular);
            }
        });
    }

}
