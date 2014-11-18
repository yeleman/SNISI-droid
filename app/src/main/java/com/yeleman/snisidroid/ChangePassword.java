package com.yeleman.snisidroid;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.orm.SugarRecord;

import junit.framework.Test;

public class ChangePassword extends CheckedFormActivity {

    private final static String TAG = Constants.getLogTag("ChangePassword");

    private EditText usernameText;
    private EditText oldPasswordText;
    private EditText newPasswordText;
    private Button changePasswordButton;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        Log.d(TAG, "onCreate ChangePassword");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.snisi_change_password);
        setupSMSReceiver();
        setupUI();
    }

    protected void setupInvalidInputChecks() {
		setAssertNotEmpty(usernameText);
        setAssertAtLeastThisLong(oldPasswordText, Constants.MIN_CHARS_PASSWORD);
        setAssertAtLeastThisLong(newPasswordText, Constants.MIN_CHARS_PASSWORD);
    }

    protected boolean ensureDataCoherence() {
    	return true;

    }

    private void setupUI() {
        setTitle(String.format(getString(R.string.label_tmpl),
                               getString(R.string.app_name),
                               getString(R.string.menu_change_password)));


        // Instantiate all UI elements
        usernameText = (EditText) findViewById(R.id.usernameField);
        oldPasswordText = (EditText) findViewById(R.id.oldPasswordField);
        newPasswordText = (EditText) findViewById(R.id.newPasswordField);
        changePasswordButton = (Button) findViewById(R.id.changePasswordButton);

        // Add Input Validation Checks
        setupInvalidInputChecks();
        
        // Prefill username from Preferences
        SharedPreferences sharedPrefs = PreferenceManager.getDefaultSharedPreferences(this);
        String username = sharedPrefs.getString("username", null);
        if (username != null) {
            usernameText.setText(username);
            oldPasswordText.requestFocus();
        }

        // register onclick
        changePasswordButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                checkAndSubmitSMSAction();
            }
        });
    }
   
	protected String buildSMSText() {
		// snisi passwd username oldpassword newpassword
		return String.format(Constants.SMS_CHANGE_PASSWRD,
							 stringFromField(usernameText),
							 stringFromField(oldPasswordText),
							 stringFromField(newPasswordText));
   }

}
