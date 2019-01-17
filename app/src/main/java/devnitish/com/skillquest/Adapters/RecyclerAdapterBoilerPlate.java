package devnitish.com.skillquest.Adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import devnitish.com.skillquest.R;

public class RecyclerAdapterBoilerPlate extends RecyclerView.Adapter<RecyclerAdapterBoilerPlate.ViewHolder> {

    LayoutInflater inflater;
    Context context;

    public RecyclerAdapterBoilerPlate(Context context) {
        inflater = LayoutInflater.from(context);
        this.context = context;

    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View view = inflater.inflate(R.layout.adapter_category_chips, viewGroup, false);
        return new ViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder,
                                 final int i) {


    }

    @Override
    public int getItemCount() {
        return 0;
    }


    public class ViewHolder extends RecyclerView.ViewHolder {


        public ViewHolder(@NonNull View view) {
            super(view);

        }
    }
}
