package devnitish.com.skillquest.Adapters;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import devnitish.com.skillquest.Adapters.AdapterHelperClasses.CategoryImplementation;
import devnitish.com.skillquest.Adapters.AdapterHelperClasses.HeadingImplementation;
import devnitish.com.skillquest.Adapters.AdapterHelperClasses.ParentInterface;
import devnitish.com.skillquest.Adapters.AdapterHelperClasses.TrainingImplementation;
import devnitish.com.skillquest.Constants;
import devnitish.com.skillquest.Interfaces.CategoryClick;
import devnitish.com.skillquest.Interfaces.TrainingClick;
import devnitish.com.skillquest.Models.CategoryModel;
import devnitish.com.skillquest.Models.TrainingModel;
import devnitish.com.skillquest.R;

public class HomeAdapter extends ArrayAdapter {

    ArrayList<ParentInterface> allItems;
    Context context;
    LayoutInflater inflater;




    CategoryClick categoryClick;
    TrainingClick trainingClick;

    public void setUpCategoryClick(CategoryClick categoryClick){
        this.categoryClick = categoryClick;
    }

    public void setUpTrainingClick(TrainingClick trainingClick){
        this.trainingClick = trainingClick;
    }

    public HomeAdapter( Context context,
                        ArrayList<ParentInterface> allItems) {

        super(context, R.layout.adapter_training_item);

        this.allItems = allItems;
        this.context = context;
        inflater = LayoutInflater.from(context);

    }

    @Override
    public int getCount() {
        return allItems.size();
    }

    @Override
    public View getView(int position,View convertView,ViewGroup parent) {

        ParentInterface parentInterface = allItems.get(position);

        if(parentInterface.getType() == Constants.TYPE_HEADING){
         return getHeaderView(position,convertView,parent);
        }
        else {
           return getItemView(position,parent);
        }

    }


    private View getHeaderView(int position,View convertView,ViewGroup parent){

        View view = inflater.inflate(R.layout.adapter_heading,parent,false);
        TextView text = view.findViewById(R.id.text);

        HeadingImplementation heading = (HeadingImplementation) allItems.get(position);
        text.setText(heading.getHeading());

        return view;
    }

    private View getItemView(int position,ViewGroup parent){

        View view = inflater.inflate(R.layout.adapter_training_item,parent,false);

        ImageView image;
        TextView title;
        TextView description;

        image = view.findViewById(R.id.image);
        title = view.findViewById(R.id.title);
        description = view.findViewById(R.id.description);

        ParentInterface parentInterface = allItems.get(position);

        // i have separated because i want different interface calling on click...

        if(parentInterface.getType() == Constants.TYPE_CATEGORY){

            CategoryImplementation cat = (CategoryImplementation) parentInterface;
            final CategoryModel model = cat.getModel();

            title.setText(model.getName());
            description.setText(model.getDescription());

            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    if(categoryClick!=null){
                        categoryClick.onCategoryClick(model.getCid());
                    }
                }
            });

            if(model.getImagePath()!=null && !model.getImagePath().equals("")) {
                Picasso.with(context)
                        .load("file:///android_asset/images/" + model.getImagePath()+".png")
                        .into(image);
            }
            else {
                image.setImageResource(R.drawable.mockup);
            }

        }
        else if(parentInterface.getType() == Constants.TYPE_TRAINING){

            TrainingImplementation implementation = (TrainingImplementation) parentInterface;
            final TrainingModel model = implementation.getModel();

            title.setText(model.getName());
            description.setText(model.getDescription());

            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    if(trainingClick!=null){
                        trainingClick.onTrainingClick(model.getTid());
                    }
                }
            });


            if(model.getImageUrl()!=null && !model.getImageUrl().equals("")) {
                Picasso.with(context)
                        .load("file:///android_asset/images/" + model.getImageUrl()+".png")
                        .into(image);
            }
            else {
                image.setImageResource(R.drawable.mockup);
            }
        }

        return view;

    }

}
