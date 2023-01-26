package iti.android.foodplanner.ui.features.onBoarding;

public class SliderItem {
    int image;
    String title;
    String descreption;


    public SliderItem(int image, String title, String descreption) {
        this.image = image;
        this.title = title;
        this.descreption = descreption;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescreption() {
        return descreption;
    }

    public void setDescreption(String descreption) {
        this.descreption = descreption;
    }
}
