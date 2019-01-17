package devnitish.com.skillquest.Walkthrough;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.design.button.MaterialButton;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import devnitish.com.skillquest.Constants;
import devnitish.com.skillquest.Database.DatabaseAccess;
import devnitish.com.skillquest.Database.DatabaseHelper;
import devnitish.com.skillquest.HomeScreen;
import devnitish.com.skillquest.R;

public class Walkthrough extends AppCompatActivity {

    ViewPager pager;
    ImageView dot1,dot2,dot3;
    MaterialButton mSkip,mNext,mStart;
    LinearLayout mDotHolder;

    WalkthroughFragment screen1,screen2,screen3;
    MyPagerAdapter adapter;
    PagerAnimator fadeAnimator;


    Context context;

    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_walkthrough);

        sharedPreferences = getSharedPreferences(Constants.SHARED,MODE_PRIVATE);

        checkForFirstTime();

        init();
        setUpNextListener();
        setUpSkipListener();
        setUpStartListener();
        setUpPageChangeListener();



    }

    private void checkForFirstTime(){

        boolean firstTime = sharedPreferences.getBoolean(Constants.FIRST,true);

        if(!firstTime){
            Intent intent  = new Intent(this,HomeScreen.class);
            startActivity(intent);
            finish();
        }

        else {
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putBoolean(Constants.FIRST,false);
            editor.commit();
        }

    }

    private void init(){

        context =this;

        pager = findViewById(R.id.pager);
        mSkip = findViewById(R.id.skip);
        mNext = findViewById(R.id.next);
        mStart = findViewById(R.id.start);
        mDotHolder = findViewById(R.id.dotHolder);
        dot1 = findViewById(R.id.dot1);
        dot2 = findViewById(R.id.dot2);
        dot3 = findViewById(R.id.dot3);


        screen1 = WalkthroughFragment.newInstance(R.color.screen1Color,
                "This is first screen",R.drawable.mockup);

        screen2 = WalkthroughFragment.newInstance(R.color.screen2Color,
                "This is second screen",R.drawable.mockup);


        screen3 = WalkthroughFragment.newInstance(R.color.screen3Color,
                "This is third screen",R.drawable.mockup);



        adapter = new MyPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(screen1);
        adapter.addFragment(screen2);
        adapter.addFragment(screen3);

        pager.setAdapter(adapter);
        pager.setOffscreenPageLimit(3);

        fadeAnimator = new PagerAnimator();
        pager.setPageTransformer(true,fadeAnimator);
    }

    private void setUpNextListener(){

        mNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int currentIndex = pager.getCurrentItem();
                currentIndex  = currentIndex+1;

                pager.setCurrentItem(currentIndex);

            }
        });
    }

    private void setUpSkipListener(){

        mSkip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(context,HomeScreen.class);
                startActivity(intent);
                finish();

            }
        });
    }

    private void setUpStartListener(){

        mStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(context,HomeScreen.class);
                startActivity(intent);
                finish();
            }
        });
    }

    private void setUpPageChangeListener(){

        pager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {



            }

            @Override
            public void onPageSelected(int i) {

                // if all pages are scrolled show start button
                if(i == 2){
                    mDotHolder.setVisibility(View.GONE);
                    mNext.setVisibility(View.GONE);
                    mSkip.setVisibility(View.GONE);

                    mStart.setVisibility(View.VISIBLE);
                }

                else{
                    mDotHolder.setVisibility(View.VISIBLE);
                    mNext.setVisibility(View.VISIBLE);
                    mSkip.setVisibility(View.VISIBLE);

                    mStart.setVisibility(View.GONE);

                    adjustDot(i);
                }

            }

            @Override
            public void onPageScrollStateChanged(int i) {

            }
        });
    }

    private void adjustDot(int i){

        setAllDotOff();

        switch (i){

            case 2:
                dot3.setImageResource(R.drawable.rounded_white);

            case 1:
                dot2.setImageResource(R.drawable.rounded_white);

            case 0:
                dot1.setImageResource(R.drawable.rounded_white);

        }

    }

    private void setAllDotOff(){

        dot1.setImageResource(R.drawable.rounded_black);
        dot2.setImageResource(R.drawable.rounded_black);
        dot3.setImageResource(R.drawable.rounded_black);
    }
}
