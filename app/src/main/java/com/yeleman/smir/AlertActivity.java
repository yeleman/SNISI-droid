package com.yeleman.smir;

import com.yeleman.snisidroid.R;
import android.app.Activity;
import android.telephony.SmsManager;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import android.text.Editable;
import android.text.TextWatcher;


public class AlertActivity extends Activity {

   private Spinner spinner;
   private Button btnSubmit;

   private static final String[] order_diseases = {
        Constants.ebola, Constants.acute_flaccid_paralysis,
        Constants.influenza_a_h1n1, Constants.cholera,
        Constants.red_diarrhea, Constants.measles, Constants.yellow_fever,
        Constants.neonatal_tetanus, Constants.meningitis,
        Constants.rabies, Constants.acute_measles_diarrhea,
        Constants.other_notifiable_disease
   };

   @Override
   public void onCreate(Bundle savedInstanceState) {
       super.onCreate(savedInstanceState);
       setContentView(R.layout.smir_alert);

       addItemsOnSpinner();
	   addListenerOnButton();
    }

   // add items into spinner dynamically
   public void addItemsOnSpinner() {

      spinner = (Spinner) findViewById(R.id.spinner);
      List<String> list = new ArrayList<String>();
      list.add("EBOLA");
      list.add("PFA");
      list.add("Grippe A H1N1");
      list.add("Choléra");
      list.add("Diarrhée rouge");
      list.add("Rougeole");
      list.add("Fièvre jaune");
      list.add("TNN");
      list.add("Méningite");
      list.add("Rage");
      list.add("Diarrhée sévère rougeole");
      list.add("Autre MADO");
      ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, list);
      dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
      spinner.setAdapter(dataAdapter);
    }

    public void addListenerOnButton() {

      spinner = (Spinner) findViewById(R.id.spinner);
      btnSubmit = (Button) findViewById(R.id.button);

      validated();
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

   private void validated() {

      final EditText input_case = (EditText) findViewById(R.id.input_case);
      final EditText input_confirmed = (EditText) findViewById(R.id.input_confirmed);
      final EditText input_death = (EditText) findViewById(R.id.input_death);
      final EditText input_password = (EditText) findViewById(R.id.input_password);

      input_case.addTextChangedListener(new TextWatcher() {
         public void afterTextChanged(Editable s) {
            SharedChecks.is_empty(input_case);
         }
         public void beforeTextChanged(CharSequence s, int start, int count, int after){}
         public void onTextChanged(CharSequence s, int start, int before, int count){}
      });
      input_confirmed.addTextChangedListener(new TextWatcher() {
         public void afterTextChanged(Editable s) {
            SharedChecks.is_empty(input_confirmed);
         }
         public void beforeTextChanged(CharSequence s, int start, int count, int after){
            SharedChecks.is_empty(input_case);
         }
         public void onTextChanged(CharSequence s, int start, int before, int count){}
      });
      input_death.addTextChangedListener(new TextWatcher() {
         public void afterTextChanged(Editable s) {
            SharedChecks.is_empty(input_death);
         }
         public void beforeTextChanged(CharSequence s, int start, int count, int after){
            SharedChecks.is_empty(input_confirmed);
         }
         public void onTextChanged(CharSequence s, int start, int before, int count){}
      });
      input_password.addTextChangedListener(new TextWatcher() {
         public void afterTextChanged(Editable s) {}
         public void beforeTextChanged(CharSequence s, int start, int count, int after){}
         public void onTextChanged(CharSequence s, int start, int before, int count){
             SharedChecks.is_empty(input_password);
         }
      });
   }

   protected boolean checkAllDataOK() {

      final EditText input_case = (EditText) findViewById(R.id.input_case);
      final EditText input_confirmed = (EditText) findViewById(R.id.input_confirmed);
      final EditText input_death = (EditText) findViewById(R.id.input_death);
      final EditText input_password = (EditText) findViewById(R.id.input_password);
      Vector<String> errors = new Vector<String>();
      
      SharedPreferences sharedPrefs = PreferenceManager.getDefaultSharedPreferences(this);
      String username = sharedPrefs.getString("username", null);
      if (username.isEmpty()){
         errors.add("L'identifiant doit être renseigné dans le paramètre.");
      }

      if (input_case.getText().toString().isEmpty()){
          displayErrorPopup("Champs cas suspect est requis.");
          return false;

      }
      if (input_confirmed.getText().toString().isEmpty()){
          displayErrorPopup("Champs confirmation est requis");
          return false;
      }
      if (input_death.getText().toString().isEmpty()){
           displayErrorPopup("Champs décès est requis.");
           return false;
      }
      if (input_password.getText().toString().isEmpty()){
           displayErrorPopup("Champs mot de passe est requis.");
           return false;
      }

      if (to_int(input_case.getText().toString()) == 0 &&
          to_int(input_confirmed.getText().toString()) == 0 &&
          to_int(input_death.getText().toString()) == 0){
               displayErrorPopup("Tout est à zero");
               return false;
      }
      if (!SharedChecks._check_not_valid(input_case, input_confirmed)){
         errors.add("Nombre de confirmé ne doit pas être supérieur au nombre cas suspect.");
      }
      if (!SharedChecks._check_not_valid(input_confirmed, input_death)){
         errors.add("Nombre de décès ne doit pas être supérieur au nombre confirmé.");
      }
      if (errors.size() > 0) {
         // display Error Message
         displayErrorPopup(errors.get(0));
         return false;
      }  else {
          return true;
      }
   }

   private int to_int(String value) {
      try {
         return Integer.parseInt(value);
      } catch (Exception e) {
         return -1;
      }
    }

   protected boolean submitText(String message) {
        // preferences
      Log.i("SMIR", "rrrrrrrrrrrrr");
      SharedPreferences sharedPrefs = PreferenceManager.getDefaultSharedPreferences(this);
      String phoneNumber = sharedPrefs.getString("serverPhoneNumber", null);
      Log.i("SMIR", phoneNumber );
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
      SharedPreferences sharedPrefs = PreferenceManager.getDefaultSharedPreferences(this);
      String username = sharedPrefs.getString("username", null);
      String disease = String.valueOf(order_diseases[spinner.getSelectedItemPosition()]);

      final EditText input_case = (EditText) findViewById(R.id.input_case);
      final EditText input_confirmed = (EditText) findViewById(R.id.input_confirmed);
      final EditText input_death = (EditText) findViewById(R.id.input_death);
      final EditText input_password = (EditText) findViewById(R.id.input_password);

      // smir alert username password code suspected confirmed deaths
      String sms_text = Constants.KEYWORD;
      sms_text += Constants.SPACER;

      sms_text += Constants.KEYALERT;
      sms_text += Constants.SPACER;

      // USERNAME
      sms_text += username;
      sms_text += Constants.SPACER;

      // PASSWORD
      sms_text += input_password.getText().toString();
      sms_text += Constants.SPACER;

      sms_text += disease;
      sms_text += Constants.SPACER;

      sms_text += input_case.getText().toString();
      sms_text += Constants.SPACER;

      sms_text += input_confirmed.getText().toString();
      sms_text += Constants.SPACER;

      sms_text += input_death.getText().toString();
      sms_text += Constants.SPACER;

      return sms_text.trim();
   }

   protected void displayErrorPopup(String message) {
      AlertDialog.Builder helpBuilder = new AlertDialog.Builder(this);
      helpBuilder.setTitle("Erreur !");
      helpBuilder.setMessage("Impossible d'envoyer l'alerte :\n\n" + message + "\n\nVous devez corriger et re-envoyer.");
      helpBuilder.setIcon(R.drawable.ic_launcher);
      helpBuilder.setPositiveButton("Fermer et corriger",
         new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
               // Do nothing but close the dialog
            }
      });
      // Remember, create doesn't show the dialog
      AlertDialog helpDialog = helpBuilder.create();
      helpDialog.show();
   }

}

