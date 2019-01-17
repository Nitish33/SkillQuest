package devnitish.com.skillquest.Adapters.AdapterHelperClasses;

import devnitish.com.skillquest.Models.TrainingModel;

public class TrainingImplementation implements ParentInterface {

    TrainingModel model;

    public TrainingImplementation(TrainingModel model){

        this.model = model;
    }

    @Override
    public int getType() {
        return 3;
    }

    public TrainingModel getModel() {
        return model;
    }

    public void setModel(TrainingModel model) {
        this.model = model;
    }
}
