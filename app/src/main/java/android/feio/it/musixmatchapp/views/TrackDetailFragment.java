package android.feio.it.musixmatchapp.views;

import android.feio.it.musixmatchapp.GlideApp;
import android.feio.it.musixmatchapp.R;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

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
            CollapsingToolbarLayout appBarLayout = getActivity().findViewById(R.id.toolbar_layout);
            if (appBarLayout != null) {
                appBarLayout.setTitle(track.get("track_name").toString());
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
//            track.get("track_id")
            ((TextView) rootView.findViewById(R.id.track_detail)).setText(track.toString());
        }
        return rootView;
    }
}
