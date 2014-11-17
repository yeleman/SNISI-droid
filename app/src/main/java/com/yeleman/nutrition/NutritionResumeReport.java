package com.yeleman.nutrition;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TableRow;
import android.widget.TextView;

import com.yeleman.snisidroid.Constants;
import com.yeleman.snisidroid.R;

import org.w3c.dom.Text;

/**
 * Created by fad on 14/11/14.
 */
public class NutritionResumeReport extends Activity {
    private final static String TAG = Constants.getLogTag("NutritionResumeReport");

    private boolean is_urenam, is_urenas, is_ureni;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.nutrition_resume_report);
        Log.d(TAG, "onCreate NutritionResumeReport");
        setupUI();
    }

    protected void setupUI() {
        Log.d(TAG, "setupUI NutritionResumeReport");
        NutritionMonthlyReportData report = NutritionMonthlyReportData.get();

        SharedPreferences sharedPrefs =
                PreferenceManager.getDefaultSharedPreferences(this);

        is_urenam = sharedPrefs.getBoolean("hc_is_urenam", false);
        is_urenas = sharedPrefs.getBoolean("hc_is_urenas", false);
        is_ureni = sharedPrefs.getBoolean("hc_is_ureni", false);

        LayoutInflater inflater = (LayoutInflater) getBaseContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        if(is_urenam || is_urenas) {
            // URENAM + URENAS
            ViewGroup urenamAndUrenasParent = (ViewGroup) findViewById(R.id.tableURENAMAndURENAS);
            View inflated_urenam_and_urenas = inflater.inflate(R.layout.nutrition_resume_uren_unit,
                    urenamAndUrenasParent);
            TextView startTotalsValueUrenasAndURENAM = (TextView) inflated_urenam_and_urenas.findViewById(R.id.startTotalsValue);
            startTotalsValueUrenasAndURENAM.setText(String.valueOf(report.totalStartURENAMAndRENAS()));
            TextView startTotalValueFUrenasAndURENAM = (TextView) inflated_urenam_and_urenas.findViewById(R.id.startTotalValueF);
            startTotalValueFUrenasAndURENAM.setText(String.valueOf(report.totalStartFURENAMAndRENAS()));
            TextView startTotalValueMUrenasAndURENAM = (TextView) inflated_urenam_and_urenas.findViewById(R.id.startTotalValueM);
            startTotalValueMUrenasAndURENAM.setText(String.valueOf(report.totalStartMURENAMAndRENAS()));

            TextView admissionTotalsValueUrenasAndURENAM = (TextView) inflated_urenam_and_urenas.findViewById(R.id.admissionTotalsValue);
            admissionTotalsValueUrenasAndURENAM.setText(String.valueOf(report.totalInURENAMAndRENAS()));
            TextView admissionTotalValueFUrenasAndURENAM = (TextView) inflated_urenam_and_urenas.findViewById(R.id.admissionTotalValueF);
            admissionTotalValueFUrenasAndURENAM.setText(String.valueOf(report.totalInFURENAMAndRENAS()));
            TextView admissionTotalValueMUrenasAndURENAM = (TextView) inflated_urenam_and_urenas.findViewById(R.id.admissionTotalValueM);
            admissionTotalValueMUrenasAndURENAM.setText(String.valueOf(report.totalInMURENAMAndRENAS()));

            TextView outTotalsValueUrenasAndURENAM = (TextView) inflated_urenam_and_urenas.findViewById(R.id.outTotalsValue);
            outTotalsValueUrenasAndURENAM.setText(String.valueOf(report.totalOutURENAMAndRENAS()));
            TextView outTotalValueFUrenasAndURENAM = (TextView) inflated_urenam_and_urenas.findViewById(R.id.outTotalValueF);
            outTotalValueFUrenasAndURENAM.setText(String.valueOf(report.totalOutFURENAMAndRENAS()));
            TextView outTotalValueMUrenasAndURENAM = (TextView) inflated_urenam_and_urenas.findViewById(R.id.outTotalValueM);
            outTotalValueMUrenasAndURENAM.setText(String.valueOf(report.totalOutMURENAMAndRENAS()));

            TextView endTotalsValueUrenasAndURENAM = (TextView) inflated_urenam_and_urenas.findViewById(R.id.endTotalsValue);
            endTotalsValueUrenasAndURENAM.setText(String.valueOf(report.totalEndURENAMAndRENAS()));
            TextView endTotalValueFUrenasAndURENAM = (TextView) inflated_urenam_and_urenas.findViewById(R.id.endTotalValueF);
            endTotalValueFUrenasAndURENAM.setText(String.valueOf(report.totalEndFURENAMAndRENAS()));
            TextView endTotalValueMUrenasAndURENAM = (TextView) inflated_urenam_and_urenas.findViewById(R.id.endTotalValueM);
            endTotalValueMUrenasAndURENAM.setText(String.valueOf(report.totalEndMURENAMAndRENAS()));
        }
        if(is_ureni) {
            // URENI
            ViewGroup ureniPrent = (ViewGroup) findViewById(R.id.tableURENI);
            View inflated_ureni = inflater.inflate(R.layout.nutrition_resume_uren_unit, ureniPrent);
            TextView ureniLabel = (TextView) inflated_ureni.findViewById(R.id.titleLabelUREN);
            ureniLabel.setText("URENI");
            TextView startTotalsValueUreni = (TextView) inflated_ureni.findViewById(R.id.startTotalsValue);
            startTotalsValueUreni.setText(String.valueOf(report.totalStartURENI()));
            TextView startTotalValueFUreni = (TextView) inflated_ureni.findViewById(R.id.startTotalValueF);
            startTotalValueFUreni.setText(String.valueOf(report.totalStartFURENI()));
            TextView startTotalValueMUreni = (TextView) inflated_ureni.findViewById(R.id.startTotalValueM);
            startTotalValueMUreni.setText(String.valueOf(report.totalStartMURENI()));

            TextView admissionTotalsValueUreni = (TextView) inflated_ureni.findViewById(R.id.admissionTotalsValue);
            admissionTotalsValueUreni.setText(String.valueOf(report.totalInURENI()));
            TextView admissionTotalValueFUreni = (TextView) inflated_ureni.findViewById(R.id.admissionTotalValueF);
            admissionTotalValueFUreni.setText(String.valueOf(report.totalInFURENI()));
            TextView admissionTotalValueMUreni = (TextView) inflated_ureni.findViewById(R.id.admissionTotalValueM);
            admissionTotalValueMUreni.setText(String.valueOf(report.totalInMURENI()));

            TextView outTotalsValueUreni = (TextView) inflated_ureni.findViewById(R.id.outTotalsValue);
            outTotalsValueUreni.setText(String.valueOf(report.totalOutURENI()));
            TextView outTotalValueFUreni = (TextView) inflated_ureni.findViewById(R.id.outTotalValueF);
            outTotalValueFUreni.setText(String.valueOf(report.totalOutFURENI()));
            TextView outTotalValueMUreni = (TextView) inflated_ureni.findViewById(R.id.outTotalValueM);
            outTotalValueMUreni.setText(String.valueOf(report.totalOutMURENI()));

            TextView endTotalsValueUreni = (TextView) inflated_ureni.findViewById(R.id.endTotalsValue);
            endTotalsValueUreni.setText(String.valueOf(report.totalEndURENI()));
            TextView endTotalValueFUreni = (TextView) inflated_ureni.findViewById(R.id.endTotalValueF);
            endTotalValueFUreni.setText(String.valueOf(report.totalEndFURENI()));
            TextView endTotalValueMUreni = (TextView) inflated_ureni.findViewById(R.id.endTotalValueM);
            endTotalValueMUreni.setText(String.valueOf(report.totalEndMURENI()));
        }
        // Inputs
        TextView plumpyBalance = (TextView) findViewById(R.id.plumpyBalance);
        plumpyBalance.setText(String.valueOf(report.balancePlumpy()));

        TextView milkF75Balance = (TextView) findViewById(R.id.milkF75Balance);
        milkF75Balance.setText(String.valueOf(report.balanceMilkF75()));

        TextView milkF100Balance = (TextView) findViewById(R.id.milkF100Balance);
        milkF100Balance.setText(String.valueOf(report.balanceMilkF100()));

        TextView resomalBalance = (TextView) findViewById(R.id.resumeBalance);
        resomalBalance.setText(String.valueOf(report.balanceResomal()));
        if (!is_ureni) {
            TableRow resomalParent = (TableRow) findViewById(R.id.resomalRow);
            resomalParent.setVisibility(View.GONE);
            TableRow milkF75Parent = (TableRow) findViewById(R.id.milkF75Row);
            milkF75Parent.setVisibility(View.GONE);
            TableRow milkF100Parent = (TableRow) findViewById(R.id.milkF100Row);
            milkF100Parent.setVisibility(View.GONE);
        }
        TextView plumpySupBalance = (TextView) findViewById(R.id.plumpySupBalance);
        plumpySupBalance.setText(String.valueOf(report.balancePlumpySup()));

        TextView supercerealBalance = (TextView) findViewById(R.id.supercerealBalance);
        supercerealBalance.setText(String.valueOf(report.balanceSupercereal()));

        TextView supercerealPlusBalance = (TextView) findViewById(R.id.supercerealPlusBalance);
        supercerealPlusBalance.setText(String.valueOf(report.balanceSupercerealPlus()));

        TextView oilBalance = (TextView) findViewById(R.id.oilBalance);
        oilBalance.setText(String.valueOf(report.balanceOil()));

        TextView amoxycilline125mgVialsBalance = (TextView) findViewById(R.id.amoxycilline125mgVialsBalance);
        amoxycilline125mgVialsBalance.setText(String.valueOf(report.balanceAmoxycilline125mgVials()));

        TextView amoxycilline250mgVialsBalance = (TextView) findViewById(R.id.amoxycilline250mgVialsBalance);
        amoxycilline250mgVialsBalance.setText(String.valueOf(report.balanceAmoxycilline250mgCaps()));

        TextView albendazole400mgBalance = (TextView) findViewById(R.id.albendazole400mgBalance);
        albendazole400mgBalance.setText(String.valueOf(report.balanceAlbendazole400mg()));

        TextView vita100KUiInjectableBalance = (TextView) findViewById(R.id.vita100KUiInjectableBalance);
        vita100KUiInjectableBalance.setText(String.valueOf(report.balanceVita100KUiInjectable()));

        TextView vita200KUiInjectableBalance = (TextView) findViewById(R.id.vita200KUiInjectableBalance);
        vita200KUiInjectableBalance.setText(String.valueOf(report.balanceVita200KUiInjectable()));

        TextView ironFolicAcidBalance = (TextView) findViewById(R.id.ironFolicAcidBalance);
        ironFolicAcidBalance.setText(String.valueOf(report.balanceIronFolicAcid()));
    }
}