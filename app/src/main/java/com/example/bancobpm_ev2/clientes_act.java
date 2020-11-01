package com.example.bancobpm_ev2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import Clases.AdminSQLiteOpenHelper;

public class clientes_act extends AppCompatActivity
{
    private EditText nombre,codigo,salario;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clientes_act);

        nombre=(EditText)findViewById(R.id.edname);
        codigo=(EditText)findViewById(R.id.edcode);
        salario=(EditText)findViewById(R.id.edsalario);
    }

    public void Anadir(View v)
    {
        AdminSQLiteOpenHelper admin= new AdminSQLiteOpenHelper(this, "datosclientes",null,1);
        SQLiteDatabase db = admin.getWritableDatabase();

        if(!codigo.getText().toString().isEmpty())
        {
            ContentValues registro = new ContentValues();

            registro.put("codigo", codigo.getText().toString());
            registro.put("nombre", nombre.getText().toString());
            registro.put("salario", salario.getText().toString());

            db.insert("clientes",null, registro);
            db.close();
            Toast.makeText(this,"Se han guardado los datos del cliente",Toast.LENGTH_LONG).show();
            codigo.setText("");
            nombre.setText("");
            salario.setText("");
        }
        else
        {
            Toast.makeText(this,"Debe ingresar un codigo",Toast.LENGTH_LONG).show();
            codigo.setText("");
            nombre.setText("");
            salario.setText("");
        }
    }

    public void Mostrar(View v)
    {
        AdminSQLiteOpenHelper admin= new AdminSQLiteOpenHelper(this, "datosclientes",null,1);
        SQLiteDatabase bd = admin.getWritableDatabase();

        String id=codigo.getText().toString();
        if(!id.isEmpty())
        {
            Cursor fila = bd.rawQuery("SELECT nombre, salario FROM clientes WHERE codigo="+id, null);
            if(fila.moveToFirst())
            {
                nombre.setText(fila.getString(0));
                salario.setText(fila.getString(1));
            }
            else
            {
                Toast.makeText(this,"El codigo seleccionado no existe",Toast.LENGTH_LONG).show();
            }
        }
        else
        {
            Toast.makeText(this,"Debe ingresar el código del cliente",Toast.LENGTH_LONG).show();
        }
    }

    public void Eliminar(View v)
    {
        AdminSQLiteOpenHelper admin= new AdminSQLiteOpenHelper(this, "datosclientes",null,1);
        SQLiteDatabase bd = admin.getWritableDatabase();

        String id=codigo.getText().toString();

        if(!id.isEmpty())
        {
            bd.delete("clientes","codigo="+id,null);
            bd.close();
            Toast.makeText(this,"El cliente ha sido borrado de la base de datos",Toast.LENGTH_LONG).show();
        }
        else
        {
            Toast.makeText(this,"Ingrese un codigo valido",Toast.LENGTH_LONG).show();
        }
    }

    public void Actualizar(View v)
    {
        AdminSQLiteOpenHelper admin= new AdminSQLiteOpenHelper(this, "datosclientes",null,1);
        SQLiteDatabase bd = admin.getWritableDatabase();

        String id=codigo.getText().toString();

        ContentValues cont= new ContentValues();

        cont.put("codigo", codigo.getText().toString());
        cont.put("nombre", nombre.getText().toString());
        cont.put("salario", salario.getText().toString());

        if(!id.isEmpty())
        {
            bd.update("clientes", cont, "codigo="+id,null);
            Toast.makeText(this,"Los datos se han actualizado",Toast.LENGTH_LONG).show();
            codigo.setText("");
            nombre.setText("");
            salario.setText("");
        }
        else
        {
            Toast.makeText(this,"Ingrese un codigo valido",Toast.LENGTH_LONG).show();
        }
    }
}