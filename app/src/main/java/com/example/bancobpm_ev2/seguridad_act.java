package com.example.bancobpm_ev2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Base64;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.security.MessageDigest;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

public class seguridad_act extends AppCompatActivity
{
    private EditText pass;
    private TextView securepass;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seguridad_act);

        pass=(EditText)findViewById(R.id.edpass);
        securepass=(TextView)findViewById(R.id.tvscript);
    }

    private SecretKeySpec generateKey(String password)throws Exception
    {
        MessageDigest sha=MessageDigest.getInstance("SHA-256");
        byte[] key=password.getBytes("UTF-8");
        key=sha.digest(key);
        SecretKeySpec secretKey= new SecretKeySpec(key, "AES");
        return secretKey;
    }

    private String encriptar(String datos, String password)throws Exception
    {
        if(!pass.getText().toString().isEmpty())
        {
            SecretKeySpec secretKey =generateKey(password);
            Cipher cipher= Cipher.getInstance("AES");
            cipher.init(Cipher.ENCRYPT_MODE, secretKey);

            byte[] datosEncriptadosBytes=cipher.doFinal(datos.getBytes());
            String datosEncriptadosString= Base64.encodeToString(datosEncriptadosBytes,Base64.DEFAULT);
            return datosEncriptadosString;
        }
        else
        {
            Toast.makeText(this, "Debe ingresar una clave", Toast.LENGTH_LONG).show();
            return null;
        }
    }

    public void encriptar(View v)
    {
        try
        {
            String mensaje=encriptar(pass.getText().toString(), securepass.getText().toString());
            securepass.setText(mensaje);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}