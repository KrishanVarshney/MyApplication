package com.example.v.myapplication;

import android.content.Intent;
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

public class add_admin extends AppCompatActivity {
    Button b1;
    EditText t1,t2,t3,t4;
    String result,name,contact,email,password;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_admin);
        b1=(Button)findViewById(R.id.add_admin_b1);
        t1=(EditText)findViewById(R.id.add_admin_t1);
        t2=(EditText)findViewById(R.id.add_admin_t2);
        t3=(EditText)findViewById(R.id.add_admin_t3);
        t4=(EditText)findViewById(R.id.add_admin_t4);

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                name=t1.getText().toString();
                contact=t2.getText().toString();
                email=t3.getText().toString();
                password=t4.getText().toString();

                make_connection_to_php();
                Intent i1=new Intent(add_admin.this,admin_profile.class);
                startActivity(i1);

            }
        });

    }
    protected void make_connection_to_php()
    {


        try {
            ArrayList<NameValuePair> list=new ArrayList<NameValuePair>();
            list.add(new BasicNameValuePair("name",name));
            list.add(new BasicNameValuePair("contact",contact));
            list.add(new BasicNameValuePair("email",email));
            list.add(new BasicNameValuePair("password",password));



            HttpClient httpclient = new DefaultHttpClient();

            HttpPost httppost = new HttpPost("http://10.0.2.2/b253/admin_insert_data.php");

            httppost.setEntity(new UrlEncodedFormEntity(list));

            HttpResponse response = httpclient.execute(httppost);

            HttpEntity entity = response.getEntity();
            InputStream in = entity.getContent();
            BufferedReader br = new BufferedReader(new InputStreamReader(in, "iso-8859-1"), 8);

            StringBuilder sb = new StringBuilder();
            String line = null;
            while ((line = br.readLine()) != null) {
                sb.append(line + "\n");
            }
            result = sb.toString();
            br.close();

            if (result != null) result = result.trim();
            Toast.makeText(add_admin.this, result, Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            Toast.makeText(add_admin.this, "Error : " + e, Toast.LENGTH_LONG).show();
        }

    }
}
