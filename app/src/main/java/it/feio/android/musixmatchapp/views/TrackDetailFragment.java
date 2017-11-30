package it.feio.android.musixmatchapp.views;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import it.feio.android.musixmatchapp.GlideApp;
import it.feio.android.musixmatchapp.R;
import it.feio.android.musixmatchapp.models.LinkedTreeMapWrapper;
import it.feio.android.musixmatchapp.services.ServicesHelper;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.SubtitleCollapsingToolbarLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
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
    private Unbinder unbinder;

    @BindView(R.id.track_detail)
    TextView trackDetail;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FragmentActivity parentActivity = getActivity();
        if (getArguments() != null && getArguments().containsKey(TRACK)) {
            track = (HashMap) getArguments().getSerializable(TRACK);
            SubtitleCollapsingToolbarLayout appBarLayout = parentActivity.findViewById(R.id.toolbar_layout);
            if (appBarLayout != null) {
                appBarLayout.setTitle(track.get("track_name").toString());
                appBarLayout.setSubtitle(track.get("artist_name").toString());
                appBarLayout.setContentScrimColor(R.color.colorPrimary);
                ImageView detailCoverView = parentActivity.findViewById(R.id.detail_cover);
                GlideApp.with(parentActivity.getApplicationContext())
                        .load(track.get("album_coverart_350x350"))
                        .transition(withCrossFade())
                        .fitCenter()
                        .into(detailCoverView);
            }
        }
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.track_detail, container, false);
        unbinder = ButterKnife.bind(this, rootView);
        String trackId = new BigDecimal(track.get("track_id").toString()).toString();
        LinkedTreeMap lyricsMap = ServicesHelper.getTrackLyric(trackId).subscribeOn(Schedulers.io()).blockingSingle();
        LinkedTreeMap lyrics = new LinkedTreeMapWrapper(lyricsMap).getTrackLyrics();
        trackDetail.setText(getLyricsBody(lyrics));
        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    private String getLyricsBody(LinkedTreeMap lyrics) {
        String lyricsBody = lyrics.get("lyrics_body").toString();
        return lyricsBody.substring(0, lyricsBody.indexOf("...\n\n*******"));
    }
}
