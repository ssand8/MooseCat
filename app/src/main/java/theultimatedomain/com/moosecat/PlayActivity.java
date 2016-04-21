package theultimatedomain.com.moosecat;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.provider.Telephony;
import android.support.annotation.Nullable;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.Random;

/**
 * Created by student6 on 4/19/16.
 */
public class PlayActivity extends AppCompatActivity {

    private ImageView mMooseCat;
    private Handler mImageHandler;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play);

        mImageHandler = new Handler();

        setupUI();
        setupClickListeners();
    }

    @Override
    protected void onResume() {
        super.onResume();

        mImageHandler.post(mImageTimer);
    }

    @Override
    protected void onPause() {
        super.onPause();

        mImageHandler.removeCallbacks(mImageTimer);
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
                mMooseCat.setImageResource(R.drawable.cat);
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

    private Runnable mImageTimer = new Runnable() {
        @Override
        public void run() {
            getRandomImage();

            mImageHandler.postDelayed(mImageTimer, 1000);
        }
    };

    private void getRandomImage() {
        Random random = new Random();
        int imageNumber = random.nextInt(3);

        if (imageNumber == 2) {
            mMooseCat.setImageResource(R.drawable.cat);
        } else if (imageNumber > 0 && imageNumber < 2) {
            mMooseCat.setImageResource(R.drawable.moose);
        }
    }
}
