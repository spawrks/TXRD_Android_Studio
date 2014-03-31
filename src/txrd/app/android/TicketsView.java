package txrd.app.android;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;

/**
 * Created by mollyrand on 3/17/14.
 */
public class TicketsView extends Activity {

    private WebView ticketPortal;
    Activity activity = this;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tickets);
        progressBar = (ProgressBar) findViewById(R.id.loadFrontgateProgress);
        ticketPortal = (WebView) findViewById(R.id.ticketsWebView);
        ticketPortal.setWebViewClient(new TicketWebClient());
        ticketPortal.setWebChromeClient(new WebChromeClient() {
            public void onProgressChanged(WebView view, int progress)
            {
                activity.setTitle("Loading...");
                activity.setProgress(progress * 100);
                progressBar.setProgress(progress);

                if(progress == 100)
                    activity.setTitle(R.string.buy_tickets);
                    progressBar.setVisibility(View.GONE);
            }
        }); //says it's loading while it loads the page to prevent user confusion.
        ticketPortal.getSettings().setBuiltInZoomControls(true);
        ticketPortal.getSettings().setJavaScriptEnabled(true);
        ticketPortal.loadUrl("http://txrd.frontgatetickets.com");

    }

    private class TicketWebClient extends WebViewClient {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            if (Uri.parse(url).getHost().equals("txrd.frontgatetickets.com")) {
                return false;
                //load page inline.
            }
            //Shouldn't be the case, but if somehow they load a different URL then open the web client
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
            startActivity(intent);
            return true;
        }

        @Override
        public void onPageStarted(WebView view, String url, Bitmap favicon) {
            super.onPageStarted(view, url, favicon);
        }
    }

    //hardware back button will go back to previous web page as long as one exists,
    //otherwise will go back to previous view
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if(event.getAction() == KeyEvent.ACTION_DOWN){
            switch(keyCode)
            {
                case KeyEvent.KEYCODE_BACK:
                    if(ticketPortal.canGoBack()){
                        ticketPortal.goBack();
                    }else{
                        finish();
                    }
                    return true;
            }

        }
        return super.onKeyDown(keyCode, event);
    }
}
