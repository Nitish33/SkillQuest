package devnitish.com.skillquest.Registration;

import android.support.design.button.MaterialButton;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import devnitish.com.skillquest.R;

public class LoginScreen extends AppCompatActivity {


    LoginFragment loginFragment;
    RegistrationFragment registrationFragment;
    FragmentManager fragmentManager;
    FragmentTransaction fragmentTransaction;
    MaterialButton login,register;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_screen);

        init();
        setUpSignInClick();
        setUpSignUpClick();
    }

    private void init(){

        loginFragment = new LoginFragment();
        registrationFragment = new RegistrationFragment();

        login = findViewById(R.id.signin);
        register = findViewById(R.id.signup);

        fragmentManager = getSupportFragmentManager();

        fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.container,loginFragment);
        fragmentTransaction.commit();

    }

    private void setUpSignInClick(){

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.container,loginFragment);
                fragmentTransaction.commit();
            }
        });
    }

    private void setUpSignUpClick(){

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.container,registrationFragment);
                fragmentTransaction.commit();

            }
        });
    }
}
