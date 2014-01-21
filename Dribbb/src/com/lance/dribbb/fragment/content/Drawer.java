package com.lance.dribbb.fragment.content;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.lance.dribbb.R;

public class Drawer extends Fragment {
  
  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
    View rootView = inflater.inflate(R.layout.fragment_drawer, container, false);
    return rootView;
  }

}
