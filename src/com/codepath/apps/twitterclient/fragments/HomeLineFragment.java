package com.codepath.apps.twitterclient.fragments;

import org.json.JSONArray;

import com.codepath.apps.twitterclient.EndlessScrollListener;
import com.codepath.apps.twitterclient.TwitterClient;
import com.codepath.apps.twitterclient.TwitterClientApp;
import com.codepath.apps.twitterclient.models.Tweet;
import com.loopj.android.http.JsonHttpResponseHandler;

import eu.erikw.PullToRefreshListView;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class HomeLineFragment extends TweetListFragment {
	private TwitterClient client;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		client = TwitterClientApp.getRestClient();
		popHomeTimeline();
		
		
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View v =super.onCreateView(inflater, container, savedInstanceState);
		lvTweets.setOnRefreshListener(new PullToRefreshListView.OnRefreshListener() {
			@Override
			public void onRefresh() {
				aTweets.clear();
				popHomeTimeline();
			}
		});
		
		lvTweets.setOnScrollListener(new EndlessScrollListener() {
			@Override
			public void onLoadMore(int page, int totalItemsCount) {
				if (totalItemsCount >= 2) {
					Log.d("total", totalItemsCount + "");
					getMoreData(String.valueOf(aTweets.getItem(
							totalItemsCount - 2).getUid()));
				}
			}
		});
		
		return v;
	}
	
	public void popHomeTimeline() {
		client.getHomeTimeline(new JsonHttpResponseHandler() {
			@Override
			public void onSuccess(JSONArray jsonArray) {
				// Log.d("debug", jsonArray.toString());
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
	
	public void getMoreData(String maxId) {
		client.getMoreData(maxId, new JsonHttpResponseHandler() {
			@Override
			public void onSuccess(JSONArray jsonArray) {
				aTweets.addAll(Tweet.fromJSONArray(jsonArray));
			}

			@Override
			public void onFailure(Throwable e, String s) {
				Log.d("debug", e.toString());
				Log.d("debug", s);
			}
		});
	}

}
