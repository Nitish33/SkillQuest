package devnitish.com.skillquest.Models;

public class CategoryModel {

    int cid;
    String name;
    String description;
    String imagePath;

    public CategoryModel() {
    }

    public CategoryModel(int cid, String name, String description, String imagePath) {
        this.cid = cid;
        this.name = name;
        this.description = description;
        this.imagePath = imagePath;
    }

    public int getCid() {
        return cid;
    }

    public void setCid(int cid) {
        this.cid = cid;
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

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    @Override
    public String toString() {
        return "CategoryModel{" +
                "cid=" + cid +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", imagePath='" + imagePath + '\'' +
                '}';
    }
}
