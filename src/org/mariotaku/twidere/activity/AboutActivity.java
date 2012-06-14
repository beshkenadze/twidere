package org.mariotaku.twidere.activity;

import org.mariotaku.twidere.R;

import android.os.Bundle;
import android.view.MenuItem;

public class AboutActivity extends BaseActivity {

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.about);
		getSupportActionBar().setDisplayHomeAsUpEnabled(true);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
			case MENU_HOME: {
				onBackPressed();
				break;
			}
		}
		return super.onOptionsItemSelected(item);
	}

}
