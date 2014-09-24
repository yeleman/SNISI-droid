package com.yeleman.snisidroid;

import android.util.Log;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import static java.lang.String.format;

/** A class to parse json data */
public class ResourcesJSONParser {

    // Receives a JSONObject and returns a list
    public List<HashMap<String,Object>> parse(JSONObject jObject){

        JSONArray jResource = null;
        try {
            // Retrieves all the elements in the 'countries' array
            jResource = jObject.getJSONArray("resources");
        } catch (JSONException e) {
            e.printStackTrace();
        }

        // Invoking getResources with the array of json object
        // where each json object represent a resource
        return getResource(jResource);
    }

    private List<HashMap<String, Object>> getResource(JSONArray jResource){
        int resourceCount = jResource.length();
        List<HashMap<String, Object>> resourceList = new ArrayList<HashMap<String,Object>>();
        HashMap<String, Object> resource = null;

        // Taking each resource, parses and adds to list object
        for(int i=0; i<resourceCount;i++){
            try {
                // Call getResource with resource JSON object to parse the resource
                resource = getResource((JSONObject) jResource.get(i));
                resourceList.add(resource);

            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return resourceList;
    }

    // Parsing the Resource JSON object
    private HashMap<String, Object> getResource(JSONObject jResource){

        HashMap<String, Object> resource = new HashMap<String, Object>();
        String uri = "";
        String name = "";
        String icon_url = "";
        String date = "";
        String description = "";
 
        try {
            uri = jResource.getString("uri");
            name = jResource.getString("name");
            description = jResource.getString("description");
            icon_url = jResource.getString("icon_url");
            date = jResource.getString("modified_on");
            String details = format("%s \nLe %s ", description, date);
            resource.put("name", name);
            resource.put("icon_url", icon_url);
            resource.put("uri", uri);
            resource.put("details", details);
 
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return resource;
    }
}
