package android.feio.it.musixmatchapp.services;

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import io.reactivex.Flowable;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class ServicesHelper {

	private static final String API_BASE_URL = "http://api.musixmatch.com/ws/1.1/";

	private static final String TAG = ServicesHelper.class.getSimpleName();

	public static Flowable<Object> getTracks() {
		Retrofit asd = new Retrofit.Builder()
				.baseUrl(API_BASE_URL)
				.addConverterFactory(GsonConverterFactory.create())
				.addCallAdapterFactory(RxJava2CallAdapterFactory.create())
				.build();
		return asd.create(ServicesInterface.class).getTracksChart("it");
	}

}
