package iti.android.foodplanner.data.models.selections.Ingredient;

import com.google.gson.annotations.SerializedName;

public class Ingredient{

	@SerializedName("strDescription")
	private String strDescription;

	private String thumnail;
	@SerializedName("strIngredient")
	private String strIngredient;

	@SerializedName("strType")
	private Object strType;

	@SerializedName("idIngredient")
	private String idIngredient;

	public void setStrDescription(String strDescription){
		this.strDescription = strDescription;
	}

	public String getStrDescription(){
		return strDescription;
	}

	public void setStrIngredient(String strIngredient){
		this.strIngredient = strIngredient;
	}

	public String getStrIngredient(){
		return strIngredient;
	}

	public void setStrType(Object strType){
		this.strType = strType;
	}

	public Object getStrType(){
		return strType;
	}

	public void setIdIngredient(String idIngredient){
		this.idIngredient = idIngredient;
	}

	public String getIdIngredient(){
		return idIngredient;
	}

	public String getThumnail() {
		return thumnail;
	}

	public void setThumnail() {
		this.thumnail = "https://www.themealdb.com/images/ingredients/"+strIngredient+".png";
	}

}