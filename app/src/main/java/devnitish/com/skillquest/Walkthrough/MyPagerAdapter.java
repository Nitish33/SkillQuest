package devnitish.com.skillquest.Walkthrough;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;

public class MyPagerAdapter extends FragmentPagerAdapter {

    ArrayList<Fragment> fragmentsList = new ArrayList<>();

    public MyPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    public void addFragment(Fragment fragment){

        if(fragmentsList == null)
            fragmentsList = new ArrayList<>();

        fragmentsList.add(fragment);
    }

    @Override
    public Fragment getItem(int position) {
        return fragmentsList.get(position);
    }

    @Override
    public int getCount() {
        return fragmentsList.size();
    }


}