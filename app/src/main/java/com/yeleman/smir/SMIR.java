package com.yeleman.smir;

import java.util.Vector;
import java.util.Hashtable;

import android.app.Activity;
import android.app.AlertDialog;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.text.Html;
import android.text.Spanned;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.LinearLayout;
import android.widget.Toast;
import android.text.InputType;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Menu;
import android.view.MenuItem;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.preference.PreferenceManager;
import android.widget.ScrollView;

import com.yeleman.snisidroid.BuildConfig;
import com.yeleman.snisidroid.Popups;
import com.yeleman.snisidroid.Preferences;
import com.yeleman.snisidroid.R;

import static java.lang.String.format;


public class SMIR extends Activity {

    protected TextView label_disease;
    protected TextView label_cas;
    protected EditText input_cas;
    protected TextView label_deces;
    protected EditText input_deces;
	// end-of-week
	protected TextView label_end_date;
	protected DatePicker input_end_date;
	// password
	protected TextView label_password;
	protected EditText input_password;

	private static final String[] order_diseases = {
        Constants.ebola, Constants.acute_flaccid_paralysis,
        Constants.influenza_a_h1n1, Constants.cholera,
        Constants.red_diarrhea, Constants.measles, Constants.yellow_fever,
        Constants.neonatal_tetanus, Constants.meningitis,
        Constants.rabies, Constants.acute_measles_diarrhea,
        Constants.other_notifiable_disease
    };

    private Hashtable<String, String> disease_list;
	private Hashtable<String, Hashtable<String, EditText>> cap_fields;
    private Hashtable<String, EditText> indiv_fields;
    @Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		//setContentView(R.layout.activity_main);
		ScrollView scrollv = new ScrollView(this);
		LinearLayout layout = new LinearLayout(this);
        layout.setOrientation(LinearLayout.VERTICAL);
        scrollv.addView(layout);
        setContentView(scrollv);


        disease_list = new Hashtable<String, String>();
        disease_list.put(Constants.ebola, "EBOLA");
        disease_list.put(Constants.acute_flaccid_paralysis, "PFA");
        disease_list.put(Constants.influenza_a_h1n1, "Grippe A H1N1");
        disease_list.put(Constants.cholera, "Choléra");
        disease_list.put(Constants.red_diarrhea, "Diarrhée rouge");
        disease_list.put(Constants.measles, "Rougeole");
        disease_list.put(Constants.yellow_fever, "Fièvre jaune");
        disease_list.put(Constants.neonatal_tetanus, "TNN");
        disease_list.put(Constants.meningitis, "Méningite");
        disease_list.put(Constants.rabies, "Rage");
        disease_list.put(Constants.acute_measles_diarrhea, "Diarrhée sévère rougeole");
        disease_list.put(Constants.other_notifiable_disease, "Autres MADOs");

        cap_fields = new Hashtable<String, Hashtable<String, EditText>>();
        for(int i=0;i<order_diseases.length;i++) {
            String code = order_diseases[i];
            indiv_fields = new Hashtable<String, EditText>();
            Spanned namefield = Html.fromHtml("<b>" + (String)disease_list.get(code) + "</b>");
            label_cas = new TextView(this);
            label_cas.setText("Cas");
            input_cas = new EditText(this);
            input_cas.setInputType(InputType.TYPE_CLASS_NUMBER);
            label_deces = new TextView(this);
            label_deces.setText("Décès");
            input_deces = new EditText(this);
            input_deces.setInputType(InputType.TYPE_CLASS_NUMBER);
            layout.addView(TextView(namefield));
            layout.addView(label_cas);
            layout.addView(input_cas);
            layout.addView(label_deces);
            layout.addView(input_deces);
            indiv_fields.put("cas", input_cas);
            indiv_fields.put("deces", input_deces);
            cap_fields.put(code, indiv_fields);
        }

        // PASSWORD
        label_password = new TextView(this);
        label_password.setText("Mot de passe SNISI");
        input_password = new EditText(this);
        input_password.setInputType(InputType.TYPE_TEXT_VARIATION_PASSWORD);
//        input_password.setHint("insensible à la casse.");
//        input_password.setError("Trop court!");
        layout.addView(label_password);
        layout.addView(input_password);

        // DATE END OF WEEK
        label_end_date = new TextView(this);
        label_end_date.setText("Fin de la semaine (date du vendredi).");
        input_end_date = new DatePicker(this);
//        input_end_date.setCalendarViewShown(false);
//        GregorianCalendar min_date = new GregorianCalendar(2014, 1, 1);
//        GregorianCalendar max_date = new GregorianCalendar(2020, 12, 31);
//        input_end_date.setMaxDate(max_date.getTimeInMillis());
//        input_end_date.setMinDate(min_date.getTimeInMillis());
        layout.addView(label_end_date);
        layout.addView(input_end_date);

