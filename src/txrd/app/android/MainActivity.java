package txrd.app.android;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

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

// Molly made all this so frack yeah!


public class MainActivity extends Activity {

    private ImageView scheduleButton = null;
    private ImageView ticketsButton = null;
    private ImageView teamsButton = null;
    private ImageView rulesButton = null;
    private ImageView headerImg = null;
    private ImageView facebookCircle = null;
    private ImageView twitterCircle = null;
    private ImageView flickrCircle;
    private ImageView gplusCircle;
    private HeaderGetter getsHeader;
    private String desc = "Check the schedule for the next bout!";
    private String headerURL;
    private MenuItem contactUs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Parse.initialize(this, "sx9RN6vXmXy3m3YEDBN8vte3eNDd6cS5bV6AHXCx", "GiQONWITzczG0rXEbOM9QKxfinlmGyz4QJWBM7Am");
        setContentView(R.layout.activity_main);

        headerImg = (ImageView) findViewById(R.id.main_header);
        facebookCircle = (ImageView) findViewById(R.id.facebook_circle);
        twitterCircle = (ImageView) findViewById(R.id.twitter_circle);
        flickrCircle = (ImageView) findViewById(R.id.flickr_circle);
        gplusCircle = (ImageView) findViewById(R.id.googleplus_circle);

        getsHeader = new HeaderGetter();//async task to load header before displaying

        ParseObject object;
        ParseQuery<ParseObject> fileQuery = ParseQuery.getQuery("Images");


        fileQuery = fileQuery.whereMatches("Description", "Main Header");//pull in Parse object currently labeled as Main Header
        try{
            object =  fileQuery.getFirst();
            desc = object.getString("Content");// gets description
        } catch (com.parse.ParseException e) {
            object = null;
            Log.e("Parse", e.getMessage());
        }
        ParseFile headerFile = object.getParseFile("imgFile");//gets URL for file, sends to headergetter

        try{
            Log.e("URL", headerFile.getUrl());
            headerURL = headerFile.getUrl();
        } catch(Exception e){
            Log.e("Fuck", e.getMessage());
            //TODO: set URL to default header
        }
        getsHeader.execute(headerURL);

        headerImg.setContentDescription(desc);//sets description

        scheduleButton = (ImageView) findViewById(R.id.imageButtonSched);

        scheduleButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openScheduleLink(view);
            }
        });
        scheduleButton.setOnTouchListener(new PinkHighlightListener());

        ticketsButton = (ImageView) findViewById(R.id.imageButtonTix);
        ticketsButton.setOnTouchListener(new PinkHighlightListener());
        ticketsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openTicketsLink(view);
            }
        
    });

        teamsButton = (ImageView) findViewById(R.id.buttonTeams);
        teamsButton.setOnTouchListener(new PinkHighlightListener());
        teamsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                seeTeams(v);
            }
        });

        rulesButton = (ImageView) findViewById(R.id.buttonRules);
        rulesButton.setOnTouchListener(new PinkHighlightListener());
        rulesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openRulesView();
            }
        });

        facebookCircle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = "http://m.facebook.com/txrollerderby";
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(url));
                startActivity(i);
            }
        });
        facebookCircle.setOnTouchListener(new PinkHighlightListener());

        twitterCircle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = "https://twitter.com/TXRDRollergirls";
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(url));
                startActivity(i);
            }
        });
        twitterCircle.setOnTouchListener(new PinkHighlightListener());

        flickrCircle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = "http://m.flickr.com/photos/txrd/collections/";
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(url));
                startActivity(i);
            }
        });
        flickrCircle.setOnTouchListener(new PinkHighlightListener());

        gplusCircle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = "https://plus.google.com/+texasrollerderby";
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(url));
                startActivity(i);
            }
        });
        gplusCircle.setOnTouchListener(new PinkHighlightListener());

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

    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()){
            case R.id.action_contact_us:
                Intent i = new Intent(Intent.ACTION_SEND);
                i.setType("message/rfc822");
                i.putExtra(Intent.EXTRA_SUBJECT, "Message from TXRD App");
                i.putExtra(Intent.EXTRA_EMAIL, new String[]{"info@txrd.com"} );
                i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                try{
                    startActivity(Intent.createChooser(i,"Send mail..."));
                } catch(android.content.ActivityNotFoundException ex){
                    Toast.makeText(MainActivity.this, "There are no email clients installed.", Toast.LENGTH_SHORT).show();
                }
                return true;
            default:
                return super.onOptionsItemSelected(item);

        }
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
        protected void onPostExecute(Bitmap bitmap) {
            super.onPostExecute(bitmap);
            headerImg.setImageBitmap(bitmap);
        }
    }

}

