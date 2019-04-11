package com.example.ahnahn_geometria;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


import org.w3c.dom.Text;

import java.lang.Math;
import java.text.DecimalFormat;
import java.math.BigInteger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MainActivity extends AppCompatActivity {
    Button button1, button_ddp, button_preta_calc;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        addListenerOnButton();

    }


    public void process2P(){
        //pega os textos dos edit text
        EditText fieldPA = (EditText)findViewById(R.id.ddp1);
        String fieldPAValue = fieldPA.getText().toString();

        EditText fieldPB = (EditText)findViewById(R.id.ddp2);
        String fieldPBValue = fieldPB.getText().toString();

        String[] pontoA = fieldPAValue.split(",");
        String[] pontoB = fieldPBValue.split(",");
        int PAx = Integer.parseInt(pontoA[0]);
        int PAy = Integer.parseInt(pontoA[1]);
        int PBx = Integer.parseInt(pontoB[0]);
        int PBy = Integer.parseInt(pontoB[1]);

        String resultadoDist = dist2P(PAx,PAy,PBx,PBy);
        String resultadoQreta = retaFrom2P(PAx, PAy, PBx, PBy);


        TextView respostaDDP = (TextView)findViewById(R.id.ddpr);
        TextView respostaEQreta = (TextView)findViewById(R.id.retaR);
        respostaDDP.setText(resultadoDist);
        respostaEQreta.setText(resultadoQreta);


    }

    public void pontoReta()
    {
        //inicializando os pontos da UI
        EditText fieldPontoReta = (EditText)findViewById(R.id.pontoVreta);
        EditText fieldEQReta = (EditText)findViewById(R.id.eqRetaPR);
        String EQreta = fieldEQReta.getText().toString();
        String pontoVreta = fieldPontoReta.getText().toString();
        TextView labelResultado = (TextView)findViewById(R.id.ResultadoPReta);

        String[] pontoV = pontoVreta.split(",");
        int px1 = Integer.parseInt(pontoV[0]);
        int py1 = Integer.parseInt(pontoV[1]);


        Pattern px = Pattern.compile("-?\\d*(?=x)");
        Pattern py = Pattern.compile("-?\\d*(?=y)");
        Pattern pc = Pattern.compile("-?\\d*(?=[=])");
        Matcher mx = px.matcher(EQreta);
        Matcher my = py.matcher(EQreta);
        Matcher mc = pc.matcher(EQreta);
        String strRx;
        String strRy;
        String strRc;
        if (mx.find()){
            strRx = mx.group(0).toString();
            if (strRx.equals("-")){
                strRx = "-1";
            } else if (strRx.equals("")){
                strRx = "1";
            }
        }
         else {
            strRx = "0";
        }


        if (my.find()){
            strRy = my.group(0).toString();
            if (strRy.equals("-")){
                strRy = "-1";
            } else if (strRy.equals("")){
                strRy = "1";
            }
        } else {
            strRy = "0";
        }


        if (mc.find()){
            strRc = mc.group(0).toString();
        } else {
            strRc = "0";
        }

        int rx = Integer.parseInt(strRx);
        int ry = Integer.parseInt(strRy);
        int rc = Integer.parseInt(strRc);

        String resultado = distPReta(px1, py1, rx, ry, rc);
        labelResultado.setText(resultado);


        //int retaX = ;
    }

    public static String distPReta(int px1, int py1, int rx, int ry, int rc)
    {
        int numerador = (rx * px1) + (ry * py1) + rc;
        numerador = moduloInt(numerador);
        int denominadorQuadrado = (rx * rx) + (ry * ry);
        double denominador = Math.sqrt(denominadorQuadrado);
        String Resultado;


        if (ehInteiro(denominador)){
            Resultado = String.valueOf(numerador/denominador);
        } else {
            Resultado = String.valueOf(numerador) + "/√" + String.valueOf(denominadorQuadrado);
        }
        return Resultado;

    }




    public static String retaFrom2P(int x1, int y1, int x2, int y2){

        double m = mAngular(x1,y1,x2,y2);
        String mF = mFracionario(x1,y1,x2,y2);
        double c = -(m*x1) + y1;

        /*
        if (main.VERBOSE) {
            System.out.println(c);
            System.out.println(m);
        }
        */
        String strM;
        if (ehInteiro(m)){
            strM = String.valueOf((int)m);
        } else {
            strM = mF;
        }

        String strC;
        if (ehInteiro(c)){


            strC = String.valueOf((int)c);
        } else {
            strC = String.valueOf(c);
        }
        if (c < 0){
            return "y=" + strM + "x" + strC;
        } else if (c > 0) {
            return "y=" + strM + "x+" + strC;
        } else {
            return "y=" + strM + "x";
        }

    }

    public static double mAngular(int x1, int y1, int x2, int y2)
    {
        //Calcula o coeficiente angular, dado os 4 valores
        return (double)(y1-y2)/(x1-x2);

    }

    public static String mFracionario(int x1, int y1, int x2, int y2)
    {
        //faz a versao fracionada de um coeficiente angular
        int numerador = y1 - y2;
        int denominador = x1 - x2;
        int mdcAtual = MDC(numerador, denominador);

        if (numerador < 0 && denominador < 0){
            numerador = numerador *(-1);
            denominador = denominador *(-1);
        }
        String coeficienteFinal = String.valueOf(numerador) + '/' + String.valueOf(denominador);
        if (mdcAtual != 1) {
            String numeradorSimples = String.valueOf(numerador/mdcAtual);
            String denominadorSimples = String.valueOf(denominador/mdcAtual);
            String simplificado = numeradorSimples  + "/" + denominadorSimples;
            //System.out.println("Simplificada em " + simplificado);
            coeficienteFinal = simplificado;

        }
        //System.out.println(MDC(numerador, denominador));
        return coeficienteFinal;
    }

    public static int MDC(int a, int b)
    {
        // calcula o mdc pra poder fazer a versão fracionada do coeficiente angular
        BigInteger b1 = BigInteger.valueOf(a);
        BigInteger b2 = BigInteger.valueOf(b);
        BigInteger mdc = b1.gcd(b2);
        return mdc.intValue();

    }
    public static String dist2P(int x1, int y1, int x2, int y2)
    {
        int termoA = x1 - x2;
        int termoB = y1 - y2;
        int soma = (termoA * termoA) + (termoB * termoB);
        double resultado = Math.sqrt(soma);

        if (ehInteiro(resultado)){
            return String.valueOf((int)resultado);
        } else {
            DecimalFormat formatador = new DecimalFormat("#.0000");
            return "D = √"+ String.valueOf(soma) + " ou "+ String.valueOf(formatador.format(resultado));
        }

    }

    public static Boolean ehInteiro(double val){
        //confere se eh inteiro ao ver a string
        String valOriginal = (String.valueOf(val));
        String valInt = String.valueOf((int)val) + ".0";
        return valOriginal.equals(valInt);
    }
    public static int moduloInt(int m)
    {
        if (m >= 0 ){
            return m;
        } else {
            return (m *(-1));
        }
    }



    public void addListenerOnButton() {

        button_ddp = (Button) findViewById(R.id.button_ddp);
        button_ddp.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                process2P();
            }
        });
        button_preta_calc = (Button)findViewById(R.id.pontoRetaCalc);
        button_preta_calc.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                pontoReta();
            }
        });


    }
}

