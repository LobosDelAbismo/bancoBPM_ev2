package com.example.bancobpm_ev2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;

import Clases.Clientes;
import Clases.Creditos;

public class prestamos_act extends AppCompatActivity
{
    private Spinner spinner1, spinner2;
    private TextView textv;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prestamos_act);

        spinner1 = (Spinner) findViewById(R.id.spin1);
        spinner2 = (Spinner) findViewById(R.id.spin2);
        textv = (TextView) findViewById(R.id.txtv);

        ArrayList<String> listaClientes = (ArrayList<String>) getIntent().getSerializableExtra("listaclientes");
        ArrayList<String> listaCreditos = (ArrayList<String>) getIntent().getSerializableExtra("listacreditos");

        ArrayAdapter<String> adapt = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, listaClientes);
        ArrayAdapter<String> adapts = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, listaCreditos);

        spinner1.setAdapter(adapt);
        spinner2.setAdapter(adapts);
    }

    public void Prestamos(View v)
    {
        String cliente = spinner1.getSelectedItem().toString();
        String credito = spinner2.getSelectedItem().toString();

        Clientes clientes = new Clientes();
        Creditos creditos = new Creditos();

        int resultado;

        if(cliente.equals("Axel"))
        {
            if (credito.equals("Credito Hipotecario"))
            {
                resultado = clientes.getAxel() + creditos.getcHipotecario();
                textv.setText("Su saldo final es: $" + resultado);
            }
            if (credito.equals("Credito Automotriz"))
            {
                resultado = clientes.getAxel() + creditos.getcAutomotriz();
                textv.setText("Su saldo final es: $" + resultado);
            }
        }
        if(cliente.equals("Roxana"))
        {
            if (credito.equals("Credito Hipotecario"))
            {
                resultado = clientes.getRoxana() + creditos.getcHipotecario();
                textv.setText("Su saldo final es: $" + resultado);
            }
            if (credito.equals("Credito Automotriz"))
            {
                resultado = clientes.getRoxana() + creditos.getcAutomotriz();
                textv.setText("Su saldo final es: $" + resultado);
            }
        }
    }

    public void Deudas(View v)
    {
        String cliente = spinner1.getSelectedItem().toString();
        String credito = spinner2.getSelectedItem().toString();

        Clientes clientes = new Clientes();
        Creditos creditos = new Creditos();

        int resultado;

        if(cliente.equals("Axel"))
        {
            if (credito.equals("Credito Hipotecario"))
            {
                resultado = (clientes.getAxel() + creditos.getcHipotecario()) / creditos.getcuotaCH();
                textv.setText("El valor de sus cuotas es de: $" + resultado);
            }
            if (credito.equals("Credito Automotriz"))
            {
                resultado = (clientes.getAxel() + creditos.getcAutomotriz()) / creditos.getcuotaCA();
                textv.setText("El valor de sus cuotas es de: $" + resultado);
            }
        }
        if(cliente.equals("Roxana"))
        {
            if (credito.equals("Credito Hipotecario"))
            {
                resultado = (clientes.getRoxana() + creditos.getcHipotecario()) / creditos.getcuotaCH();
                textv.setText("El valor de sus cuotas es de: $" + resultado);
            }
            if (credito.equals("Credito Automotriz"))
            {
                resultado = (clientes.getRoxana() + creditos.getcAutomotriz()) / creditos.getcuotaCA();
                textv.setText("El valor de sus cuotas es de: $" + resultado);
            }
        }
    }
}