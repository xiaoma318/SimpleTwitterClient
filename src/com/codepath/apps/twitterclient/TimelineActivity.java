package com.codepath.apps.twitterclient;

import android.app.ActionBar;
import android.app.ActionBar.Tab;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.view.Menu;
import android.view.MenuItem;

import com.codepath.apps.twitterclient.fragments.HomeLineFragment;
import com.codepath.apps.twitterclient.fragments.MentionFragment;
import com.codepath.apps.twitterclient.fragments.TweetListFragment;
import com.codepath.apps.twitterclient.listeners.FragmentTabListener;
import com.codepath.apps.twitterclient.models.ComposeActivity;

/**
 * Created by chengma on 6/18/14.
 */
public class TimelineActivity extends FragmentActivity {
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.timeline);
		setTitle("Home");
		setupTabs();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.home, menu);
		return true;
	}

	public void onCompose(MenuItem mi) {
		Intent intent = new Intent(this, ComposeActivity.class);
		startActivityForResult(intent, 1);
	}
	
	public void onProfile(MenuItem mi) {
		Intent intent = new Intent(this, ProfileActivity.class);
		startActivity(intent);
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		if (requestCode == 1 && resultCode == RESULT_OK) {
			HomeLineFragment fragment=(HomeLineFragment)getSupportFragmentManager().findFragmentByTag("home");
			fragment.getATweets().clear();
			fragment.popHomeTimeline();
		}
	}

	private void setupTabs() {
		ActionBar actionBar = getActionBar();
		actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
		actionBar.setDisplayShowTitleEnabled(true);
		actionBar.setStackedBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.gray)));
		
		Tab tab1 = actionBar
				.newTab()
				.setText("Home")
				.setIcon(R.drawable.ic_home)
				.setTag("HomeLineFragment")
				.setTabListener(
						new FragmentTabListener<HomeLineFragment>(
								R.id.flContainer, this, "home",
							HomeLineFragment.class));
		

		actionBar.addTab(tab1);
		actionBar.selectTab(tab1);

		Tab tab2 = actionBar
				.newTab()
				.setText("Mentions")
				.setIcon(R.drawable.ic_mentions)
				.setTag("MentionsTimelineFragment")
				.setTabListener(
						new FragmentTabListener<MentionFragment>(
								R.id.flContainer, this, "mentions",
								MentionFragment.class));

		actionBar.addTab(tab2);
	}
}