package com.oluwafemi.payoneer.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.oluwafemi.payoneer.R;
import com.oluwafemi.payoneer.ui.pages.main.MainFragment;

import java.util.Objects;

import dagger.hilt.android.AndroidEntryPoint;

import static java.util.Objects.requireNonNull;

@AndroidEntryPoint
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);

        requireNonNull(getSupportActionBar()).setTitle(getString(R.string.payment_methods));

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.container, MainFragment.newInstance())
                    .commitNow();
        }
    }
}