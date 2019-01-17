package devnitish.com.skillquest.Fragments;


import android.os.Bundle;
import android.support.design.button.MaterialButton;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.ArrayList;

import devnitish.com.skillquest.Adapters.AdapterHelperClasses.CategoryImplementation;
import devnitish.com.skillquest.Adapters.AdapterHelperClasses.HeadingImplementation;
import devnitish.com.skillquest.Adapters.AdapterHelperClasses.ParentInterface;
import devnitish.com.skillquest.Adapters.AdapterHelperClasses.TrainingImplementation;
import devnitish.com.skillquest.Adapters.HomeAdapter;
import devnitish.com.skillquest.Database.DatabaseAccess;
import devnitish.com.skillquest.Interfaces.CategoryClick;
import devnitish.com.skillquest.Interfaces.ExploreClick;
import devnitish.com.skillquest.Interfaces.TrainingClick;
import devnitish.com.skillquest.Models.CategoryModel;
import devnitish.com.skillquest.Models.TrainingModel;
import devnitish.com.skillquest.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment
        implements CategoryClick,
        TrainingClick {

    ArrayList<ParentInterface> allItems  = new ArrayList<>();
    ListView listView;

    HomeAdapter adapter;
    View view;
    MaterialButton explore;

    // variable for forwarding category and traing click to the activity.
    // which is received from adapter..........................
    CategoryClick categoryClick;
    TrainingClick trainingClick;
    ExploreClick exploreClick;

    public void setUpCategoryClick(CategoryClick categoryClick){
        this.categoryClick = categoryClick;
    }

    public void setUpTrainingClick(TrainingClick trainingClick){
        this.trainingClick = trainingClick;
    }

    public void setUpExploreClick(ExploreClick exploreClick){

        this.exploreClick = exploreClick;
    }


    public HomeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_home,container,false);

        init();

        setUpAdapter();
        setUpExploreButton();


        return view;
    }

    private void init(){

        listView = view.findViewById(R.id.list);
        explore = view.findViewById(R.id.explore);

    }

    private void setUpAdapter(){

        allItems.clear();

        HeadingImplementation categoryHeading = new HeadingImplementation("Category");
        ArrayList<CategoryImplementation> categoryImplementations = new ArrayList<>();
        ArrayList<TrainingImplementation> trainingImplementations = new ArrayList<>();
        HeadingImplementation trainingHeading = new HeadingImplementation("Top Training");
        ArrayList<TrainingImplementation> recentlyImplementations = new ArrayList<>();
        HeadingImplementation recentlyHeading = new HeadingImplementation("Recently Added");

        ArrayList<CategoryModel> allCategory =DatabaseAccess.getAllCategory(getActivity());
        ArrayList<TrainingModel> allTraining = DatabaseAccess.getAllTraining(getActivity());

        for (CategoryModel model : allCategory){
            categoryImplementations.add(new CategoryImplementation(model));
        }


        // top / famous training....................
        int maxCount = allTraining.size();

        if(allTraining.size()>4){
            maxCount = 4;
        }


        for(int i = 0;i<maxCount;++i){
            TrainingImplementation implementation = new TrainingImplementation(
                    allTraining.get(i));
            trainingImplementations.add(implementation);

        }

        // recently added..................................


        if(allTraining.size()>4){
            maxCount = 4;
        }


        for(int i = 0;i<maxCount;++i){
            TrainingImplementation implementation = new TrainingImplementation(
                    allTraining.get(i));
            recentlyImplementations.add(implementation);

        }



        allItems.add(categoryHeading);
        allItems.addAll(categoryImplementations);
        allItems.add(trainingHeading);
        allItems.addAll(trainingImplementations);
        allItems.add(recentlyHeading);
        allItems.addAll(recentlyImplementations);

        adapter = new HomeAdapter(getActivity(),allItems);
        listView.setAdapter(adapter);

        // attaching category and training click listener
        adapter.setUpCategoryClick(this);
        adapter.setUpTrainingClick(this);

    }

    @Override
    public void onCategoryClick(int categoryId) {

        // forwarding received category to the activity...
        if(categoryClick!=null){
            categoryClick.onCategoryClick(categoryId);
        }
    }

    @Override
    public void onTrainingClick(int trainingId) {

        // forwarding received training to the activity
        if(trainingClick!=null){
            trainingClick.onTrainingClick(trainingId);
        }

    }

    private void setUpExploreButton(){

        explore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(exploreClick!=null){
                    exploreClick.onExploreClick();
                }
            }
        });
    }
}
