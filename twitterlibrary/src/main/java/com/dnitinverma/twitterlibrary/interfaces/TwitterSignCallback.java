package com.dnitinverma.twitterlibrary.interfaces;


import com.twitter.sdk.android.core.Result;
import com.twitter.sdk.android.core.TwitterException;
import com.twitter.sdk.android.core.TwitterSession;

/**
 * Created by Rajat on 21-02-2017.
 */

public interface TwitterSignCallback {
    void twitterSignInSuccessResult(Result<TwitterSession> twitterSessionResult);
    void twitterSignInFailure(TwitterException e);
}
