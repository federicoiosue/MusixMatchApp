package android.feio.it.musixmatchapp.services;

import android.feio.it.musixmatchapp.BuildConfig;
import com.google.gson.internal.LinkedTreeMap;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import io.reactivex.Flowable;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class ServicesHelper {

	private static final String TAG = ServicesHelper.class.getSimpleName();

	public static Flowable<LinkedTreeMap> getTracks() {
		return getServices().getTracksChart("it");
	}

	private static ServicesInterface getServices() {
		return new Retrofit.Builder()
				.baseUrl(BuildConfig.API_BASE_URL)
				.addConverterFactory(GsonConverterFactory.create())
				.addCallAdapterFactory(RxJava2CallAdapterFactory.create())
				.build()
				.create(ServicesInterface.class);
	}

}
