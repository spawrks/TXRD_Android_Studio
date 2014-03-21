package txrd.app.android;

import android.app.Activity;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.parse.Parse;

/**
 * Created by mollyrand on 3/5/14.
 */
public class RulesView extends Activity{

    private TextView chosenText = null;
    private TextView choosePositions = null;
    private TextView chooseGameplay = null;
    private TextView choosePenalties = null;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Parse.initialize(this, "sx9RN6vXmXy3m3YEDBN8vte3eNDd6cS5bV6AHXCx", "GiQONWITzczG0rXEbOM9QKxfinlmGyz4QJWBM7Am");
        setContentView(R.layout.activity_rules);

        choosePositions = (TextView) findViewById(R.id.position_select);
        chooseGameplay = (TextView) findViewById(R.id.gameplay_select);
        choosePenalties = (TextView) findViewById(R.id.penalties_select);
        chosenText = (TextView) findViewById(R.id.selected_rules_text);
        Typeface font = Typeface.createFromAsset(getAssets(), "Vitesse-Medium.otf");
        chooseGameplay.setTypeface(font);
        choosePenalties.setTypeface(font);
        choosePositions.setTypeface(font);

        choosePositions.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chosenText.setText(R.string.positions_desc);
                choosePositions.setBackgroundColor(getResources().getColor(R.color.txrd_pink));
                choosePenalties.setBackgroundColor(getResources().getColor(R.color.black));
                chooseGameplay.setBackgroundColor(getResources().getColor(R.color.black));
            }
        });

        chooseGameplay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chosenText.setText(R.string.gameplay_desc);
                choosePositions.setBackgroundColor(getResources().getColor(R.color.black));
                choosePenalties.setBackgroundColor(getResources().getColor(R.color.black));
                chooseGameplay.setBackgroundColor(getResources().getColor(R.color.txrd_pink));
            }
        });

        choosePenalties.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chosenText.setText(R.string.penalties_desc);
                choosePositions.setBackgroundColor(getResources().getColor(R.color.black));
                choosePenalties.setBackgroundColor(getResources().getColor(R.color.txrd_pink));
                chooseGameplay.setBackgroundColor(getResources().getColor(R.color.black));
            }
        });


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


