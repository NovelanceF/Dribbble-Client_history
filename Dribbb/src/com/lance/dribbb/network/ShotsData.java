package com.lance.dribbb.network;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.R.bool;
import android.R.integer;
import android.app.Activity;
import android.app.ActivityManager;
import android.content.Context;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.DiskBasedCache;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.handmark.pulltorefresh.library.PullToRefreshGridView;
import com.lance.dribbb.activites.ContentActivity;
import com.lance.dribbb.adapter.ContentShotsAdapter;
import com.lance.dribbb.application.AppData;

public class ShotsData {

  private Activity mActivity;
  private String url;
  private RequestQueue mRequestQueue;
  private List<Map<String, Object>> list = new ArrayList<Map<String,Object>>();
  private ContentShotsAdapter adapter;

  public ShotsData(Activity a) {
    mActivity = a;
    mRequestQueue = Volley.newRequestQueue(a);
  }

  public void getShots(String url, final PullToRefreshGridView gridView) {
    JsonObjectRequest jsonStringRequest = new JsonObjectRequest(
        Request.Method.GET, url, null, new Response.Listener<JSONObject>() {

          @Override
          public void onResponse(JSONObject arg0) {
            try {
              initShotsList(arg0);
              adapter = new ContentShotsAdapter(mActivity, list);
              gridView.setAdapter(adapter);
            } catch (JSONException e) {
              e.printStackTrace();
            }
          }
        }, new Response.ErrorListener() {

          @Override
          public void onErrorResponse(VolleyError arg0) {
          }
        });
    mRequestQueue.add(jsonStringRequest);
  }
  
  public void getShotsRefresh(String url, final PullToRefreshGridView gridView) {
    JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null, 
        new Response.Listener<JSONObject>() {

          @Override
          public void onResponse(JSONObject arg0) {
            try {
              initShotsList(arg0);
              adapter.notifyDataSetChanged();
              gridView.onRefreshComplete();
              Log.i("refresh","data");
            } catch (JSONException e) {
              e.printStackTrace();
            }
          }
        }, new Response.ErrorListener() {

          @Override
          public void onErrorResponse(VolleyError arg0) {
            
          }
        });
    mRequestQueue.add(jsonObjectRequest);
  }

  private void initShotsList(JSONObject jsonObject) throws JSONException {
    int respond_count = jsonObject.getInt("per_page");
    JSONArray array = jsonObject.getJSONArray("shots");
    for (int i = 0; i < respond_count; i++) {
      Map<String, Object> map = new HashMap<String, Object>();
      
      //shots
      map.put("id", array.getJSONObject(i).getString("id"));
      map.put("title", array.getJSONObject(i).getString("title"));
      map.put("image_url", array.getJSONObject(i).getString("image_url"));
      map.put("image_teaser_url", array.getJSONObject(i).getString("image_teaser_url"));
      map.put("views_count", array.getJSONObject(i).getString("views_count"));
      map.put("likes_count", array.getJSONObject(i).getString("likes_count"));
      
      //player
      map.put("player_name", array.getJSONObject(i).getJSONObject("player").getString("name").toString());
      map.put("player_avatar_url", array.getJSONObject(i).getJSONObject("player").getString("avatar_url").toString());
      list.add(map);
    }
  }
}
