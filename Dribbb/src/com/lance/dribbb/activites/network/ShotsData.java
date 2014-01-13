package com.lance.dribbb.activites.network;

import org.json.JSONObject;

import android.content.Context;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

public class ShotsData {
  
  private Context context;
  private String url;
  private RequestQueue mRequestQueue;
  StringBuffer sb;
  
  public ShotsData (Context mContext) {
    context = mContext;
    mRequestQueue = Volley.newRequestQueue(mContext);
  }
  
  public String getShots(String url) {
    JsonObjectRequest jsonStringRequest = new JsonObjectRequest(
        Request.Method.GET, url, null,
        new Response.Listener<JSONObject>() {

          @Override
          public void onResponse(JSONObject arg0) {
            sb = new StringBuffer();
            sb.append(arg0.toString());
            Log.i("message", sb.toString());
          }     
        }, 
        new Response.ErrorListener() {

          @Override
          public void onErrorResponse(VolleyError arg0) {
            Log.i("Volley error", arg0.getMessage());  
          }      
        });
    mRequestQueue.add(jsonStringRequest);
    return sb.toString(); 
  }

}
