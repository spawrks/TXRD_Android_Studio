package txrd.app.android;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

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
        setContentView(R.layout.activity_rules);

        choosePositions = (TextView) findViewById(R.id.position_select);
        chooseGameplay = (TextView) findViewById(R.id.gameplay_select);
        choosePenalties = (TextView) findViewById(R.id.penalties_select);
        chosenText = (TextView) findViewById(R.id.selected_rules_text);

        choosePositions.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chosenText.setText(R.string.positions_desc);
            }
        });

        chooseGameplay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chosenText.setText(R.string.gameplay_desc);
            }
        });

        choosePenalties.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chosenText.setText(R.string.penalties_desc);
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


