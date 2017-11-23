package android.feio.it.musixmatchapp;

import android.content.Context;
import android.content.Intent;
import android.feio.it.musixmatchapp.models.DummyItem;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import android.feio.it.musixmatchapp.models.DummyContent;

import java.util.List;


public class TrackListActivity extends AppCompatActivity {

	private boolean mTwoPane;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_track_list);

		Toolbar toolbar = findViewById(R.id.toolbar);
		setSupportActionBar(toolbar);
		toolbar.setTitle(getTitle());

		FloatingActionButton fab = findViewById(R.id.fab);
		fab.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
						.setAction("Action", null).show();
			}
		});

		if (findViewById(R.id.track_detail_container) != null) {
			mTwoPane = true;
		}

		View recyclerView = findViewById(R.id.track_list);
		assert recyclerView != null;
		setupRecyclerView((RecyclerView) recyclerView);
	}

	private void setupRecyclerView(@NonNull RecyclerView recyclerView) {
		recyclerView.setAdapter(new SimpleItemRecyclerViewAdapter(this, DummyContent.ITEMS, mTwoPane));
	}

	public static class SimpleItemRecyclerViewAdapter
			extends RecyclerView.Adapter<SimpleItemRecyclerViewAdapter.ViewHolder> {

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
		public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
			View view = LayoutInflater.from(parent.getContext())
					.inflate(R.layout.track_list_content, parent, false);
			return new ViewHolder(view);
		}

		@Override
		public void onBindViewHolder(final ViewHolder holder, int position) {
			holder.mIdView.setText(mValues.get(position).id);
			holder.mContentView.setText(mValues.get(position).content);

			holder.itemView.setTag(mValues.get(position));
			holder.itemView.setOnClickListener(mOnClickListener);
		}

		@Override
		public int getItemCount() {
			return mValues.size();
		}

		class ViewHolder extends RecyclerView.ViewHolder {

			final TextView mIdView;
			final TextView mContentView;

			ViewHolder(View view) {
				super(view);
				mIdView = view.findViewById(R.id.id_text);
				mContentView = view.findViewById(R.id.content);
			}
		}
	}
}
