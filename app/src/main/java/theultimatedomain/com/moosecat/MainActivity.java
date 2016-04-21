package theultimatedomain.com.moosecat;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private Button mPlay;
    private Button mOptions;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setupUI();
        setupClickListeners();

        //this is simon

        // This is Marshall

        //this is Joe Buenning who sucks
    }
    //THIS IS SPARTA

    private void setupUI()
    {
        mPlay = (Button) findViewById(R.id.play_btn);
        mOptions = (Button) findViewById(R.id.options_btn);
    }

    private void setupClickListeners()
    {
        mPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, PlayActivity.class);
                startActivity(intent);
            }
        });

        mOptions.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }
}
