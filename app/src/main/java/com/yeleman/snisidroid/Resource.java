package com.yeleman.snisidroid;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.List;

import org.json.JSONObject;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;


public class Resource extends Activity {

    ListView mListView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.resource);

        // URL to the JSON data
        //String strUrl = "http://192.168.5.55:8000/android.json";
        String strUrl = "http://snisi.sante.gov.ml/resources/android.json";
        // Creating a new non-ui thread task to download json data

        // Starting the download process
        DownloadTask downloadTask = new DownloadTask();
        downloadTask.execute(strUrl);

        // Getting a reference to ListView of activity_main
        mListView = (ListView) findViewById(R.id.lv_resource);
    }

    /** A method to download json data from url */
    private String downloadUrl(String strUrl) throws IOException{
        String data = "";
        InputStream iStream = null;
        try{
            URL url = new URL(strUrl);

            // Creating an http connection to communicate with url
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();

            // Connecting to url
            urlConnection.connect();

            // Reading data from url
            iStream = urlConnection.getInputStream();

            BufferedReader br = new BufferedReader(new InputStreamReader(iStream));

            StringBuffer sb  = new StringBuffer();

            String line = "";
            while( ( line = br.readLine())  != null){
                sb.append(line);
            }

            data = sb.toString();

            br.close();

        }catch(Exception e){
            Log.d("Exception while downloading url", e.toString());
        }finally{
            iStream.close();
        }

        return data;
    }

    /** AsyncTask to download json data */
    private class DownloadTask extends AsyncTask<String, Integer, String>{

        String data = null;
        private ProgressDialog Dialog = new ProgressDialog(Resource.this);

        public boolean isOnline() {
            ConnectivityManager cm =
                    (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo netInfo = cm.getActiveNetworkInfo();
            if (netInfo != null && netInfo.isConnectedOrConnecting()) {
                return true;
            }
            return false;
        }

        @Override
        protected void onPreExecute() {
            // Loading
            if (!isOnline())
                return;
            Dialog.setMessage("Chargement en cours ...");
            Dialog.show();
        }

        @Override
        protected String doInBackground(String... url) {
            try{
                data = downloadUrl(url[0]);
            }catch(Exception e){
                Log.d("Background Task",e.toString());
            }
            return data;
        }

        @Override
        protected void onPostExecute(String result) {
            if (isOnline()) {
                if (result != "") {
                    // The parsing of the xml data is done in a non-ui thread
                    ListViewLoaderTask listViewLoaderTask = new ListViewLoaderTask();
                    // Start parsing xml data
                    listViewLoaderTask.execute(result);
                }
                // after completed finished the progressbar
                Dialog.dismiss();
            }else{
                //Dialog.
                AlertDialog alertDialog = new AlertDialog.Builder(Resource.this).create();
                alertDialog.setTitle("Problème de connexion");
                alertDialog.setIcon(R.drawable.ic_launcher);
                alertDialog.setMessage("Une connexion Internet est requise.\nVeuillez l'activer et réessayer.");
                alertDialog.setButton("OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        finish();
                    }
                });
                alertDialog.show();
            }
        }
    }

    /** AsyncTask to parse json data and load ListView */
    private class ListViewLoaderTask extends AsyncTask<String, Void, SimpleAdapter>{

        JSONObject jObject;
        // Doing the parsing of xml data in a non-ui thread
        @Override
        protected SimpleAdapter doInBackground(String... strJson) {
            try{
                jObject = new JSONObject(strJson[0]);
                ResourcesJSONParser apkJsonParser = new ResourcesJSONParser();
                apkJsonParser.parse(jObject);
            }catch(Exception e){
                Log.d("JSON Exception1",e.toString());
            }

            // Instantiating json parser class
            ResourcesJSONParser apkJsonParser = new ResourcesJSONParser();

            // A list object to store the parsed countries list
            List<HashMap<String, Object>> resources = null;

            try{
                // Getting the parsed data as a List construct
                resources = apkJsonParser.parse(jObject);
            }catch(Exception e){
                Log.d("Exception",e.toString());
            }
            // Keys used in Hashmap
            String[] from = {"name","icon_url","details"};
            // Ids of views in listview_layout
            int[] to = {R.id.title_resource,R.id.icon,R.id.resource_details};
            // Instantiating an adapter to store each items
            // R.layout.listview_layout defines the layout of each item
            SimpleAdapter adapter = new SimpleAdapter(getBaseContext(), resources, R.layout.lv_layout, from, to);

            return adapter;
        }

        /** Invoked by the Android on "doInBackground" is executed */
        @Override
        protected void onPostExecute(final SimpleAdapter adapter) {

            // Setting adapter for the listview
            mListView.setAdapter(adapter);

            for(int i=0;i<adapter.getCount();i++){
                HashMap<String, Object> hm = (HashMap<String, Object>) adapter.getItem(i);
                String imgUrl = (String) hm.get("icon_url");
                ImageLoaderTask imageLoaderTask = new ImageLoaderTask();
                HashMap<String, Object> hmDownload = new HashMap<String, Object>();
                hm.put("icon_url",imgUrl);
                hm.put("position", i);
                // Starting ImageLoaderTask to download and populate image in the listview
                if (!imgUrl.equals("null")) {
                    imageLoaderTask.execute(hm);
                }
                //if (imgUrl != null){
                //    imageLoaderTask.execute(hm);
                //}
            }
            mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    HashMap<String, Object> hm = (HashMap<String, Object>) adapter.getItem((int) id);
                    final String uri = (String) hm.get("uri");
                    Intent intent = new Intent(Intent.ACTION_VIEW);
                    intent.setData(Uri.parse(uri));
                    startActivity(intent);
                }
            });

        }
    }

    /** AsyncTask to download and load an image in ListView */
    private class ImageLoaderTask extends AsyncTask<HashMap<String, Object>, Void, HashMap<String, Object>>{

        @Override
        protected HashMap<String, Object> doInBackground(HashMap<String, Object>... hm) {
            
            InputStream iStream=null;
            String imgUrl = (String) hm[0].get("icon_url");
            int position = (Integer) hm[0].get("position");
            
            URL url;
            try {
                url = new URL(imgUrl);
                
                // Creating an http connection to communicate with url
                HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();

                // Connecting to url                
                urlConnection.connect();

                // Reading data from url 
                iStream = urlConnection.getInputStream();
                
                // Getting Caching directory 
                File cacheDirectory = getBaseContext().getCacheDir();
                
                // Temporary file to store the downloaded image 
                File tmpFile = new File(cacheDirectory.getPath() + "/wpta_"+position+".png");               
                    
                // The FileOutputStream to the temporary file
                FileOutputStream fOutStream = new FileOutputStream(tmpFile);
                
                // Creating a bitmap from the downloaded inputstream
                Bitmap b = BitmapFactory.decodeStream(iStream);             
                
                // Writing the bitmap to the temporary file as png file
                b.compress(Bitmap.CompressFormat.PNG,100, fOutStream);              
                
                // Flush the FileOutputStream
                fOutStream.flush();
                
                //Close the FileOutputStream
                fOutStream.close();             
                
                // Create a hashmap object to store image path and its position in the listview
                HashMap<String, Object> hmBitmap = new HashMap<String, Object>();
                
                // Storing the path to the temporary image file
                hmBitmap.put("icon_url",tmpFile.getPath());
                
                // Storing the position of the image in the listview
                hmBitmap.put("position",position);              
                
                // Returning the HashMap object containing the image path and position
                return hmBitmap;                
                

            }catch (Exception e) {              
                e.printStackTrace();
            }
            return null;
        }
        
        @Override
        protected void onPostExecute(HashMap<String, Object> result) {
            // Getting the path to the downloaded image
            String path = (String) result.get("icon_url");
            
            // Getting the position of the downloaded image
            int position = (Integer) result.get("position");
            
            // Getting adapter of the listview
            SimpleAdapter adapter = (SimpleAdapter ) mListView.getAdapter();
            
            // Getting the hashmap object at the specified position of the listview
            HashMap<String, Object> hm = (HashMap<String, Object>) adapter.getItem(position);   
            
            // Overwriting the existing path in the adapter 
            hm.put("icon_url",path);
            
            // Noticing listview about the dataset changes
            adapter.notifyDataSetChanged(); 
        }
    }
}