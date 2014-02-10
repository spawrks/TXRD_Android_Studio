package txrd.app.android;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.KeyEvent;
//import android.view.Display;
import android.view.Menu;
import android.view.View;
import android.webkit.WebView;
//import android.widget.Button;

public class MainActivity extends Activity {
	
	WebView scheduleView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
    }

    public void openScheduleLink(View v) { 
    	//2.9.14 - should now open up a webview to display schedule card image
//        dammit, why do you keep killing the app?? are you too big?
        Intent intent = new Intent(this, ScheduleView.class);
        startActivity(intent);
//    	scheduleView = (WebView)findViewById(R.id.scheduleCard);
//		scheduleView.getSettings().setBuiltInZoomControls(true);
//		scheduleView.loadUrl("file:///android_R/drawable/sched_2014.jpg");
    	
    }
    public void openTicketsLink(View v) { 
    //this is good for now, but I'd like to integrate with them in a later version 	
	    String url = "http://txrd.frontgatetickets.com"; 
	    Intent i = new Intent(Intent.ACTION_VIEW); 
	    i.setData(Uri.parse(url)); 
	    startActivity(i); 
}
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    
    public boolean onKeyDown(int keyCode, KeyEvent event) {
	    // Check if the key event was the Back button and if there's history
	    if ((keyCode == KeyEvent.KEYCODE_BACK) && scheduleView.canGoBack()) {
	        scheduleView.goBack();
	        return true;
	    }
	    // If it wasn't the Back key or there's no web page history, bubble up to the default
	    // system behavior (probably exit the activity)
	    return super.onKeyDown(keyCode, event);
	}

    public void seeTeams(View view) {
    	Intent intent = new Intent(this, AllTeams.class);
    	startActivity(intent);
    }
}
