package com.yeleman.snisidroid;

import android.app.AlertDialog;
import android.content.DialogInterface;

import com.yeleman.smir.SMIR;

import static android.provider.Settings.Global.getString;
import static java.lang.String.format;
/**
 * Created by fad on 21/10/14.
 */

public class Popups {

    public static class displayVersionPopup extends Popups {
        public displayVersionPopup(SNISI snisi) {
            super();
            AlertDialog.Builder versionBuilder = new AlertDialog.Builder(snisi);
            versionBuilder.setTitle(R.string.app_name);
            String versionName = BuildConfig.VERSION_NAME;
            String msg_version = format("Version %s \n\nEn cas de problème contactez ANTIM.", versionName);
            versionBuilder.setMessage(msg_version);
            versionBuilder.setIcon(R.drawable.ic_launcher);
            versionBuilder.setPositiveButton("OK",
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            // Do nothing but close the dialog
                        }
                    });
            // Remember, create doesn't show the dialog
            AlertDialog helpDialog = versionBuilder.create();
            helpDialog.show();
        }
    }

    public static class displayErrorPopup extends Popups {

        public displayErrorPopup(SMIR smir, String message) {
            super();
            AlertDialog.Builder helpBuilder = new AlertDialog.Builder(smir);
            helpBuilder.setTitle("Erreur !");
            helpBuilder.setMessage("Impossible d'envoyer le rapport :\n\n" + message + "\n\nVous devez corriger et re-envoyer.");
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

}
