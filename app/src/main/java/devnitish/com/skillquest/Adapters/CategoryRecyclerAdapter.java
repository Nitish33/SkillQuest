package devnitish.com.skillquest.Adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.design.chip.Chip;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import devnitish.com.skillquest.Interfaces.CategoryClick;
import devnitish.com.skillquest.Models.CategoryModel;
import devnitish.com.skillquest.R;

public class CategoryRecyclerAdapter extends
        RecyclerView.Adapter<CategoryRecyclerAdapter.ViewHolder> {

    LayoutInflater inflater;
    Context context;
    ArrayList<CategoryModel> allCategory;
    volatile int selectedCategoryId;

    CategoryClick categoryClick;

    public void setUpCategoryClick(CategoryClick categoryClick){
        this.categoryClick = categoryClick;
    }

    public void setCurrentCategory(int cid){
        this.selectedCategoryId = cid;
    }

    public CategoryRecyclerAdapter(Context context,
                                   ArrayList<CategoryModel> allCategory,
                                   int selectedCategoryId){

            inflater = LayoutInflater.from(context);
            this.context = context;
            this.allCategory = allCategory;
            this.selectedCategoryId = selectedCategoryId;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View view = inflater.inflate(R.layout.adapter_category_chips,viewGroup,false);
        return new ViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder,
                                 final int i) {

        final CategoryModel model = allCategory.get(i);

        holder.chip.setText(model.getName());

        if(selectedCategoryId == model.getCid()){
           holder.chip.setSelected(true);
        }
        else {

            holder.chip.setSelected(false);
        }

        holder.chip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Log.e("click","clip clicked");

                if(categoryClick!=null){
                    categoryClick.onCategoryClick(model.getCid());
                }
            }
        });

    }

    @Override
    public int getItemCount() {
        return allCategory.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder{

        Chip chip;

        public ViewHolder(@NonNull View view) {
            super(view);

            chip = view.findViewById(R.id.categoryName);
        }
    }
}
