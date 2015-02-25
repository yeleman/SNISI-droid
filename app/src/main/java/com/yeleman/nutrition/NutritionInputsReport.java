package com.yeleman.nutrition;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.yeleman.snisidroid.CheckedFormActivity;
import com.yeleman.snisidroid.Constants;
import com.yeleman.snisidroid.R;


/**
 * Created by fad on 29/10/14.
 */
public class NutritionInputsReport extends CheckedFormActivity {
    private final static String TAG = Constants.getLogTag("NutritionInputsReport");

    protected EditText plumpyNutInitialField;
    protected EditText plumpyNutReceivedField;
    protected EditText plumpyNutUsedField;
    protected EditText plumpyNutLostField;
    protected EditText milkF75InitialField;
    protected EditText milkF75ReceivedField;
    protected EditText milkF75UsedField;
    protected EditText milkF75LostField;
    protected EditText milkF100InitialField;
    protected EditText milkF100ReceivedField;
    protected EditText milkF100UsedField;
    protected EditText milkF100LostField;
    protected EditText resomalInitialField;
    protected EditText resomalReceivedField;
    protected EditText resomalUsedField;
    protected EditText resomalLostField;
    protected EditText plumpySupInitialField;
    protected EditText plumpySupReceivedField;
    protected EditText plumpySupUsedField;
    protected EditText plumpySupLostField;
    protected EditText supercerealInitialField;
    protected EditText supercerealReceivedField;
    protected EditText supercerealUsedField;
    protected EditText supercerealLostField;
    protected EditText supercerealPlusInitialField;
    protected EditText supercerealPlusReceivedField;
    protected EditText supercerealPlusUsedField;
    protected EditText supercerealPlusLostField;
    protected EditText oilInitialField;
    protected EditText oilReceivedField;
    protected EditText oilUsedField;
    protected EditText oilLostField;
    protected EditText amoxycilline125VialsInitialField;
    protected EditText amoxycilline125VialsReceivedField;
    protected EditText amoxycilline125VialsUsedField;
    protected EditText amoxycilline125VialsLostField;
    protected EditText amoxycilline250CapsInitialField;
    protected EditText amoxycilline250CapsReceivedField;
    protected EditText amoxycilline250CapsUsedField;
    protected EditText amoxycilline250CapsLostField;
    protected EditText albendazole400InitialField;
    protected EditText albendazole400ReceivedField;
    protected EditText albendazole400UsedField;
    protected EditText albendazole400LostField;
    protected EditText vita100InjectableInitialField;
    protected EditText vita100InjectableReceivedField;
    protected EditText vita100InjectableUsedField;
    protected EditText vita100InjectableLostField;
    protected EditText vita200InjectableInitialField;
    protected EditText vita200InjectableReceivedField;
    protected EditText vita200InjectableUsedField;
    protected EditText vita200InjectableLostField;
    protected EditText ironFolicAcidInitialField;
    protected EditText ironFolicAcidReceivedField;
    protected EditText ironFolicAcidUsedField;
    protected EditText ironFolicAcidLostField;

