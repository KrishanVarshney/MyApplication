package com.example.v.myapplication;

import android.content.Intent;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

public class ShowData extends AppCompatActivity {

    Button b1;
    ListView lst1;
    String result="";
    String data[]=null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_data);

        StrictMode.enableDefaults();

        b1= (Button) findViewById(R.id.showdata_b1);
        lst1= (ListView) findViewById(R.id.showdata_lst1);

        show_data_in_list();

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i1=new Intent(ShowData.this,MainActivity.class);
                startActivity(i1);
            }
        });
    }
    private void show_data_in_list()
    {
        try
        {
            HttpClient httpclient=new DefaultHttpClient();

            HttpPost httppost= new HttpPost("http://10.0.2.2/b253/ShowData.php");

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
            //Toast.makeText(ShowData.this, result, Toast.LENGTH_LONG).show();

            try
            {
                JSONArray jarray=new JSONArray(result);
                data=new String[jarray.length()];

                for(int i=0; i<jarray.length(); i++)
                {
                    JSONObject obj=jarray.getJSONObject(i);
                    String text=obj.getString("name")+"\n"+obj.getString("branch")+"\n"+obj.getString("roll")+"\n"+obj.getString("contact")+"\n"+obj.getString("email");
                    data[i]=text;
                }
                ArrayAdapter<String> adapter=new ArrayAdapter<String>(ShowData.this,android.R.layout.simple_list_item_1,android.R.id.text1,data);
                lst1.setAdapter(adapter);

            }
            catch(Exception e)
            {
                Toast.makeText(ShowData.this, "JSON : "+e, Toast.LENGTH_LONG).show();
            }

        }
        catch(Exception e)
        {
            Toast.makeText(ShowData.this, "Error : "+e, Toast.LENGTH_LONG).show();
        }

    }
}
