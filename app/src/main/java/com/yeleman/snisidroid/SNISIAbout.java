package com.yeleman.snisidroid;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class SNISIAbout extends Activity {

	private Button versionButton;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.snisi_about);
        setupUI();
    }

    protected void setupUI() {
    	versionButton = (Button) findViewById(R.id.versionButton);
    	versionButton.setText(String.format(
    		getString(R.string.version_button_label),
    		BuildConfig.VERSION_NAME));
    	versionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String market_uri = getString(R.string.app_market_url);
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse(market_uri));
                startActivity(intent);
            }
        });
    }

}
