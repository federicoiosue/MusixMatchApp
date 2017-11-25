package android.feio.it.musixmatchapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.google.gson.internal.LinkedTreeMap;

import java.util.List;


public class SimpleItemRecyclerViewAdapter extends RecyclerView.Adapter<TracksViewHolder> {

    private final TrackListActivity mParentActivity;
    private final List<LinkedTreeMap> mValues;
    private final boolean mTwoPane;

    private final View.OnClickListener mOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            LinkedTreeMap track = (LinkedTreeMap) ((LinkedTreeMap) view.getTag()).get("track");
            if (mTwoPane) {
                Bundle arguments = new Bundle();
                arguments.putSerializable(TrackDetailFragment.TRACK, track);
                TrackDetailFragment fragment = new TrackDetailFragment();
                fragment.setArguments(arguments);
                mParentActivity.getSupportFragmentManager().beginTransaction()
                        .replace(R.id.track_detail_container, fragment)
                        .commit();
            } else {
                Intent intent = new Intent(mParentActivity.getApplicationContext(), TrackDetailActivity.class);
                intent.putExtra(TrackDetailFragment.TRACK, track);
                mParentActivity.getApplicationContext().startActivity(intent);
            }
        }
    };

    SimpleItemRecyclerViewAdapter(TrackListActivity parent,
                                  List<LinkedTreeMap> items,
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
        LinkedTreeMap track = mValues.get(position);

        Glide.with(mParentActivity).load(getTrackAlbumCover(track)).into(holder.trackAlbumCover);
        holder.trackTitle.setText(getTrackName(track));
        holder.trackArtist.setText(getTrackArtist(track));

        holder.itemView.setTag(mValues.get(position));
        holder.itemView.setOnClickListener(mOnClickListener);
    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    private String getTrackName(LinkedTreeMap item) {
        return ((LinkedTreeMap) item.get("track")).get("track_name").toString();
    }

    private String getTrackArtist(LinkedTreeMap item) {
        return ((LinkedTreeMap) item.get("track")).get("artist_name").toString();
    }

    private String getTrackAlbumCover(LinkedTreeMap item) {
        return ((LinkedTreeMap) item.get("track")).get("album_coverart_100x100").toString();
    }

}
