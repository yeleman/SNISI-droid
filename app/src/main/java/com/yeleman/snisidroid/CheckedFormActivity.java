package com.yeleman.snisidroid;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.PendingIntent;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.telephony.SmsManager;
import android.telephony.SmsMessage;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;

public class CheckedFormActivity extends Activity implements SMSUpdater {

    private final static String TAG = "SNISILog-CheckedFormActivity";

    /* progress dialog */
    private ProgressDialog progressDialog;

    /* SMS Receiver */
    private SMSReceiver mSmsReceiver = null;
    private SMSSentReceiver mSmsSentReceiver = null;
    private SMSDeliveredReceiver mSmsDeliveredReceiver = null;

    /* Keep an internal state of input validation for each fields */
	protected LinkedHashMap<Integer, Boolean> checkedFields = new LinkedHashMap<Integer, Boolean>();

	protected void updateFieldCheckedStatus(EditText editText) {
		updateFieldCheckedStatus(editText, false);
	}

	protected void updateFieldCheckedStatus(EditText editText, Boolean status) {
		checkedFields.put(editText.getId(), status);
	}

	/* Abstract methods */
	protected void setupInvalidInputChecks() {}
	protected boolean ensureDataCoherence() { return false; }
	protected String buildSMSText() { return ""; }
	protected void storeReportData() {}
	protected void restoreReportData() {}

	/* Visual feedback for invalid and incorect data */
	protected void addErrorToField(EditText editText, String message) {
		editText.setError(message);
		// editText.requestFocus();
	}

	protected boolean doCheckAndProceed(boolean test, String error_msg, EditText editText) {
		if (test) {
            addErrorToField(editText, error_msg);
			return false;
		} else {
			addErrorToField(editText, null);
		}
		return true;
	}

