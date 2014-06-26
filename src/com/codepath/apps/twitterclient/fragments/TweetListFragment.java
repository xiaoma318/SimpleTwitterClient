package com.codepath.apps.twitterclient.fragments;

import java.util.ArrayList;

import com.codepath.apps.twitterclient.EndlessScrollListener;
import com.codepath.apps.twitterclient.R;
import com.codepath.apps.twitterclient.TweetArrayAdapter;
import com.codepath.apps.twitterclient.models.Tweet;

import eu.erikw.PullToRefreshListView;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class TweetListFragment extends Fragment {
	 ArrayList<Tweet> tweets;
	 TweetArrayAdapter aTweets;
	 PullToRefreshListView lvTweets;
	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		tweets = new ArrayList<Tweet>();
		aTweets = new TweetArrayAdapter(getActivity(), tweets);

		
	}
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View v = inflater.inflate(R.layout.fragment_tweet_list, container,false);
		
		lvTweets = (PullToRefreshListView) v.findViewById(R.id.lvTweets);
		lvTweets.setAdapter(aTweets);
		
		return v;
	}
	
	public PullToRefreshListView getLvTweets(){
		return lvTweets;
	}
	
	public TweetArrayAdapter getATweets(){
		return aTweets;
	}

}
