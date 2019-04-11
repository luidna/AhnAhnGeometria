package com.example.ahnahn_geometria;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import java.lang.Math;
import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity {
    Button button1, button_ddp;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        addListenerOnButton();

    }


    public void dist2p_noformat(){
        //pega os textos dos edit text
        EditText fieldPA = (EditText)findViewById(R.id.ddp1);
        String fieldPAValue = fieldPA.getText().toString();

        EditText fieldPB = (EditText)findViewById(R.id.ddp2);
        String fieldPBValue = fieldPB.getText().toString();

        String[] pontoA = fieldPAValue.split(@'-?\d');

        TextView respostaDDP = (TextView)findViewById(R.id.ddpr);
        respostaDDP.setText(pontoA[0]);

    }

    public static double dist2P(int x1, int y1, int x2, int y2)
    {
        int termoA = x1 - x2;
        int termoB = y1 - y2;
        int soma = (termoA * termoA) + (termoB * termoB);
        double resultado = Math.sqrt(soma);

        if (ehInteiro(resultado)){
            System.out.println(String.valueOf((int)resultado));
        } else {
            DecimalFormat formatador = new DecimalFormat("#.0000");
            System.out.println("√"+ soma + " ou "+ String.valueOf(formatador.format(resultado)));
        }
        return resultado;

    }

    public static Boolean ehInteiro(double val){
        //confere se eh inteiro ao ver a string
        String valOriginal = (String.valueOf(val));
        String valInt = String.valueOf((int)val) + ".0";
        return valOriginal.equals(valInt);
    }



    public void addListenerOnButton() {

        button_ddp = (Button) findViewById(R.id.button_ddp);
        button_ddp.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                dist2p_noformat();
            }
        });
    }
}

