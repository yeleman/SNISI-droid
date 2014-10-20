package com.yeleman.snisidroid;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.telephony.SmsManager;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class ChangePassword extends Activity {

    private Button btnSubmit;

    //private EditText usernameField;
    //private EditText oldpasswordField;
    //private EditText newpasswordField;


    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.changepassword);

        btnSubmit = (Button) findViewById(R.id.nut_id);
        addListenerOnButton();
    }

    public void addListenerOnButton() {
        btnSubmit.setOnClickListener(new View.OnClickListener() {
          	@Override
          	public void onClick(View v) {
              	if (checkAllDataOK()) {
               	    String sms_str = getSMSString();
               	    Log.i("SMIR SMS-OUT", sms_str);
               	    boolean succeeded = submitText(sms_str);
                if (succeeded) {
                    finish();
                }
              }
          }
      });
    }

   	protected boolean checkAllDataOK() {
   		final EditText username = (EditText) findViewById(R.id.usernameField);
        final EditText oldpassword = (EditText) findViewById(R.id.oldpasswordField);
        final EditText newpassword = (EditText) findViewById(R.id.newpasswordField);
	      
		if (!SharedChecks.is_empty(username)){
		  return false;
		}

		if (!SharedChecks.is_empty(oldpassword)){
		  return false;
		}
		if (!SharedChecks.is_empty(newpassword)){
		  return false;
		}
		return true;
   }

   protected boolean submitText(String message) {
        // preferences
      SharedPreferences sharedPrefs = PreferenceManager.getDefaultSharedPreferences(this);
      String phoneNumber = sharedPrefs.getString("serverPhoneNumber", null);
      try {
         SmsManager sms = SmsManager.getDefault();
         sms.sendTextMessage(phoneNumber, null, message, null, null);
          Toast toast = Toast.makeText(getApplicationContext(), getString(R.string.notif_sms_sent), Toast.LENGTH_LONG);
          toast.setGravity(Gravity.CENTER, 0, 0);
          toast.show();
      } catch (Exception e) {
         Toast.makeText(getApplicationContext(), getString(R.string.notif_sms_sent), Toast.LENGTH_LONG).show();
         e.printStackTrace();
         return false;
      }
      return true;
    }

    
   protected String getSMSString() {
      //SharedPreferences sharedPrefs = PreferenceManager.getDefaultSharedPreferences(this);
      //String username = sharedPrefs.getString("username", null);

      final EditText username = (EditText) findViewById(R.id.usernameField);
      final EditText oldpassword = (EditText) findViewById(R.id.oldpasswordField);
      final EditText newpassword = (EditText) findViewById(R.id.newpasswordField);

      // snisi passwd username oldpassword newpasswoRD
      String sms_text = Constants.KEYWORD;
      sms_text += Constants.SPACER;

      sms_text += "passwd";
      sms_text += Constants.SPACER;

      sms_text += username.getText().toString();
      sms_text += Constants.SPACER;

      sms_text += oldpassword.getText().toString();
      sms_text += Constants.SPACER;

      sms_text += newpassword.getText().toString();
      sms_text += Constants.SPACER;

      return sms_text.trim();
   }

}