	public void fireErrorDialog(Activity activity, String errorMsg, final EditText fieldToReturnTo) {
        AlertDialog.Builder errorDialogBuilder = Popups.getDialogBuilder(
                activity, getString(R.string.error_dialog_title),
                errorMsg, false);
        errorDialogBuilder.setPositiveButton(R.string.error_dialog_button_text,
            new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
                    // close the dialog and focus on the requested field
                    if (fieldToReturnTo != null) {
                    	fieldToReturnTo.requestFocus();	
                    }
                }
            });
        AlertDialog errorDialog = errorDialogBuilder.create();
        errorDialog.show();
        // update color of popup to show it's an error.
        Popups.updatePopupForStatus(errorDialog, Constants.SMS_ERROR);
    }

	/* General checking methods */
	protected boolean ensureValidInputs(boolean focusOnFailing) {
    	for (Map.Entry<Integer, Boolean> entry : checkedFields.entrySet()) {
 			if (!entry.getValue()) {
 				if (focusOnFailing) {
 					EditText field = (EditText) findViewById(entry.getKey());
                    field.setText(field.getText());
 					field.requestFocus();
 				}
 				return false;
 			}
		}
        return true;
    }

    protected void removeFocusFromFields() {
        InputMethodManager imm = (InputMethodManager) getSystemService(
                Context.INPUT_METHOD_SERVICE);
        imm.toggleSoftInput(InputMethodManager.HIDE_IMPLICIT_ONLY, 0);
    }

    protected boolean checkInputsAndCoherence() {
        // remove focus so to remove
        removeFocusFromFields();

    	if (!ensureValidInputs(true)) {
    		Log.d(TAG, "Invalid inputs");
    		return false;
    	}
    	if (!ensureDataCoherence()) {
    		Log.d(TAG, "Not coherent inputs");
    		return false;
    	}
    	Log.i(TAG, "data looks good");
    	return true;
    }

	/* Input Validation Checks (standalone functions) */
	protected boolean assertNotEmpty(EditText editText) {
		boolean test = (editText.getText().toString().trim().length() == 0);
		String error_msg = String.format(getString(R.string.error_field_empty));
		return doCheckAndProceed(test, error_msg, editText);
	}

	protected boolean assertAtLeastThisLong(EditText editText, int min_chars) {
		boolean test = (editText.getText().toString().trim().length() < min_chars);
		String error_msg = String.format(getString(R.string.error_field_min_chars),
                String.valueOf(min_chars));
		return doCheckAndProceed(test, error_msg, editText);
	}

	protected boolean assertPositiveInteger(EditText editText) {
		boolean test = (integerFromField(editText, -1) < 0);
		String error_msg = String.format(
			getString(R.string.error_field_positive_integer));
		return doCheckAndProceed(test, error_msg, editText);
	}

	/* Input Validation Checks (EventListener creators) */
	protected void setAssertNotEmpty(final EditText editText) {
    	updateFieldCheckedStatus(editText, false);
    	editText.addTextChangedListener(new TextWatcher() {
            public void afterTextChanged(Editable s) {
            	updateFieldCheckedStatus(editText, assertNotEmpty(editText));
            }

            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
            public void onTextChanged(CharSequence s, int start, int before, int count) {}
        });
    }

    protected void setAssertAtLeastThisLong(final EditText editText, final int min_chars) {

    	updateFieldCheckedStatus(editText, false);
    	editText.addTextChangedListener(new TextWatcher() {
            public void afterTextChanged(Editable s) {
            	updateFieldCheckedStatus(editText, assertAtLeastThisLong(editText, min_chars));
            }

            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
            public void onTextChanged(CharSequence s, int start, int before, int count) {}
        });
    }

    protected void setAssertPositiveInteger(final EditText editText) {

    	updateFieldCheckedStatus(editText, false);
    	editText.addTextChangedListener(new TextWatcher() {
            public void afterTextChanged(Editable s) {
            	updateFieldCheckedStatus(editText, assertPositiveInteger(editText));
            }

            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
            public void onTextChanged(CharSequence s, int start, int before, int count) {}
        });
    }

    /* Data Coherence helpers */
    protected boolean mustBeInferior(EditText fieldToReturnTo, EditText fieldA, EditText fieldB) {
    	int valueA = integerFromField(fieldA);
    	int valueB = integerFromField(fieldB);
    	String errorMsg = String.format(getString(R.string.error_must_be_inferior),
    									fieldA.getHint(), valueA,
    									fieldB.getHint(), valueB);
    	if (valueA >= valueB) {
    		fireErrorDialog(this, errorMsg, fieldToReturnTo);
            return false;
    	}
        return true;
    }

    protected boolean mustBeInferiorOrEqual(EditText fieldToReturnTo, EditText fieldA, EditText fieldB) {
        int valueA = integerFromField(fieldA);
        int valueB = integerFromField(fieldB);
        String errorMsg = String.format(getString(R.string.error_must_be_inferior_or_equal),
                fieldA.getHint(), valueA,
                fieldB.getHint(), valueB);
        if (valueA > valueB) {
            fireErrorDialog(this, errorMsg, fieldToReturnTo);
            return false;
        }
        return true;
    }

    /* Bundled data-ok callbacks */
    protected void checkAndFinish() {
		if (!checkInputsAndCoherence()) { return; }
		finish();
	}

	protected void checkAndLogSMS() {
		if (!checkInputsAndCoherence()) { return; }

		String sms_text = buildSMSText();
        Log.d(TAG, sms_text);
		
		finish();
	}

    protected void checkAndSubmitSMSAction() {
    	if (!checkInputsAndCoherence()) { return; }
        
        String sms_text = buildSMSText();
        Log.d(TAG, sms_text);

		boolean succeeded = transmitSMSForReply(sms_text);
		if (!succeeded) {
			Log.e(TAG, "Unable to send SMS (exception on send command).");
		}
    }

    /* Input CleanUp/convertions */
    protected String stringFromField(EditText editText) {
        return editText.getText().toString().trim();
    }

    protected int integerFromField(EditText editText) {
        return integerFromField(editText, -1);
    }
    protected int integerFromField(EditText editText, int fallback) {
        String text = stringFromField(editText);
        if (text.length() > 0) {
            return Integer.parseInt(text);
        }
        return fallback;
    }

    protected void setTextOnField(EditText editText, Object value) {
        editText.setText(String.valueOf(value));
    }

    /* SMS Submission Code */
    protected boolean transmitSMS(String message) {
		// retrieve server number
		SharedPreferences sharedPrefs = PreferenceManager.getDefaultSharedPreferences(this);
		String serverNumber = sharedPrefs.getString("serverPhoneNumber", Constants.server_number);
		try {
			SmsManager sms = SmsManager.getDefault();
			PendingIntent piSent = PendingIntent.getBroadcast(
				this, 0, new Intent(Constants.SMS_SENT_INTENT), 0);
			PendingIntent piDelivered = PendingIntent.getBroadcast(
				this, 0, new Intent(Constants.SMS_DELIVERED_INTENT), 0);
			sms.sendTextMessage(serverNumber, null, message, piSent, piDelivered);
			Toast toast = Toast.makeText(getApplicationContext(), getString(R.string.notif_sms_sent), Toast.LENGTH_LONG);
			toast.show();
		} catch (Exception e) {
			Toast.makeText(getApplicationContext(), getString(R.string.notif_sms_sent), Toast.LENGTH_LONG).show();
			e.printStackTrace();
			return false;
		}
		return true;
    }

    protected boolean transmitSMSForReply(String message) {
        // display loading message
        progressDialog = Popups.getStandardProgressDialog(
                this, getString(R.string.sending_sms_report_title),
                getString(R.string.sending_sms_report_body), false);
        progressDialog.show();
        setProgressTimeOut(Constants.NB_SECONDS_WAIT_FOR_REPLY * 1000);

        // send message
        return transmitSMS(message);
    }

    protected void setProgressTimeOut(long time) {
        final Activity activity = this;
        new Handler().postDelayed(new Runnable() {
            public void run() {
                // close progress
                if (progressDialog != null) {
                    progressDialog.dismiss();
                }

                if (!progressDialog.isShowing()) {
                    return;
                }

                // display popup to warn user
                AlertDialog dialog = Popups.getStandardDialog(
                        activity, getString(R.string.sms_timeout_dialog_title),
                        getString(R.string.sms_timeout_dialog_body), false, false);
                dialog.show();
                Popups.updatePopupForStatus(dialog, Constants.SMS_WARNING);
            }
        }, time);
    }

    protected void registerSMSReceiver() {
        if (mSmsReceiver != null) {
            Log.d(TAG, "registering SMSReceiver");
            IntentFilter filter = new IntentFilter();
            filter.setPriority(1);
            filter.addAction("android.provider.Telephony.SMS_RECEIVED");
            registerReceiver(mSmsReceiver, filter);
        }

        if (mSmsSentReceiver != null) {
            registerReceiver(mSmsSentReceiver, new IntentFilter(Constants.SMS_SENT_INTENT));
            Log.d(TAG, "Registering BroadcastReceiver SMS_SENT");
        }

        if (mSmsDeliveredReceiver != null) {
            registerReceiver(mSmsDeliveredReceiver, new IntentFilter(Constants.SMS_DELIVERED_INTENT));
            Log.d(TAG, "Registering BroadcastReceiver SMS_DELIVERED");
        }
    }

    protected void unRegisterSMSReceiver() {
        if (mSmsReceiver != null) {
            Log.d(TAG, "unregistering SMSReceiver");
            unregisterReceiver(mSmsReceiver);
        }

        if (mSmsSentReceiver != null) {
            Log.d(TAG, "unregistering SMSSentReceiver");
            unregisterReceiver(mSmsSentReceiver);
        }

        if (mSmsDeliveredReceiver != null) {
            Log.d(TAG, "unregistering SMSDeliveredReceiver");
            unregisterReceiver(mSmsDeliveredReceiver);
        }
    }

    protected void setupSMSReceiver() {
        Log.d(TAG, "setupSMSReceiver - CheckedFormActivity");
        progressDialog = null; //new ProgressDialog(this);
        // SMS Replies from server
        mSmsReceiver = new SMSReceiver(this);
        // SMS sent feedback
        mSmsSentReceiver = new SMSSentReceiver(this);
        // SMS delivery reports
        mSmsDeliveredReceiver = new SMSDeliveredReceiver(this);
        //registerSMSReceiver();
    }

    @Override
    protected void onResume()
    {
        super.onResume();
        registerSMSReceiver();
    }

    @Override
    public void onPause()
    {
        super.onPause();
        unRegisterSMSReceiver();
    }

    public void gotSMSStatusUpdate(int status, String message) {
        // SMS is in good way, let's keep evertything in place
        if (status == Constants.SMS_SUCCESS) {
            return;
        }

        // remove progress dialog
        if (progressDialog != null) {
            progressDialog.dismiss();
        }

        // display error message
        int textColor = Constants.getColorForStatus(status);
        AlertDialog smsMessageDialog = Popups.getStandardDialog(
                this, getString(R.string.error_sms_not_sent_title),
                String.format(getString(R.string.error_sms_not_sent_body), message),
                false, false);
        smsMessageDialog.show();
        // change color
        Popups.updatePopupForStatus(smsMessageDialog, status);
    }

    public void gotSms(ArrayList<SmsMessage> messages) {
        // remove progress dialog
        if (progressDialog != null) {
            progressDialog.dismiss();
        }

        int responseStatus = Constants.SMS_UNKNOWN;

        // concatenate Strings from SMS (usualy just one)
        String textToDisplay = "";
        for (SmsMessage smsMessage : messages) {
            String smsText = smsMessage.getMessageBody();
            int smsStatus = Constants.getSMSStatus(smsText);
            // change status yet conserve any error one (ERROR, WARNING) over a success one
            if (smsStatus == Constants.SMS_SUCCESS) {
                if (responseStatus != Constants.SMS_ERROR &&
                    responseStatus != Constants.SMS_WARNING) {
                    responseStatus = smsStatus;
                }
            } else {
                responseStatus = smsStatus;
            }
            textToDisplay += smsText;
        }
        final int finalResponseStatus = responseStatus;
        
        // display SMS message
        int textColor = Constants.getColorForStatus(responseStatus);
        AlertDialog smsMessageDialog = Popups.getStandardDialog(
                this, getString(R.string.server_sms_received_title),
                textToDisplay,
                false,
                (finalResponseStatus == Constants.SMS_SUCCESS));
        smsMessageDialog.show();
        // change color
        Popups.updatePopupForStatus(smsMessageDialog, finalResponseStatus);


    }

    /* Data Restore Dialog */
    public void requestForResumeReport(Activity activity, ReportData report) {

        Log.d(TAG, "report.id: " + String.valueOf(report.getId()));

    	if (report.getName() == null) {
    		// no report data present (only very first time)
            Log.d(TAG, "report.name is null. first time");
    		return;
    	}
        Log.d(TAG, "report.name is not null.");
        AlertDialog.Builder questionDialogBuilder = Popups.getDialogBuilder(this,
                getString(R.string.resume_report_title),
                String.format(getString(R.string.resume_report_body), report.getName()),
                false);
        questionDialogBuilder.setPositiveButton(R.string.resume_report_ok_label,
            new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
                    // close the dialog, restore data into fields
                    restoreReportData();
                }
            });
        questionDialogBuilder.setNeutralButton(R.string.resume_report_cancel_label, Popups.getBlankClickListener());
        AlertDialog errorDialog = questionDialogBuilder.create();
        errorDialog.show();
    }

}