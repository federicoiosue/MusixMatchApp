package it.feio.android.musixmatchapp;

import android.app.Application;
import com.squareup.leakcanary.LeakCanary;


public class MusixMatchApplication extends Application {

	@Override
	public void onCreate() {
		super.onCreate();
		if (LeakCanary.isInAnalyzerProcess(this)) {
			return;
		}
		LeakCanary.install(this);
	}

}
