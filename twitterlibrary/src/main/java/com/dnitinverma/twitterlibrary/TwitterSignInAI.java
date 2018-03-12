package com.dnitinverma.twitterlibrary;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v4.content.FileProvider;
import android.util.Log;
import android.widget.Toast;

import com.dnitinverma.twitterlibrary.interfaces.TwitterSignCallback;
import com.twitter.sdk.android.core.Callback;
import com.twitter.sdk.android.core.Result;
import com.twitter.sdk.android.core.Twitter;
import com.twitter.sdk.android.core.TwitterAuthConfig;
import com.twitter.sdk.android.core.TwitterConfig;
import com.twitter.sdk.android.core.TwitterCore;
import com.twitter.sdk.android.core.TwitterException;
import com.twitter.sdk.android.core.TwitterSession;
import com.twitter.sdk.android.core.identity.TwitterAuthClient;
import com.twitter.sdk.android.tweetcomposer.ComposerActivity;
import com.twitter.sdk.android.tweetcomposer.TweetComposer;
import com.twitter.sdk.android.tweetcomposer.TweetUploadService;

import java.io.File;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * Created by appinventiv on 7/9/17.
 */

public class TwitterSignInAI  {
    private Activity mActivity;
    private TwitterSignCallback twitterSignCallback;
    private TwitterAuthClient authClient;

    /*
     *  Initialize activity instance
     */
    public void setActivity(Activity activity) {
        this.mActivity = activity;
    }

    /*
    *  Initialize Twitter callback
    */
    public void setCallback(TwitterSignCallback twitterSignCallback) {
        this.twitterSignCallback = twitterSignCallback;
    }



    /*
    *  Sign In Method
    */
    public void doSignIn() {

        authClient = new TwitterAuthClient();
        endAuthorizeInProgress();
        authClient.authorize(mActivity, new Callback<TwitterSession>() {
            @Override
            public void success(Result<TwitterSession> twitterSessionResult) {
                twitterSignCallback.twitterSignInSuccessResult(twitterSessionResult);
            }

            @Override
            public void failure(TwitterException e) {
                twitterSignCallback.twitterSignInFailure(e);
            }
        });
    }


    /*
    *  return callback to twitter using callbackmanager
    */
    public void setActivityResult(int requestCode, int resultCode, Intent data) {
        authClient.onActivityResult(requestCode, resultCode, data);
    }

    /*
    *  End Authorize progress
    */
    private void endAuthorizeInProgress() {
        try {
            Field authStateField = authClient.getClass().getDeclaredField("authState");
            authStateField.setAccessible(true);
            Object authState = authStateField.get(authClient);
            Method endAuthorize = authState.getClass().getDeclaredMethod("endAuthorize");
            endAuthorize.invoke(authState);
        } catch (NoSuchFieldException | SecurityException | InvocationTargetException | NoSuchMethodException | IllegalAccessException e) {
            Log.e("Couldn't end ", e + "");
        }
    }

    /*
    *  Method to share text and image
    */
    private void shareTweetWithImage(Uri image, String text)
    {
        /*The image Uri should be a Uri using the content:// scheme. For example:-
        Uri imageUri = FileProvider.getUriForFile(MainActivity.this,
        BuildConfig.APPLICATION_ID + ".file_provider",
        new File("/path/to/image"));*/

        TweetComposer.Builder builder = new TweetComposer.Builder(mActivity)
                .text(text)
                .image(image);
        builder.show();
    }

    /*
   *  Method to share text only
   */
    private void shareTweet(String text)
    {
        TweetComposer.Builder builder = new TweetComposer.Builder(mActivity)
                .text(text);
        builder.show();
    }


}
