package txrd.app.android;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.graphics.Color;
import android.os.Bundle;
import android.app.Activity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.support.v4.app.NavUtils;
import android.view.View;
import android.webkit.WebView;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.List;

//purpose of this class is to define activity for viewing team rosters
public class AllTeams extends Activity {


    private Spinner teamSpinner = null;
    private WebView webView;


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_all_teams);


        teamSpinner = (Spinner) findViewById(R.id.teamSelector);
        teamSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            RelativeLayout teamWrapper = (RelativeLayout) findViewById(R.id.totalTeamView);
            TextView roster = (TextView) findViewById(R.id.rosterView);
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                switch (position){
                    case 0:
                        //default
                        webView = (WebView) findViewById(R.id.teamHeaderView);
                        webView.loadUrl("http://txrd.bellastorage.com/wp-content/uploads/2014/02/txrd-logo_spelled-out_web2.png");
                        teamWrapper.setBackgroundColor(getResources().getColor(R.color.black));
                        roster.setText(R.string.no_team);
                        roster.setTextColor(Color.WHITE);
                        break;
                    case 1:
                        Log.e("TAG", "Bombs Chosen");
                        teamWrapper.setBackgroundColor(getResources().getColor(R.color.bomb_green));
                        roster.setText(R.string.cherry_bombs);
                        roster.setTextColor(Color.BLACK);
                        //load bomb image
                        break;
                    case 2:
                        Log.e("TAG", "Cats Chosen");
                        teamWrapper.setBackgroundColor(getResources().getColor(R.color.hellcat_pink));
                        roster.setText(R.string.hellcats);
                        roster.setTextColor(Color.BLACK);
                        //load cat image
                        break;
                    case 3:
                        Log.e("TAG", "Rollers Chosen");
                        teamWrapper.setBackgroundColor(getResources().getColor(R.color.roller_blue));
                        roster.setTextColor(Color.WHITE);
                        roster.setText(R.string.holy_rollers);
                        //load roller image
                        break;
                    case 4:
                        Log.e("TAG", "Putas Chosen");
                        teamWrapper.setBackgroundColor(getResources().getColor(R.color.puta_red));
                        roster.setText(R.string.putas);
                        roster.setTextColor(Color.BLACK);
                        //load puta image
                        break;
                    case 5:
                        Log.e("TAG", "Cowgirls Chosen");
                        teamWrapper.setBackgroundColor(getResources().getColor(R.color.rhinestone_red));
                        roster.setText(R.string.rhinestone_cowgirls);
                        roster.setTextColor(Color.WHITE);

                        //load rc image
                        break;
                    case 6:
                        Log.e("TAG", "Guns Chosen");
                        teamWrapper.setBackgroundColor(getResources().getColor(R.color.guns_teal));
                        roster.setText(R.string.hired_guns);
                        roster.setTextColor(Color.BLACK);
                        //load gun image
                        break;
                    default:
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                teamWrapper.setBackgroundColor(getResources().getColor(R.color.black));
                webView = (WebView) findViewById(R.id.teamHeaderView);
                webView.loadUrl("http://txrd.bellastorage.com/wp-content/uploads/2014/02/txrd-logo_spelled-out_web2.png");
                roster.setText(R.string.no_team);
                roster.setTextColor(Color.WHITE);
            }
        });

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
		getMenuInflater().inflate(R.menu.all_teams, menu);
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

}
