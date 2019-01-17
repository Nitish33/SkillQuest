package devnitish.com.skillquest.Walkthrough;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import devnitish.com.skillquest.Constants;
import devnitish.com.skillquest.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class WalkthroughFragment extends Fragment {

    View view;

    int color;
    String text;
    int image;
    Bundle arguements;

    RelativeLayout parent;
    ImageView mImage;
    TextView mText;

    public WalkthroughFragment() {
        // Required empty public constructor
    }

    public static WalkthroughFragment newInstance(int color,String text,int image) {

        Bundle args = new Bundle();
        args.putInt(Constants.Color,color);
        args.putString(Constants.TEXT,text);
        args.putInt(Constants.IMAGE,image);

        WalkthroughFragment fragment = new WalkthroughFragment();
        fragment.setArguments(args);

        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view =  inflater.inflate(R.layout.fragment_walkthrough, container, false);

        arguements = getArguments();

        color = arguements.getInt(Constants.Color);
        text = arguements.getString(Constants.TEXT);
        image = arguements.getInt(Constants.IMAGE);

        init();

        return view;
    }

    private void init(){

        parent = view.findViewById(R.id.parent);
        mText = view.findViewById(R.id.text);
        mImage = view.findViewById(R.id.image);

        parent.setBackgroundResource(color);
        mText.setText(text+"");
        mImage.setImageResource(image);
    }

}
