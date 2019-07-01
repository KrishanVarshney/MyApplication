package com.example.v.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.SearchView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
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

public class show_student_data extends AppCompatActivity {
    ListView lv;
    EditText t1;
    String result="";
    String data[]=null;
    ArrayAdapter<String> adapter=null;
    ArrayList<HashMap<String,String>> studentlist;
    String email[]=null;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_student_data);


        init_adapter();

        if(adapter!=null) {
            t1.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                }

                @Override
                public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                    show_student_data.this.adapter.getFilter().filter(charSequence);

                }

                @Override
                public void afterTextChanged(Editable editable) {

                }
            });
        }

    }
    private void init_adapter()
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
            if(result!=null)
                result=result.trim();
            //Toast.makeText(show_student_data.this, result+"/"+result.length(), Toast.LENGTH_LONG).show();

            try
            {
                if(result.length()>0) {
                    JSONArray jarray = new JSONArray(result);
                    int len = jarray.length();
                    if (len > 0) {
                        data = new String[len];

                        final String email[] = new String[len];


                        for (int i = 0; i < jarray.length(); i++) {

                            JSONObject obj = jarray.getJSONObject(i);
                            String c = obj.getString("email");
                            email[i] = c;
                            String text = obj.getString("email") + "\n" + obj.getString("name") + "\n" + obj.getString("roll");
                            data[i] = text;
                        }


                        lv = (ListView) findViewById(R.id.show_s_l1);
                        t1 = (EditText) findViewById(R.id.show_s_t1);
                        if (data.length > 0) {
                            adapter = new ArrayAdapter<String>(show_student_data.this, R.layout.list_item, R.id.list_item_t1, data);
                            lv.setAdapter(adapter);
                            lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                @Override
                                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                                    String em = email[i];
                                    Intent i1 = new Intent(show_student_data.this, edit_student_data.class);
                                    i1.putExtra("eemail", em);
                                    startActivity(i1);
                                }

                            });
                        } else {
                            Toast.makeText(show_student_data.this, "No data", Toast.LENGTH_LONG).show();
                        }


                    }

                }
                else
                {
                    Toast.makeText(show_student_data.this, "No Data Found !!", Toast.LENGTH_SHORT).show();
                }
            }
            catch(Exception e)
            {
                Toast.makeText(show_student_data.this, "JSON : "+e, Toast.LENGTH_LONG).show();
            }

        }
        catch(Exception e)
        {
            Toast.makeText(show_student_data.this, "Error : "+e, Toast.LENGTH_LONG).show();
        }

    }
}
