package com.codepath.apps.twitterclient.models;

import java.io.Serializable;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by chengma on 6/19/14.
 */
public class User implements Serializable {
	
    /**
	 * 
	 */
	private static final long serialVersionUID = 3731348544766309652L;
	
	private String name;
    private Long uid;
    private String screenName;
    private String profileImageUrl;
    private Long followers;
	private Long following;
	private String description;

    public static User fromJSON(JSONObject jsonObject){
        User user = new User();
        try{
            user.name = jsonObject.getString("name");
            user.uid=jsonObject.getLong("id");
            user.screenName=jsonObject.getString("screen_name");
            user.profileImageUrl=jsonObject.getString("profile_image_url");
            user.followers=jsonObject.getLong("followers_count");
            user.following=jsonObject.getLong("friends_count");
            user.description=jsonObject.getString("description");
        }catch (JSONException e){
            e.printStackTrace();;
            return null;
        }

        return user;
    }

    public String getName() {
        return name;
    }

    public Long getUid() {
        return uid;
    }

    public String getScreenName() {
        return screenName;
    }

    public String getProfileImageUrl() {
        return profileImageUrl;
    }
    
    public Long getFollowers() {
  		return followers;
  	}

  	public Long getFollowing() {
  		return following;
  	}

	public String getDescription() {
		return description;
	}
  	
  	
  

}
