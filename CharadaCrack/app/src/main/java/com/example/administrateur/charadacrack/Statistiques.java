package com.example.administrateur.charadacrack;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.regex.Pattern;

/**
 * Created by Administrateur on 2015-03-06.
 */
public class Statistiques extends ActionBarActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_statistique);
        ListView list = (ListView) findViewById(R.id.listView_stats);
        ListView listtitre = (ListView) findViewById(R.id.listView_titre);
        String Tabu1="\t"+"\t"+"\t"+"\t"+"\t"+"\t";
        String Tabu2="\t"+"\t"+"\t"+"\t"+"\t"+"\t"+"\t"+"\t"+"\t";
        String Tabu3="\t"+"\t"+"\t"+"\t"+"\t"+"\t"+"\t"+"\t"+"\t"+"\t"+"\t";


        final String[] values = new String[] { Tabu1+"Niveaux"+Tabu2+"Scores"+Tabu3+"Date"};

        final ArrayAdapter<String> titre = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, android.R.id.text1,values);

        listtitre.setAdapter(titre);

        try {
            InputStream inputStream = openFileInput("Statistiques.txt");

            if ( inputStream != null ) {
                InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
                //String stats = "";
                final ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                        android.R.layout.simple_list_item_1, android.R.id.text1);
                String line="";

                while ( (line = bufferedReader.readLine()) != null ) {
                    //stats+=line;
                    adapter.add(line);
                }
                list.setAdapter(adapter);

                //((TextView)findViewById(R.id.txtView_Stats)).setText(stats);
                inputStream.close();
            }
        }
        catch (FileNotFoundException e) {
            Log.e("login activity", "File not found: " + e.toString());
        } catch (IOException e) {
            Log.e("login activity", "Can not read file: " + e.toString());
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_game, menu);
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
