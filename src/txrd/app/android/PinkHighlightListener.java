package txrd.app.android;

import android.graphics.Color;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;

/**
 * Created by mollyrand on 3/18/14.
 * ONLY FOR USE ON IMAGEVIEWS!
 */
public class PinkHighlightListener implements View.OnTouchListener {
    private static final int TXRD_PINK = Color.argb(155, 171, 6, 52);
    private ImageView imageView;


    @Override
    public boolean onTouch(View v, MotionEvent event) {
        imageView = (ImageView) v;
        if (v != null) {
            if (event.getAction() == MotionEvent.ACTION_DOWN) {
                imageView.setColorFilter(TXRD_PINK);
            } else if (event.getAction() == MotionEvent.ACTION_UP) {
                imageView.clearColorFilter();
            }
        }
        return false;//if it returns true the onclicklistener won't work
    }
}
