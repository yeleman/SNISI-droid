package com.yeleman.snisidroid;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.yeleman.smir.SMIR;

import static java.lang.String.format;


public class SNISI extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.snisi_home);

        Button btndisplaySNISI = (Button) findViewById(R.id.site_id);
        Button btnSMIR = (Button) findViewById(R.id.smir_id);
        Button btnnutrition = (Button) findViewById(R.id.nut_id);

        btndisplaySNISI.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String url = "http://snisi.sante.gov.ml/";
                Intent intent = new Intent( Intent.ACTION_VIEW, Uri.parse(url) );
                startActivity(intent);
            }
        });
        btnnutrition.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Log.i("SNISI", "Nut");
                Intent i=new Intent(getApplicationContext(), NutritionActivity.class);
                startActivity(i);
            }
        });
        btnSMIR.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Log.i("SNISI", "SMIR");
                Intent i=new Intent(getApplicationContext(), SMIR.class);
                startActivity(i);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.snisi, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.

        int RESULT_SETTINGS = 1;
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            Intent i = new Intent(this, Preferences.class);
            startActivityForResult(i, RESULT_SETTINGS);
        }
        if (id == R.id.app_version) {
            Popups versionPopupBuilder = new Popups.displayVersionPopup(this);
        }
        if (id == R.id.news) {
            Intent intent = new Intent(this, Resource.class );
            startActivity(intent);
        }
        if (id == R.id.change_password) {
            Intent intent = new Intent(this, ChangePassword.class );
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }
}