    protected Button saveButton;
    private boolean is_ureni;
    private boolean is_urenam;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.nutrition_inputs_report);
        setTitle(String.format(getString(R.string.nutrition_fillout_section),
                getString(R.string.inputs)));
        Log.d(TAG, "onCreate NutritionInputsReport");

        setupSMSReceiver();
        setupUI();
    }

    @Override
    public void onResume() {
        super.onResume();
        setupUI();
    }

    protected void setupUI() {
        Log.d(TAG, "setupUI NutritionInputsReport");

        SharedPreferences sharedPrefs =
                PreferenceManager.getDefaultSharedPreferences(this);
        is_ureni = sharedPrefs.getBoolean("hc_is_ureni", false);
        is_urenam = sharedPrefs.getBoolean("hc_is_urenam", false);

        plumpyNutInitialField = (EditText) findViewById(R.id.plumpyNutInitialField);
        plumpyNutReceivedField = (EditText) findViewById(R.id.plumpyNutReceivedField);
        plumpyNutUsedField = (EditText) findViewById(R.id.plumpyNutUsedField);
        plumpyNutLostField = (EditText) findViewById(R.id.plumpyNutLostField);
        // Milk_F75
        milkF75InitialField = (EditText) findViewById(R.id.milkF75InitialField);
        milkF75ReceivedField = (EditText) findViewById(R.id.milkF75ReceivedField);
        milkF75UsedField = (EditText) findViewById(R.id.milkF75UsedField);
        milkF75LostField = (EditText) findViewById(R.id.milkF75LostField);
        // Milk_F100
        milkF100InitialField = (EditText) findViewById(R.id.milkF100InitialField);
        milkF100ReceivedField = (EditText) findViewById(R.id.milkF100ReceivedField);
        milkF100UsedField = (EditText) findViewById(R.id.milkF100UsedField);
        milkF100LostField = (EditText) findViewById(R.id.milkF100LostField);
        // Resomal
        resomalInitialField = (EditText) findViewById(R.id.resomalInitialField);
        resomalReceivedField = (EditText) findViewById(R.id.resomalReceivedField);
        resomalUsedField = (EditText) findViewById(R.id.resomalUsedField);
        resomalLostField = (EditText) findViewById(R.id.resomalLostField);
        if (!is_ureni) {
            LinearLayout resomalParent = (LinearLayout) findViewById(R.id.resomalLinearLayout);
            resomalParent.setVisibility(View.GONE);
            LinearLayout milkF75Parent = (LinearLayout) findViewById(R.id.milkF75LinearLayout);
            milkF75Parent.setVisibility(View.GONE);
            LinearLayout milkF100Parent = (LinearLayout) findViewById(R.id.milkF100LinearLayout);
            milkF100Parent.setVisibility(View.GONE);
        }
        // Plumpy_Sup
        plumpySupInitialField = (EditText) findViewById(R.id.plumpySupInitialField);
        plumpySupReceivedField = (EditText) findViewById(R.id.plumpySupReceivedField);
        plumpySupUsedField = (EditText) findViewById(R.id.plumpySupUsedField);
        plumpySupLostField = (EditText) findViewById(R.id.plumpySupLostField);
        // Supercereal
        supercerealInitialField = (EditText) findViewById(R.id.supercerealInitialField);
        supercerealReceivedField = (EditText) findViewById(R.id.supercerealReceivedField);
        supercerealUsedField = (EditText) findViewById(R.id.supercerealUsedField);
        supercerealLostField = (EditText) findViewById(R.id.supercerealLostField);
        // Supercereal_Plus
        supercerealPlusInitialField = (EditText) findViewById(R.id.supercerealPlusInitialField);
        supercerealPlusReceivedField = (EditText) findViewById(R.id.supercerealPlusReceivedField);
        supercerealPlusUsedField = (EditText) findViewById(R.id.supercerealPlusUsedField);
        supercerealPlusLostField = (EditText) findViewById(R.id.supercerealPlusLostField);
        // Oil
        oilInitialField = (EditText) findViewById(R.id.oilInitialField);
        oilReceivedField = (EditText) findViewById(R.id.oilReceivedField);
        oilUsedField = (EditText) findViewById(R.id.oilUsedField);
        oilLostField = (EditText) findViewById(R.id.oilLostField);
        if (!is_urenam) {
            LinearLayout plumpySupParent = (LinearLayout) findViewById(R.id.plumpySupLinearLayout);
            plumpySupParent.setVisibility(View.GONE);
            LinearLayout supercerealParent = (LinearLayout) findViewById(R.id.supercerealLinearLayout);
            supercerealParent.setVisibility(View.GONE);
            LinearLayout supercerealPlusParent = (LinearLayout) findViewById(R.id.supercerealPlusLinearLayout);
            supercerealPlusParent.setVisibility(View.GONE);
            LinearLayout oilParent = (LinearLayout) findViewById(R.id.oilLinearLayout);
            oilParent.setVisibility(View.GONE);
            LinearLayout ironFolicAcidParent = (LinearLayout) findViewById(R.id.ironFolicAcidLinearLayout);
            ironFolicAcidParent.setVisibility(View.GONE);
        }
        // Amoxycilline 125mg Vials
        amoxycilline125VialsInitialField = (EditText) findViewById(R.id.amoxycilline125VialsInitialField);
        amoxycilline125VialsReceivedField = (EditText) findViewById(R.id.amoxycilline125VialsReceivedField);
        amoxycilline125VialsUsedField = (EditText) findViewById(R.id.amoxycilline125VialsUsedField);
        amoxycilline125VialsLostField = (EditText) findViewById(R.id.amoxycilline125VialsLostField);
        // Amoxycilline 250mg Caps
        amoxycilline250CapsInitialField = (EditText) findViewById(R.id.amoxycilline250CapsInitialField);
        amoxycilline250CapsReceivedField = (EditText) findViewById(R.id.amoxycilline250CapsReceivedField);
        amoxycilline250CapsUsedField = (EditText) findViewById(R.id.amoxycilline250CapsUsedField);
        amoxycilline250CapsLostField = (EditText) findViewById(R.id.amoxycilline250CapsLostField);
        // Albendazole 400mg
        albendazole400InitialField = (EditText) findViewById(R.id.albendazole400InitialField);
        albendazole400ReceivedField = (EditText) findViewById(R.id.albendazole400ReceivedField);
        albendazole400UsedField = (EditText) findViewById(R.id.albendazole400UsedField);
        albendazole400LostField = (EditText) findViewById(R.id.albendazole400LostField);
        // VitA 100K UI Injectable
        vita100InjectableInitialField = (EditText) findViewById(R.id.vita100InjectableInitialField);
        vita100InjectableReceivedField = (EditText) findViewById(R.id.vita100InjectableReceivedField);
        vita100InjectableUsedField = (EditText) findViewById(R.id.vita100InjectableUsedField);
        vita100InjectableLostField = (EditText) findViewById(R.id.vita100InjectableLostField);
        // VitA 200K UI Injectable
        vita200InjectableInitialField = (EditText) findViewById(R.id.vita200InjectableInitialField);
        vita200InjectableReceivedField = (EditText) findViewById(R.id.vita200InjectableReceivedField);
        vita200InjectableUsedField = (EditText) findViewById(R.id.vita200InjectableUsedField);
        vita200InjectableLostField = (EditText) findViewById(R.id.vita200InjectableLostField);
        // Iron_Folic_Acid
        ironFolicAcidInitialField = (EditText) findViewById(R.id.ironFolicAcidInitialField);
        ironFolicAcidReceivedField = (EditText) findViewById(R.id.ironFolicAcidReceivedField);
        ironFolicAcidUsedField = (EditText) findViewById(R.id.ironFolicAcidUsedField);
        ironFolicAcidLostField = (EditText) findViewById(R.id.ironFolicAcidLostField);

        // setup invalid inputs checks
        setupInvalidInputChecks();

        NutritionInputsReportData report = NutritionInputsReportData.get();
        if (report.input_is_complete) {
            restoreReportData();
        }

        saveButton = (Button) findViewById(R.id.saveButton);
        saveButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // ensure data is OK
                if (!checkInputsAndCoherence()) { return; }
                // save data to DB
                storeReportData();

                finish();
            }
        });
    }

    protected void storeReportData() {
        Log.d(TAG, "storeReportData");
        NutritionInputsReportData report = NutritionInputsReportData.get();
        report.updateMetaData();

        report.plumpy_nut_initial = integerFromField(plumpyNutInitialField);
        report.plumpy_nut_received = integerFromField(plumpyNutReceivedField);
        report.plumpy_nut_used = integerFromField(plumpyNutUsedField);
        report.plumpy_nut_lost = integerFromField(plumpyNutLostField);
        if(is_ureni) {
            report.milk_f75_initial = integerFromField(milkF75InitialField);
            report.milk_f75_received = integerFromField(milkF75ReceivedField);
            report.milk_f75_used = integerFromField(milkF75UsedField);
            report.milk_f75_lost = integerFromField(milkF75LostField);
            report.milk_f100_initial = integerFromField(milkF100InitialField);
            report.milk_f100_received = integerFromField(milkF100ReceivedField);
            report.milk_f100_used = integerFromField(milkF100UsedField);
            report.milk_f100_lost = integerFromField(milkF100LostField);
            report.resomal_initial = integerFromField(resomalInitialField);
            report.resomal_received = integerFromField(resomalReceivedField);
            report.resomal_used = integerFromField(resomalUsedField);
            report.resomal_lost = integerFromField(resomalLostField);
        }
        if(is_urenam) {
            report.plumpy_sup_initial = integerFromField(plumpySupInitialField);
            report.plumpy_sup_received = integerFromField(plumpySupReceivedField);
            report.plumpy_sup_used = integerFromField(plumpySupUsedField);
            report.plumpy_sup_lost = integerFromField(plumpySupLostField);
            report.supercereal_initial = floatFromField(supercerealInitialField);
            report.supercereal_received = floatFromField(supercerealReceivedField);
            report.supercereal_used = floatFromField(supercerealUsedField);
            report.supercereal_lost = floatFromField(supercerealLostField);
            report.supercereal_plus_initial = integerFromField(supercerealPlusInitialField);
            report.supercereal_plus_received = integerFromField(supercerealPlusReceivedField);
            report.supercereal_plus_used = integerFromField(supercerealPlusUsedField);
            report.supercereal_plus_lost = integerFromField(supercerealPlusLostField);
            report.oil_initial = integerFromField(oilInitialField);
            report.oil_received = integerFromField(oilReceivedField);
            report.oil_used = integerFromField(oilUsedField);
            report.oil_lost = integerFromField(oilLostField);
            report.iron_folic_acid_initial = integerFromField(ironFolicAcidInitialField);
            report.iron_folic_acid_received = integerFromField(ironFolicAcidReceivedField);
            report.iron_folic_acid_used = integerFromField(ironFolicAcidUsedField);
            report.iron_folic_acid_lost = integerFromField(ironFolicAcidLostField);
        }
        report.amoxycilline_125_vials_initial = integerFromField(amoxycilline125VialsInitialField);
        report.amoxycilline_125_vials_received = integerFromField(amoxycilline125VialsReceivedField);
        report.amoxycilline_125_vials_used = integerFromField(amoxycilline125VialsUsedField);
        report.amoxycilline_125_vials_lost = integerFromField(amoxycilline125VialsLostField);
        report.amoxycilline_250_caps_initial = integerFromField(amoxycilline250CapsInitialField);
        report.amoxycilline_250_caps_received = integerFromField(amoxycilline250CapsReceivedField);
        report.amoxycilline_250_caps_used = integerFromField(amoxycilline250CapsUsedField);
        report.amoxycilline_250_caps_lost = integerFromField(amoxycilline250CapsLostField);
        report.albendazole_400_initial = integerFromField(albendazole400InitialField);
        report.albendazole_400_received = integerFromField(albendazole400ReceivedField);
        report.albendazole_400_used = integerFromField(albendazole400UsedField);
        report.albendazole_400_lost = integerFromField(albendazole400LostField);
        report.vita_100_injectable_initial = integerFromField(vita100InjectableInitialField);
        report.vita_100_injectable_received = integerFromField(vita100InjectableReceivedField);
        report.vita_100_injectable_used = integerFromField(vita100InjectableUsedField);
        report.vita_100_injectable_lost = integerFromField(vita100InjectableLostField);
        report.vita_200_injectable_initial = integerFromField(vita200InjectableInitialField);
        report.vita_200_injectable_received = integerFromField(vita200InjectableReceivedField);
        report.vita_200_injectable_used = integerFromField(vita200InjectableUsedField);
        report.vita_200_injectable_lost = integerFromField(vita200InjectableLostField);
        report.input_is_complete = true;
        report.safeSave();
        Log.d(TAG, "storeReportData -- end");
    }

    protected void restoreReportData() {
        Log.d(TAG, "restoreReportData");
        NutritionInputsReportData report = NutritionInputsReportData.get();

        setTextOnField(plumpyNutInitialField, report.plumpy_nut_initial);
        setTextOnField(plumpyNutReceivedField, report.plumpy_nut_received);
        setTextOnField(plumpyNutUsedField, report.plumpy_nut_used);
        setTextOnField(plumpyNutLostField, report.plumpy_nut_lost);
        if(is_ureni){
            setTextOnField(milkF75InitialField, report.milk_f75_initial);
            setTextOnField(milkF75ReceivedField, report.milk_f75_received);
            setTextOnField(milkF75UsedField, report.milk_f75_used);
            setTextOnField(milkF75LostField, report.milk_f75_lost);
            setTextOnField(milkF100InitialField, report.milk_f100_initial);
            setTextOnField(milkF100ReceivedField, report.milk_f100_received);
            setTextOnField(milkF100UsedField, report.milk_f100_used);
            setTextOnField(milkF100LostField, report.milk_f100_lost);
            setTextOnField(resomalInitialField, report.resomal_initial);
            setTextOnField(resomalReceivedField, report.resomal_received);
            setTextOnField(resomalUsedField, report.resomal_used);
            setTextOnField(resomalLostField, report.resomal_lost);
        }
        if (is_urenam) {
            setTextOnField(plumpySupInitialField, report.plumpy_sup_initial);
            setTextOnField(plumpySupReceivedField, report.plumpy_sup_received);
            setTextOnField(plumpySupUsedField, report.plumpy_sup_used);
            setTextOnField(plumpySupLostField, report.plumpy_sup_lost);
            setTextOnField(supercerealInitialField, report.supercereal_initial);
            setTextOnField(supercerealReceivedField, report.supercereal_received);
            setTextOnField(supercerealUsedField, report.supercereal_used);
            setTextOnField(supercerealLostField, report.supercereal_lost);
            setTextOnField(supercerealPlusInitialField, report.supercereal_plus_initial);
            setTextOnField(supercerealPlusReceivedField, report.supercereal_plus_received);
            setTextOnField(supercerealPlusUsedField, report.supercereal_plus_used);
            setTextOnField(supercerealPlusLostField, report.supercereal_plus_lost);
            setTextOnField(oilInitialField, report.oil_initial);
            setTextOnField(oilReceivedField, report.oil_received);
            setTextOnField(oilUsedField, report.oil_used);
            setTextOnField(oilLostField, report.oil_lost);
            setTextOnField(ironFolicAcidInitialField, report.iron_folic_acid_initial);
            setTextOnField(ironFolicAcidReceivedField, report.iron_folic_acid_received);
            setTextOnField(ironFolicAcidUsedField, report.iron_folic_acid_used);
            setTextOnField(ironFolicAcidLostField, report.iron_folic_acid_lost);
        }
        setTextOnField(amoxycilline125VialsInitialField, report.amoxycilline_125_vials_initial);
        setTextOnField(amoxycilline125VialsReceivedField, report.amoxycilline_125_vials_received);
        setTextOnField(amoxycilline125VialsUsedField, report.amoxycilline_125_vials_used);
        setTextOnField(amoxycilline125VialsLostField, report.amoxycilline_125_vials_lost);
        setTextOnField(amoxycilline250CapsInitialField, report.amoxycilline_250_caps_initial);
        setTextOnField(amoxycilline250CapsReceivedField, report.amoxycilline_250_caps_received);
        setTextOnField(amoxycilline250CapsUsedField, report.amoxycilline_250_caps_used);
        setTextOnField(amoxycilline250CapsLostField, report.amoxycilline_250_caps_lost);
        setTextOnField(albendazole400InitialField, report.albendazole_400_initial);
        setTextOnField(albendazole400ReceivedField, report.albendazole_400_received);
        setTextOnField(albendazole400UsedField, report.albendazole_400_used);
        setTextOnField(albendazole400LostField, report.albendazole_400_lost);
        setTextOnField(vita100InjectableInitialField, report.vita_100_injectable_initial);
        setTextOnField(vita100InjectableReceivedField, report.vita_100_injectable_received);
        setTextOnField(vita100InjectableUsedField, report.vita_100_injectable_used);
        setTextOnField(vita100InjectableLostField, report.vita_100_injectable_lost);
        setTextOnField(vita200InjectableInitialField, report.vita_200_injectable_initial);
        setTextOnField(vita200InjectableReceivedField, report.vita_200_injectable_received);
        setTextOnField(vita200InjectableUsedField, report.vita_200_injectable_used);
        setTextOnField(vita200InjectableLostField, report.vita_200_injectable_lost);
    }

    protected void setupInvalidInputChecks() {
        setAssertPositiveInteger(plumpyNutInitialField);
        setAssertPositiveInteger(plumpyNutReceivedField);
        setAssertPositiveInteger(plumpyNutUsedField);
        setAssertPositiveInteger(plumpyNutLostField);
        if (is_ureni) {
            setAssertPositiveInteger(milkF75InitialField);
            setAssertPositiveInteger(milkF75ReceivedField);
            setAssertPositiveInteger(milkF75UsedField);
            setAssertPositiveInteger(milkF75LostField);
            setAssertPositiveInteger(milkF100InitialField);
            setAssertPositiveInteger(milkF100ReceivedField);
            setAssertPositiveInteger(milkF100UsedField);
            setAssertPositiveInteger(milkF100LostField);
            setAssertPositiveInteger(resomalInitialField);
            setAssertPositiveInteger(resomalReceivedField);
            setAssertPositiveInteger(resomalUsedField);
            setAssertPositiveInteger(resomalLostField);
        }
        if (is_urenam) {
            setAssertPositiveInteger(plumpySupInitialField);
            setAssertPositiveInteger(plumpySupReceivedField);
            setAssertPositiveInteger(plumpySupUsedField);
            setAssertPositiveInteger(plumpySupLostField);
            setAssertPositiveFloat(supercerealInitialField);
            setAssertPositiveFloat(supercerealReceivedField);
            setAssertPositiveFloat(supercerealUsedField);
            setAssertPositiveFloat(supercerealLostField);
            setAssertPositiveInteger(supercerealPlusInitialField);
            setAssertPositiveInteger(supercerealPlusReceivedField);
            setAssertPositiveInteger(supercerealPlusUsedField);
            setAssertPositiveInteger(supercerealPlusLostField);
            setAssertPositiveInteger(oilInitialField);
            setAssertPositiveInteger(oilReceivedField);
            setAssertPositiveInteger(oilUsedField);
            setAssertPositiveInteger(oilLostField);
            setAssertPositiveInteger(ironFolicAcidInitialField);
            setAssertPositiveInteger(ironFolicAcidReceivedField);
            setAssertPositiveInteger(ironFolicAcidUsedField);
            setAssertPositiveInteger(ironFolicAcidLostField);
        }
        setAssertPositiveInteger(amoxycilline125VialsInitialField);
        setAssertPositiveInteger(amoxycilline125VialsReceivedField);
        setAssertPositiveInteger(amoxycilline125VialsUsedField);
        setAssertPositiveInteger(amoxycilline125VialsLostField);
        setAssertPositiveInteger(amoxycilline250CapsInitialField);
        setAssertPositiveInteger(amoxycilline250CapsReceivedField);
        setAssertPositiveInteger(amoxycilline250CapsUsedField);
        setAssertPositiveInteger(amoxycilline250CapsLostField);
        setAssertPositiveInteger(albendazole400InitialField);
        setAssertPositiveInteger(albendazole400ReceivedField);
        setAssertPositiveInteger(albendazole400UsedField);
        setAssertPositiveInteger(albendazole400LostField);
        setAssertPositiveInteger(vita100InjectableInitialField);
        setAssertPositiveInteger(vita100InjectableReceivedField);
        setAssertPositiveInteger(vita100InjectableUsedField);
        setAssertPositiveInteger(vita100InjectableLostField);
        setAssertPositiveInteger(vita200InjectableInitialField);
        setAssertPositiveInteger(vita200InjectableReceivedField);
        setAssertPositiveInteger(vita200InjectableUsedField);
        setAssertPositiveInteger(vita200InjectableLostField);
    }

    protected boolean ensureDataCoherence() {
        boolean isEnsureDataCoherence;
        // Initial + Received >= Used + Lost
        isEnsureDataCoherence =  mustMatchStockCoherence(plumpyNutInitialField,
                                       plumpyNutReceivedField,
                                       plumpyNutUsedField,
                                       plumpyNutLostField) &&
               mustMatchStockCoherence(amoxycilline125VialsInitialField,
                                       amoxycilline125VialsReceivedField,
                                       amoxycilline125VialsUsedField,
                                       amoxycilline125VialsLostField) &&
               mustMatchStockCoherence(amoxycilline250CapsInitialField,
                                       amoxycilline250CapsReceivedField,
                                       amoxycilline250CapsUsedField,
                                       amoxycilline250CapsLostField) &&
               mustMatchStockCoherence(albendazole400InitialField,
                                       albendazole400ReceivedField,
                                       albendazole400UsedField,
                                       albendazole400LostField) &&
               mustMatchStockCoherence(vita100InjectableInitialField,
                                       vita100InjectableReceivedField,
                                       vita100InjectableUsedField,
                                       vita100InjectableLostField) &&
               mustMatchStockCoherence(vita200InjectableInitialField,
                                       vita200InjectableReceivedField,
                                       vita200InjectableUsedField,
                                       vita200InjectableLostField);
        if (is_ureni) {
            isEnsureDataCoherence = isEnsureDataCoherence &&
                                    mustMatchStockCoherence(milkF75InitialField,
                                            milkF75ReceivedField,
                                            milkF75UsedField,
                                            milkF75LostField) &&
                                    mustMatchStockCoherence(milkF100InitialField,
                                            milkF100ReceivedField,
                                            milkF100UsedField,
                                            milkF100LostField) &&
                                    mustMatchStockCoherence(resomalInitialField,
                                            resomalReceivedField,
                                            resomalUsedField,
                                            resomalLostField);
        }
        if (is_urenam) {
            isEnsureDataCoherence = isEnsureDataCoherence &&
                    mustMatchStockCoherence(plumpySupInitialField,
                            plumpySupReceivedField,
                            plumpySupUsedField,
                            plumpySupLostField) &&
                    mustMatchStockCoherence(supercerealInitialField,
                            supercerealReceivedField,
                            supercerealUsedField,
                            supercerealLostField, true) &&
                    mustMatchStockCoherence(supercerealPlusInitialField,
                            supercerealPlusReceivedField,
                            supercerealPlusUsedField,
                            supercerealPlusLostField)&&
                    mustMatchStockCoherence(oilInitialField,
                            oilReceivedField,
                            oilUsedField,
                            oilLostField) &&
                    mustMatchStockCoherence(ironFolicAcidInitialField,
                            ironFolicAcidReceivedField,
                            ironFolicAcidUsedField,
                            ironFolicAcidLostField);
        }
        return isEnsureDataCoherence;
    }

    protected String buildSMSText() {
        NutritionInputsReportData report = NutritionInputsReportData.get();
        return report.buildSMSText();
    }
}
