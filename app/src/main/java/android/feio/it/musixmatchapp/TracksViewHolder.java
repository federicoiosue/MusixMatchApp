package android.feio.it.musixmatchapp;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;


public class TracksViewHolder extends RecyclerView.ViewHolder {

	final TextView mIdView;
	final TextView mContentView;

	TracksViewHolder(View view) {
		super(view);
		mIdView = view.findViewById(R.id.id_text);
		mContentView = view.findViewById(R.id.content);
	}
}
