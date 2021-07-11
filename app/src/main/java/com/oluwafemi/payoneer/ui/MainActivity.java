package com.oluwafemi.payoneer.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.oluwafemi.payoneer.R;
import com.oluwafemi.payoneer.ui.pages.main.MainFragment;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.container, MainFragment.newInstance())
                    .commitNow();
        }
    }
}