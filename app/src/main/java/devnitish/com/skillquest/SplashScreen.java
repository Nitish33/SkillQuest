package devnitish.com.skillquest;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import devnitish.com.skillquest.Database.DatabaseHelper;
import devnitish.com.skillquest.Registration.LoginScreen;
import devnitish.com.skillquest.Walkthrough.Walkthrough;

public class SplashScreen extends AppCompatActivity {

    SharedPreferences sharedPreferences;

    String exit;
    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        intent = getIntent();

        if(intent!=null){

            exit = intent.getStringExtra(Constants.EXIT);

            if(exit != null && exit.equals(Constants.EXIT)){
                finish();
                return;
            }

        }

        sharedPreferences = getSharedPreferences(Constants.SHARED,MODE_PRIVATE);

        checkForAutoLogin();


        // this line will initailize database and create if not exists..
        DatabaseHelper helper = new DatabaseHelper(this);

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(SplashScreen.this,Walkthrough.class);
                startActivity(intent);
                finish();
            }
        },1000);
    }

    private void checkForAutoLogin(){

        boolean autoLogin = sharedPreferences.getBoolean(Constants.AUTO_LOGIN,false);

        if(autoLogin){

            String userName = sharedPreferences.getString(Constants.USERNAME,null);
            String phoneNo = sharedPreferences.getString(Constants.PHONENO,null);
            String password = sharedPreferences.getString(Constants.PASSWORD,null);

            Session.USERNAME = userName;
            Session.PHONE = phoneNo;
            Session.PASSWORD = password;
        }

    }
}
