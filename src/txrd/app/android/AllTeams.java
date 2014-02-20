package txrd.app.android;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.os.Bundle;
import android.app.Activity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.support.v4.app.NavUtils;
import android.view.View;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.List;

//purpose of this class is to define activity for viewing team rosters
public class AllTeams extends Activity {


    private Spinner teamSpinner = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_all_teams);


        teamSpinner = (Spinner) findViewById(R.id.teamSelector);
        int position = teamSpinner.getSelectedItemPosition();
        Intent implicitIntent = null;
        switch (position){
            case 0:
                //default
                break;
            case 1:
                Log.e("TAG", "Bombs Chosen");//this isn't registering...:/
                break;
            default:
                break;
        }
        if (implicitIntent!= null){
            if(isIntentAvailable(implicitIntent) == true) {
                startActivity(implicitIntent);
            }else{
                Toast.makeText(this, "no application availble", Toast.LENGTH_LONG).show();
            }
        }

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
