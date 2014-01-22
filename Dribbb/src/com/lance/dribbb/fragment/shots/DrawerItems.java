package com.lance.dribbb.fragment.shots;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.lance.dribbb.R;

public class DrawerItems {
  
  public static List<Map<String, Object>> initDrawerItems() {
    List<Map<String, Object>> list = new ArrayList<Map<String,Object>>();
    Map<String, Object> map = new HashMap<String, Object>();
    map.put("user_avatar", R.drawable.player_unconnected);
    map.put("user_info1", "Tap to connect");
    map.put("user_info2", "to your dribbble account");
    list.add(map);
    
    map = new HashMap<String, Object>();
    map.put("drawer_items", "Followings");
    list.add(map);
    
    map = new HashMap<String, Object>();
    map.put("drawer_items", "Likes");
    list.add(map);
    
    map = new HashMap<String, Object>();
    map.put("drawer_items", "About");
    list.add(map);
    
    return list;
  }

}
