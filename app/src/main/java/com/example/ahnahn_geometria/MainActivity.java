package com.example.ahnahn_geometria;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import java.lang.Math;

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



        TextView respostaDDP = (TextView)findViewById(R.id.ddpr);
        respostaDDP.setText(fieldPAValue);

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

