package txrd.app.android;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;

/**
 * Created by mollyrand on 3/17/14.
 */
public class TicketsView extends Activity {

    private WebView ticketPortal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tickets);

        //TODO: make back key go back in webview
        ticketPortal = (WebView) findViewById(R.id.ticketsWebView);
        ticketPortal.setWebViewClient(new TicketWebClient());
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
    }
}
