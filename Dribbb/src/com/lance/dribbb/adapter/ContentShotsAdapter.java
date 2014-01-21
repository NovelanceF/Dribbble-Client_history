package com.lance.dribbb.adapter;

import java.util.List;
import java.util.Map;

import android.app.Activity;
import android.app.ActivityManager;
import android.content.Context;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;
import com.android.volley.toolbox.Volley;
import com.lance.dribbb.R;
import com.lance.dribbb.activites.ContentActivity;
import com.lance.dribbb.application.AppData;
import com.lance.dribbb.network.BitmapLruCache;

public class ContentShotsAdapter extends BaseAdapter{
  
  private Activity mActivity;
  private List<Map<String, Object>> mList;
  private LayoutInflater mInflater;
  private RequestQueue mRequestQueue;
  private ImageLoader mImageLoader;
  
  public ContentShotsAdapter(Activity c, List<Map<String, Object>> list) {
    this.mActivity = c;
    mList = list;
    mInflater = LayoutInflater.from(c);
    mRequestQueue = Volley.newRequestQueue(mActivity);
    
    mImageLoader = new ImageLoader(mRequestQueue, new BitmapLruCache());
  }

  @Override
  public int getCount() {
    return mList.size();
  }

  @Override
  public Object getItem(int position) {
    return mList.get(position);
  }

  @Override
  public long getItemId(int position) {
    return position;
  }

  @Override
  public View getView(int position, View convertView, ViewGroup parent) {
    Holder holder;
    if (convertView == null) {
      convertView = mInflater.inflate(R.layout.item_shots_layout, null);
      holder = new Holder();
      holder.shotsImage = (NetworkImageView)convertView.findViewById(R.id.shots_item_image);
      holder.shotsPlayer = (TextView)convertView.findViewById(R.id.shots_item_player);
      holder.shotsViews = (TextView)convertView.findViewById(R.id.shots_item_views);
      holder.shotsTitle = (TextView)convertView.findViewById(R.id.shots_item_title);
      holder.shotsLikes = (TextView)convertView.findViewById(R.id.shots_item_likes);
      
      convertView.setTag(holder);
    } else {
      holder = (Holder)convertView.getTag();
    }
    
    if(position == 0) {
      holder.button = (ImageView)convertView.findViewById(R.id.hhhhh);
      holder.button.setVisibility(View.VISIBLE);
      holder.button.setPadding(0, 75, 0, 0);
    } else {
      holder.button = (ImageView)convertView.findViewById(R.id.hhhhh);
      holder.button.setPadding(0, 0, 0, 0);
    }
    
    holder.shotsImage.setLayoutParams(getParams(holder));
    holder.shotsImage.setImageUrl((String) mList.get(position).get("image_teaser_url"), mImageLoader);
    holder.shotsTitle.setText(mList.get(position).get("title").toString());
    holder.shotsPlayer.setText(mList.get(position).get("player_name").toString());
    holder.shotsViews.setText(mList.get(position).get("views_count").toString());
    holder.shotsLikes.setText(mList.get(position).get("likes_count").toString());
    
    return convertView;
  }
  
  private static class Holder {
    public NetworkImageView shotsImage;
    public TextView shotsTitle;
    public TextView shotsViews;
    public TextView shotsLikes;
    public TextView shotsPlayer;
    public ImageView button;
  }
  
  private android.view.ViewGroup.LayoutParams getParams(Holder holder){
    WindowManager manager = mActivity.getWindowManager();
    Display display = manager.getDefaultDisplay();
    android.view.ViewGroup.LayoutParams params = holder.shotsImage.getLayoutParams();
    params.width = display.getWidth() - display.getWidth() * 1/18;
    params.height = params.width * 3/4;
    return params;
  }

}
