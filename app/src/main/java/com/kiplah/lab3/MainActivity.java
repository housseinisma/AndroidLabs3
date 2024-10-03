package com.kiplah.lab3;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    private EditText nameEditText;
    private static final String PREFS_NAME = "MyPrefsFile";
    private static final String NAME_KEY = "name";
    private static final int NAME_ACTIVITY_REQUEST_CODE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        nameEditText = findViewById(R.id.nameEditText);
        Button nextButton = findViewById(R.id.nextButton);

        // Load name from SharedPreferences
        SharedPreferences settings = getSharedPreferences(PREFS_NAME, 0);
        String savedName = settings.getString(NAME_KEY, "");
        nameEditText.setText(savedName);

        nextButton.setOnClickListener(v -> {
            String name = nameEditText.getText().toString();
            Intent intent = new Intent(MainActivity.this, NameActivity.class);
            intent.putExtra("name", name);
            startActivityForResult(intent, NAME_ACTIVITY_REQUEST_CODE);
        });
    }

    @Override
    protected void onPause() {
        super.onPause();
        // Save name to SharedPreferences
        SharedPreferences settings = getSharedPreferences(PREFS_NAME, 0);
        SharedPreferences.Editor editor = settings.edit();
        editor.putString(NAME_KEY, nameEditText.getText().toString());
        editor.apply();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == NAME_ACTIVITY_REQUEST_CODE) {
            if (resultCode == 0) {
                // User wants to change their name, do nothing (stay on this activity)
            } else if (resultCode == 1) {
                // User is happy, close the app
                finish();
            }
        }
    }
}