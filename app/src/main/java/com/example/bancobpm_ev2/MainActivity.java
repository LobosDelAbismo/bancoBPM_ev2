package com.example.bancobpm_ev2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity
{
    private EditText txt1,txt2;
    private TextView tv;
    private ProgressBar barraprogreso;
    private Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        txt1=(EditText) findViewById(R.id.edcode);
        txt2=(EditText) findViewById(R.id.edpass);
        tv=(TextView) findViewById(R.id.tverror);
        btn=(Button)findViewById(R.id.blogin) ;
        barraprogreso= (ProgressBar)findViewById(R.id.bp);
        barraprogreso.setVisibility(View.INVISIBLE);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String user, password;
                user=txt1.getText().toString();
                password=txt2.getText().toString();
                user=user.toLowerCase();

                if (user.equals("android")  && (password.equals("123")))
                {

                    new Task().execute();
                }
                else
                {
                    tv.setText("Usuario o contraseña incorrecta, intentelo nuevamente");
                }
            }
        });
    }

    class Task extends AsyncTask<String, Void, String>
    {
        @Override
        protected void onPreExecute()
        {
            barraprogreso.setVisibility(View.VISIBLE);
        }

        @Override
        protected String doInBackground(String... strings) {
            for(int i=1; i<=10; i++)
            {
                try
                {
                    Thread.sleep(500);
                }
                catch (Exception e)
                {
                    e.printStackTrace();
                }
            }
            return null;
        }

        @Override
        protected void onPostExecute(String s)
        {
            Intent i = new Intent(getBaseContext(), menu_act.class);
            startActivity(i);
        }
    }
}