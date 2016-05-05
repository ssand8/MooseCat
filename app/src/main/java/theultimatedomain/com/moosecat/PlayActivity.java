package theultimatedomain.com.moosecat;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
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
    private int mLives = 5;
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
    public String mScoreString;
    //a swipe up or down will be given the value of 2, and a life will be removed


    private int mTotalLives = 4;
    private View mLifeOne;
    private View mLifeTwo;
    private View mLifeThree;
    private View mLifeFour;


    private TextView mTimerView;
    private double mStartTime;

    private Handler mHandler;



    public static final String START_TIME_KEY = "Start_time:";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play);

        setupUI();
        setupClickListeners();

        mHandler = new Handler();
        mStartTime = 60;


    }

    @Override
    protected void onResume() {
        super.onResume();
        mHandler.post(mTimerUpdate);
        mHandler.post(mImageTimer);
    }


    @Override
    protected void onPause() {
        super.onPause();

        mHandler.removeCallbacks(mTimerUpdate);
        mHandler.removeCallbacks(mImageTimer);
    }


    private void setupUI()
    {
        mMooseCat = (ImageView) findViewById(R.id.moose_cat_img);
        mTimerView = (TextView) findViewById(R.id.timer_view);
        mScoreText = (TextView) findViewById(R.id.score_text);
       // mLivesText = (TextView) findViewById(R.id.lives_text);
        mLifeOne = (View) findViewById(R.id.life_one);
        mLifeTwo = (View) findViewById(R.id.life_two);
        mLifeThree = (View) findViewById(R.id.life_three);
        mLifeFour = (View) findViewById(R.id.life_four);

//        mLivesText.setText(String.valueOf(mLives));
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
                checkUserInput(3);
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
            mScore = mScore + 100;
                mScoreText.setText(String.valueOf(mScore));
                randomImage();
                mHandler.removeCallbacks(mImageTimer);
                mHandler.postDelayed(mImageTimer, mDelay);
        } else {
//                mLives--;
//                changeLife();
//                mLivesText.setText(String.valueOf(mLives));
                removeLife();
                randomImage();
                mHandler.removeCallbacks(mImageTimer);
                mHandler.postDelayed(mImageTimer, mDelay);
//                if(mLives == 0)
//                {
//                    gameOver();
//                }
        }



//        if(mCorrectInput)
//            //correct input is swipe, picture is moose
//        {
//            if (input == 0) {
//                mScore++;
//                mScoreText.setText(String.valueOf(mScore));
//                randomImage();
//                mHandler.removeCallbacks(mImageTimer);
//                mHandler.postDelayed(mImageTimer, mDelay);
//            } else {
//                mLives--;
//                mLivesText.setText(String.valueOf(mLives));
//                randomImage();
//                mHandler.removeCallbacks(mImageTimer);
//                mHandler.postDelayed(mImageTimer, mDelay);
//            }
//        }
//        else
//        {
//            if(input == 1)
//            {
//                mScore++;
//                mScoreText.setText(String.valueOf(mScore));
//                randomImage();
//                mHandler.removeCallbacks(mImageTimer);
//                mHandler.postDelayed(mImageTimer, mDelay);
//            }
//            else
//            {
//                mLives--;
//                mLivesText.setText(String.valueOf(mLives));
//                randomImage();
//                mHandler.removeCallbacks(mImageTimer);
//                mHandler.postDelayed(mImageTimer, mDelay);
//            }
//        }


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
    private void gameOver()
    {
        mScoreString = String.valueOf(mScore);
        //Game over code
        Intent intent = new Intent(PlayActivity.this, GameOverActivity.class);
        intent.putExtra(mScoreKey, mScoreString);
        startActivity(intent);
        overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
        finish();
    }

    private void removeLife()
    {
        mLives--;
        changeLife();
//        mLivesText.setText(String.valueOf(mLives));
        if(mLives == 0)
        {
            gameOver();
        }
    }


    private void randomImage()
    {
        System.gc();
       // int value = Math.round((int) (Math.random() * 2));
        Random R = new Random();
        value = R.nextInt(3);
        Animation fadeIn = AnimationUtils.loadAnimation(PlayActivity.this, theultimatedomain.com.moosecat.R.anim.fade_in);
        mMooseCat.setBackgroundResource(images[value]);
        mMooseCat.startAnimation(fadeIn);
        if (value == 0) {
            //cat is value 1 which is swipe right
            mCorrectInput = 0;
        } else if (value == 1) {
            //moose is value 2 which is swipe left
            mCorrectInput = 1;
        } else if (value == 2) {
            //moosecat is value 3 which is swipe up
            mCorrectInput = 2;
        }
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
            removeLife();
            mHandler.postDelayed(mImageTimer, mDelay);
        }
    };

    private void getRandomImage() {
        Random random = new Random();
        value = random.nextInt(3) + 1;

        Animation fadeIn = AnimationUtils.loadAnimation(PlayActivity.this, R.anim.fade_in);
        // Animation fadeOut = AnimationUtils.loadAnimation(PlayActivity.this, R.anim.fade_out);

        if (value == 1) {
            mMooseCat.startAnimation(fadeIn);
            //cat is value 1 which is swipe right
            mCorrectInput = 1;
            mMooseCat.setImageResource(R.drawable.cat);
        } else if (value == 2) {
            mMooseCat.startAnimation(fadeIn);
            //moose is value 2 which is swipe left
            mCorrectInput = 2;
            mMooseCat.setImageResource(R.drawable.moose);
        } else if (value == 3) {
            mMooseCat.startAnimation(fadeIn);
            //moosecat is value 3 which is swipe up
            mCorrectInput = 3;
            mMooseCat.setImageResource(R.drawable.moosecat);
        }
    }

    private Runnable mTimerUpdate = new Runnable() {
        @Override
        public void run() {
            if (mStartTime > 0){
                mStartTime -= 1;

                if (mStartTime == 0){
                    gameOver();
                }

                mTimerView.setText(getFormattedtime(mStartTime));

                mHandler.postDelayed(mTimerUpdate, 1000);
            }




        }
    };


    private String getFormattedtime(double timeInSeconds){

        String result = "";
        int minutes = 0;
        int seconds = 0;

        //logic

        minutes = (int) (timeInSeconds / 60);
        seconds = (int) (timeInSeconds % 60);

        if (minutes > 9)
        {
            result += minutes;
        }
        else{
            result += "0" + minutes;
        }
        result += " : ";
        if (seconds > 9){
            result += seconds;
        }
        else
        {
            result += "0" + seconds;
        }

        return result;
    }


//
//    private void lifeLost(){
//        mLives = mLives - 1;
//        checkLives();
//    }

    // changeLife() is supposed to update the life circles at the bottom of activity_play.xml
    private void changeLife() {
        if (mLives == 3) {
            mLifeFour.setBackgroundResource(R.drawable.dead_life);
        } else if (mLives == 2) {
            mLifeThree.setBackgroundResource(R.drawable.dead_life);
        } else if (mLives == 1) {
            mLifeTwo.setBackgroundResource(R.drawable.dead_life);
        } else if (mLives ==0) {
            mLifeOne.setBackgroundResource(R.drawable.dead_life);
        }

//        checkLives();
    }



//    private void checkLives(){
//        if (mLives == 0)
//        {
//            gameOver();
//        }
//    }


//    private void gameOver(){
//
//        mTimerView.setText("GAME OVER");
//        mHandler.postDelayed(mTimerUpdate, 1000);
//    }



}
