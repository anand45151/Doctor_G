package com.example.doctorg.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import com.example.doctorg.Adapter.OnBoarding_Screen_Adapter;
import com.example.doctorg.R;

public class OnBoarding_Screen extends AppCompatActivity {

    private ViewPager viewPager;
    private OnBoarding_Screen_Adapter adapter;
    private Button nextBtn, backBtn, skipButton;
    private LinearLayout indicatorLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_on_boarding_screen);

        // Initialize Views
        viewPager = findViewById(R.id.slideViewPager);
        nextBtn = findViewById(R.id.nextbtn);
        backBtn = findViewById(R.id.backbtn);
        skipButton = findViewById(R.id.skipButton);
        indicatorLayout = findViewById(R.id.indicator_layout);

        // Set up Adapter
        adapter = new OnBoarding_Screen_Adapter(this);
        viewPager.setAdapter(adapter);

        // Set up Button Listeners
        nextBtn.setOnClickListener(v -> {
            int currentItem = viewPager.getCurrentItem();
            if (currentItem < adapter.getCount() - 1) {
                viewPager.setCurrentItem(currentItem + 1);
            } else {
                // Last screen - navigate to main activity or home screen
                launchHomeScreen();
            }
        });

        backBtn.setOnClickListener(v -> {
            int currentItem = viewPager.getCurrentItem();
            if (currentItem > 0) {
                viewPager.setCurrentItem(currentItem - 1);
            }
        });

        skipButton.setOnClickListener(v -> launchHomeScreen());

        // Add page change listener if you want to update UI based on page changes
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageSelected(int position) {
                // Change the UI or indicator here if needed
                updateButtons(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });

        // Initial button state update
        updateButtons(0);
    }

    private void updateButtons(int position) {
        if (position == 0) {
            backBtn.setVisibility(View.INVISIBLE);
        } else {
            backBtn.setVisibility(View.VISIBLE);
        }

        if (position == adapter.getCount() - 1) {
            nextBtn.setText("Finish");
        } else {
            nextBtn.setText("Next");
        }
    }

    private void launchHomeScreen() {
        // Navigate to the main activity or home screen
        Intent intent = new Intent(OnBoarding_Screen.this, User_Login_Activity.class);
        startActivity(intent);
        finish(); // Optional: Prevent returning to onboarding

    }
}
