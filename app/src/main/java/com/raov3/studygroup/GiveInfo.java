package com.raov3.studygroup;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.jar.Attributes;


public class GiveInfo extends Activity {
    Context c;

    EditText new_username;
    EditText new_password;
    EditText email;
    Button new_log_button;
    User user;
    String nu, np, e;
    boolean correct = false;
    private static final String DEBUG_TAG = "Wi-fi Check";
    private static final String TAG = "NETWORKING CHECK!!!!";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_give_info);
        c = this;
        new_log_button = (Button) findViewById(R.id.new_log_button);

        new_log_button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Log.d(DEBUG_TAG,"Login button hit!");
                new_username = (EditText) findViewById(R.id.new_user);
                new_password = (EditText) findViewById(R.id.new_password);
                email = (EditText) findViewById(R.id.email);
                nu = new_username.getText().toString();
                np = new_password.getText().toString();
                e = email.getText().toString();
                user = new User(nu,np,e);
                if (nu.length() == 0)
                {
                    Toast.makeText(c,"Please fill in username!", Toast.LENGTH_SHORT).show();

                }
                else
                    if (np.length() == 0)
                    {
                        Toast.makeText(c,"Please fill in password!", Toast.LENGTH_SHORT).show();
                        return;
                    }
                else
                    if (e.length() == 0)
                    {
                        Toast.makeText(c,"Please fill in email!", Toast.LENGTH_SHORT).show();
                        return;
                    }
                //do networking!
                Networking n = new Networking();
                n.execute("http://http://127.0.0.1:5000/new_login",Networking.NETWORK_STATE_REGISTER);
                if (correct)
                {
                    sendMessage();
                }

            }
        });
    }
    public void sendMessage() {
        // Do something in response to button
        Intent intent = new Intent(this, ChooseCategory.class);
        startActivity(intent);

    }

    public class Networking extends  AsyncTask
    {
        public static final int NETWORK_STATE_REGISTER=1;
        @Override
        protected Object doInBackground(Object[] params) {
            getJson((String)params[0],(int)params[1]);
            return null;
        }
    }
    private void getJson(String url,int state){
        HttpClient httpClient = new DefaultHttpClient();
        Log.d(DEBUG_TAG,"POSTING NOW");
        //doing post to url now
        HttpPost request = new HttpPost(url);
        //sent post request to URL, now adding what we want to post
        List<NameValuePair> postParameters = new ArrayList<NameValuePair>();

        boolean valid = false;
        switch(state)
        {
            case Networking.NETWORK_STATE_REGISTER:
                postParameters.add(new BasicNameValuePair("userName",nu));
                postParameters.add(new BasicNameValuePair("passWord",np));
                postParameters.add(new BasicNameValuePair("email",e));
                valid = true;
                Log.d(TAG,"IT WORKS");
                break;
            default:
                Log.d(DEBUG_TAG,"Unknown state!");
        }
        if (valid)
        {
            BufferedReader bufferedReader = null;
            StringBuffer stringBuffer = new StringBuffer("");
            try{
                UrlEncodedFormEntity entity = new UrlEncodedFormEntity(postParameters);
                request.setEntity(entity);
                HttpResponse response = httpClient.execute(request);

                bufferedReader = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));

                String line = "";
                String LineSeparator = System.getProperty("line.separator");
                while((line = bufferedReader.readLine()) != null)
                {
                    final String finalLine = line;
                    runOnUiThread(new Runnable() {
                        public void run() {

                            Toast.makeText(c, finalLine.toString(), Toast.LENGTH_SHORT).show();
                            if (finalLine.toString() == "Login successful"){
                                correct = true;
                            }
                            else
                                correct = false;
                        }
                    });

                    stringBuffer.append(line + LineSeparator);
                }

                bufferedReader.close();
                Log.d(TAG, "Buffering complete??");



            } catch (Exception e){
                Log.d(DEBUG_TAG,"error doing networking!"+e.getMessage());
                e.printStackTrace();
            }
        }
        else
            Log.d(DEBUG_TAG,"valid not true! stop!");
    }




    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_give_info, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
