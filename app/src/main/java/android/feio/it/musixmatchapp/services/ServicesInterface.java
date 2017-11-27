package android.feio.it.musixmatchapp.services;

import android.feio.it.musixmatchapp.BuildConfig;
import com.google.gson.internal.LinkedTreeMap;
import io.reactivex.Flowable;
import retrofit2.http.GET;
import retrofit2.http.Query;


public interface ServicesInterface {

	@GET("chart.tracks.get?f_has_lyrics=1&page_size=50&apikey=" + BuildConfig.API_KEY)
	Flowable<LinkedTreeMap> getTracksChart(@Query("country") String country);

	@GET("track.lyrics.get?apikey=" + BuildConfig.API_KEY)
	Flowable<LinkedTreeMap> getTrackLyric(@Query("track_id") String trackId);
}
