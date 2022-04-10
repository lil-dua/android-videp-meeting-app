package com.example.chatapplication.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.Toast;

import com.example.chatapplication.R;
import com.example.chatapplication.databinding.ActivitySignInBinding;
import com.example.chatapplication.databinding.ActivitySplashBinding;
import com.example.chatapplication.utilities.Constants;
import com.example.chatapplication.utilities.PreferenceManager;

public class SplashActivity extends AppCompatActivity {

    private ActivitySplashBinding binding;
    private PreferenceManager preferenceManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySplashBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        preferenceManager = new PreferenceManager(getApplicationContext());
//        preferenceManager.putBoolean(Constants.KEY_IS_SPLASH, false);

        new Handler().postDelayed(() -> {
            if (!preferenceManager.getBoolean(Constants.KEY_IS_SPLASH)) {
                if (preferenceManager.getBoolean(Constants.KEY_IS_SIGN_IN)) {
                    preferenceManager.putBoolean(Constants.KEY_IS_SPLASH, true);
                    startActivity(new Intent(getApplicationContext(), MainActivity.class));
                    finish();
                } else {
                    preferenceManager.putBoolean(Constants.KEY_IS_SPLASH, true);
                    startActivity(new Intent(getApplicationContext(), SignInActivity.class));
                    finish();
                }
            }
        }, 1000);

//        new Handler().postDelayed(() -> {
//            preferenceManager.putBoolean(Constants.KEY_IS_SPLASH, false);
//            finish();
//        }, 1001);

    }
}