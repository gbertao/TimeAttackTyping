package com.giovanni.example.timeattacktyping;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    EditText et;
    TextView tv;
    final int timeLeft = 10;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        et = (EditText)findViewById(R.id.etInput);
        tv = (TextView)findViewById(R.id.tvTime);
    }

    public void startTimeAttack(View v) {
        ((Button) v).setEnabled(false);
        et.setText("");
        et.setEnabled(true);
        et.requestFocus();

        new Thread() {
            public void run() {
                for(int i = timeLeft; i > 0; i--) {
                    int finalI = i;
                    runOnUiThread(() -> tv.setText("Time Left: " + finalI + "s"));
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                runOnUiThread(() -> {
                    tv.setText("Time's up");
                    et.setEnabled(false);
                    ((Button) v).setEnabled(true);
                });
            }
        }.start();
    }
}