package org.droidstrap.view;

import java.util.List;

import principal.ApplicationData;
import org.droidstrap.R;
import android.app.ActivityManager;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.app.ListFragment;
import android.view.KeyEvent;

import com.actionbarsherlock.view.MenuItem;
import com.actionbarsherlock.view.Window;
import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;
import com.jeremyfeinstein.slidingmenu.lib.app.SlidingFragmentActivity;

import org.droidstrap.fragments.Fragment_MenuList;

public class BaseActivity extends SlidingFragmentActivity {

	protected	SlidingMenu slidingMenu ;
	protected 	ListFragment mFrag;

	public static	ApplicationData mAppdata;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

		requestWindowFeature(Window.FEATURE_INDETERMINATE_PROGRESS);

		if (mAppdata == null) {
			mAppdata = (ApplicationData) getApplication();

			//		this.mAppdata.setSharedPreferences(this);
		}

		//		setTitle(mTitleRes);

		// set the Behind View
		setBehindContentView(R.layout.menu_frame);
		if (savedInstanceState == null) {
			FragmentTransaction t = this.getSupportFragmentManager().beginTransaction();
			mFrag = new Fragment_MenuList();
			t.replace(R.id.menu_frame, mFrag);
			t.commit();
		} else {
			mFrag = (ListFragment)this.getSupportFragmentManager().findFragmentById(R.id.menu_frame);
		}

		configureAbar();

		getSupportActionBar().setDisplayHomeAsUpEnabled(true);



		// customize the SlidingMenu
		slidingMenu = getSlidingMenu();
		slidingMenu.setShadowWidthRes(R.dimen.slidingmenu_shadow_width);
		slidingMenu.setShadowDrawable(R.drawable.slidingmenu_shadow);
		slidingMenu.setBehindOffsetRes(R.dimen.slidingmenu_offset);
		slidingMenu.setFadeDegree(0.35f);
		slidingMenu.setTouchModeAbove(SlidingMenu.TOUCHMODE_MARGIN);
		//		sm.attachToActivity(this, SlidingMenu.SLIDING_WINDOW);
		//		sm.setMenu(R.layout.menu_frame);
		setSlidingActionBarEnabled(true);

	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if ( keyCode == KeyEvent.KEYCODE_MENU ) {
			this.slidingMenu.toggle();
			return true;
		}
		return super.onKeyDown(keyCode, event);
	}

	@Override
	public boolean onMenuItemSelected(int featureId, MenuItem item) {

		int itemId = item.getItemId();
		
		switch (itemId) {
		case android.R.id.home: {		
			if (!isLastActivity())
				finish();
			else
				toggle();
		}
		}
		
		return true;
	}

	protected boolean isLastActivity() {
		ActivityManager mngr = (ActivityManager) getSystemService( ACTIVITY_SERVICE );

		List<ActivityManager.RunningTaskInfo> taskList = mngr.getRunningTasks(10);

		return (taskList.get(0).numActivities == 1 &&
				taskList.get(0).topActivity.getClassName().equals(this.getClass().getName()));

	}

	private void configureAbar() {
		/*
		ActionBar ab = getSupportActionBar();
		Drawable redcolor = getResources().getDrawable(R.color.red_dador);

		//		ab.setHomeButtonEnabled(true);

		setSupportProgressBarIndeterminateVisibility(false);

		ab.setBackgroundDrawable(redcolor);
		ab.setIcon(R.drawable.ic_action_gota);
		 */
		//		ab.setDisplayShowHomeEnabled(true);	
	}

}
