package com.lance.dribbb.fragment.content;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
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
  private static int pageDebut = 1, pagePopular = 1, pageEveryone = 1;
  private static String currentUrl = null;
  
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
    initGridView(m, 1, gridView);
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
  
  private int getCurrentPage(){
    if(m == 0){
      currentUrl = DribbbleAPI.SHOTS_DEBUTS;
      return ++ pageDebut;
    } else if (m == 1) {
      currentUrl = DribbbleAPI.SHOTS_EVERYONE;
      return ++ pagePopular;
    } else {
      currentUrl = DribbbleAPI.SHOTS_POPULAR;
      return ++ pageEveryone;
    }
  }

  @Override
  public void onRefresh(PullToRefreshBase<GridView> refreshView) {
    new GetDataTask((PullToRefreshGridView)refreshView, getCurrentPage()).execute();
  }
  
  private class GetDataTask extends AsyncTask<Void, Void, String[]> {
    
    PullToRefreshGridView pullToRefreshGridView;
    int page = 1;
    
    public GetDataTask(PullToRefreshGridView gridView, int p) {
      pullToRefreshGridView = gridView;
      page = p; 
    }

    @Override
    protected String[] doInBackground(Void... params) {
      data.getShotsRefresh(currentUrl + page, pullToRefreshGridView);
      return null;
    }
  }
  
  @Override
  public void onStop() {
    pageDebut = pageEveryone = pagePopular = 1;
    super.onStop();
  }

}
