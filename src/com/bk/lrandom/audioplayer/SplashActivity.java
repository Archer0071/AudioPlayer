package com.bk.lrandom.audioplayer;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

public class SplashActivity extends Activity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);

		setContentView(R.layout.splash_layout);
		Handler handler = new Handler();
		handler.postDelayed(new Runnable() {
			public void run() {
				// next to home
				Intent intent = new Intent(SplashActivity.this,
						ExplorationActivity.class);
				startActivity(intent);
				// finish splash screen
				finish();
			}
		}, Integer.parseInt(getResources().getString(R.string.splash_time_out)));
	}
}
