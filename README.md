# TwitterSignIn

TwitterSignIn is a library which makes signin easier.
Library is still in development so more features will be added soon.

<b>Features:</b>
<ul>
  <li>Twitter SignIn</li>
  <li>Fetch User Details(ID, Name)</li>
</ul>

<b>Getting started:</b><br>

<ul>
  <li>First of all, you need to register you application with twitter developer (https://apps.twitter.com) and don't enable email checkbox in twitter developer account.</li>
  
  
  <li>Add twitterlibrary into your project as module.</li>
<li> Add below line under <b>dependencies{..}</b> tag of app.gradle of your project
<br>
<b>compile project(path: ':twitterlibrary')</b></li>

<li> How to call library function from your activity or fragment
<b>Declare this global in activity - private TwitterSignInAI mTwitterSignInAI; </b></li>
<li> Call initializeTwitter method from onCreate method
<br>

            private void initializeTwitter() {
                   mTwitterSignInAI = new TwitterSignInAI();
                   mTwitterSignInAI.setActivity(MainActivity.this);
                   mTwitterSignInAI.setCallback(this);
               }

after this you can call doSignIn method using mTwitterSignInAI.
<li>Implement TwitterSignCallback in your activity or fragment.</li>
<li> Add below code into onActivityResultMethod
<br>
<b> mTwitterSignInAI.setActivityResult(requestCode, resultCode, data);
</b></li>
</ul>

<b>How to parse result data</b><br>

      public void twitterSignInSuccessResult(Result<TwitterSession> twitterSessionResult) {
            String name = twitterSessionResult.data.getUserName();
            String id = String.valueOf(twitterSessionResult.data.getId());
            ((TextView)findViewById(R.id.tv_id)).setText("Id: "+id);
            ((TextView)findViewById(R.id.tv_name)).setText("Name: "+name);
        }


<b>Releases</b><br>
<ul>
  <li>Version 1.1 - 3 Nov 2017 <br>
  End Authorize Progress (Twitter Sign in at two places - Handling of unauthorized access) </li>

 <li>Version 1.2 - 12 March 2018 <br>
  Resolve context issue</li>
  
</ul>