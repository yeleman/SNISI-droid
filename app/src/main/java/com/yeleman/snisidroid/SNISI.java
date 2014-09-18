package com.yeleman.snisidroid;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;


import static java.lang.String.format;


public class SNISI extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_snisi);

        Button btndisplaySNISI = (Button) findViewById(R.id.button);
        btndisplaySNISI.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String url = "http://snisi.sante.gov.ml/";
                Intent intent = new Intent( Intent.ACTION_VIEW, Uri.parse(url) );
                startActivity(intent);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.snisi, menu);
        return true;
    }
    protected void displayVersionPopup() {
        AlertDialog.Builder helpBuilder = new AlertDialog.Builder(this);
        helpBuilder.setTitle(getString(R.string.app_name));
        String msg_version = format("Version %s \n\nEn cas de problème contactez ANTIM.", R.string.version);
        helpBuilder.setMessage(msg_version);
        helpBuilder.setIcon(R.drawable.ic_launcher);
        helpBuilder.setPositiveButton("OK",
            new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
                 // Do nothing but close the dialog
                }
        });

         // Remember, create doesn't show the dialog
         AlertDialog helpDialog = helpBuilder.create();
         helpDialog.show();
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.

        int RESULT_SETTINGS = 1;
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            Intent i = new Intent(this, Parametres.class);
            startActivityForResult(i, RESULT_SETTINGS);
        }
        if (id == R.id.app_version) {
            displayVersionPopup();
        }

        if (id == R.id.news) {
            //String url = "http://snisi.sante.gov.ml/";
           // Intent intent = new Intent(this, Resource.class, Uri.parse(url) );
            Intent intent = new Intent(this, Resource.class );
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }
}
