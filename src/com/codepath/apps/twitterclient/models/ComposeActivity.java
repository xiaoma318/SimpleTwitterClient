package com.codepath.apps.twitterclient.models;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.*;
import com.codepath.apps.twitterclient.R;
import com.codepath.apps.twitterclient.TwitterClient;
import com.codepath.apps.twitterclient.TwitterClientApp;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.JsonHttpResponseHandler;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 * Created by chengma on 6/19/14.
 */
public class ComposeActivity extends Activity {
    static final int MAX_LENGTH = 140;

    EditText etInput;
    TextView tvCount;
    ImageButton btnTweet;
    private TwitterClient client;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.compose);
        client = TwitterClientApp.getRestClient();
        etInput = (EditText) findViewById(R.id.etInput);
        tvCount = (TextView) findViewById(R.id.tvWordCount);
        btnTweet = (ImageButton) findViewById(R.id.btnTweet);
        etInput.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                tvCount.setText(String.valueOf(MAX_LENGTH - etInput.getText().length()));
            }
        });
    }

    public void onTweet(View v) {
        if(etInput.getText().toString().equals("")){
            Toast.makeText(getBaseContext(),"Please input some words",Toast.LENGTH_SHORT).show();
            return;
        }else {
            client.compose(etInput.getText().toString(), new JsonHttpResponseHandler() {
                @Override
                public void onSuccess(JSONObject jsonObject) {
                    Toast.makeText(getBaseContext(), "Successfully post new tweet", Toast.LENGTH_LONG).show();
                    Intent intent = new Intent();
                    setResult(RESULT_OK, intent);
                    finish();
                }

                @Override
                public void onFailure(Throwable e, String s) {
                    Log.d("debug", e.toString());
                    Log.d("debug", s);
                }
            });
        }

    }
}