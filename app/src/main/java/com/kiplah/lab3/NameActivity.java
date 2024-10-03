package com.kiplah.lab3;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class NameActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_name);

        TextView welcomeTextView = findViewById(R.id.welcomeTextView);
        Button thankYouButton = findViewById(R.id.thankYouButton);
        Button dontCallMeThatButton = findViewById(R.id.dontCallMeThatButton);

        String name = getIntent().getStringExtra("name");
        welcomeTextView.setText(getString(R.string.welcome_message, name));

        thankYouButton.setOnClickListener(v -> {
            setResult(1);
            finish();
        });

        dontCallMeThatButton.setOnClickListener(v -> {
            setResult(0);
            finish();
        });
    }
}