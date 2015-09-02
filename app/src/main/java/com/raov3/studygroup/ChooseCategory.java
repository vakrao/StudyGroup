package com.raov3.studygroup;

import android.app.Activity;
import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.internal.widget.AdapterViewCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class ChooseCategory extends ActionBarActivity {
    /*public class ArticleFragment extends Fragment {
        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            // Inflate the layout for this fragment
            return inflater.inflate(R.layout.article_view, container, false);
        }
    }*/
    ListView listView;
    String TAG;
    public final static String EXTRA_MESSAGE = "com.raov3.studygroup.MESSAGE";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_category);

        //get a listView variable assigned to ID listview
        listView = (ListView) findViewById(R.id.list);

        String[] values = new String[] {
                "Math",
                "Science",
                "Engineering",
                "Other",

        };

        // Define a new Adapter
        // First parameter - Context
        // Second parameter - Layout for the row
        // Third parameter - ID of the TextView to which the data is written
        // Forth - the Array of data

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, android.R.id.text1, values);
        //Assign adapter to listview
        listView.setAdapter(adapter);



        //ListView item click Listener

       listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
           @Override
           public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {


               String Picked = "You selected "+
               String.valueOf(adapterView.getItemAtPosition(i));
               wheretoGo(String.valueOf(adapterView.getItemAtPosition(i)));



               Toast.makeText(ChooseCategory.this, Picked, Toast.LENGTH_SHORT).show();

           }
       });





        };







    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_choose_category, menu);
        return true;
    }

    public void wheretoGo(String value){
        String science,math,engineering,other;
        science = "Science";
        math = "Math";
        engineering = "Engineering";
        other = "Other";
        switch(value){
            case "Science":
                Intent z = new Intent(this,AllGroups.class);
                z.putExtra(EXTRA_MESSAGE,science);
                startActivity(z);
                break;

            case "Engineering":
                Intent d = new Intent(this,AllGroups.class);
                d.putExtra(EXTRA_MESSAGE, engineering);
                startActivity(d);
                break;

            case "Math":
                Intent m = new Intent(this,AllGroups.class);
                m.putExtra(EXTRA_MESSAGE, math);
                startActivity(m);
                break;




        }
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
