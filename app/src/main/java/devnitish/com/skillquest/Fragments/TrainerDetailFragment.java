package devnitish.com.skillquest.Fragments;


import android.os.Bundle;
import android.support.design.button.MaterialButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

import devnitish.com.skillquest.Adapters.AdapterHelperClasses.CategoryImplementation;
import devnitish.com.skillquest.Adapters.AdapterHelperClasses.HeadingImplementation;
import devnitish.com.skillquest.Adapters.AdapterHelperClasses.ParentInterface;
import devnitish.com.skillquest.Adapters.AdapterHelperClasses.TrainingImplementation;
import devnitish.com.skillquest.Adapters.HomeAdapter;
import devnitish.com.skillquest.Adapters.TrainingRecycler;
import devnitish.com.skillquest.Constants;
import devnitish.com.skillquest.Database.DatabaseAccess;
import devnitish.com.skillquest.Interfaces.CategoryClick;
import devnitish.com.skillquest.Interfaces.ExploreClick;
import devnitish.com.skillquest.Interfaces.TrainingClick;
import devnitish.com.skillquest.Models.CategoryModel;
import devnitish.com.skillquest.Models.TrainerModel;
import devnitish.com.skillquest.Models.TrainingModel;
import devnitish.com.skillquest.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class TrainerDetailFragment extends Fragment implements TrainingClick {

    View view;
    TextView aboutTrainer,trainerName,qualification,expertAt,experiance,description;
    RecyclerView list;
    TextView coursesOffered;

    TrainerModel trainerModel;
    ArrayList<TrainingModel> allTraining;
    TrainingRecycler adapter;


    int trainerId;

    TrainingClick trainingClick;

    public void setUpTrainingClick(TrainingClick trainingClick){

        this.trainingClick = trainingClick;

    }

    public TrainerDetailFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_teacher_detail, container, false);


        trainerId = getArguments().getInt(Constants.TEACHER);

        init();
        fetchData();

        return view;
    }

    private void init(){

        trainerName = view.findViewById(R.id.trainerName);
        qualification = view.findViewById(R.id.qualification);
        expertAt = view.findViewById(R.id.expertAt);
        experiance = view.findViewById(R.id.experiance);
        description = view.findViewById(R.id.trainerDescription);
        aboutTrainer = view.findViewById(R.id.aboutTrainer);
        coursesOffered = view.findViewById(R.id.coursesOffered);

        list = view.findViewById(R.id.list);

    }

    private void fetchData(){

        trainerModel = DatabaseAccess.getTrainerDetail(getActivity(),trainerId);
        allTraining = DatabaseAccess.getTeacherTrainings(getActivity(),trainerId);

        coursesOffered.setText("Courses offered by "+trainerModel.getName());
        aboutTrainer.setText("About "+trainerModel.getName());
        trainerName.setText(trainerModel.getName());
        qualification.setText(trainerModel.getQualification());
        expertAt.setText(trainerModel.getExpertise());
        experiance.setText(trainerModel.getExperiance());
        description.setText(trainerModel.getDetail());

        Log.e("data",allTraining.toString());

        adapter = new TrainingRecycler(getActivity(),allTraining);
        list.setAdapter(adapter);

        adapter.setUpTrainingClick(this);
    }

    @Override
    public void onTrainingClick(int trainingId) {

        if (trainingClick!=null){
            trainingClick.onTrainingClick(trainingId);
        }

    }
}