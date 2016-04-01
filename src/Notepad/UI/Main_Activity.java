package Notepad.UI;

import java.util.ArrayList;
import java.util.List;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.View;
import android.view.View.OnClickListener;

public class Main_Activity extends FragmentActivity implements OnClickListener{

	private ViewPager mPager;
	private List<Fragment> mList;
	private FragmentPagerAdapter mAdapter;
	private List<MyView> mViews;
	private MyView indexView;
	private MyView addView;
	private MyView inOutView;
	private MyView upLoadView;
	
	@Override
	protected void onCreate(Bundle arg0) {
		// TODO Auto-generated method stub
		super.onCreate(arg0);
		setContentView(R.layout.activity_main);
		initView();
		initEvent();
	}

	private void initView() {
		// TODO Auto-generated method stub
		mList = new ArrayList<Fragment>();
		mViews = new ArrayList<MyView>();
		mPager = (ViewPager) findViewById(R.id.vp);
		indexView = (MyView) findViewById(R.id.indicator_index);
		addView = (MyView) findViewById(R.id.indicator_add);
		inOutView = (MyView) findViewById(R.id.indicator_inOut);
		upLoadView = (MyView) findViewById(R.id.indicator_upload);
		mViews.add(indexView);
		mViews.add(addView);
		mViews.add(inOutView);
		mViews.add(upLoadView);
		Fragment index_f = new Index_Fragment();
		Fragment add_f = new add_Fragment();
		Fragment inOut_f = new inOut_Fragment();
		Fragment upload_f = new upload_Fragment();
		mList.add(index_f);
		mList.add(add_f);
		mList.add(inOut_f);
		mList.add(upload_f);
		mAdapter = new FragmentPagerAdapter(getSupportFragmentManager()) {
			
			@Override
			public int getCount() {
				// TODO Auto-generated method stub
				return mList.size();
			}
			
			@Override
			public Fragment getItem(int arg0) {
				// TODO Auto-generated method stub
				return mList.get(arg0);
			}
		};
	}
	private void initEvent() {
		// TODO Auto-generated method stub
		indexView.setOnClickListener(this);
		addView.setOnClickListener(this);
		inOutView.setOnClickListener(this);
		upLoadView.setOnClickListener(this);
		mPager.setAdapter(mAdapter);
		mPager.setOnPageChangeListener(new OnPageChangeListener() {
			
			@Override
			public void onPageSelected(int arg0) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void onPageScrolled(int arg0, float arg1, int arg2) {
				// TODO Auto-generated method stub
				if (arg1>0) {
					mViews.get(arg0).setIconAlpha(1-arg1);
					mViews.get(arg0+1).setIconAlpha(arg1);
				}
			}
			
			@Override
			public void onPageScrollStateChanged(int arg0) {
				// TODO Auto-generated method stub
				
			}
		});
	}

	@Override
	public void onClick(View arg0) {
		// TODO Auto-generated method stub
		resetIcon();
		switch (arg0.getId()) {
		case R.id.indicator_index:
			indexView.setIconAlpha(1.0f);
			mPager.setCurrentItem(0, false);
			break;

		case R.id.indicator_add:
			addView.setIconAlpha(1.0f);
			mPager.setCurrentItem(1, false);
			break;
		case R.id.indicator_inOut:
			inOutView.setIconAlpha(1.0f);
			mPager.setCurrentItem(2, false);
			break;
		case R.id.indicator_upload:
			upLoadView.setIconAlpha(1.0f);
			mPager.setCurrentItem(3, false);
			break;
		}
	}
	public void resetIcon() {
		indexView.setIconAlpha(0);
		addView.setIconAlpha(0);
		inOutView.setIconAlpha(0);
		upLoadView.setIconAlpha(0);
	}
}
