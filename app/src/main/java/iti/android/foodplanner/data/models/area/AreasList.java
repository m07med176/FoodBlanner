package iti.android.foodplanner.data.models.area;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class AreasList {

	@SerializedName("meals")
	private List<Area> areas;

	public void setAreas(List<Area> areas){
		this.areas = areas;
	}

	public List<Area> getAreas(){
		return areas;
	}

	@Override
 	public String toString(){
		return 
			"Areas{" +
			"Area = '" + areas + '\'' +
			"}";
		}
}