package com.lance.dribbb.activites.content;

import com.handmark.pulltorefresh.library.PullToRefreshGridView;
import com.lance.dribbb.R;
import com.lance.dribbb.R.layout;
import com.lance.dribbb.R.menu;
import com.lance.dribbb.network.DribbbleAPI;
import com.lance.dribbb.network.ShotsData;

import android.os.Bundle;
import android.app.Activity;
import android.util.Log;
import android.view.Menu;
import android.widget.TextView;

public class ContentActivity extends Activity {
  
  private PullToRefreshGridView shotsContent;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_content);
    
    shotsContent = (PullToRefreshGridView)findViewById(R.id.content_shots);
    ShotsData data = new ShotsData(ContentActivity.this);
    data.getShots(DribbbleAPI.SHOTS_POPULAR, shotsContent);
    
  }

}
