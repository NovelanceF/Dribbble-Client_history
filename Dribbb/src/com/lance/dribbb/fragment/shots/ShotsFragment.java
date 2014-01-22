package com.lance.dribbb.fragment.shots;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.GridView;

import com.lance.dribbb.R;
import com.lance.dribbb.adapter.ContentShotsAdapter;
import com.lance.dribbb.network.DribbbleAPI;
import com.lance.dribbb.network.ShotsData;
import com.lance.dribbb.views.FooterState;

public class ShotsFragment extends Fragment {
  
  private int m;
  private ShotsData data;
  private static int pageDebut = 1, pagePopular = 1, pageEveryone = 1;
  private static String currentUrl = null;
  private ContentShotsAdapter adapter;
  final FooterState footerState = new FooterState();
  
  public ShotsFragment(int index, Activity a) {
    m = index;
    data = new ShotsData(a);
    adapter = new ContentShotsAdapter(a, data.getList());
  }
  
  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container,
      Bundle savedInstanceState) {
    View rootView = inflater.inflate(R.layout.fragment_shots, null);
    final GridView gridView = (GridView)rootView.findViewById(R.id.shots_grid);
    gridView.setAdapter(adapter);
    gridView.setOnScrollListener(new OnScrollListener() {
      
      @Override
      public void onScrollStateChanged(AbsListView view, int scrollState) {
        
      }
      
      @Override
      public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
        if(footerState.getState() == FooterState.State.Loading || footerState.getState() == FooterState.State.TheEnd) {
          return;
        }
        if(firstVisibleItem + visibleItemCount == totalItemCount && totalItemCount != 0 && totalItemCount != 2 && adapter.getCount() > 0) {
          setCurrentUrl();
          data.getShotsRefresh(currentUrl + getCurrentPage(), gridView, adapter, footerState);
          footerState.setState(FooterState.State.Loading);
          Log.i("GRIDVIEW", "BOTTOM");
        }
      }
    });
    
    initGridView(m, 1, gridView);
    return rootView;
  }
  
  private void initGridView(int index, int page, GridView gridView) {
    if(index == 0){
      data.getShotsRefresh(DribbbleAPI.SHOTS_DEBUTS + page, gridView, adapter, footerState);
    } else if (index == 1) {
      data.getShotsRefresh(DribbbleAPI.SHOTS_POPULAR + page, gridView, adapter, footerState);
    } else {
      data.getShotsRefresh(DribbbleAPI.SHOTS_EVERYONE + page, gridView, adapter, footerState);
    }
  }
  
  private int getCurrentPage(){
    if(m == 0){
      return ++ pageDebut;
    } else if (m == 2) {
      return ++ pagePopular;
    } else {
      return ++ pageEveryone;
    }
  }
  
  private void setCurrentUrl() {
    if(m == 0){
      currentUrl = DribbbleAPI.SHOTS_DEBUTS;
    } else if (m == 2) {
      currentUrl = DribbbleAPI.SHOTS_EVERYONE;
    } else {
      currentUrl = DribbbleAPI.SHOTS_POPULAR;
    }
  }

/*  @Override
  public void onRefresh(PullToRefreshBase<GridView> refreshView) {
    new GetDataTask((PullToRefreshGridView)refreshView, getCurrentPage()).execute();
  }*/
  
  /*private class GetDataTask extends AsyncTask<Void, Void, String[]> {
    
    GridView gridView;
    int page = 1;
    
    public GetDataTask(GridView gView, int p) {
      gridView = gView;
      page = p; 
    }

    @Override
    protected String[] doInBackground(Void... params) {
      data.getShotsRefresh(currentUrl + page, gridView, adapter);
      footerState.setState(FooterState.State.Loading);
      return null;
    }
    
    @Override
    protected void onPostExecute(String[] result) {
      super.onPostExecute(result);
      footerState.setState(FooterState.State.Idle);
    }
    
  }*/
  
  @Override
  public void onStop() {
    pageDebut = pageEveryone = pagePopular = 1;
    super.onStop();
  }

}
