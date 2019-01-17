package devnitish.com.skillquest.Fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import devnitish.com.skillquest.Adapters.CategoryRecyclerAdapter;
import devnitish.com.skillquest.Adapters.HomeAdapter;
import devnitish.com.skillquest.Adapters.TrainingRecycler;
import devnitish.com.skillquest.Adapters.TrainingSimpleAdapter;
import devnitish.com.skillquest.Constants;
import devnitish.com.skillquest.Database.DatabaseAccess;
import devnitish.com.skillquest.Interfaces.CategoryClick;
import devnitish.com.skillquest.Interfaces.TrainingClick;
import devnitish.com.skillquest.Models.CategoryModel;
import devnitish.com.skillquest.Models.TrainingModel;
import devnitish.com.skillquest.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class CategoryFragment extends Fragment implements CategoryClick ,TrainingClick  {

    RecyclerView categoryList;
    ListView list;
    View view;
    View headerLayout;
    TextView title,description;
    ImageView image;

    CategoryRecyclerAdapter categoryNameAdapter;
    TrainingSimpleAdapter trainingRecyclerAdapter;
    CategoryModel categoryModel;

    int cid;

    ArrayList<CategoryModel> allCategory = new ArrayList<>();
    ArrayList<TrainingModel> allTraining = new ArrayList<>();

    TrainingClick trainingClick;

    public void setUpTrainingClick(TrainingClick click){
        this.trainingClick = click;
    }

    public CategoryFragment() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_category, container, false);
        headerLayout = (RelativeLayout) inflater.inflate(R.layout.header_category_list,null);
        cid = getArguments().getInt(Constants.CATEGORY);

        init();

        return view;
    }

    private void init(){

        title = headerLayout.findViewById(R.id.title);
        description  = headerLayout.findViewById(R.id.description);
        image = headerLayout.findViewById(R.id.image);

        categoryList = view.findViewById(R.id.categoryList);
        list = view.findViewById(R.id.list);

        allCategory = DatabaseAccess.getAllCategory(getActivity());
        allTraining = DatabaseAccess.getCategoryTraining(getActivity(),cid);
        categoryModel = DatabaseAccess.getCategoryDetail(getActivity(),cid);

        categoryNameAdapter = new CategoryRecyclerAdapter(getActivity(),allCategory,cid);
        categoryList.setAdapter(categoryNameAdapter);

        trainingRecyclerAdapter = new TrainingSimpleAdapter(getActivity(),allTraining);
        list.setAdapter(trainingRecyclerAdapter);

        trainingRecyclerAdapter.setUpTrainingClick(this);
        categoryNameAdapter.setUpCategoryClick(this);

        list.addHeaderView(headerLayout);

        title.setText(categoryModel.getName());
        description.setText(categoryModel.getDescription());
//
//        "file:///android_asset/pract_recommend_section1_pic2.png"

        if(categoryModel.getImagePath()!=null && !categoryModel.getImagePath().equals("")) {
            Picasso.with(getActivity())
                    .load("file:///android_asset/images/" + categoryModel.getImagePath()+".png")
                    .into(image);
        }
        else {
            image.setImageResource(R.drawable.mockup);
        }
    }


    @Override
    public void onCategoryClick(int categoryId) {

        Log.e("received","yes");

        cid = categoryId;

        allTraining.clear();
        allTraining.addAll(DatabaseAccess.getCategoryTraining(getActivity(),categoryId));
        trainingRecyclerAdapter.notifyDataSetChanged();
        categoryNameAdapter.setCurrentCategory(categoryId);
        categoryNameAdapter.notifyDataSetChanged();

        categoryModel = DatabaseAccess.getCategoryDetail(getActivity(),cid);
        title.setText(categoryModel.getName());
        description.setText(categoryModel.getDescription());


        if(categoryModel.getImagePath()!=null && !categoryModel.getImagePath().equals("")) {
            Picasso.with(getActivity())
                    .load("file:///android_asset/images/" + categoryModel.getImagePath()+".png")
                    .into(image);
        }
        else {
            image.setImageResource(R.drawable.mockup);
        }

    }

    @Override
    public void onTrainingClick(int trainingId) {

        if(trainingClick!=null){
            trainingClick.onTrainingClick(trainingId);
        }
    }

}
