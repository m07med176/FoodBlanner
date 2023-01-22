package iti.android.foodplanner.data.models.selections.area;

import java.util.HashMap;

public class Area {
    private String strArea;
    private String thumbnail;

    public Area() {
    }

    public Area(String strArea) {
        this.strArea = strArea;
        this.thumbnail = getThumbnail();
    }

    public String getThumbnail() {
        setThumbnail();
        return thumbnail;
    }

    public void setThumbnail() {
        HashMap<String,String> countryCode = new HashMap<>();
        countryCode.put("Vietnamese","vi");
        countryCode.put("Turkish","vi");
        countryCode.put("Tunisian","vi");
        countryCode.put("Thai","th");
        countryCode.put("Spanish","sp");
        countryCode.put("Russian","ru");
        countryCode.put("American","us");
        countryCode.put("British","uk");
        countryCode.put("Canadian","ca");
        countryCode.put("Chinese","ch");
        countryCode.put("Croatian","cr");
        countryCode.put("Dutch","du");
        countryCode.put("Egyptian","eg");
        countryCode.put("French","fr");
        countryCode.put("Greek","gr");
        countryCode.put("Indian","in");
        countryCode.put("Irish","ir");
        countryCode.put("Italian","it");
        countryCode.put("Jamaican","code");
        countryCode.put("Japanese","ja");
        countryCode.put("Kenyan","ke");
        countryCode.put("Malaysian","ma");
        countryCode.put("Mexican","me");
        countryCode.put("Moroccan","ma");
        countryCode.put("Polish","po");
        countryCode.put("Portuguese","pr");
        this.thumbnail = "https://www.themealdb.com/images/icons/flags/big/64/"+countryCode.get(strArea)+".png";
    }

    public String getStrArea() {
        return strArea;
    }

    public void setStrArea(String strArea) {
        this.strArea = strArea;
    }

    @Override
    public String toString() {
        return "Area{" +
                "strArea='" + strArea + '\'' +
                '}';
    }
}
