package devnitish.com.skillquest.Fragments;


import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import devnitish.com.skillquest.Adapters.TrainingRecycler;
import devnitish.com.skillquest.Database.DatabaseAccess;
import devnitish.com.skillquest.Interfaces.TrainingClick;
import devnitish.com.skillquest.Models.TrainingModel;
import devnitish.com.skillquest.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class TrainingListFragment extends Fragment implements TrainingClick {

    RecyclerView list;
    View view;

    ArrayList<TrainingModel> allTraining = new ArrayList<>();
    TrainingRecycler adapter;

    TrainingClick trainingClick;

    public void setUpTrainingClick(TrainingClick trainingClick){
        this.trainingClick = trainingClick;
    }

    public TrainingListFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_training_list, container, false);

        init();

        return view;
    }

    private void init(){

        list = view.findViewById(R.id.list);

        allTraining = DatabaseAccess.getAllTraining(getActivity());

        adapter = new TrainingRecycler(getActivity(),allTraining);
        list.setAdapter(adapter);

        adapter.setUpTrainingClick(this);

    }

    @Override
    public void onTrainingClick(int trainingId) {

        if(trainingClick!=null){
            trainingClick.onTrainingClick(trainingId);
        }
    }
}
