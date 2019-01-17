package devnitish.com.skillquest.Models;

public class TrainerModel {

    int trainerId;
    String name;
    String detail;
    String qualification;
    String expertise;
    String experiance;

    public TrainerModel() {
    }

    public TrainerModel(int trainerId, String name,
                        String detail, String qualification,
                        String expertise, String experiance) {
        this.trainerId = trainerId;
        this.name = name;
        this.detail = detail;
        this.qualification = qualification;
        this.expertise = expertise;
        this.experiance = experiance;
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

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public String getQualification() {
        return qualification;
    }

    public void setQualification(String qualification) {
        this.qualification = qualification;
    }

    public String getExpertise() {
        return expertise;
    }

    public void setExpertise(String expertise) {
        this.expertise = expertise;
    }

    public String getExperiance() {
        return experiance;
    }

    public void setExperiance(String experiance) {
        this.experiance = experiance;
    }

    @Override
    public String toString() {
        return "TrainerModel{" +
                "trainerId=" + trainerId +
                ", name='" + name + '\'' +
                ", detail='" + detail + '\'' +
                ", qualification='" + qualification + '\'' +
                ", expertise='" + expertise + '\'' +
                ", experiance='" + experiance + '\'' +
                '}';
    }
}