        // SUBMIT BUTTON
        Button button_submit = new Button(this);
        button_submit.setText("Envoyer");
        button_submit.setOnClickListener(new OnClickListener() {
        	@Override
			public void onClick(View v) {
				if (checkAllDataOK()) {
					String sms_str = getSMSString();
					Log.i("SMIR SMS-OUT", sms_str);
					boolean succeeded = submitText(sms_str);
					if (succeeded) {
						resetAllFields();
					}
				}
			}
         });
         layout.addView(button_submit);
	}

    private View TextView(Spanned spanned) {
        final View label;
        label = new TextView(this);
        ((android.widget.TextView) label).setText(spanned);
        return label;
    }

    private View TextView(final String namefield2) {
        final View label;
        label = new TextView(this);
        ((android.widget.TextView) label).setText(namefield2);
        return label;
    }

	protected void resetAllFields() {
		// this.input_ebola.setText(null);
//		Date d = new Date(); // today
//		this.input_end_date.updateDate(d.getYear(), d.getMonth(), d.getDay());
		this.input_password.setText(null);
        for(int i=0;i<order_diseases.length;i++) {
            String code = order_diseases[i];
            indiv_fields = (Hashtable<String, EditText>)cap_fields.get(code);
            EditText casfield = (EditText)indiv_fields.get("cas");
            EditText decesfield = (EditText)indiv_fields.get("deces");
            casfield.setText(null);
            decesfield.setText(null);
        }
	}


	protected boolean checkAllDataOK() {

		Vector<String> errors = new Vector<String>();

        // USERNAME
		SharedPreferences sharedPrefs = PreferenceManager.getDefaultSharedPreferences(this);
        String username = sharedPrefs.getString("username", null);
        if (username == null || username.isEmpty()){
        	errors.add("L'identifiant doit être renseigné dans le paramètre.");
        }
        // DISEASES
        for(int i=0;i<order_diseases.length;i++) {
            String code = order_diseases[i];
            indiv_fields = (Hashtable<String, EditText>)cap_fields.get(code);

			String namefield = (String)disease_list.get(code);
            EditText casfield = (EditText)indiv_fields.get("cas");
            EditText decesfield = (EditText)indiv_fields.get("deces");

            SharedChecks._check_not_empty_message(casfield, namefield + Constants.SPACER + "cas", errors);
            SharedChecks._check_not_empty_message(decesfield, namefield + Constants.SPACER + "décès", errors);
            try{
            	SharedChecks._check_isValid_message(casfield, decesfield, namefield, errors);
            } catch(NumberFormatException dashi) {
    		}
        }

		// END DATE
		SharedChecks._check_date_is_friday_message(this.input_end_date,
									  label_end_date.getText().toString(),
									  errors);
		// PASSWORD 4 chars min.
		SharedChecks._check_min_characters_message(this.input_password, 4,
									  label_password.getText().toString(),
									  errors);
		if (errors.size() > 0) {
			// display Error Message
            Popups displayPopupBuilder = new Popups.displayErrorPopup(this, errors.get(0));
			return false;
		} else {
			return true;
		}
	}

	protected String getSMSString() {
		SharedPreferences sharedPrefs = PreferenceManager.getDefaultSharedPreferences(this);
		String username = sharedPrefs.getString("username", null);


		String sms_text = Constants.KEYWORD;
		sms_text += Constants.SPACER;

		// USERNAME
		sms_text += username;
		sms_text += Constants.SPACER;

		// PASSWORD
		sms_text += this.input_password.getText().toString();
		sms_text += Constants.SPACER;

        // Date DD/MM/YYYY
        sms_text += this.input_end_date.getDayOfMonth() + "/" +
        			this.input_end_date.getMonth() + "/" +
        			this.input_end_date.getYear();

        // Diseases
        for(int i=0;i<order_diseases.length;i++) {
            String code = order_diseases[i];
            indiv_fields = (Hashtable<String, EditText>)cap_fields.get(code);
            EditText casfield = (EditText)indiv_fields.get("cas");
            EditText decesfield = (EditText)indiv_fields.get("deces");
            sms_text += Constants.SPACER + casfield.getText().toString() +
                        Constants.SPACER + decesfield.getText().toString();
        }
		return sms_text.trim();
	}

	protected boolean submitText(String message) {
		// preferences
		SharedPreferences sharedPrefs = PreferenceManager.getDefaultSharedPreferences(this);
		String phoneNumber = sharedPrefs.getString("serverPhoneNumber", null);
		try {
			SmsManager sms = SmsManager.getDefault();
			sms.sendTextMessage(phoneNumber, null, message, null, null);
			Toast.makeText(getApplicationContext(), getString(R.string.notif_sms_sent), Toast.LENGTH_LONG).show();
		  } catch (Exception e) {
			Toast.makeText(getApplicationContext(), getString(R.string.notif_sms_sent), Toast.LENGTH_LONG).show();
			e.printStackTrace();
			return false;
		  }
		return true;
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// required for settings to work
		getMenuInflater().inflate(R.menu.smir, menu);
		return true;
	}


    @Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Android HW button for settings
		switch (item.getItemId()) {
            case R.id.menu_alert:
                Intent a = new Intent(SMIR.this, AlertActivity.class);
                startActivity(a);
                break;
            case R.id.menu_settings:
                Intent i = new Intent(this, Preferences.class);
                startActivityForResult(i, Constants.RESULT_SETTINGS);
                break;
            case R.id.app_version:
                // Toast.makeText(getApplicationContext(), getString(R.string.app_version_num), Toast.LENGTH_LONG).show();
               // displayVersionPopup();

                break;
		}
		return true;
	}
}
