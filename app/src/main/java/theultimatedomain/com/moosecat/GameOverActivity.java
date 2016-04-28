package theultimatedomain.com.moosecat;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

/**
 * Created by student6 on 4/28/16.
 */
public class GameOverActivity extends AppCompatActivity {

    private String mScore;
    private TextView mScoreText;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.game_over);
        Bundle bundle = getIntent().getExtras();
        mScore = bundle.getString(PlayActivity.mScoreKey);
        mScoreText = (TextView) findViewById(R.id.score_text);
        mScoreText.setText(mScore);
    }
}
