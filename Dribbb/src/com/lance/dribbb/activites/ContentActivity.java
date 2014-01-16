package com.lance.dribbb.activites;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;

import com.lance.dribbb.R;
import com.lance.dribbb.fragment.content.ShotsFragment;

public class ContentActivity extends FragmentActivity implements OnPageChangeListener {
  
  private ViewPager contentPager;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_content);
    contentPager = (ViewPager)findViewById(R.id.content_pager);
    contentPager.setOnPageChangeListener(this);
    mPagerAdapter adapter = new mPagerAdapter(getSupportFragmentManager());
    contentPager.setAdapter(adapter);
  }
  
  private class mPagerAdapter extends FragmentStatePagerAdapter {

    public mPagerAdapter(FragmentManager fm) {
      super(fm);
    }

    @Override
    public Fragment getItem(int arg0) {
      return new ShotsFragment(arg0, ContentActivity.this);
    }

    @Override
    public int getCount() {
      return 3;
    }
    
  }

  @Override
  public void onPageScrollStateChanged(int arg0) {
    
  }

  @Override
  public void onPageScrolled(int arg0, float arg1, int arg2) {
    
  }

  @Override
  public void onPageSelected(int arg0) {
    
  }

}
