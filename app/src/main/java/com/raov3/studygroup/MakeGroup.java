package com.raov3.studygroup;

import android.os.AsyncTask;
import android.os.Message;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.JsonWriter;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.MalformedInputException;
import java.util.List;

public class MakeGroup extends ActionBarActivity {
    Spinner main_spinner;
    Button btn_submit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_make_group);


      //  addListenerOnSpinnerItemSelection();
    }


   /* public void writeJsonStream(OutputStream out, List messages) throws IOException {
        JsonWriter writer = new JsonWriter(new OutputStreamWriter(out, "UTF-8"));
        writer.setIndent("  ");
        writeMessagesArray(writer, messages);
        writer.close();
    }


    public void writeMessagesArray(JsonWriter writer, List messages) throws IOException {
        writer.beginArray();
        for (Message message : messages) {
            writeMessage(writer, message);
        }
        writer.endArray();
    }

    public void writeMessage(JsonWriter writer, Message message) throws IOException {
        writer.beginObject();
        writer.name("id").value(message.getId());
        writer.name("text").value(message.getText());
        if (message.getGeo() != null) {
            writer.name("geo");
            writeDoublesArray(writer, message.getGeo());
        } else {
            writer.name("geo").nullValue();
        }
        writer.name("user");
        writeUser(writer, message.getUser());
        writer.endObject();
    }

    public void writeUser(JsonWriter writer, User user) throws IOException {
        writer.beginObject();
        writer.name("name").value(user.getName());
        writer.name("followers_count").value(user.getFollowersCount());
        writer.endObject();
    }

    public void writeDoublesArray(JsonWriter writer, List doubles) throws IOException {
        writer.beginArray();
        for (Double value : doubles) {
            writer.value(value);
        }
        writer.endArray();
    }*/


    /*public void addListenerOnSpinnerItemSelection(){
        main_spinner = (Spinner) findViewById(R.id.student_spinner);
        btn_submit = (Button) findViewById(R.id.form_submit);
        btn_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MakeGroup.this,
                        "OnClickListenere : " + "\nSpinner Value : " + String.valueOf(main_spinner.getSelectedItem()), Toast.LENGTH_SHORT).show();


                try {
                    Networking n = new Networking();
                    n.execute("http://192.168.56.1:5000/new_login",Networking.NETWORK_STATE_REGISTER);


                    //InputStream in = new BufferedInputStream(urlConnection.getInputStream());
                    //readStream(in);

                } catch (IOException e) {
                    e.printStackTrace();
                }
                catch(java.url.malformedexception) {

                }finally {

                }

            }
        });

    }

    public class Networking extends AsyncTask
    {
        public static final int NETWORK_STATE_REGISTER=1;
        @Override
        protected Object doInBackground(Object[] params) {
            getJson((String)params[0],(int)params[1]);
            return null;
        }
    }

    private void getJson(String u,int state){
        HttpURLConnection urlConnection = null;
        URL url =   new URL("https://192.168.65.1:5000/send-data");
        urlConnection = (HttpURLConnection) url.openConnection();
        urlConnection.setRequestMethod("POST");
        urlConnection.setDoOutput(true);


        OutputStream os = urlConnection.getOutputStream();
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(os, "UTF-8"));
        writer.write("Test Account");
        writer.flush();
        writer.close();
        os.close();
        urlConnection.disconnect();

    }*/

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_make_group, menu);
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
