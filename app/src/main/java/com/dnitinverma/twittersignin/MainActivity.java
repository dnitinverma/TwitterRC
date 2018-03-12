package com.dnitinverma.twittersignin;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.dnitinverma.twitterlibrary.TwitterSignInAI;
import com.dnitinverma.twitterlibrary.interfaces.TwitterSignCallback;
import com.twitter.sdk.android.core.Result;
import com.twitter.sdk.android.core.TwitterException;
import com.twitter.sdk.android.core.TwitterSession;

import org.json.JSONObject;

public class MainActivity extends AppCompatActivity implements TwitterSignCallback {
    private TwitterSignInAI mTwitterSignInAI;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initializeTwitter();
    }

    /*
  *  Initialize Twitter instance
  */
    private void initializeTwitter() {
        mTwitterSignInAI = new TwitterSignInAI();
        mTwitterSignInAI.setActivity(MainActivity.this);
        mTwitterSignInAI.setCallback(this);
    }


    /*
   *  Sign In Method
   */
    public void doLogin(View view){
        mTwitterSignInAI.doSignIn();
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        mTwitterSignInAI.setActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void twitterSignInSuccessResult(Result<TwitterSession> twitterSessionResult) {
        String name = twitterSessionResult.data.getUserName();
        String id = String.valueOf(twitterSessionResult.data.getId());
        ((TextView)findViewById(R.id.tv_id)).setText("Id: "+id);
        ((TextView)findViewById(R.id.tv_name)).setText("Name: "+name);
    }

    @Override
    public void twitterSignInFailure(TwitterException e) {
        Toast.makeText(MainActivity.this,e.getMessage().toString()+"",Toast.LENGTH_SHORT).show();
    }
}
