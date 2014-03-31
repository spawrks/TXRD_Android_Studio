package txrd.app.android;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

import com.parse.Parse;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.StatusLine;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.InputStream;
import java.util.List;

//purpose of this class is to define activity for viewing team rosters
public class AllTeams extends Activity {


//    private Spinner teamSpinner = null;
//    private WebView webView;
    private ImageView chooseRollers;
    private ImageView chooseHellcats;
    private ImageView choosePutas;
    private ImageView chooseBombs;
    private ImageView chooseRhinestones;
    private ImageView chooseGuns;
    private ImageView headerImg;
    private ImageGetter getImage;


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
        Parse.initialize(this, "sx9RN6vXmXy3m3YEDBN8vte3eNDd6cS5bV6AHXCx", "GiQONWITzczG0rXEbOM9QKxfinlmGyz4QJWBM7Am");
        setContentView(R.layout.activity_choose_team);
        this.setTitle(R.string.teams);
        chooseRollers = (ImageView) findViewById(R.id.rollerpinup);
        chooseRollers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                teamSelected("HolyRollers");
            }
        });
        chooseRollers.setOnTouchListener(new PinkHighlightListener());
        chooseBombs = (ImageView) findViewById(R.id.cherrypinup);
        chooseBombs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                teamSelected("CherryBombs");
            }
        });
        chooseBombs.setOnTouchListener(new PinkHighlightListener());
        chooseHellcats = (ImageView) findViewById(R.id.catpinup);
        chooseHellcats.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                teamSelected("Hellcats");
            }
        });
        chooseHellcats.setOnTouchListener(new PinkHighlightListener());
        choosePutas = (ImageView) findViewById(R.id.putapinup);
        choosePutas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                teamSelected("Putas");
            }
        });
        choosePutas.setOnTouchListener(new PinkHighlightListener());
        chooseRhinestones = (ImageView) findViewById(R.id.rhinestonepinup);
        chooseRhinestones.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                teamSelected("Rhinestones");
            }
        });
        chooseRhinestones.setOnTouchListener(new PinkHighlightListener());
        chooseGuns = (ImageView) findViewById(R.id.gunpinup);
        chooseGuns.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                teamSelected("HiredGuns");
            }
        });
        chooseGuns.setOnTouchListener(new PinkHighlightListener());

//        setContentView(R.layout.activity_all_teams);

