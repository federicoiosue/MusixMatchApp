package android.feio.it.musixmatchapp.views;

import android.feio.it.musixmatchapp.GlideApp;
import android.feio.it.musixmatchapp.R;
import android.feio.it.musixmatchapp.models.LinkedTreeMapWrapper;
import android.feio.it.musixmatchapp.services.ServicesHelper;
import android.os.Bundle;
import android.support.design.widget.SubtitleCollapsingToolbarLayout;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.google.gson.internal.LinkedTreeMap;
import io.reactivex.schedulers.Schedulers;

import java.math.BigDecimal;
import java.util.HashMap;

import static com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions.withCrossFade;


public class TrackDetailFragment extends Fragment {

	public static final String TRACK = "track";

	private HashMap track;

	public TrackDetailFragment() {
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		if (getArguments().containsKey(TRACK)) {
			track = (HashMap) getArguments().getSerializable(TRACK);
			SubtitleCollapsingToolbarLayout appBarLayout = getActivity().findViewById(R.id.toolbar_layout);
			if (appBarLayout != null) {
				appBarLayout.setTitle(track.get("track_name").toString());
				appBarLayout.setSubtitle(track.get("artist_name").toString());
				appBarLayout.setContentScrimColor(R.color.colorPrimary);
				ImageView detailCoverView = getActivity().findViewById(R.id.detail_cover);
				GlideApp.with(getActivity())
						.load(track.get("album_coverart_350x350"))
						.transition(withCrossFade())
						.fitCenter()
						.into(detailCoverView);
			}
		}
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
							 Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.track_detail, container, false);
		if (track != null) {
			String trackId = new BigDecimal(track.get("track_id").toString()).toString();
			LinkedTreeMap lyricsMap = ServicesHelper.getTrackLyric(trackId).subscribeOn(Schedulers.io()).blockingSingle();
			LinkedTreeMap lyrics = new LinkedTreeMapWrapper(lyricsMap).getTrackLyrics();
			((TextView) rootView.findViewById(R.id.track_detail)).setText(lyrics.get("lyrics_body").toString());
		}
		return rootView;
	}
}
