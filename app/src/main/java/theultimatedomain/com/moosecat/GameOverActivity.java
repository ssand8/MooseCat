package theultimatedomain.com.moosecat;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by student6 on 4/28/16.
 */
public class GameOverActivity extends AppCompatActivity {

    private String mScore;
    private TextView mScoreText;
    private ImageView mImageView;
    private Button mCheat;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        System.gc();
        setContentView(R.layout.game_over);
        mImageView = (ImageView) findViewById(R.id.image_image);
        Bundle bundle = getIntent().getExtras();
        mScore = bundle.getString(PlayActivity.mScoreKey);
        mScoreText = (TextView) findViewById(R.id.score_text);
        mScoreText.setText(String.valueOf(mScore));
        mCheat = (Button) findViewById(R.id.cheat_btn);
        setupClickListeners();
    }

    private void setupClickListeners()
    {
        mCheat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mScoreText.setText(R.string.joesucks);
            }
        });
    }
}
