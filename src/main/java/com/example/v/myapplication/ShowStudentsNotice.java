package com.example.v.myapplication;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
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
import java.util.HashMap;

public class ShowStudentsNotice extends AppCompatActivity
{
    ListView lv;
    EditText t1;
    String result="",title,content,uemail,em,branc,yr;
    String data[]=null;
    ArrayAdapter<String> adapter=null;
    ArrayList<HashMap<String,String>> studentlist;
    String email[]=null;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) 
    {
        SharedPreferences pref= getSharedPreferences("mypref",MODE_PRIVATE);
        uemail=pref.getString("email","no email defined");

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_students_notice);
        init_adapter();

        if(adapter!=null) {
            t1.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                }

                @Override
                public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                    ShowStudentsNotice.this.adapter.getFilter().filter(charSequence);

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
            final BufferedReader br = new BufferedReader(new InputStreamReader(in,"iso-8859-1"),8);

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
            //Toast.makeText(ShowStudentsNotice.this, result+"/"+result.length(), Toast.LENGTH_LONG).show();

            try
            {
                if(result.length()>0) {
                    JSONArray jarray = new JSONArray(result);
                    int len = jarray.length();
                    if (len > 0) {
                        data = new String[len];

                        final String email[] = new String[len];
                        final String branch[] = new String[len];
                        final String year[] = new String[len];



                        for (int i = 0; i < jarray.length(); i++) {

                            JSONObject obj = jarray.getJSONObject(i);
                            String c = obj.getString("email");
                            String d = obj.getString("branch");
                            String e = obj.getString("year");
                            email[i] = c;
                            branch[i] = d;
                            year[i] = e;
                            String text = obj.getString("email") + "\n" + obj.getString("name") + "\n" + obj.getString("roll");
                            data[i] = text;
                        }


                        lv = (ListView) findViewById(R.id.show_s_n_l1);
                        t1 = (EditText) findViewById(R.id.show_s_n_t1);
                        if (data.length > 0) {
                            adapter = new ArrayAdapter<String>(ShowStudentsNotice.this, R.layout.list_item, R.id.list_item_t1, data);
                            lv.setAdapter(adapter);
                            lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                @Override
                                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                                    em = email[i];
                                    branc = branch[i];
                                    yr = year[i];

                                    LayoutInflater li=LayoutInflater.from(ShowStudentsNotice.this);
                                    View v1=li.inflate(R.layout.add_notice,null);
                                    AlertDialog.Builder adb=new AlertDialog.Builder(ShowStudentsNotice.this);
                                    adb.setView(v1);
                                    adb.setCancelable(false);
                                    final EditText et1=(EditText)v1.findViewById(R.id.add_n_t1);
                                    final EditText et2=(EditText)v1.findViewById(R.id.add_n_t2);
                                    adb.setPositiveButton("add", new DialogInterface.OnClickListener()
                                    {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which)
                                        {
                                            title=et1.getText().toString();
                                            content=et2.getText().toString();
                                            notice_connection();
                                            Intent i1=new Intent(ShowStudentsNotice.this,admin_profile.class);
                                            startActivity(i1);
                                        }
                                    });
                                    adb.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {
                                          dialog.cancel();
                                        }
                                    });
                                    adb.create();
                                    adb.show();
                                }

                            });
                        } else {
                            Toast.makeText(ShowStudentsNotice.this, "No data", Toast.LENGTH_LONG).show();
                        }


                    }

                }
                else
                {
                    Toast.makeText(ShowStudentsNotice.this, "No Data Found !!", Toast.LENGTH_SHORT).show();
                }
            }
            catch(Exception e)
            {
                Toast.makeText(ShowStudentsNotice.this, "JSON : "+e, Toast.LENGTH_LONG).show();
            }

        }
        catch(Exception e)
        {
            Toast.makeText(ShowStudentsNotice.this, "Error : "+e, Toast.LENGTH_LONG).show();
        }

    }
    protected void notice_connection()
    {
        ArrayList<NameValuePair> list=new ArrayList<NameValuePair>();
        list.add(new BasicNameValuePair("title",title));
        list.add(new BasicNameValuePair("content",content));
        list.add(new BasicNameValuePair("branch",branc));
        list.add(new BasicNameValuePair("year",yr));
        list.add(new BasicNameValuePair("by",uemail));
        list.add(new BasicNameValuePair("semail",em));

        try {


            HttpClient httpclient = new DefaultHttpClient();

            HttpPost httppost = new HttpPost("http://10.0.2.2/b253/add_notice2.php");
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
            Toast.makeText(ShowStudentsNotice.this, result, Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            Toast.makeText(ShowStudentsNotice.this, "Error : " + e, Toast.LENGTH_LONG).show();
        }
    }
}

