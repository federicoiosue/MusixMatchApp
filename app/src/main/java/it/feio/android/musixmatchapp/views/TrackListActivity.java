package it.feio.android.musixmatchapp.views;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
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

        loadTracksList();
    }

    private void loadTracksList() {
        TextView errorView = findViewById(R.id.error);
        RecyclerView recyclerView = findViewById(R.id.track_list);
        try {
            LinkedTreeMap tracksResult = ServicesHelper.getTracks().subscribeOn(Schedulers.io()).blockingSingle();
            List<LinkedTreeMap> tracks = new LinkedTreeMapWrapper(tracksResult).getTracks();
            recyclerView.setAdapter(new SimpleItemRecyclerViewAdapter(this, tracks, mTwoPane));
            errorView.setVisibility(View.GONE);
            recyclerView.setVisibility(View.VISIBLE);
        } catch (Exception e) {
            errorView.setText(getErrorMessage(e));
            errorView.setVisibility(View.VISIBLE);
            recyclerView.setVisibility(View.GONE);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_refresh:
                loadTracksList();
                return true;
            default:
                return super.onOptionsItemSelected(item);
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
