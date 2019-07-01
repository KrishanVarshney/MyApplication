package com.example.v.myapplication;

import android.content.Intent;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    EditText t1,t2,t3,t4,t5,t6,t7;
    Button b1;

    String result="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        StrictMode.enableDefaults();

        t1= (EditText) findViewById(R.id.main_t1);
        t2= (EditText) findViewById(R.id.main_t2);
        t3= (EditText) findViewById(R.id.main_t3);
        t4= (EditText) findViewById(R.id.main_t4);
        t5= (EditText) findViewById(R.id.main_t5);
        t6= (EditText) findViewById(R.id.main_t6);
        t7= (EditText) findViewById(R.id.main_t8);


        b1= (Button) findViewById(R.id.main_b1);


        b1.setOnClickListener(new A());

    }
    class A implements View.OnClickListener
    {
        @Override
        public void onClick(View view) {
            String name,branch,roll,contact,email,password,year;
            name=t1.getText().toString();
            branch=t2.getText().toString();
            year=t7.getText().toString();
            roll=t3.getText().toString();
            contact=t4.getText().toString();
            email=t5.getText().toString();
            password=t6.getText().toString();

            ArrayList <NameValuePair> list=new ArrayList<NameValuePair>();
            list.add(new BasicNameValuePair("name",name));
            list.add(new BasicNameValuePair("branch",branch));
            list.add(new BasicNameValuePair("year",year));
            list.add(new BasicNameValuePair("roll",roll));
            list.add(new BasicNameValuePair("contact",contact));
            list.add(new BasicNameValuePair("email",email));
            list.add(new BasicNameValuePair("password",password));

            try
            {
                HttpClient httpclient=new DefaultHttpClient();

                HttpPost httppost= new HttpPost("http://10.0.2.2/b253/insert_data.php");

                httppost.setEntity(new UrlEncodedFormEntity(list));

                HttpResponse response=httpclient.execute(httppost);

                HttpEntity entity=response.getEntity();
                InputStream in=entity.getContent();
                BufferedReader br = new BufferedReader(new InputStreamReader(in,"iso-8859-1"),8);

                StringBuilder sb = new StringBuilder();
                String line=null;
                while ((line = br.readLine()) != null)
                {
                    sb.append(line+"\n");
                }
                br.close();
                result=sb.toString();
                if(result!=null) result=result.trim();
                Toast.makeText(MainActivity.this, result, Toast.LENGTH_LONG).show();

            }
            catch(Exception e)
            {
                Toast.makeText(MainActivity.this, "Error : "+e, Toast.LENGTH_LONG).show();
            }
            Intent i1=new Intent(MainActivity.this,admin_profile.class);
            startActivity(i1);
        }
    }
}
