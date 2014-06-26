package com.codepath.apps.twitterclient.fragments;

import org.json.JSONArray;

import android.os.Bundle;
import android.util.Log;

import com.codepath.apps.twitterclient.TwitterClient;
import com.codepath.apps.twitterclient.TwitterClientApp;
import com.codepath.apps.twitterclient.models.Tweet;
import com.loopj.android.http.JsonHttpResponseHandler;

public class UserFragment extends TweetListFragment {
	private TwitterClient client;
	private String userId;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		client = TwitterClientApp.getRestClient();
		popUserTimeline();
	}

	public void popUserTimeline() {
		client.getUserTimeline(userId,new JsonHttpResponseHandler() {
			@Override
			public void onSuccess(JSONArray jsonArray) {
				aTweets.addAll(Tweet.fromJSONArray(jsonArray));
				lvTweets.onRefreshComplete();
			}

			@Override
			public void onFailure(Throwable e, String s) {
				Log.d("debug", e.toString());
				Log.d("debug", s);
			}
		});
	}
	
	public void setUserId(String id){
		userId = id;
	}
}
