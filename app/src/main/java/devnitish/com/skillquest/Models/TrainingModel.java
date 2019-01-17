package devnitish.com.skillquest.Models;

public class TrainingModel {

    int tid;
    int cid;
    int trainerId;
    String name;
    String description;
    String startDate;
    String availability;
    String duration;
    float cost;
    String location;
    String imageUrl;

    public TrainingModel() {

    }

    public TrainingModel(int tid, int cid, int trainerId,
                         String name, String description,
                         String startDate, String availability,
                         String duration, float cost,
                         String location, String imageUrl) {
        this.tid = tid;
        this.cid = cid;
        this.trainerId = trainerId;
        this.name = name;
        this.description = description;
        this.startDate = startDate;
        this.availability = availability;
        this.duration = duration;
        this.cost = cost;
        this.location = location;
        this.imageUrl = imageUrl;
    }

    public int getTid() {
        return tid;
    }

    public void setTid(int tid) {
        this.tid = tid;
    }

    public int getCid() {
        return cid;
    }

    public void setCid(int cid) {
        this.cid = cid;
    }

    public int getTrainerId() {
        return trainerId;
    }

    public void setTrainerId(int trainerId) {
        this.trainerId = trainerId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getAvailability() {
        return availability;
    }

    public void setAvailability(String availability) {
        this.availability = availability;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public float getCost() {
        return cost;
    }

    public void setCost(float cost) {
        this.cost = cost;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    @Override
    public String toString() {
        return "TrainingModel{" +
                "tid=" + tid +
                ", cid=" + cid +
                ", trainerId=" + trainerId +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", startDate='" + startDate + '\'' +
                ", availability='" + availability + '\'' +
                ", duration='" + duration + '\'' +
                ", cost=" + cost +
                ", location='" + location + '\'' +
                ", imageUrl='" + imageUrl + '\'' +
                '}';
    }
}
