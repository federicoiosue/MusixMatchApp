package android.feio.it.musixmatchapp;

import android.content.Context;
import android.content.Intent;
import android.feio.it.musixmatchapp.models.DummyItem;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;


public class SimpleItemRecyclerViewAdapter extends RecyclerView.Adapter<TracksViewHolder> {

	private final TrackListActivity mParentActivity;
	private final List<DummyItem> mValues;
	private final boolean mTwoPane;
	private final View.OnClickListener mOnClickListener = new View.OnClickListener() {
		@Override
		public void onClick(View view) {
			DummyItem item = (DummyItem) view.getTag();
			if (mTwoPane) {
				Bundle arguments = new Bundle();
				arguments.putString(TrackDetailFragment.ARG_ITEM_ID, item.id);
				TrackDetailFragment fragment = new TrackDetailFragment();
				fragment.setArguments(arguments);
				mParentActivity.getSupportFragmentManager().beginTransaction()
						.replace(R.id.track_detail_container, fragment)
						.commit();
			} else {
				Context context = view.getContext();
				Intent intent = new Intent(context, TrackDetailActivity.class);
				intent.putExtra(TrackDetailFragment.ARG_ITEM_ID, item.id);

				context.startActivity(intent);
			}
		}
	};

	SimpleItemRecyclerViewAdapter(TrackListActivity parent,
								  List<DummyItem> items,
								  boolean twoPane) {
		mValues = items;
		mParentActivity = parent;
		mTwoPane = twoPane;
	}

	@Override
	public TracksViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
		View view = LayoutInflater.from(parent.getContext())
				.inflate(R.layout.track_list_content, parent, false);
		return new TracksViewHolder(view);
	}

	@Override
	public void onBindViewHolder(TracksViewHolder holder, int position) {
		holder.mIdView.setText(mValues.get(position).id);
		holder.mContentView.setText(mValues.get(position).content);

		holder.itemView.setTag(mValues.get(position));
		holder.itemView.setOnClickListener(mOnClickListener);
	}

	@Override
	public int getItemCount() {
		return mValues.size();
	}

}
