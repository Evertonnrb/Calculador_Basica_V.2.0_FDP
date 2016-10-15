package br.com.fabricadeprogramador.calculadorafpd;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.regex.Pattern;

public class MainActivity extends AppCompatActivity {
    private TextView _tela;
    private  String display="";
    private String _operadores = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //Limpando a view que recebera as operaçoes
        _tela = (TextView)findViewById(R.id.textView);
        _tela.setText(display);
    }

    //metodo para limpar a tela
    private void _atualizaTela() {
        _tela.setText(display);
    }

        //Instanciando os botoes
    public void onClickNumber(View v){
        Button b = (Button) v;
        display += b.getText();
        _atualizaTela();
    }

    //instanciando operadores
    public void onClickOperator(View v){
        Button b = (Button)v;
        display += b.getText();
        //limpando operador
        _operadores = b.getText().toString();
        _atualizaTela();
    }

    private void clear(){
        display="";
        _operadores ="";
    }
    public void onClickClear(View v){
        clear();
        _atualizaTela();
    }

    private double operacoes(String a, String b, String op){
        switch (op){
            case "+":return Double.valueOf(a) + Double.valueOf(b);
            case "-":return Double.valueOf(a) - Double.valueOf(b);
            case "*":return Double.valueOf(a) * Double.valueOf(b);
            case "/": try{return Double.valueOf(a) / Double.valueOf(b);}
            catch (Exception e){
                Log.d("Calc ",e.getMessage());
            }
            default:return -1;
        }
    }

    public void onClickEquals(View v){
        String[] operation = display.split(Pattern.quote(_operadores));
        if(operation.length<2) return;

        Double _result = operacoes(operation[0],operation[1], _operadores);
        _tela.setText(display+"\n"+String.valueOf(_result));
    }
}
