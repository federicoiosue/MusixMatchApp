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

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.schedulers.Schedulers;
import it.feio.android.musixmatchapp.R;
import it.feio.android.musixmatchapp.models.LinkedTreeMapWrapper;
import it.feio.android.musixmatchapp.models.SimpleItemRecyclerViewAdapter;
import it.feio.android.musixmatchapp.services.ServicesHelper;
import it.feio.android.musixmatchapp.utils.ConnectionUtils;


public class TrackListActivity extends AppCompatActivity {

    private boolean mTwoPane;

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.error)
    TextView errorView;
    @BindView(R.id.track_list)
    RecyclerView trackList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_track_list);
        ButterKnife.bind(this);

        setSupportActionBar(toolbar);
        toolbar.setTitle(getTitle());

        if (findViewById(R.id.track_detail_container) != null) {
            mTwoPane = true;
        }

        loadTracksList();
    }

    private void loadTracksList() {
        try {
            LinkedTreeMap tracksResult = ServicesHelper.getTracks().subscribeOn(Schedulers.io()).blockingSingle();
            List<LinkedTreeMap> tracks = new LinkedTreeMapWrapper(tracksResult).getTracks();
            trackList.setAdapter(new SimpleItemRecyclerViewAdapter(this, tracks, mTwoPane));
            errorView.setVisibility(View.GONE);
            trackList.setVisibility(View.VISIBLE);
        } catch (Exception e) {
            errorView.setText(getErrorMessage(e));
            errorView.setVisibility(View.VISIBLE);
            trackList.setVisibility(View.GONE);
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
