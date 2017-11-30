package it.feio.android.musixmatchapp.views;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.google.gson.internal.LinkedTreeMap;

import java.util.List;

import io.reactivex.schedulers.Schedulers;
import it.feio.android.musixmatchapp.R;
import it.feio.android.musixmatchapp.models.LinkedTreeMapWrapper;
import it.feio.android.musixmatchapp.models.SimpleItemRecyclerViewAdapter;
import it.feio.android.musixmatchapp.services.ServicesHelper;
import it.feio.android.musixmatchapp.utils.ConnectionUtils;


public class TrackListActivity extends AppCompatActivity {

    private boolean mTwoPane;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_track_list);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setTitle(getTitle());

        if (findViewById(R.id.track_detail_container) != null) {
            mTwoPane = true;
        }

        try {
            LinkedTreeMap tracksResult = ServicesHelper.getTracks().subscribeOn(Schedulers.io()).blockingSingle();
            List<LinkedTreeMap> tracks = new LinkedTreeMapWrapper(tracksResult).getTracks();
            RecyclerView recyclerView = findViewById(R.id.track_list);
            recyclerView.setAdapter(new SimpleItemRecyclerViewAdapter(this, tracks, mTwoPane));
        } catch (Exception e) {
            TextView errorView = findViewById(R.id.error);
            errorView.setText(getErrorMessage(e));
            errorView.setVisibility(View.VISIBLE);
        }
    }

    private String getErrorMessage(Exception e) {
        if (!ConnectionUtils.isOnline(getApplicationContext())) {
            return getString(R.string.missing_internet_connection);
        } else {
            return getString(R.string.error) + ": " + e.getMessage();
        }
    }

}
