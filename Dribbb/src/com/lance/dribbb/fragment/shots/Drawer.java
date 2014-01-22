package com.lance.dribbb.fragment.shots;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.lance.dribbb.R;
import com.lance.dribbb.adapter.DrawerAdapter;

public class Drawer extends Fragment {
  
  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
    View rootView = inflater.inflate(R.layout.fragment_drawer, container, false);
    ListView listView = (ListView)rootView.findViewById(R.id.drawer_list);
    
    DrawerAdapter adapter = new DrawerAdapter(getActivity(), DrawerItems.initDrawerItems());
    listView.setAdapter(adapter);
    return rootView;
  }

}
