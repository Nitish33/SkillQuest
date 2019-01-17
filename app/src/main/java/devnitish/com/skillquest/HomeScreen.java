package devnitish.com.skillquest;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import devnitish.com.skillquest.Fragments.CategoryFragment;
import devnitish.com.skillquest.Fragments.HomeFragment;
import devnitish.com.skillquest.Fragments.SearchFragment;
import devnitish.com.skillquest.Fragments.TrainerDetailFragment;
import devnitish.com.skillquest.Fragments.TrainingDetailFragment;
import devnitish.com.skillquest.Fragments.TrainingListFragment;
import devnitish.com.skillquest.Interfaces.CategoryClick;
import devnitish.com.skillquest.Interfaces.ContactClick;
import devnitish.com.skillquest.Interfaces.ExploreClick;
import devnitish.com.skillquest.Interfaces.ShowTrainer;
import devnitish.com.skillquest.Interfaces.TrainingClick;
import devnitish.com.skillquest.Registration.LoginScreen;

public class HomeScreen extends AppCompatActivity implements CategoryClick,
        TrainingClick, ExploreClick, ShowTrainer, ContactClick {

    // various fragment
    HomeFragment homeFragment;
    CategoryFragment categoryFragment;
    SearchFragment searchFragment;
    TrainingDetailFragment trainingDetailFragment;
    TrainingListFragment trainingListFragment;
    TrainerDetailFragment teacherDetailFragment;
    FrameLayout container;
    DrawerLayout drawerLayout;

    FragmentManager manager;
    RelativeLayout parent;
    FragmentTransaction transaction;
    NavigationView navigationView;
    Menu navigationMenu;

    View header;
    TextView mUserName;

    Toolbar toolbar;

    Fragment currentFragment;

    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_screen);

        init();
        setUpDrawerListener();
        setUpNavigationItemListener();

    }

    private void init() {

        sharedPreferences = getSharedPreferences(Constants.SHARED,MODE_PRIVATE);
        editor = sharedPreferences.edit();

        container = findViewById(R.id.container);
        drawerLayout = findViewById(R.id.drawerLayout);
        navigationView = findViewById(R.id.navigationView);
        toolbar = findViewById(R.id.toolbar);
        parent = findViewById(R.id.parent);
        navigationMenu = navigationView.getMenu();

        header = navigationView.getHeaderView(0);
        mUserName = header.findViewById(R.id.userName);

        homeFragment = new HomeFragment();
        categoryFragment = new CategoryFragment();
        searchFragment = new SearchFragment();
        trainingDetailFragment = new TrainingDetailFragment();
        trainingListFragment = new TrainingListFragment();
        teacherDetailFragment = new TrainerDetailFragment();

        manager = getSupportFragmentManager();

        transaction = manager.beginTransaction();
        transaction.replace(R.id.container, homeFragment);
        transaction.commit();

        currentFragment = homeFragment;

        // attaching category and training listerner to the home fragment
        homeFragment.setUpCategoryClick(this);
        homeFragment.setUpTrainingClick(this);
        homeFragment.setUpExploreClick(this);

        categoryFragment.setUpTrainingClick(this);
        trainingListFragment.setUpTrainingClick(this);
        trainingDetailFragment.setUpShowTrainer(this);
        trainingDetailFragment.setUpCategoryClick(this);
        trainingDetailFragment.setUpContactClick(this);

        teacherDetailFragment.setUpTrainingClick(this);

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawerLayout, toolbar,
                R.string.navigation_drawer_open, R.string.navigation_drawer_close);

        toggle.syncState();
        drawerLayout.setDrawerListener(toggle);



    }

    @Override
    protected void onResume() {
        super.onResume();

        if (Session.USERNAME != null) {

            mUserName.setText(Session.USERNAME);
            navigationMenu.findItem(R.id.gLogout).setVisible(true);
            navigationMenu.findItem(R.id.gLogin).setVisible(false);

        }
        else {
            navigationMenu.findItem(R.id.gLogout).setVisible(false);
            navigationMenu.findItem(R.id.login).setVisible(true);

        }


    }

    @Override
    public void onCategoryClick(int categoryId) {

        Log.e("category", "" + categoryId);

        Bundle bundle = new Bundle();
        bundle.putInt(Constants.CATEGORY, categoryId);
        categoryFragment.setArguments(bundle);

        transaction = manager.beginTransaction();
        transaction.replace(R.id.container, categoryFragment);
        transaction.addToBackStack(Constants.TAG_CATEGORY);
        transaction.commit();

        currentFragment = categoryFragment;
    }

    @Override
    public void onTrainingClick(int trainingId) {
        Log.e("training", "" + trainingId);

        Bundle bundle = new Bundle();
        bundle.putInt(Constants.TRAINING, trainingId);
        trainingDetailFragment.setArguments(bundle);

        transaction = manager.beginTransaction();
        transaction.replace(R.id.container, trainingDetailFragment);
        transaction.addToBackStack(null);
        transaction.commit();

        currentFragment = trainingDetailFragment;
    }

    // when user press explore button in home fragment..
    @Override
    public void onExploreClick() {

        transaction = manager.beginTransaction();
        transaction.replace(R.id.container, trainingListFragment);
        transaction.addToBackStack(null);
        transaction.commit();

        currentFragment = trainingListFragment;
    }


    private void setUpDrawerListener() {

        drawerLayout.addDrawerListener(new DrawerLayout.DrawerListener() {
            @Override
            public void onDrawerSlide(@NonNull View drawerView, float slideOffset) {

                parent.setTranslationX(slideOffset * drawerView.getWidth());

            }

            @Override
            public void onDrawerOpened(@NonNull View drawerView) {

            }

            @Override
            public void onDrawerClosed(@NonNull View drawerView) {

            }

            @Override
            public void onDrawerStateChanged(int newState) {

            }
        });
        drawerLayout.setScrimColor(Color.TRANSPARENT);


    }

    @Override
    public void onShowTrainer(int teacherId) {

        Bundle bundle = new Bundle();
        bundle.putInt(Constants.TEACHER, teacherId);
        teacherDetailFragment.setArguments(bundle);

        transaction = manager.beginTransaction();
        transaction.replace(R.id.container, teacherDetailFragment);
        transaction.addToBackStack(null);
        transaction.commit();

        currentFragment = teacherDetailFragment;

    }

    @Override
    public void onContactClick() {


        Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:+917019916858"));
        startActivity(intent);


    }

    private void setUpNavigationItemListener() {

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

                int id = menuItem.getItemId();

                switch (id) {

                    case R.id.login:
                        // check is user is already loged in or not
                        if(Session.USERNAME == null) {
                            Intent intent = new Intent(HomeScreen.this, LoginScreen.class);
                            startActivity(intent);
                            return true;
                        }
                        else {
                            Toast.makeText(HomeScreen.this,"You are already logged in",Toast.LENGTH_SHORT).show();

                        }

                        break;

                    case R.id.logout:
                        editor.putBoolean(Constants.AUTO_LOGIN,false);
                        editor.commit();

                        Intent intent = new Intent(HomeScreen.this,
                                SplashScreen.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|
                        Intent.FLAG_ACTIVITY_NEW_TASK);
                        intent.putExtra(Constants.EXIT,Constants.EXIT);
                        startActivity(intent);

                        break;


                }


                return false;
            }
        });
    }

}

