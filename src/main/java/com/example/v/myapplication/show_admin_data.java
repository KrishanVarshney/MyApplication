package com.example.v.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;

public class show_admin_data extends AppCompatActivity {
    ListView lv;
    EditText t1;
    String result="";
    String data[]=null;
    ArrayAdapter<String> adapter;
    ArrayList<HashMap<String,String>> studentlist;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_admin_data);
        show_data_in_list();
        lv=(ListView)findViewById(R.id.show_a_l1);
        t1=(EditText)findViewById(R.id.show_a_t1);
        adapter=new ArrayAdapter<String>(show_admin_data.this,R.layout.list_item,R.id.list_item_t1,data);
        lv.setAdapter(adapter);
        t1.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2)
            {
                show_admin_data.this.adapter.getFilter().filter(charSequence);

            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }
    private void show_data_in_list()
    {
        try
        {
            HttpClient httpclient=new DefaultHttpClient();

            HttpPost httppost= new HttpPost("http://10.0.2.2/b253/show_admin_list.php");

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
                    String text=obj.getString("name")+"\n"+obj.getString("contact")+"\n"+obj.getString("email");
                    data[i]=text;
                }

            }
            catch(Exception e)
            {
                Toast.makeText(show_admin_data.this, "JSON : "+e, Toast.LENGTH_LONG).show();
            }

        }
        catch(Exception e)
        {
            Toast.makeText(show_admin_data.this, "Error : "+e, Toast.LENGTH_LONG).show();
        }

    }
}
