package theultimatedomain.com.moosecat;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.Toast;

/**
 * Created by student6 on 4/19/16.
 */
public class PlayActivity extends AppCompatActivity {

    private ImageView mMooseCat;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play);

        setupUI();
        setupClickListeners();
    }

    private void setupUI()
    {
        mMooseCat = (ImageView) findViewById(R.id.moose_cat_img);

    }

    private void setupClickListeners()
    {
        mMooseCat.setOnTouchListener(new OnSwipeTouchListener(PlayActivity.this) {
            public void onSwipeTop() {
                Toast.makeText(PlayActivity.this, "top", Toast.LENGTH_SHORT).show();
            }
            public void onSwipeRight() {
                Toast.makeText(PlayActivity.this, "right", Toast.LENGTH_SHORT).show();
            }
            public void onSwipeLeft() {
                Toast.makeText(PlayActivity.this, "left", Toast.LENGTH_SHORT).show();
            }
            public void onSwipeBottom() {
                Toast.makeText(PlayActivity.this, "bottom", Toast.LENGTH_SHORT).show();
            }

        });
    }
}
