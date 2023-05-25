package com.example.t3_fn;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Debug;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

//CALULADORA NO FUNCIONA

public class MainActivity extends AppCompatActivity {

    float respuestaOperación = 0, valor1, valor2;
    String operador, numero = "";
    List<Integer> numeros = new ArrayList<>();
    List<String> operadores = new ArrayList<>();
    List<Float> operaciones = new ArrayList<>();
    List<String> borrarOperadores = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Textos
        TextView resultado = findViewById(R.id.textViewResultado);

        //Botones numeros

        //N0
        Button btn0 = findViewById(R.id.button0);
        btn0.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                resultado.setText(resultado.getText() + " " + (btn0.getText().toString()));
                numero = numero + "" + btn0.getText(); //string para guardar el numero
            }
        });

        //N1
        Button btn1 = findViewById(R.id.button1);
        btn1.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                resultado.setText(resultado.getText() + " " + (btn1.getText().toString()));
                numero = numero + "" + btn1.getText();
            }
        });

        //N2
        Button btn2 = findViewById(R.id.button2);
        btn2.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                resultado.setText(resultado.getText() + " " + (btn2.getText().toString()));
                numero = numero + "" + btn2.getText();
            }
        });

        //N3
        Button btn3 = findViewById(R.id.button3);
        btn3.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                resultado.setText(resultado.getText() + " " + (btn3.getText().toString()));
                numero = numero + "" + btn3.getText();
            }
        });

        //N4
        Button btn4 = findViewById(R.id.button4);
        btn4.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                resultado.setText(resultado.getText() + " " + (btn4.getText().toString()));
                numero = numero + "" + btn4.getText();
            }
        });

        //N5
        Button btn5 = findViewById(R.id.button5);
        btn5.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                resultado.setText(resultado.getText() + " " + (btn5.getText().toString()));
                numero = numero + "" + btn5.getText();
            }
        });

        //N6
        Button btn6 = findViewById(R.id.button6);
        btn6.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                resultado.setText(resultado.getText() + " " + (btn6.getText().toString()));
                numero = numero + "" + btn6.getText();
            }
        });

        //N7
        Button btn7 = findViewById(R.id.button7);
        btn7.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                resultado.setText(resultado.getText() + " " + (btn7.getText().toString()));
                numero = numero + "" + btn7.getText();
            }
        });

        //N8
        Button btn8 = findViewById(R.id.button8);
        btn8.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                resultado.setText(resultado.getText() + " " + (btn8.getText().toString()));
                numero = numero + "" + btn8.getText();
            }
        });

        //N9
        Button btn9 = findViewById(R.id.button9);
        btn9.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                resultado.setText(resultado.getText() + " " + (btn9.getText().toString()));
                numero = numero + "" + btn9.getText();
            }
        });

        //Botones operaciones

        Button btnsuma = findViewById(R.id.buttonSuma);
        btnsuma.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                operador = "+";
                resultado.setText(resultado.getText() + operador);
                System.out.println("el boton funciona" + numero);
                numeros.add(Integer.parseInt(numero));
                numero = "";
                operadores.add(operador);
            }
        });

        Button btnResta = findViewById(R.id.buttonResta);
        btnResta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                operador = "-";
                resultado.setText(resultado.getText() + operador);
                numeros.add(Integer.parseInt(numero));
                numero = "";
                operadores.add(operador);
            }
        });

        Button btnMulti = findViewById(R.id.buttonMultiplicacion);
        btnMulti.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                operador = "*";
                resultado.setText(resultado.getText() + operador);
                numeros.add(Integer.parseInt(numero));
                numero = "";
                operadores.add(operador);
            }
        });

        Button btnDivision = findViewById(R.id.buttonDivision);
        btnDivision.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                operador = "/";
                resultado.setText(resultado.getText() + operador);
                numeros.add(Integer.parseInt(numero));
                numero = "";
                operadores.add(operador);
            }
        });

        Button btnIgual = findViewById(R.id.buttonIgualA);
        btnIgual.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //recorro la lista y ocupo 2 numeros - op entre y por
                //i++ para que avance
                for(int i= 0; i< numeros.size(); i++){
                    if(operadores.get(i).equals("/") || operadores.get(i).equals("*")){
                        if(operadores.get(i).equals("/")){
                            float re = numeros.get(i)/numeros.get(i+1);
                            operaciones.add(re);
                        }
                        else{
                            float re = numeros.get(i)*numeros.get(i+1);
                            operaciones.add(re);
                        }
                        i++;
                        if(i == (numeros.size()-1)) borrarOperadores.add(operadores.get(i)); //borrar operador - mandamos más y menos
                    }
                    else{
                        operaciones.add((float)numeros.get(i)); //devolviendo los numeros
                        if(i == (numeros.size()-1)) borrarOperadores.add(operadores.get(i));
                    }
                }
                float re2 = operaciones.get(0); //va a guardar todos los datos
                //arranca en el primer numero y si hay un + suma  el siguiente

                for (int i = 1; i< operaciones.size(); i++){
                    if(borrarOperadores.get(i).equals("+")){
                        re2 = re2 + operaciones.get(i); //proceso de suma
                    }
                    if(borrarOperadores.get(i).equals("-")){
                        re2 = re2 - operaciones.get(i); //proceso de resta
                    }
                }
                resultado.setText(String.valueOf(re2));
            }
        });
    }
}