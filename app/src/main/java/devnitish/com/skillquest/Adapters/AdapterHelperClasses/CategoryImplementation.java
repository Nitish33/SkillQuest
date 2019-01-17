package devnitish.com.skillquest.Adapters.AdapterHelperClasses;

import devnitish.com.skillquest.Models.CategoryModel;

public class CategoryImplementation implements ParentInterface {

    CategoryModel model;

    public CategoryImplementation(CategoryModel categoryModel){

        this.model = categoryModel;
    }

    @Override
    public int getType() {
        return 2;
    }

    public CategoryModel getModel() {
        return model;
    }

    public void setModel(CategoryModel model) {
        this.model = model;
    }
}
