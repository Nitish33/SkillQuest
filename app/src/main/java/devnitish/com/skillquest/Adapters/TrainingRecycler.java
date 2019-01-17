package devnitish.com.skillquest.Adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import devnitish.com.skillquest.Interfaces.TrainingClick;
import devnitish.com.skillquest.Models.TrainingModel;
import devnitish.com.skillquest.R;

public class TrainingRecycler
        extends RecyclerView.Adapter<TrainingRecycler.ViewHolder> {

    LayoutInflater inflater;
    Context context;
    ArrayList<TrainingModel> allTraining;

    TrainingClick trainingClick;

    public void setUpTrainingClick(TrainingClick trainingClick){

        this.trainingClick = trainingClick;
    }

    public TrainingRecycler(Context context,ArrayList<TrainingModel> allTraining) {
        inflater = LayoutInflater.from(context);
        this.context = context;
        this.allTraining= allTraining;



    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View view = inflater.inflate(R.layout.adapter_training_item, viewGroup, false);
        return new ViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder,
                                 final int i) {

        final TrainingModel model = allTraining.get(i);

        holder.title.setText(""+model.getName());
        holder.description.setText(""+model.getDescription());

        holder.view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(trainingClick!=null){
                    trainingClick.onTrainingClick(model.getTid());
                }
            }
        });


        if(model.getImageUrl()!=null && !model.getImageUrl().equals("")) {
            Picasso.with(context)
                    .load("file:///android_asset/images/" +model.getImageUrl()+".png")
                    .into(holder.image);
        }
        else {
            holder.image.setImageResource(R.drawable.mockup);
        }


    }

    @Override
    public int getItemCount() {
        return allTraining.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView image;
        TextView title;
        TextView description;
        View view;

        public ViewHolder(@NonNull View view) {
            super(view);

            image = view.findViewById(R.id.image);
            title = view.findViewById(R.id.title);
            description = view.findViewById(R.id.description);
            this.view =view;

        }
    }
}
