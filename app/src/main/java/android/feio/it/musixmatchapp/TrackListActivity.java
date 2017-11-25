package android.feio.it.musixmatchapp;

import android.feio.it.musixmatchapp.services.ServicesHelper;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import com.google.gson.internal.LinkedTreeMap;
import io.reactivex.schedulers.Schedulers;

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

		if (findViewById(R.id.track_detail_container) != null) {
			mTwoPane = true;
		}

		LinkedTreeMap tracksResult = ServicesHelper.getTracks().subscribeOn(Schedulers.io()).blockingSingle();
		List<LinkedTreeMap> tracks = new LinkedTreeMapWrapper(tracksResult).getTracks();
		RecyclerView recyclerView = findViewById(R.id.track_list);
		recyclerView.setAdapter(new SimpleItemRecyclerViewAdapter(this, tracks, mTwoPane));
	}

}
