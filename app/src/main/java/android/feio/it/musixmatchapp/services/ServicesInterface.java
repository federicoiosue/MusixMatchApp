package android.feio.it.musixmatchapp.services;

import io.reactivex.Flowable;
import retrofit2.http.GET;
import retrofit2.http.Query;


public interface ServicesInterface {

	String APIKEY = "asd";

	@GET("chart.tracks.get?f_has_lyrics=1&apikey=" + APIKEY)
	Flowable<Object> getTracksChart(@Query("country") String country);
}
