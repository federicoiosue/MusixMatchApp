package it.feio.android.musixmatchapp.models;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import it.feio.android.musixmatchapp.R;


public class TracksViewHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.track_album_cover)
    ImageView trackAlbumCover;
    @BindView(R.id.track_name)
    TextView trackTitle;
    @BindView(R.id.track_artist)
    TextView trackArtist;

    TracksViewHolder(View view) {
        super(view);
        ButterKnife.bind(this, view);
    }
}
