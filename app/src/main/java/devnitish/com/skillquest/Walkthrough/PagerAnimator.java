package devnitish.com.skillquest.Walkthrough;

import android.support.v4.view.ViewPager;
import android.view.View;

public class PagerAnimator implements ViewPager.PageTransformer {


    @Override
    public void transformPage(View page, float position) {

        page.setTranslationX(-page.getWidth()*position);

        if(position<-1)
            page.setAlpha(0);

        else if(position<=0){
            page.setAlpha(1f-Math.abs(position));
        }

        else if(position<=1){

            page.setAlpha(1f-position);

        }

        else {
            page.setAlpha(0);
        }
    }
}
