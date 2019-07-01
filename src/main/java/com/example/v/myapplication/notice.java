package com.example.v.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.TextView;
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
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

public class notice extends AppCompatActivity {
    String result,content,title,id,date,by="Uploded By - ",fulldate,fulltime,dt="Uploded On - ";
    TextView t1,t2,t3,t4;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notice);

        id=getIntent().getExtras().getString("id");
        title=getIntent().getExtras().getString("title");
        make_connection_to_php();
        t1=(TextView)findViewById(R.id.show_n_t1);
        t2=(TextView)findViewById(R.id.show_n_t2);
        t3=(TextView)findViewById(R.id.show_n_t3);
        t4=(TextView)findViewById(R.id.show_n_t4);
        timestamp_to_date();
        t1.setText(title);
        t2.setText(content);
        t3.setText(dt);
        t4.setText(by);

    }

    protected void make_connection_to_php()
    {


        ArrayList<NameValuePair> list=new ArrayList<NameValuePair>();
        list.add(new BasicNameValuePair("id",id));

        try {


            HttpClient httpclient = new DefaultHttpClient();

            HttpPost httppost = new HttpPost("http://10.0.2.2/b253/show_notice.php");
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


            try
            {
                JSONArray jarray=new JSONArray(result);

                for(int i=0; i<jarray.length(); i++)
                {

                    JSONObject obj=jarray.getJSONObject(i);
                    date=obj.getString("ndate");
                    content=obj.getString("content");
                    by=by+obj.getString("nby");


                }

            }
            catch(Exception e)
            {
                Toast.makeText(notice.this, "JSON : "+e, Toast.LENGTH_LONG).show();
            }

        } catch (Exception e) {
            Toast.makeText(notice.this, "Error : " + e, Toast.LENGTH_LONG).show();
        }

    }
    protected void timestamp_to_date()
    {
        long ms=Long.parseLong(date);
        ms=ms*1000;
        DateFormat formatter=new SimpleDateFormat("dd/MM/yyyy");
        DateFormat formatter1=new SimpleDateFormat("HH:mm:ss");
        Calendar calendar=Calendar.getInstance();
        calendar.setTimeInMillis(ms);
        fulldate=formatter.format(calendar.getTime());
        fulltime=formatter1.format(calendar.getTime());
        dt=dt+" "+fulldate+" "+fulltime;

    }
}
