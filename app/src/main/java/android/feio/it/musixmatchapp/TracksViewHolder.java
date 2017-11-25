package android.feio.it.musixmatchapp;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;


public class TracksViewHolder extends RecyclerView.ViewHolder {

    final ImageView trackAlbumCover;
    final TextView trackTitle;
    final TextView trackArtist;

    TracksViewHolder(View view) {
        super(view);
        trackAlbumCover = view.findViewById(R.id.track_album_cover);
        trackTitle = view.findViewById(R.id.track_name);
        trackArtist = view.findViewById(R.id.track_artist);
    }
}
