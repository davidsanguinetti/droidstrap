package org.droidstrap.view;

import android.content.Intent;

public class Activity_Commuter extends BaseActivity  {

	public void changeActivity(Intent extras) {
//		ApplicationData da = (ApplicationData) getApplication();
		
		startActivity(extras);		
	}
}
