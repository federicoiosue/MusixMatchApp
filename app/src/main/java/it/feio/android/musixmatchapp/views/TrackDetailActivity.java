package it.feio.android.musixmatchapp.views;

import android.content.Intent;

import butterknife.BindView;
import butterknife.ButterKnife;
import it.feio.android.musixmatchapp.R;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.ActionBar;
import android.view.MenuItem;


public class TrackDetailActivity extends AppCompatActivity {

	@BindView(R.id.detail_toolbar)
	Toolbar toolbar;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_track_detail);
		ButterKnife.bind(this);
		setSupportActionBar(toolbar);

		ActionBar actionBar = getSupportActionBar();
		if (actionBar != null) {
			actionBar.setDisplayHomeAsUpEnabled(true);
		}

		if (savedInstanceState == null) {
			Bundle arguments = new Bundle();
			arguments.putSerializable(TrackDetailFragment.TRACK,
					getIntent().getExtras().getSerializable("track"));
			TrackDetailFragment fragment = new TrackDetailFragment();
			fragment.setArguments(arguments);
			getSupportFragmentManager().beginTransaction()
					.add(R.id.track_detail_container, fragment)
					.commit();
		}
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		int id = item.getItemId();
		if (id == android.R.id.home) {
			navigateUpTo(new Intent(this, TrackListActivity.class));
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
