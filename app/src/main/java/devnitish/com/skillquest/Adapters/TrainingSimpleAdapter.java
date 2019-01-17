package devnitish.com.skillquest.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import devnitish.com.skillquest.Interfaces.TrainingClick;
import devnitish.com.skillquest.Models.TrainingModel;
import devnitish.com.skillquest.R;

public class TrainingSimpleAdapter extends ArrayAdapter {

    ArrayList<TrainingModel> allTraining = new ArrayList<>();
    Context mContext;
    LayoutInflater inflater;

    TrainingClick trainingClick;

    public void setUpTrainingClick(TrainingClick trainingClick){

        this.trainingClick = trainingClick;
    }


    public TrainingSimpleAdapter(Context context,ArrayList<TrainingModel> allTraining) {
        super(context, R.layout.adapter_training_item);

        mContext = context;
        inflater = LayoutInflater.from(mContext);
        this.allTraining = allTraining;

    }

    @Override
    public int getCount() {

        return allTraining.size();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View view = inflater.inflate(R.layout.adapter_training_item,parent,false);

        ImageView imageView;
        TextView title;
        TextView description;

        final TrainingModel model = allTraining.get(position);

        imageView = view.findViewById(R.id.image);
        title = view.findViewById(R.id.title);
        description = view.findViewById(R.id.description);

        title.setText(""+model.getName());
        description.setText(""+model.getDescription());

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(trainingClick!=null){

                    trainingClick.onTrainingClick(model.getTid());
                }

            }
        });


        if(model.getImageUrl()!=null && !model.getImageUrl().equals("")) {
            Picasso.with(mContext)
                    .load("file:///android_asset/images/" +model.getImageUrl()+".png")
                    .into(imageView);
        }
        else {
            imageView.setImageResource(R.drawable.mockup);
        }


        return view;
    }
}
