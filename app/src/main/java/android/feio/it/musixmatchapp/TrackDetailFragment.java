package android.feio.it.musixmatchapp;

import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.HashMap;


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
			}
		}
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
							 Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.track_detail, container, false);
		if (track != null) {
			((TextView) rootView.findViewById(R.id.track_detail)).setText(track.toString());
		}
		return rootView;
	}
}
