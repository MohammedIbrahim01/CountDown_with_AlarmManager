package com.example.x.test028;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    public static final String APP_PREFS = "AppPrefs";
    public static final String KEY_COUNT = "key-count";
    private static final int START_VALUE = 21;
    public static final String KEY_START_BUTTON_VISIBILITY = "start-button-visibility";

    private int startButtonVisibility;

    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;

    private TextView counterContainerTextView;
    private Button startButton;
    private Button resetButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //get SharedPreferences
        sharedPreferences = getSharedPreferences(APP_PREFS, MODE_PRIVATE);
        editor = sharedPreferences.edit();

        //initViews
        counterContainerTextView = (TextView) findViewById(R.id.counter_container_textView);
        startButton = (Button) findViewById(R.id.start_button);
        resetButton = (Button) findViewById(R.id.reset_button);

        //when click on startButton
        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //start Counter
                Counter.start(MainActivity.this);

                //hide startButton
                editor.putInt(KEY_START_BUTTON_VISIBILITY, View.GONE);
                editor.apply();
            }
        });

        resetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editor.putInt(KEY_COUNT, START_VALUE);
                editor.putInt(KEY_START_BUTTON_VISIBILITY, View.VISIBLE);
                editor.apply();
            }
        });

    }

    @Override
    protected void onResume() {
        super.onResume();
        sharedPreferences.registerOnSharedPreferenceChangeListener(new SharedPreferences.OnSharedPreferenceChangeListener() {
            @Override
            public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        updateUI();
                    }
                });
            }
        });
        updateUI();
    }

    private void updateUI() {

        String count = String.valueOf(sharedPreferences.getInt(KEY_COUNT, START_VALUE));

        startButtonVisibility = sharedPreferences.getInt(KEY_START_BUTTON_VISIBILITY, View.VISIBLE);

        counterContainerTextView.setText(count);

        startButton.setVisibility(startButtonVisibility);



    }

}