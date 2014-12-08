package com.yeleman.snisidroid;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.yeleman.nutrition.NutritionHome;
import com.yeleman.smir.SMIRHome;
import com.yeleman.malaria.MalariaHome;


public class SNISIHome extends ActionBarActivity {

	private final static String TAG = Constants.getLogTag("Home");

	private Button nutritionButton;
	private Button smirButton;
	private Button pnlpButton;
	private Button webSiteButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        Log.d(TAG, "onCreate SNISIHome");
        setContentView(R.layout.snisi_home);

        nutritionButton = (Button) findViewById(R.id.nutritionButton);
        smirButton = (Button) findViewById(R.id.smirButton);
        webSiteButton = (Button) findViewById(R.id.webSiteButton);
        pnlpButton = (Button) findViewById(R.id.pnlpButton);

        final Activity activity = this;

        webSiteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(
                	Intent.ACTION_VIEW,
                	Uri.parse(Constants.server_url));
                Popups.startIntentIfOnline(activity, intent);
            }
        });

        nutritionButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(
                	getApplicationContext(),
                	NutritionHome.class);
                startActivity(intent);
            }
        });

        smirButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
            	Intent intent = new Intent(
                	getApplicationContext(),
                	SMIRHome.class);
                startActivity(intent);
            }
        });

        pnlpButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
            	Intent intent = new Intent(
                	getApplicationContext(),
                    MalariaHome.class);
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

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.menu_settings) {
            Intent intent = new Intent(this, Preferences.class);
            startActivityForResult(intent, 1);
        }
        if (id == R.id.resources) {
            String resourceUrl = String.format(Constants.resource_url, Constants.server_url);
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(resourceUrl));
            Popups.startIntentIfOnline(this, intent);
        }
        if (id == R.id.change_password) {
            Intent intent = new Intent(this, ChangePassword.class);
            startActivity(intent);
        }
        if (id == R.id.about) {
            Intent intent = new Intent(this, SNISIAbout.class);
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }
}
