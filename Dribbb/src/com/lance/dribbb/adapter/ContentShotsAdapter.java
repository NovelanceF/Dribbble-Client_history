package com.lance.dribbb.adapter;

import java.util.List;
import java.util.Map;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.toolbox.NetworkImageView;
import com.lance.dribbb.R;

public class ContentShotsAdapter extends BaseAdapter{
  
  Context mContext;
  List<Map<String, Object>> mList;
  LayoutInflater mInflater;
  
  public ContentShotsAdapter(Context c, List<Map<String, Object>> list) {
    this.mContext = c;
    mList = list;
    mInflater = LayoutInflater.from(c);
    
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
      holder.button.setPadding(0, 100, 0, 0);
    } else {
      holder.button = (ImageView)convertView.findViewById(R.id.hhhhh);
      holder.button.setPadding(0, 0, 0, 0);
    }
    
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

}
