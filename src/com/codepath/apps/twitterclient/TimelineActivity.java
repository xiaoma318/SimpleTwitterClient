package com.codepath.apps.twitterclient;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import com.codepath.apps.twitterclient.R;
import com.codepath.apps.twitterclient.models.ComposeActivity;
import com.codepath.apps.twitterclient.models.Tweet;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.JsonHttpResponseHandler;
import org.json.JSONArray;

import java.util.ArrayList;

/**
 * Created by chengma on 6/18/14.
 */
public class TimelineActivity extends Activity {
    private TwitterClient client;
    private ArrayList<Tweet> tweets;
    private ArrayAdapter<Tweet> aTweets;
    private ListView lvTweets;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.timeline);
        client = TwitterClientApp.getRestClient();
        popHomeTimeline();

        lvTweets = (ListView)findViewById(R.id.lvTweets);
        tweets=new ArrayList<Tweet>();
        aTweets=new TweetArrayAdapter(this,tweets);
        lvTweets.setAdapter(aTweets);
        lvTweets.setOnScrollListener(new EndlessScrollListener() {
            @Override
            public void onLoadMore(int page, int totalItemsCount) {
                getMoreData(String.valueOf(aTweets.getItem(totalItemsCount-1).getUid()));
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.home, menu);
        return true;
    }

    public void popHomeTimeline(){
        client.getHomeTimeline(new JsonHttpResponseHandler(){
            @Override
            public void onSuccess(JSONArray jsonArray) {
                Log.d("debug",jsonArray.toString());
               aTweets.addAll(Tweet.fromJSONArray(jsonArray));
            }

            @Override
            public void onFailure(Throwable e, String s) {
                Log.d("debug",e.toString());
                Log.d("debug",s);
            }
        });
    }

    public void getMoreData(String maxId){
        client.getMoreData(maxId,new JsonHttpResponseHandler(){
            @Override
            public void onSuccess(JSONArray jsonArray) {
                aTweets.addAll(Tweet.fromJSONArray(jsonArray));
            }

            @Override
            public void onFailure(Throwable e, String s) {
                Log.d("debug",e.toString());
                Log.d("debug",s);
            }
        });
    }

    public void onCompose(MenuItem mi){
        Intent intent = new Intent(this, ComposeActivity.class);
        startActivityForResult(intent,1);
    }

    public void refresh(MenuItem mi){
        aTweets.clear();
        popHomeTimeline();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode==1&&resultCode==RESULT_OK){
            aTweets.clear();
            popHomeTimeline();
        }
    }
}