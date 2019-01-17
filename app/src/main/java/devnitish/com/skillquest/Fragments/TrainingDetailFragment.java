package devnitish.com.skillquest.Fragments;


import android.support.design.button.MaterialButton;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import devnitish.com.skillquest.Constants;
import devnitish.com.skillquest.Database.DatabaseAccess;
import devnitish.com.skillquest.Interfaces.CategoryClick;
import devnitish.com.skillquest.Interfaces.ContactClick;
import devnitish.com.skillquest.Interfaces.ShowTrainer;
import devnitish.com.skillquest.Models.CategoryModel;
import devnitish.com.skillquest.Models.TrainerModel;
import devnitish.com.skillquest.Models.TrainingModel;
import devnitish.com.skillquest.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class TrainingDetailFragment extends Fragment {

    ImageView image;
    TextView title,description;
    TextView startFrom,availability,duration,price,category,location;
    TextView trainerName,qualification,expertAt,experiance, trainerDescription,otherCourses;
    MaterialButton contact;

    View view;

    TrainingModel trainingModel;
    CategoryModel categoryModel;
    TrainerModel trainerModel;

    int tid;
    int trainerId;
    Bundle arguments;

    ShowTrainer showTrainer;
    CategoryClick categoryClick;
    ContactClick contactClick;

    public void setUpShowTrainer(ShowTrainer showTrainer){
        this.showTrainer = showTrainer;
    }

    public void setUpCategoryClick(CategoryClick categoryClick){
        this.categoryClick = categoryClick;
    }

    public void setUpContactClick(ContactClick contactClick){
        this.contactClick = contactClick;
    }

    public TrainingDetailFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view =  inflater.inflate(R.layout.fragment_training_detail, container, false);


        init();
        fillDetails();

        setUpCategoryClickListener();
        setUpOtherCoursesClickListener();
        setUpContactClick();

        return view;
    }

    private void setUpCategoryClickListener() {

        category.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(categoryClick!=null){
                    categoryClick.onCategoryClick(categoryModel.getCid());
                }

            }
        });
    }

    private void init(){

        image = view.findViewById(R.id.image);
        title = view.findViewById(R.id.title);
        description = view.findViewById(R.id.description);
        startFrom = view.findViewById(R.id.startFrom);
        availability = view.findViewById(R.id.availability);
        duration = view.findViewById(R.id.duration);
        price = view.findViewById(R.id.price);
        category = view.findViewById(R.id.category);
        location = view.findViewById(R.id.location);
        trainerName = view.findViewById(R.id.trainerName);
        qualification = view.findViewById(R.id.qualification);
        expertAt = view.findViewById(R.id.expertAt);
        experiance = view.findViewById(R.id.experiance);
        trainerDescription = view.findViewById(R.id.trainerDescription);
        otherCourses = view.findViewById(R.id.otherCourses);
        contact = view.findViewById(R.id.contact);
    }

    private void fillDetails(){

        arguments = getArguments();

        if(arguments == null){

            Toast.makeText(getActivity(),"No argument passed",Toast.LENGTH_SHORT).show();
            return;
        }

        tid = arguments.getInt(Constants.TRAINING);

        trainingModel = DatabaseAccess.getTraining(getActivity(),tid);
        trainerModel = DatabaseAccess.getTrainerDetail(getActivity(),trainingModel.getTrainerId());
        categoryModel = DatabaseAccess.getCategoryDetail(getActivity(),trainingModel.getCid());

        trainerId = trainingModel.getTrainerId();

        title.setText(trainingModel.getName());
        description.setText(trainingModel.getDescription());
        startFrom.setText(trainingModel.getStartDate());
        availability.setText(trainingModel.getAvailability());
        price.setText(""+trainingModel.getCost());
        location.setText(trainingModel.getLocation());
        duration.setText(trainingModel.getDuration());
        category.setText(categoryModel.getName());

        // trainer details
        trainerName.setText(trainerModel.getName());
        qualification.setText(trainerModel.getQualification());
        expertAt.setText(trainerModel.getExpertise());
        experiance.setText(trainerModel.getExperiance());
        trainerDescription.setText(trainerModel.getDetail());



        if(trainingModel.getImageUrl()!=null && !trainingModel.getImageUrl().equals("")) {
            Picasso.with(getActivity())
                    .load("file:///android_asset/images/" +trainingModel.getImageUrl()+".png")
                    .into(image);
        }
        else {
            image.setImageResource(R.drawable.mockup);
        }

    }

    private void setUpOtherCoursesClickListener(){

        otherCourses.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(showTrainer!=null){
                    showTrainer.onShowTrainer(trainerId);
                }
            }
        });
    }

    private void setUpContactClick(){

        contact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(contactClick!=null){
                    contactClick.onContactClick();
                }

            }
        });


    }

}
