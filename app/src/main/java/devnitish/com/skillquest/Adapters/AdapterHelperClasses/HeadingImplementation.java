package devnitish.com.skillquest.Adapters.AdapterHelperClasses;

import devnitish.com.skillquest.Constants;

public class HeadingImplementation implements ParentInterface {

    String heading;

    int type;   // because we have 2 for training section ,, recently added, popular
    // 2 => Top training
    // 3 => Recently added.

    // donot want to chage it, there other constructor..
    public HeadingImplementation(String heading){
        this.heading = heading;
        type =2;
    }

    // added later
    public HeadingImplementation(String heading,int type)
    {
        this.heading = heading;
        this.type = type;
    }

    @Override
    public int getType() {
        return Constants.TYPE_HEADING;
    }

    public String getHeading() {
        return heading;
    }

    public void setHeading(String heading) {
        this.heading = heading;
    }


}
