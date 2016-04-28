package theultimatedomain.com.moosecat;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

/**
 * Created by student6 on 4/19/16.
 */
public class PlayActivity extends AppCompatActivity {

    private ImageView mMooseCat;
    private TextView mScoreText;
    private TextView mLivesText;
    private int mCorrectInput;
    private boolean mSwipe;
    private int mDelay = 3000;
    private int mScore = 0;
    private int mLives = 3;
    private int value;
    private int[] images = new int[] {R.drawable.cat2, R.drawable.moose, R.drawable.moosecat};
    private float mDownX;
    private float mDownY;
    private final float SCROLL_THRESHOLD = 10;
    private boolean isOnClick;
    private Handler mImageHandler;
    private int swipeLeft = 0;
    private int swipeRight = 1;
    public static final String mScoreKey = "mScoreKey";
    //a swipe up or down will be given the value of 2, and a life will be removed

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
        mScoreText = (TextView) findViewById(R.id.score_text);
        mLivesText = (TextView) findViewById(R.id.lives_text);

    }



//    @Override
//    public boolean onTouchEvent(MotionEvent ev) {
//        switch (ev.getAction() & MotionEvent.ACTION_MASK) {
//            case MotionEvent.ACTION_DOWN:
//                mDownX = ev.getX();
//                mDownY = ev.getY();
//                isOnClick = true;
//                break;
//            case MotionEvent.ACTION_CANCEL:
//            case MotionEvent.ACTION_UP:
//                if (isOnClick) {
//  //                  Log.i(LOG_TAG, "onClick ");
//
//                    //tap is true
//                    checkUserInput(true);
//                    mSwipe = false;
//                }
//                break;
//            case MotionEvent.ACTION_MOVE:
//                if (isOnClick && (Math.abs(mDownX - ev.getX()) > SCROLL_THRESHOLD || Math.abs(mDownY - ev.getY()) > SCROLL_THRESHOLD)) {
//  //                  Log.i(LOG_TAG, "movement detected");
//                    isOnClick = false;
//                    //swipe is false
//                    checkUserInput(false);
//                }
//                break;
//            default:
//                break;
//        }
//        return true;
//    }

    private void setupClickListeners()
    {
//        mMooseCat.setOnTouchListener(new View.OnTouchListener() {
//            @Override
//            public boolean onTouch(View v, MotionEvent event) {
//                checkUserInput(true);
//                return false;
//            }
//        });

//        mMooseCat.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                mSwipe = false;
//                checkUserInput(true);
//            }
//        });



        mMooseCat.setOnTouchListener(new OnSwipeTouchListener(PlayActivity.this) {
            public void onSwipeTop() {
                Toast.makeText(PlayActivity.this, "top", Toast.LENGTH_SHORT).show();
                mSwipe = true;
                checkUserInput(2);
            }
            public void onSwipeRight() {
                Toast.makeText(PlayActivity.this, "right", Toast.LENGTH_SHORT).show();
                mSwipe = true;
                checkUserInput(1);
            }
            public void onSwipeLeft() {
                Toast.makeText(PlayActivity.this, "left", Toast.LENGTH_SHORT).show();
                mSwipe = true;
                checkUserInput(0);
            }
            public void onSwipeBottom() {
                Toast.makeText(PlayActivity.this, "bottom", Toast.LENGTH_SHORT).show();
                mSwipe = true;
                checkUserInput(2);
            }


//            @Override
//            public boolean onTouch(View v, MotionEvent event) {
//                if(event.getAction() == MotionEvent.ACTION_UP)
//                {
//                    mSwipe = false;
//                }
//                return super.onTouch(v, event);
//            }
//        });

//        mMooseCat.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                checkUserInput("tap");
//                mScoreText.setText(R.string.tap);
//            }
//        });
        });
    }


    private void checkUserInput(int input)
    {
        if(input == mCorrectInput) {
            mScore++;
                mScoreText.setText(String.valueOf(mScore));
                randomImage();
                mImageHandler.removeCallbacks(mImageTimer);
                mImageHandler.postDelayed(mImageTimer, mDelay);
        } else {
                mLives--;
                mLivesText.setText(String.valueOf(mLives));
                randomImage();
                mImageHandler.removeCallbacks(mImageTimer);
                mImageHandler.postDelayed(mImageTimer, mDelay);
        }
//        if(mCorrectInput)
//            //correct input is swipe, picture is moose
//        {
//            if (input == 0) {
//                mScore++;
//                mScoreText.setText(String.valueOf(mScore));
//                randomImage();
//                mImageHandler.removeCallbacks(mImageTimer);
//                mImageHandler.postDelayed(mImageTimer, mDelay);
//            } else {
//                mLives--;
//                mLivesText.setText(String.valueOf(mLives));
//                randomImage();
//                mImageHandler.removeCallbacks(mImageTimer);
//                mImageHandler.postDelayed(mImageTimer, mDelay);
//            }
//        }
//        else
//        {
//            if(input == 1)
//            {
//                mScore++;
//                mScoreText.setText(String.valueOf(mScore));
//                randomImage();
//                mImageHandler.removeCallbacks(mImageTimer);
//                mImageHandler.postDelayed(mImageTimer, mDelay);
//            }
//            else
//            {
//                mLives--;
//                mLivesText.setText(String.valueOf(mLives));
//                randomImage();
//                mImageHandler.removeCallbacks(mImageTimer);
//                mImageHandler.postDelayed(mImageTimer, mDelay);
//            }
//        }
        if(mLives == 0)
        {
            //Game over code
            Intent intent = new Intent(PlayActivity.this, GameOverActivity.class);
            intent.putExtra(mScoreKey, mScore);
            startActivity(intent);
        }

//        if(mSwipe == true && input == mCorrectInput)
//        {
//            mScore++;
//            mScoreText.setText(String.valueOf(mScore));
//            randomImage();
//        }
//        else if(input != mCorrectInput)
//        {
//            mLives--;
//            mLivesText.setText(String.valueOf(mLives));
//            randomImage();
//        }


//            if(input == mCorrectInput)
//            {
//                mScore++;
//                mScoreText.setText(String.valueOf(mScore));
//                randomImage();
//            }
//            else
//            {
//                mLives--;
//                mLivesText.setText(String.valueOf(mLives));
//                randomImage();
//            }


    }

    private void randomImage()
    {
        System.gc();
       // int value = Math.round((int) (Math.random() * 2));
        Random R = new Random();
        value = R.nextInt(2);
        Animation fadeIn = AnimationUtils.loadAnimation(PlayActivity.this, theultimatedomain.com.moosecat.R.anim.fade_in);
        mMooseCat.setBackgroundResource(images[value]);
        mMooseCat.startAnimation(fadeIn);
        //moose is #1
//        if(value == 1)
//        {
//            //moose is swipe
//            mCorrectInput = true;
//        }
//        else{
//            //cat is tap
//            mCorrectInput = false;
//        }
    }

    private Runnable mImageTimer = new Runnable() {
        @Override
        public void run() {
            randomImage();

            mImageHandler.postDelayed(mImageTimer, mDelay);
        }
    };

    private void getRandomImage() {
        Random random = new Random();
        value = random.nextInt(3) + 1;

        Animation fadeIn = AnimationUtils.loadAnimation(PlayActivity.this, R.anim.fade_in);
        // Animation fadeOut = AnimationUtils.loadAnimation(PlayActivity.this, R.anim.fade_out);

        if (value == 1) {
            mMooseCat.startAnimation(fadeIn);

            mMooseCat.setImageResource(R.drawable.cat);
        } else if (value == 2) {
            mMooseCat.startAnimation(fadeIn);

            mMooseCat.setImageResource(R.drawable.moose);
        } else if (value == 3) {
            mMooseCat.startAnimation(fadeIn);

            mMooseCat.setImageResource(R.drawable.moose_cat);
        }
    }
}
