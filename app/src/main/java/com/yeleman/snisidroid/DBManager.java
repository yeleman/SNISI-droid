package com.yeleman.snisidroid;

import android.app.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.channels.FileChannel;
import com.orm.SugarContext;

/**
 * Created by fad on 05/03/15.
 */
public class DBManager extends Activity {
    private static final String TAG = Constants.getLogTag("DBManager");
    private String packageName;
    private String databaseName = "snisi.db";
    private Button exportBtt;
    private Button resetBtt;
    private File currentDB;
    private File backupDir;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.db_manager);

        File sd = Environment.getExternalStorageDirectory();

        if (!sd.canWrite()) {
            return;
        }
        currentDB = getApplicationContext().getDatabasePath(databaseName);
        packageName = getPackageName();

        backupDir = new File(sd.getAbsolutePath()+ "/" + packageName);
        if(!backupDir.exists()) {
            if(backupDir.mkdir()) {}
        }
        exportBtt = (Button) findViewById(R.id.exportDB);
        exportBtt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                exportDatabase(databaseName);
            }
        });
        resetBtt = (Button) findViewById(R.id.resetDB);
        resetBtt.setOnClickListener(new View.OnClickListener() {
            int counter = 0;
            @Override
            public void onClick(View v) {
                counter ++;
                if(counter == 2) {
                    restDatabase();
                }
            }
        });
    }
    public void restDatabase (){
        SugarContext.terminate();
        getApplicationContext().deleteDatabase(databaseName);
        SugarContext.init(DBManager.this);
        Intent i = getBaseContext().getPackageManager()
                .getLaunchIntentForPackage( getBaseContext().getPackageName() );
        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(i);
        Toast.makeText(getBaseContext(), databaseName +
                       " a été supprimée avec succès", Toast.LENGTH_LONG).show();
    }
    //exporting database
    public void exportDatabase(String databaseName) {
        try {
            String backupDBName = String.format("backup_%s", databaseName);
            File backupDB = new File(backupDir, backupDBName);

            Log.d(TAG, backupDir.toString() + " " + currentDB + " " + currentDB.exists());
            if (currentDB.exists()) {
                FileChannel src = new FileInputStream(currentDB).getChannel();
                FileChannel dst = new FileOutputStream(backupDB).getChannel();
                dst.transferFrom(src, 0, src.size());
                src.close();
                dst.close();
                Toast.makeText(getBaseContext(), databaseName + " a été exportée avec succès dans " + backupDB.getAbsolutePath(), Toast.LENGTH_LONG).show();
            }
        } catch (Exception e) {
            Toast.makeText(getBaseContext(), e.toString(), Toast.LENGTH_LONG).show();
        }
    }
}
