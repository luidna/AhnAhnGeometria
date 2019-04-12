package com.example.ahnahn_geometria;

import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.AlertDialog;
import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import java.lang.Math;
import java.text.DecimalFormat;
import java.math.BigInteger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class reserve extends AppCompatActivity {

    Button button1, button_ddp, button_preta_calc, button_det_calc, button_calc_retas;

    Pattern Ppx = Pattern.compile("-?\\d*(?=[xX])");
    Pattern Ppy = Pattern.compile("-?\\d*(?=[yY])");



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reserve);
        addListenerOnButton();

    }



    public void detcalc(){
        TextView labelResultado = (TextView)findViewById(R.id.resultadoDet);
        EditText fieldD11 = (EditText)findViewById(R.id.det11);
        EditText fieldD12 = (EditText)findViewById(R.id.det12);
        EditText fieldD13 = (EditText)findViewById(R.id.det13);
        EditText fieldD21 = (EditText)findViewById(R.id.det21);
        EditText fieldD22 = (EditText)findViewById(R.id.det22);
        EditText fieldD23 = (EditText)findViewById(R.id.det23);
        EditText fieldD31 = (EditText)findViewById(R.id.det31);
        EditText fieldD32 = (EditText)findViewById(R.id.det32);
        EditText fieldD33 = (EditText)findViewById(R.id.det33);

        EditText[] arrayteste = {fieldD11, fieldD12, fieldD13, fieldD21, fieldD22, fieldD23, fieldD31, fieldD32, fieldD33};

        for (EditText loopedEditText: arrayteste){
            String a = loopedEditText.getText().toString();
            if (a.equals("")){


                AlertDialog.Builder builderDialog = new AlertDialog.Builder(this);
                builderDialog.setMessage("Sua determinante tem valores nulos, conserte!");
                builderDialog.setTitle("Seu burro");
                AlertDialog dialog = builderDialog.create();
                dialog.show();
                return;}
        }

        int D11 = Integer.parseInt(fieldD11.getText().toString());
        int D12 = Integer.parseInt(fieldD12.getText().toString());
        int D13 = Integer.parseInt(fieldD13.getText().toString());
        int D21 = Integer.parseInt(fieldD21.getText().toString());
        int D22 = Integer.parseInt(fieldD22.getText().toString());
        int D23 = Integer.parseInt(fieldD23.getText().toString());
        int D31 = Integer.parseInt(fieldD31.getText().toString());
        int D32 = Integer.parseInt(fieldD32.getText().toString());
        int D33 = Integer.parseInt(fieldD33.getText().toString());

        int resultado, ladoa, ladob;
        ladoa = (D11*D22*D33)+(D12*D23*D31)+(D13*D21*D32);
        ladob = (D12*D21*D33)+(D11*D23*D32)+(D13*D22*D31);

        resultado = ladoa - ladob;


        labelResultado.setText(String.valueOf(resultado));

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


        Pattern pc = Pattern.compile("-?\\d*(?=[=])");
        Matcher mx = Ppx.matcher(EQreta);
        Matcher my = Ppy.matcher(EQreta);
        Matcher mc = pc.matcher(EQreta);
        String strRx, strRy, strRc;
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

    public void intersectRetasUI()
    {

        TextView LabelResultadoRetas =  (TextView)findViewById(R.id.resultInterRetas);
        EditText fieldRetaA = (EditText)findViewById(R.id.interReta1);
        EditText fieldRetaB = (EditText)findViewById(R.id.interReta2);
        String strRetaA = fieldRetaA.getText().toString();
        String strRetaB = fieldRetaB.getText().toString();

        Pattern Pc = Pattern.compile("-?\\d+(?!x)(?!y)");

        Matcher mx = Ppx.matcher(strRetaA);
        Matcher mc = Pc.matcher(strRetaA);

        String Ax, Ac, Bx, Bc;
        if (mx.find()) {
            Ax = mx.group(0).toString();
            if (Ax.equals("-")){
                Ax = "-1";
            } else if (Ax.equals("")){
                Ax = "1";
            }

        } else {
            Ax = "0";
        }

        if (mc.find()){
            Ac = mc.group(0).toString();
        } else {
            Ac = "";
        }

        mx = Ppx.matcher(strRetaB);
        mc = Pc.matcher(strRetaB);

        if (mx.find()) {
            Bx = mx.group(0).toString();
            if (Bx.equals("-")){
                Bx = "-1";
            } else if (Bx.equals("")){
                Bx = "1";
            }
        } else {
            Bx = "0";
        }

        if (mc.find()){
            Bc = mc.group(0).toString();
        } else {
            Bc = "0";
        }

        int x1 = Integer.parseInt(Ax);
        int c1 = Integer.parseInt(Ac);
        int x2 = Integer.parseInt(Bx);
        int c2 = Integer.parseInt(Bc);

        String resultado = intersectRetas(x1, c1, x2, c2);
        LabelResultadoRetas.setText(resultado);


    }

    public static String intersectRetas(int x1,int c1, int x2, int c2)
    {
        //acha a interseccao por igualar o y
        int resulx = x1 - x2;
        int resulc = c2 - c1;
        int mdcresultado = MDC(resulx, resulc);
        boolean xnegativo = (resulx < 0);
        if (xnegativo){
            resulx = resulx * (-1);
            resulc = resulc * (-1);
        }

        //fazendo em doubles pra poder fazer a divisao depois
        double resulfx, resulfc;

        if (mdcresultado == resulx){
            resulfx = resulx/mdcresultado;
            resulfc = resulc/mdcresultado;
        } else {
            resulfc = (double)resulc / (double)resulx;
            resulfx = 1;
        }
        double xintersect = resulfc;
        double yintersect = (double)x1 * xintersect + (double)c1;

        DecimalFormat formatador = new DecimalFormat("#.0000");

        if (ehInteiro(xintersect) && ehInteiro(yintersect)){
            return "(" + String.valueOf((int)xintersect) + ", " + String.valueOf((int)yintersect) + ")";
        } else {
            return "(" + String.valueOf(formatador.format(xintersect)) + ", " + String.valueOf(formatador.format(yintersect)) + ")";
        }

    }

    public static String retaFrom2P(int x1, int y1, int x2, int y2)
    {

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
            DecimalFormat formatador = new DecimalFormat("#.0000");
            strC = String.valueOf(formatador.format(c));
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

    public static Boolean ehInteiro(double val)
    {
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
        button_det_calc = (Button)findViewById(R.id.calculo_det);
        button_det_calc.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                detcalc();
            }
        });
        button_calc_retas = (Button)findViewById(R.id.calc_inter_retas);
        button_calc_retas.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                intersectRetasUI();
            }
        });


    }
}