//        webView = (WebView) findViewById(R.id.teamHeaderView);
//        teamSpinner = (Spinner) findViewById(R.id.teamSelector);
//        teamSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
//            RelativeLayout teamWrapper = (RelativeLayout) findViewById(R.id.totalTeamView);
//            TextView roster = (TextView) findViewById(R.id.rosterView);
//            @Override
//            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
//                switch (position){
//                    case 0:
//                        //default
////                        webView = (WebView) findViewById(R.id.teamHeaderView);
////                        webView.loadUrl("http://txrd.bellastorage.com/wp-content/uploads/2014/02/txrd-logo_spelled-out_web2.png");
//                        webView.loadUrl("http://i.imgur.com/EwJHAAh.png");
//                        teamWrapper.setBackgroundColor(getResources().getColor(R.color.black));
//                        roster.setText(R.string.no_team);
//                        roster.setTextColor(Color.WHITE);
//                        break;
//                    case 1:
//                        Log.e("TAG", "Bombs Chosen");
//                        teamWrapper.setBackgroundColor(getResources().getColor(R.color.bomb_green));
//                        roster.setText(R.string.cherry_bombs);
//                        roster.setTextColor(Color.BLACK);
//                        webView.loadUrl("http://i.imgur.com/v6TDtLW.gif");
//                        //load bomb image
//                        break;
//                    case 2:
//                        Log.e("TAG", "Cats Chosen");
//                        teamWrapper.setBackgroundColor(getResources().getColor(R.color.hellcat_pink));
//                        roster.setText(R.string.hellcats);
//                        roster.setTextColor(Color.BLACK);
//                        webView.loadUrl("http://i.imgur.com/JaSf50P.gif");
//                        //load cat image
//                        break;
//                    case 3:
//                        Log.e("TAG", "Rollers Chosen");
//                        teamWrapper.setBackgroundColor(getResources().getColor(R.color.roller_blue));
//                        roster.setTextColor(Color.WHITE);
//                        roster.setText(R.string.holy_rollers);
//                        webView.loadUrl("http://i.imgur.com/cvotE4E.gif");
//                        //load roller image
//                        break;
//                    case 4:
//                        Log.e("TAG", "Putas Chosen");
//                        teamWrapper.setBackgroundColor(getResources().getColor(R.color.puta_red));
//                        roster.setText(R.string.putas);
//                        roster.setTextColor(Color.BLACK);
//                        webView.loadUrl("http://i.imgur.com/waeEW3p.gif");
//                        //load puta image
//                        break;
//                    case 5:
//                        Log.e("TAG", "Cowgirls Chosen");
//                        teamWrapper.setBackgroundColor(getResources().getColor(R.color.rhinestone_red));
//                        roster.setText(R.string.rhinestone_cowgirls);
//                        roster.setTextColor(Color.WHITE);
//                        webView.loadUrl("http://i.imgur.com/GeUBcVe.gif");
//
//                        //load rc image
//                        break;
//                    case 6:
//                        Log.e("TAG", "Guns Chosen");
//                        teamWrapper.setBackgroundColor(getResources().getColor(R.color.guns_teal));
//                        roster.setText(R.string.hired_guns);
//                        roster.setTextColor(Color.BLACK);
//                        webView.loadUrl("http://i.imgur.com/EwJHAAh.png");
//                        //load gun image
//                        break;
//                    default:
//                        break;
//                }
//            }
//
//            @Override
//            public void onNothingSelected(AdapterView<?> parent) {
//                teamWrapper.setBackgroundColor(getResources().getColor(R.color.black));
//                webView = (WebView) findViewById(R.id.teamHeaderView);
//                webView.loadUrl("http://txrd.bellastorage.com/wp-content/uploads/2014/02/txrd-logo_spelled-out_web2.png");
//                roster.setText(R.string.no_team);
//                roster.setTextColor(Color.WHITE);
//            }
//        });

		// Show the Up button in the action bar.
		setupActionBar();


	}


    public boolean isIntentAvailable(Intent intent) {
        PackageManager packageManager = getPackageManager();
        List<ResolveInfo> activities = packageManager.queryIntentActivities(intent, 0);
        boolean isIntentSafe = activities.size() > 0;
        return isIntentSafe;
    }

	/**
	 * Set up the {@link android.app.ActionBar}.
	 */
	private void setupActionBar() {

		getActionBar().setDisplayHomeAsUpEnabled(true);

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case android.R.id.home:
			// This ID represents the Home or Up button. In the case of this
			// activity, the Up button is shown. Use NavUtils to allow users
			// to navigate up one level in the application structure. For
			// more details, see the Navigation pattern on Android Design:
			//
			// http://developer.android.com/design/patterns/navigation.html#up-vs-back
			//
			NavUtils.navigateUpFromSameTask(this);
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

    public void teamSelected(String teamName){
        //change view - should this be at end?
        setContentView(R.layout.activity_selected_team);
        //find Parse class matching teamName
        ParseQuery<ParseObject> fileQuery = ParseQuery.getQuery(teamName);
        ParseQuery<ParseObject> headerQuery = fileQuery.whereMatches("name", "Header");
        try{
            getImage.execute(headerQuery.getFirst().getParseFile("headshot").getUrl());
        } catch (com.parse.ParseException e){
            Log.e("Parse Exception", e.getMessage());
        }
        //load header
        //download all entries
        //for each entry, load headshot & player description in proper spot
        //special space for captain & co-captain?
    }

    private class ImageGetter extends AsyncTask<String, Void, Bitmap> {

        public ImageGetter() {
            super();
        }

        @Override
        protected Bitmap doInBackground(String... params) {
            Bitmap bitmap;
            DefaultHttpClient client = new DefaultHttpClient();
            HttpGet getRequest = new HttpGet(params[0]);
            try{
                HttpResponse response = client.execute(getRequest);
                StatusLine statusLine = response.getStatusLine();
                if(statusLine.getStatusCode() == HttpStatus.SC_OK){
                    HttpEntity httpEntity = response.getEntity();
                    if(httpEntity != null){
                        InputStream inputStream = null;
                        try{
                            inputStream = httpEntity.getContent();
                            bitmap = BitmapFactory.decodeStream(inputStream);
                            return bitmap;
                        }finally {
                            if(inputStream != null){
                                inputStream.close();
                            }
                        }
                    }
                }
            }catch(Exception e){
                Log.e("Exception", e.getMessage());
            }
            return null;
        }

        @Override
        protected void onPostExecute(Bitmap bitmap) {
            super.onPostExecute(bitmap);
            headerImg.setImageBitmap(bitmap);
        }
    }
}
