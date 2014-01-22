package com.lance.dribbb.adapter;

import java.util.List;
import java.util.Map;

import android.app.Activity;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.lance.dribbb.R;

public class DrawerAdapter extends BaseAdapter {
  
  private Activity mActivity;
  private LayoutInflater mInflater;
  private List<Map<String, Object>> mList;
  private Typeface typeface;
  
  public DrawerAdapter(Activity a, List<Map<String, Object>> list) {
    mActivity = a;
    mInflater = LayoutInflater.from(a);
    mList = list;
    typeface = Typeface.createFromAsset(a.getAssets(), "font/Roboto-Light.ttf");
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
    if(position == 0) {
      Holder1 holder;
      if(convertView == null) {
        convertView = mInflater.inflate(R.layout.item_user, null);
        holder = new Holder1();
        holder.userAvatar = (ImageView)convertView.findViewById(R.id.item_user_avatar);
        holder.userInfo1 = (TextView)convertView.findViewById(R.id.item_user_info);
        holder.userInfo2 = (TextView)convertView.findViewById(R.id.item_user_info2);
        convertView.setTag(holder);
      } else {
        holder = (Holder1)convertView.getTag();
      }
      
      holder.userAvatar.setImageResource((Integer) mList.get(position).get("user_avatar"));
      holder.userInfo1.setText(mList.get(position).get("user_info1").toString());
      holder.userInfo1.setTypeface(typeface);
      holder.userInfo2.setText(mList.get(position).get("user_info2").toString());
      holder.userInfo2.setTypeface(typeface);
      
    } else {
      Holder2 holder;
      if (convertView == null) {
        convertView = mInflater.inflate(R.layout.item_others, null);
        holder = new Holder2();
        holder.itemText = (TextView)convertView.findViewById(R.id.item_text);
        convertView.setTag(holder);
      } else {
        holder = (Holder2)convertView.getTag();
      }
      
      holder.itemText.setText(mList.get(position).get("drawer_items").toString());
      holder.itemText.setTypeface(typeface);
      
    }
    return convertView;
  }
  
  private static class Holder1 {
    ImageView userAvatar;
    TextView userInfo1;
    TextView userInfo2;
  }
  
  private static class Holder2 {
    TextView itemText;
  }

}
