package com.codepath.apps.twitterclient;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.codepath.apps.twitterclient.models.Tweet;
import com.codepath.apps.twitterclient.models.User;
import com.nostra13.universalimageloader.core.ImageLoader;
import java.util.List;

/**
 * Created by chengma on 6/19/14.
 */
public class TweetArrayAdapter extends ArrayAdapter<Tweet> {
    ImageView ivProfile;
    TextView tvScreenName,tvBody,tvName,tvTime;
    ImageLoader imageLoader;


    public TweetArrayAdapter(Context context,List<Tweet> tweets){
        super(context,0,tweets);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Tweet tweet = getItem(position);
        View v;
        if(convertView==null){
            LayoutInflater inflater=LayoutInflater.from(getContext());
            v=inflater.inflate(R.layout.tweet_item,parent,false);
        }else{
            v=convertView;
        }

        ivProfile = (ImageView)v.findViewById(R.id.ivProfile);
        tvScreenName = (TextView)v.findViewById(R.id.tvScreenName);
        tvBody = (TextView)v.findViewById(R.id.tvBody);
        tvName = (TextView)v.findViewById(R.id.tvName);
        tvTime = (TextView)v.findViewById(R.id.tvTime);

        ivProfile.setImageResource(android.R.color.transparent);
        imageLoader=ImageLoader.getInstance();
        User user = tweet.getUser();
        imageLoader.displayImage(user.getProfileImageUrl(),ivProfile);
        tvScreenName.setText("@"+user.getScreenName());
        tvName.setText(user.getName());
        tvBody.setText(tweet.getBody());
        tvTime.setText(Utils.getRelativeTimeAgo(tweet.getCreateAt()));

        return v;


    }
}
