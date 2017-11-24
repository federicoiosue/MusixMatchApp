package android.feio.it.musixmatchapp.services;

import android.feio.it.musixmatchapp.BuildConfig;
import io.reactivex.Flowable;
import retrofit2.http.GET;
import retrofit2.http.Query;


public interface ServicesInterface {

	@GET("chart.tracks.get?f_has_lyrics=1&apikey=" + BuildConfig.API_KEY)
	Flowable<Object> getTracksChart(@Query("country") String country);
}
