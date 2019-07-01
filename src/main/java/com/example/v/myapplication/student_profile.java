package com.example.v.myapplication;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
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
import java.util.ArrayList;

public class student_profile extends AppCompatActivity {
TextView t1,t2;
    ListView l1;
    String email,result,old,newp,confirm,data[]=null,nid[]=null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_profile);
        t1=(TextView)findViewById(R.id.s_p_1);
        t2=(TextView)findViewById(R.id.s_p_2);
        l1=(ListView)findViewById(R.id.student_profile_l1);
        //email=getIntent().getExtras().getString("email");
        SharedPreferences pref= getSharedPreferences("mypref",MODE_PRIVATE);
        email=pref.getString("email","no email defined");

        make_connection_to_php();
        profile_notice_connection();

    }

    public boolean onCreateOptionsMenu(Menu menu)
    {
        super.onCreateOptionsMenu(menu);
        MenuInflater obj=getMenuInflater();
        obj.inflate(R.menu.student_menu,menu);
        return  true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // return super.onOptionsItemSelected(item);
        switch (item.getItemId())
        {
            case R.id.student_item1:
                change_password();
                return true;

            case R.id.student_item2:
                logout();
                return true;



        }
        return  false;
    }
    protected  void change_password()
    {
        LayoutInflater li=LayoutInflater.from(student_profile.this);
        View v1=li.inflate(R.layout.change_password,null);
        AlertDialog.Builder adb=new AlertDialog.Builder(student_profile.this);
        adb.setView(v1);
        adb.setCancelable(false);

        final EditText tv1=(EditText)v1.findViewById(R.id.change_t1);
        final EditText tv2=(EditText)v1.findViewById(R.id.change_t2);
        final EditText tv3=(EditText)v1.findViewById(R.id.change_t3);
        adb.setPositiveButton("Change", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i)
            {

                String old=tv1.getText().toString();
                String newp=tv2.getText().toString();
                String confirm=tv3.getText().toString();


                ArrayList<NameValuePair> list=new ArrayList<NameValuePair>();
                list.add(new BasicNameValuePair("email",email));
                list.add(new BasicNameValuePair("old",old));
                list.add(new BasicNameValuePair("newp",newp));
                list.add(new BasicNameValuePair("confirm",confirm));

                try {


                    HttpClient httpclient = new DefaultHttpClient();

                    HttpPost httppost = new HttpPost("http://10.0.2.2/b253/change_password.php");
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
                    Toast.makeText(student_profile.this, result, Toast.LENGTH_SHORT).show();

                    if (result != null) result = result.trim();

                }
                catch (Exception e)
                {
                    Toast.makeText(student_profile.this, "Error : " + e, Toast.LENGTH_LONG).show();
                }
            }
        });
        adb.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i)
            {
                dialogInterface.cancel();
            }
        });
        adb.create();
        adb.show();

    }

    public void logout()
    {
        AlertDialog.Builder adb=new AlertDialog.Builder(student_profile.this);
        adb.setMessage("Are you sure..??");
        adb.setCancelable(false);
        adb.setPositiveButton("Logout", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                SharedPreferences pref=getSharedPreferences("mypref",MODE_PRIVATE);
                SharedPreferences.Editor editor=pref.edit();
                editor.remove("email");
                editor.commit();
                Intent i1=new Intent(student_profile.this,Login.class);
                startActivity(i1);

            }
        });
        adb.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.cancel();
            }
        });
        adb.create();
        adb.show();
    }
    protected void make_connection_to_php()
    {


        ArrayList<NameValuePair> list=new ArrayList<NameValuePair>();
        list.add(new BasicNameValuePair("email",email));

        try {


            HttpClient httpclient = new DefaultHttpClient();

            HttpPost httppost = new HttpPost("http://10.0.2.2/b253/student_fetch.php");
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
            t1.setText(result);
            t2.setText("eamil - "+email);
        } catch (Exception e) {
            Toast.makeText(student_profile.this, "Error : " + e, Toast.LENGTH_LONG).show();
        }

    }

    protected void profile_notice_connection()
    {
        ArrayList<NameValuePair> list=new ArrayList<NameValuePair>();
        list.add(new BasicNameValuePair("email",email));


        try
        {
            HttpClient httpclient=new DefaultHttpClient();

            HttpPost httppost= new HttpPost("http://10.0.2.2/b253/notice_fetch_student.php");
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
            if(result!=null)
                result=result.trim();
            //Toast.makeText(ShowData.this, result, Toast.LENGTH_LONG).show();

            try
            {
                JSONArray jarray=new JSONArray(result);
                data=new String[jarray.length()];
                nid=new String[jarray.length()];
                for(int i=0; i<jarray.length(); i++)
                {

                    JSONObject obj=jarray.getJSONObject(i);
                    String title=obj.getString("title");
                    String id=obj.getString("nid");
                    nid[i]=id;
                    data[i]=title;

                }
                ArrayAdapter<String >adapter=new ArrayAdapter<String>(student_profile.this,android.R.layout.simple_list_item_1,android.R.id.text1,data);
                l1.setAdapter(adapter);
                l1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                        Intent ii=new Intent(student_profile.this,notice.class);
                        String ti=nid[i];
                        String t=data[i];
                        ii.putExtra("id",ti);
                        ii.putExtra("title",t);
                        startActivity(ii);

                    }
                });



            }
            catch(Exception e)
            {
                Toast.makeText(student_profile.this, "JSON : "+e, Toast.LENGTH_LONG).show();
            }

        }
        catch(Exception e)
        {
            Toast.makeText(student_profile.this, "Error : "+e, Toast.LENGTH_LONG).show();
        }
    }


}
