package com.lance.dribbb.fragment.content;

import android.R.integer;
import android.support.v4.app.Fragment;
import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshBase.Mode;
import com.handmark.pulltorefresh.library.PullToRefreshBase.OnRefreshListener;
import com.handmark.pulltorefresh.library.PullToRefreshGridView;
import com.lance.dribbb.R;
import com.lance.dribbb.network.DribbbleAPI;
import com.lance.dribbb.network.ShotsData;

public class ShotsFragment extends Fragment implements OnRefreshListener<GridView> {
  
  private int m;
  private ShotsData data;
  private static int page = 1;
  
  public ShotsFragment(int index, Activity a) {
    m = index;
    data = new ShotsData(a);
  }
  
  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container,
      Bundle savedInstanceState) {
    View rootView = inflater.inflate(R.layout.fragment_shots, null);
    PullToRefreshGridView gridView = (PullToRefreshGridView)rootView.findViewById(R.id.shots_grid);
    gridView.setMode(Mode.PULL_FROM_END);
    gridView.setOnRefreshListener(this);
    initGridView(m, page, gridView);
    return rootView;
  }
  
  private void initGridView(int index, int page, PullToRefreshGridView gridView) {
    if(index == 0){
      data.getShots(DribbbleAPI.SHOTS_DEBUTS + page, gridView);
    } else if (index == 2) {
      data.getShots(DribbbleAPI.SHOTS_POPULAR + page, gridView);
    } else {
      data.getShots(DribbbleAPI.SHOTS_EVERYONE + page, gridView);
    }
  }

  @Override
  public void onRefresh(PullToRefreshBase<GridView> refreshView) {
    //++page;
    //initGridView(m, page, (PullToRefreshGridView) refreshView);
  }

}
