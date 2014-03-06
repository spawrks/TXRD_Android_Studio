package txrd.app.android;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
//import android.view.Display;
import android.view.Menu;
import android.view.View;
import android.widget.Button;

public class MainActivity extends Activity {

    private Button scheduleButton = null;
    private Button ticketsButton = null;
    private Button teamsButton = null;
    private Button rulesButton = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        scheduleButton = (Button) findViewById(R.id.imageButtonSched);
        scheduleButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openScheduleLink(view);
            }
        });

        ticketsButton = (Button) findViewById(R.id.imageButtonTix);
        ticketsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openTicketsLink(view);
            }
        
    });

        teamsButton = (Button) findViewById(R.id.buttonTeams);
        teamsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                seeTeams(v);
            }
        });

        rulesButton = (Button) findViewById(R.id.buttonRules);
        rulesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openRulesView();
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    public void openScheduleLink(View v) { 
//  currently: shows schedulecard image
//  future: integrate with calendar?
        Intent intent = new Intent(this, ScheduleView.class);
        startActivity(intent);

    	
    }
    public void openTicketsLink(View v) { 
    //this is good for now, but I'd like to integrate with them in a later version 	
	    String url = "http://txrd.frontgatetickets.com"; 
	    Intent i = new Intent(Intent.ACTION_VIEW); 
	    i.setData(Uri.parse(url)); 
	    startActivity(i); 
}

    public void openRulesView(){
        Intent intent = new Intent(this,RulesView.class);
        startActivity(intent);
    }


    public void seeTeams(View view) {
    	Intent intent = new Intent(this, AllTeams.class);
    	startActivity(intent);
    }
}
