package android.feio.it.musixmatchapp;

import android.app.Activity;
import android.feio.it.musixmatchapp.models.DummyItem;
import android.support.design.widget.CollapsingToolbarLayout;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.feio.it.musixmatchapp.models.DummyContent;


public class TrackDetailFragment extends Fragment {

	public static final String ARG_ITEM_ID = "item_id";

	private DummyItem mItem;

	public TrackDetailFragment() {
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		if (getArguments().containsKey(ARG_ITEM_ID)) {
			mItem = DummyContent.ITEM_MAP.get(getArguments().getString(ARG_ITEM_ID));

			Activity activity = this.getActivity();
			CollapsingToolbarLayout appBarLayout = activity.findViewById(R.id.toolbar_layout);
			if (appBarLayout != null) {
				appBarLayout.setTitle(mItem.content);
			}
		}
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
							 Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.track_detail, container, false);

		if (mItem != null) {
			((TextView) rootView.findViewById(R.id.track_detail)).setText(mItem.details);
		}

		return rootView;
	}
}
