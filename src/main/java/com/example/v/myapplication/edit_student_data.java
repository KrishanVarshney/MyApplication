package com.example.v.myapplication;

import android.content.DialogInterface;
import android.content.Intent;
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
import android.widget.Button;
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

public class edit_student_data extends AppCompatActivity {

    String eemail;
    EditText t1, t2, t3, t4, t5,t6;
    Button b1;
    String name,contact,branch,roll,year;

    String result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_student_data);
        t1 = (EditText) findViewById(R.id.edit_s_t1);//name
        t2 = (EditText) findViewById(R.id.edit_s_t2);//roll
        t3 = (EditText) findViewById(R.id.edit_s_t3);//contact
        t4 = (EditText) findViewById(R.id.edit_s_t4);//email
        t5 = (EditText) findViewById(R.id.edit_s_t5);//branch
        t6 = (EditText) findViewById(R.id.edit_s_t6);
        b1 = (Button) findViewById(R.id.edit_s_b1);//update

        eemail = getIntent().getExtras().getString("eemail");
        t4.setText(eemail);
        t4.setEnabled(false);
        make_connection_to_php();

        b1.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                AlertDialog.Builder adb=new AlertDialog.Builder(edit_student_data.this);
                adb.setMessage("Are You Sure...??");
                adb.setCancelable(false);
                adb.setPositiveButton("Update", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i)
                    {
                        name=t1.getText().toString();
                        roll=t2.getText().toString();
                        branch=t5.getText().toString();
                        contact=t3.getText().toString();
                        year=t6.getText().toString();
                        update_connection();
                        Intent i11=new Intent(edit_student_data.this,admin_profile.class);
                        startActivity(i11);


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
        });

    }

    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        MenuInflater obj = getMenuInflater();
        obj.inflate(R.menu.edit_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId())
        {
            case R.id.Edit_item_1:
                change_password();
                return true;
            case R.id.Edit_item_2:
                delete();
                return true;


        }
        return false;
    }

    protected void change_password()
    {
        LayoutInflater li=LayoutInflater.from(edit_student_data.this);
        View v1=li.inflate(R.layout.via_admin_change_password,null);
        AlertDialog.Builder adb=new AlertDialog.Builder(edit_student_data.this);
        adb.setView(v1);
        adb.setCancelable(false);


        final EditText tv2=(EditText)v1.findViewById(R.id.via_t1);
        final EditText tv3=(EditText)v1.findViewById(R.id.via_t2);
        adb.setPositiveButton("Change", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i)
            {


                String newp=tv2.getText().toString();
                String confirm=tv3.getText().toString();


                ArrayList<NameValuePair> list=new ArrayList<NameValuePair>();
                list.add(new BasicNameValuePair("email",eemail));
                list.add(new BasicNameValuePair("newp",newp));
                list.add(new BasicNameValuePair("confirm",confirm));

                try {


                    HttpClient httpclient = new DefaultHttpClient();

                    HttpPost httppost = new HttpPost("http://10.0.2.2/b253/change_password_via_admin.php");
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
                    Toast.makeText(edit_student_data.this, result, Toast.LENGTH_SHORT).show();

                    if (result != null) result = result.trim();

                }
                catch (Exception e)
                {
                    Toast.makeText(edit_student_data.this, "Error : " + e, Toast.LENGTH_LONG).show();
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

    protected void delete()
    {
        AlertDialog.Builder adb=new AlertDialog.Builder(edit_student_data.this);
        adb.setMessage("Want to Delete..?");
        adb.setPositiveButton("yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

                ArrayList<NameValuePair> list=new ArrayList<NameValuePair>();
                list.add(new BasicNameValuePair("email",eemail));



                try {


                    HttpClient httpclient = new DefaultHttpClient();

                    HttpPost httppost = new HttpPost("http://10.0.2.2/b253/delete_s_profile.php");
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
                    Toast.makeText(edit_student_data.this, result, Toast.LENGTH_SHORT).show();

                    if (result != null) result = result.trim();

                }
                catch (Exception e)
                {
                    Toast.makeText(edit_student_data.this, "Error : " + e, Toast.LENGTH_LONG).show();
                }
                Intent i1=new Intent(edit_student_data.this,admin_profile.class);
                startActivity(i1);

            }
        });
        adb.setNegativeButton("cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.cancel();
            }
        });
        adb.setCancelable(false);
        adb.create();
        adb.show();

    }


    protected  void update_connection()
    {
        ArrayList<NameValuePair> list=new ArrayList<NameValuePair>();
        list.add(new BasicNameValuePair("name",name));
        list.add(new BasicNameValuePair("branch",branch));
        list.add(new BasicNameValuePair("year",year));
        list.add(new BasicNameValuePair("roll",roll));
        list.add(new BasicNameValuePair("contact",contact));
        list.add(new BasicNameValuePair("email",eemail));

        try {


            HttpClient httpclient = new DefaultHttpClient();

            HttpPost httppost = new HttpPost("http://10.0.2.2/b253/update_edit_student.php");
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
            Toast.makeText(edit_student_data.this, result, Toast.LENGTH_SHORT).show();

        }
        catch (Exception e)
        {
            Toast.makeText(edit_student_data.this, "Error : " + e, Toast.LENGTH_LONG).show();
        }

    }


    protected void make_connection_to_php()
    {
        ArrayList<NameValuePair> list=new ArrayList<NameValuePair>();
        list.add(new BasicNameValuePair("email",eemail));

        try {
            HttpClient httpclient = new DefaultHttpClient();

            HttpPost httppost = new HttpPost("http://10.0.2.2/b253/ShowData2.php");
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
            br.close();
            result = sb.toString();
            if (result != null) result = result.trim();
            //Toast.makeText(ShowData.this, result, Toast.LENGTH_LONG).show();

            try {
                JSONArray jarray = new JSONArray(result);



                for (int i = 0; i < jarray.length(); i++) {

                    JSONObject obj = jarray.getJSONObject(i);
                    String namee = obj.getString("name");
                    String rolle = obj.getString("roll");
                    String branche = obj.getString("branch");
                    String yeare = obj.getString("year");
                    String contacte = obj.getString("contact");
                    t1.setText(namee);
                    t2.setText(rolle);
                    t5.setText(branche);
                    t3.setText(contacte);
                    t6.setText(yeare);

                }

            } catch (Exception e) {
                Toast.makeText( edit_student_data.this, "JSON : " + e, Toast.LENGTH_LONG).show();
            }

        } catch (Exception e) {
            Toast.makeText(edit_student_data.this, "Error : " + e, Toast.LENGTH_LONG).show();
        }

    }

}
