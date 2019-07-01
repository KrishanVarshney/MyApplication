package com.example.v.myapplication;

import android.content.Intent;
import android.content.SharedPreferences;
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
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Login extends AppCompatActivity {
    String email,password,result;
    Button b1;
    EditText t1,t2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        StrictMode.enableDefaults();
        b1=(Button)findViewById(R.id.login_b1);
        t1=(EditText)findViewById(R.id.login_t1);
        t2=(EditText)findViewById(R.id.login_t2);

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

             email=t1.getText().toString();
            //Toast.makeText(Login.this, email, Toast.LENGTH_SHORT).show();
            password=t2.getText().toString();

                ArrayList<NameValuePair> list=new ArrayList<NameValuePair>();
                list.add(new BasicNameValuePair("email",email));
                list.add(new BasicNameValuePair("password",password));

                try
                {
                    HttpClient httpclient=new DefaultHttpClient();

                    HttpPost httppost= new HttpPost("http://10.0.2.2/b253/login.php");

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
                    result=sb.toString();
                    br.close();

                    if(result!=null) result=result.trim();

                    if(result.equalsIgnoreCase("Failure"))
                    {
                        Toast.makeText(Login.this, "unsuccessfull ", Toast.LENGTH_SHORT).show();
                    }
                    else
                    {
                        SharedPreferences sp=getSharedPreferences("mypref",MODE_PRIVATE);
                        SharedPreferences.Editor editor=sp.edit();

                        if(result.equalsIgnoreCase("admin"))
                        {
                            Toast.makeText(Login.this, "Welcome ADMIN", Toast.LENGTH_SHORT).show();
                            editor.clear();
                            editor.putString("email",email);
                            editor.commit();
                            Intent i1=new Intent(Login.this,admin_profile.class);
                            //i1.putExtra("email",email);
                            startActivity(i1);
                        }
                        else if(result.equalsIgnoreCase("student"))
                        {
                            Toast.makeText(Login.this, "Welcome STUDENT", Toast.LENGTH_SHORT).show();
                            editor.clear();
                            editor.putString("email",email);
                            editor.commit();
                            Intent i1=new Intent(Login.this,student_profile.class);
                            //i1.putExtra("email",email);
                            startActivity(i1);

                        }
                    }




                }
                catch(Exception e)
                {
                    Toast.makeText(Login.this, "Error : "+e, Toast.LENGTH_LONG).show();
                }

            }
        });

    }
}
