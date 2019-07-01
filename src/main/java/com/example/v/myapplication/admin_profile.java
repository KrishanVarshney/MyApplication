package com.example.v.myapplication;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.FileUriExposedException;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Switch;
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

import static android.R.attr.breadCrumbShortTitle;
import static android.R.attr.id;

public class admin_profile extends AppCompatActivity
{
    int flag;
    TextView t1,t2;
    Button b1,b2,b3,b4;
    ListView l1;
    String result,email,name,branch,year,contact,title,content,data[]=null,nid[]=null;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_profile);
        t1=(TextView)findViewById(R.id.admin_t1);
        t2=(TextView)findViewById(R.id.admin_t2);

        b1=(Button)findViewById(R.id.admin_b1);//add student
        b2=(Button)findViewById(R.id.admin_b2);//add admin
        b3=(Button) findViewById(R.id.admin_b3);//view admin
        b4=(Button)findViewById(R.id.admin_b4);//view student

        l1=(ListView)findViewById(R.id.admin_list);
        profile_notice_connection();


       // email=getIntent().getExtras().getString("email");
        SharedPreferences pref= getSharedPreferences("mypref",MODE_PRIVATE);
        email=pref.getString("email","no email defined");

        make_connection_to_php();
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(admin_profile.this,MainActivity.class);
                startActivity(i);
            }
        });
        b2.setOnClickListener(new add_admins());

        b3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(admin_profile.this,show_admin_data.class);
                startActivity(i);

            }
        });

        b4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent i=new Intent(admin_profile.this,show_student_data.class);
                startActivity(i);

            }
        });



    }
    class add_admins implements View.OnClickListener
    {


        @Override
        public void onClick(View view)
        {

           Intent i1=new Intent(admin_profile.this,add_admin.class);
            startActivity(i1);
        }
    }
    public boolean onCreateOptionsMenu(Menu menu)
    {
        super.onCreateOptionsMenu(menu);
        MenuInflater obj=getMenuInflater();
        obj.inflate(R.menu.admin_menu,menu);
        return  true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
       // return super.onOptionsItemSelected(item);
        switch (item.getItemId())
        {
            case R.id.item1:
                change_password();
                return true;
            case R.id.item2:
                add_notice();
                return true;
            case R.id.item3:
                logout();
                return true;



        }
        return  false;
    }


    public void add_notice()
    {
      LayoutInflater li=LayoutInflater.from(admin_profile.this);
        View v1=li.inflate(R.layout.choice_add_notice,null);
        AlertDialog.Builder adb=new AlertDialog.Builder(admin_profile.this);
        adb.setView(v1);
        adb.setCancelable(false);
        final Spinner s1=(Spinner) v1.findViewById(R.id.choice_s1);
        ArrayAdapter<CharSequence> adapter=ArrayAdapter.createFromResource(admin_profile.this,R.array.choice,android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        s1.setAdapter(adapter);
        s1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id)
            {
                switch (position)
                {
                    case 0:
                        flag=0;
                        break;
                    case 1:
                        flag=1;
                        break;
                    case 2:
                        flag=2;
                        break;

                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent)
            {

            }
        });
        adb.setPositiveButton("Add", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i)
            {
                if(flag==0)
                {
                    Intent i1=new Intent(admin_profile.this,ShowStudentsNotice.class);
                    startActivity(i1);
                }
                else if(flag==1)
                {
                    BranchWise();

                }
                else if(flag==2)
                {
                    all();

                }




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
    public void logout()
    {
        AlertDialog.Builder adb=new AlertDialog.Builder(admin_profile.this);
        adb.setMessage("Are you sure..??");
        adb.setCancelable(false);
        adb.setPositiveButton("Logout", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                SharedPreferences pref=getSharedPreferences("mypref",MODE_PRIVATE);
                SharedPreferences.Editor editor=pref.edit();
                editor.remove("email");
                editor.commit();
                Intent i1=new Intent(admin_profile.this,Login.class);
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
    public void change_password()
    {
        LayoutInflater li=LayoutInflater.from(admin_profile.this);
        View v1=li.inflate(R.layout.change_password,null);
        AlertDialog.Builder adb=new AlertDialog.Builder(admin_profile.this);
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
                    Toast.makeText(admin_profile.this, result, Toast.LENGTH_SHORT).show();

                    if (result != null) result = result.trim();

                }
                catch (Exception e)
                {
                    Toast.makeText(admin_profile.this, "Error : " + e, Toast.LENGTH_LONG).show();
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
    protected void make_connection_to_php()
    {


        ArrayList<NameValuePair> list=new ArrayList<NameValuePair>();
        list.add(new BasicNameValuePair("email",email));

        try {


            HttpClient httpclient = new DefaultHttpClient();

            HttpPost httppost = new HttpPost("http://10.0.2.2/b253/admin_fetch.php");
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
            t2.setText(email);
        } catch (Exception e) {
            Toast.makeText(admin_profile.this, "Error : " + e, Toast.LENGTH_LONG).show();
        }

    }
    //for branch
    protected void notice_connection()
    {
        ArrayList<NameValuePair> list=new ArrayList<NameValuePair>();
        list.add(new BasicNameValuePair("title",title));
        list.add(new BasicNameValuePair("content",content));
        list.add(new BasicNameValuePair("year",year));
        list.add(new BasicNameValuePair("branch",branch));
        list.add(new BasicNameValuePair("by",email));

        try {


            HttpClient httpclient = new DefaultHttpClient();

            HttpPost httppost = new HttpPost("http://10.0.2.2/b253/add_notice3.php");
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
            Toast.makeText(admin_profile.this, result, Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            Toast.makeText(admin_profile.this, "Error : " + e, Toast.LENGTH_LONG).show();
        }
    }
    //for all
    protected void notice_connection_all()
    {
        ArrayList<NameValuePair> list=new ArrayList<NameValuePair>();
        list.add(new BasicNameValuePair("title",title));
        list.add(new BasicNameValuePair("content",content));
        list.add(new BasicNameValuePair("by",email));

        try {


            HttpClient httpclient = new DefaultHttpClient();

            HttpPost httppost = new HttpPost("http://10.0.2.2/b253/add_notice.php");
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
            Toast.makeText(admin_profile.this, result, Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            Toast.makeText(admin_profile.this, "Error : " + e, Toast.LENGTH_LONG).show();
        }
    }
    protected void profile_notice_connection()
    {


        try
        {
            HttpClient httpclient=new DefaultHttpClient();

            HttpPost httppost= new HttpPost("http://10.0.2.2/b253/notice_fetch.php");

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
                ArrayAdapter<String >adapter=new ArrayAdapter<String>(admin_profile.this,android.R.layout.simple_list_item_1,android.R.id.text1,data);
                l1.setAdapter(adapter);
                l1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                        Intent ii=new Intent(admin_profile.this,notice.class);
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
                Toast.makeText(admin_profile.this, "JSON : "+e, Toast.LENGTH_LONG).show();
            }

        }
        catch(Exception e)
        {
            Toast.makeText(admin_profile.this, "Error : "+e, Toast.LENGTH_LONG).show();
        }
    }
    protected void BranchWise()
    {
        LayoutInflater li1=LayoutInflater.from(admin_profile.this);
        View v2= li1.inflate(R.layout.add_notice_branchwise,null);
        AlertDialog.Builder addb=new AlertDialog.Builder(admin_profile.this);
        addb.setView(v2);
        addb.setCancelable(false);
        final Spinner ss1=(Spinner)v2.findViewById(R.id.a_n_b_w_s1);
        final Spinner ss2=(Spinner)v2.findViewById(R.id.a_n_b_w_s2);
        final EditText et1=(EditText)v2.findViewById(R.id.a_n_b_w_t1);
        final EditText et2=(EditText)v2.findViewById(R.id.a_n_b_w_t2);
        ArrayAdapter<CharSequence> adapter1= ArrayAdapter.createFromResource(admin_profile.this,R.array.branch,android.R.layout.simple_spinner_item);
        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        ss1.setAdapter(adapter1);
        ss1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
        {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id)
            {
                switch (position)
                {
                    case 0:
                        branch="cse";
                        break;
                    case 1:
                        branch="ece";
                        break;
                    case 2:
                        branch="me";
                        break;
                    case 3:
                        branch="ce";
                        break;
                    case 4:
                        branch="all";
                        break;
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        ArrayAdapter<CharSequence> adapter2= ArrayAdapter.createFromResource(admin_profile.this,R.array.year,android.R.layout.simple_spinner_item);
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        ss2.setAdapter(adapter2);
        ss2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
        {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id)
            {
                switch (position)
                {
                    case 0:
                        year="1";
                        break;
                    case 1:
                        year="2";
                        break;
                    case 2:
                        year="3";
                        break;
                    case 3:
                        year="4";
                        break;
                    case 4:
                        year="5";
                        break;
                    case 5:
                        year="all";
                        break;
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent)
            {

            }
        });
        addb.setPositiveButton("add", new DialogInterface.OnClickListener()
        {
            @Override
            public void onClick(DialogInterface dialog, int which)
            {
               title=et1.getText().toString();
                content=et2.getText().toString();
                notice_connection();
                recreate();


            }
        });
        addb.setNegativeButton("cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        addb.create();
        addb.show();

    }
    protected void all()
    {
        LayoutInflater l=LayoutInflater.from(admin_profile.this);
        View v3= l.inflate(R.layout.add_notice,null);
        AlertDialog.Builder addbb=new AlertDialog.Builder(admin_profile.this);
        addbb.setView(v3);
        addbb.setCancelable(false);
        final EditText eet1=(EditText)v3.findViewById(R.id.add_n_t1);
        final EditText eet2=(EditText)v3.findViewById(R.id.add_n_t2);
        addbb.setPositiveButton("add", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which)
            {
                title=eet1.getText().toString();
                content=eet2.getText().toString();
                notice_connection_all();
                recreate();
            }
        });
        addbb.setNegativeButton("cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        addbb.create();
        addbb.show();


    }

}
