package txrd.app.android;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.parse.Parse;
import com.parse.ParseFile;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.StatusLine;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.InputStream;
import java.util.Date;

public class MainActivity extends Activity {

    private Button scheduleButton = null;
    private Button ticketsButton = null;
    private Button teamsButton = null;
    private Button rulesButton = null;
    private ImageView headerImg = null;
    private HeaderGetter getsHeader;
    private String desc = "Check the schedule for the next bout!";
    private String headerURL;
    private Date today = new Date();
    private Date start;
    private Date end;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Parse.initialize(this, "sx9RN6vXmXy3m3YEDBN8vte3eNDd6cS5bV6AHXCx", "GiQONWITzczG0rXEbOM9QKxfinlmGyz4QJWBM7Am");
        setContentView(R.layout.activity_main);

        headerImg = (ImageView) findViewById(R.id.main_header);
        getsHeader = new HeaderGetter();

        ParseObject object;
        ParseQuery<ParseObject> fileQuery = ParseQuery.getQuery("Images");
        fileQuery = fileQuery.whereMatches("Description", "Main Header");
        try{
            object =  fileQuery.getFirst();
            Log.e("file", object.getObjectId());
            desc = object.getString("Content");
        } catch (com.parse.ParseException e) {
            object = null;
            Log.e("Parse", e.getMessage());
        }
        ParseFile headerFile = object.getParseFile("imgFile");
        try{
            Log.e("URL", headerFile.getUrl());
            headerURL = headerFile.getUrl();
        } catch(Exception e){
            Log.e("Fuck", e.getMessage());
            //TODO: set URL to default header
        }
        getsHeader.execute(headerURL);

        headerImg.setContentDescription(desc);

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
        Intent intent = new Intent(this, TicketsView.class);
        startActivity(intent);
}

    public void openRulesView(){
        Intent intent = new Intent(this,RulesView.class);
        startActivity(intent);
    }


    public void seeTeams(View view) {
    	Intent intent = new Intent(this, AllTeams.class);
    	startActivity(intent);
    }

    private class HeaderGetter extends AsyncTask<String, Void, Bitmap>{

        public HeaderGetter() {
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
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected void onPostExecute(Bitmap bitmap) {
            super.onPostExecute(bitmap);
            headerImg.setImageBitmap(bitmap);
        }
    }
}

