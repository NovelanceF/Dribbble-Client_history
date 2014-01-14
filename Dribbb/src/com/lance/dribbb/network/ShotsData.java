package com.lance.dribbb.network;

import org.json.JSONObject;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

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
  String sb;
  
  public ShotsData (Context mContext) {
    context = mContext;
    mRequestQueue = Volley.newRequestQueue(mContext);
  }
  
  public String getShots(String url, final TextView text) {
    JsonObjectRequest jsonStringRequest = new JsonObjectRequest(
        Request.Method.GET, url, null,
        new Response.Listener<JSONObject>() {

          @Override
          public void onResponse(JSONObject arg0) {
            text.setText(arg0.toString());
          }     
        }, 
        new Response.ErrorListener() {

          @Override
          public void onErrorResponse(VolleyError arg0) {
            Log.i("Volley error", arg0.getMessage());  
          }      
        });
    mRequestQueue.add(jsonStringRequest); 
    return null;
  }

}