package iti.android.foodplanner.data.models.meal;

import androidx.room.Entity;
import androidx.room.Ignore;

import com.google.gson.annotations.SerializedName;

@Entity(tableName = "meals")
public class MealsItem{

	@SerializedName("strImageSource")
	private String strImageSource;

	@SerializedName("strIngredient10")
	private String strIngredient10;

	@SerializedName("strIngredient12")
	private String strIngredient12;

	@SerializedName("strIngredient11")
	private String strIngredient11;

	@SerializedName("strIngredient14")
	private String strIngredient14;

	@SerializedName("strCategory")
	private String strCategory;

	@SerializedName("strIngredient13")
	private String strIngredient13;

	@SerializedName("strIngredient16")
	private Object strIngredient16;

	@SerializedName("strIngredient15")
	private String strIngredient15;

	@SerializedName("strIngredient18")
	private Object strIngredient18;

	@SerializedName("strIngredient17")
	private Object strIngredient17;

	@SerializedName("strArea")
	private String strArea;

	@SerializedName("strCreativeCommonsConfirmed")
	private Object strCreativeCommonsConfirmed;

	@SerializedName("strIngredient19")
	private Object strIngredient19;

	@SerializedName("strTags")
	private String strTags;

	@SerializedName("idMeal")
	private String idMeal;

	@SerializedName("strInstructions")
	private String strInstructions;

	@SerializedName("strIngredient1")
	private String strIngredient1;

	@SerializedName("strIngredient3")
	private String strIngredient3;

	@SerializedName("strIngredient2")
	private String strIngredient2;

	@SerializedName("strIngredient20")
	private Object strIngredient20;

	@SerializedName("strIngredient5")
	private String strIngredient5;

	@SerializedName("strIngredient4")
	private String strIngredient4;

	@SerializedName("strIngredient7")
	private String strIngredient7;

	@SerializedName("strIngredient6")
	private String strIngredient6;

	@SerializedName("strIngredient9")
	private String strIngredient9;

	@SerializedName("strIngredient8")
	private String strIngredient8;


	@SerializedName("strMealThumb")
	private String strMealThumb;

	@SerializedName("strYoutube")
	private String strYoutube;

	@SerializedName("strMeal")
	private String strMeal;


	@SerializedName("dateModified")
	private Object dateModified;

	@SerializedName("strDrinkAlternate")
	private Object strDrinkAlternate;

	@SerializedName("strSource")
	private Object strSource;

	public void setStrImageSource(String strImageSource){
		this.strImageSource = strImageSource;
	}

	public String getStrImageSource(){
		return strImageSource;
	}

	public void setStrIngredient10(String strIngredient10){
		this.strIngredient10 = strIngredient10;
	}

	public String getStrIngredient10(){
		return strIngredient10;
	}

	public void setStrIngredient12(String strIngredient12){
		this.strIngredient12 = strIngredient12;
	}

	public String getStrIngredient12(){
		return strIngredient12;
	}

	public void setStrIngredient11(String strIngredient11){
		this.strIngredient11 = strIngredient11;
	}

	public String getStrIngredient11(){
		return strIngredient11;
	}

	public void setStrIngredient14(String strIngredient14){
		this.strIngredient14 = strIngredient14;
	}

	public String getStrIngredient14(){
		return strIngredient14;
	}

	public void setStrCategory(String strCategory){
		this.strCategory = strCategory;
	}

	public String getStrCategory(){
		return strCategory;
	}

	public void setStrIngredient13(String strIngredient13){
		this.strIngredient13 = strIngredient13;
	}

	public String getStrIngredient13(){
		return strIngredient13;
	}

	public void setStrIngredient16(Object strIngredient16){
		this.strIngredient16 = strIngredient16;
	}

	public Object getStrIngredient16(){
		return strIngredient16;
	}

	public void setStrIngredient15(String strIngredient15){
		this.strIngredient15 = strIngredient15;
	}

	public String getStrIngredient15(){
		return strIngredient15;
	}

	public void setStrIngredient18(Object strIngredient18){
		this.strIngredient18 = strIngredient18;
	}

	public Object getStrIngredient18(){
		return strIngredient18;
	}

	public void setStrIngredient17(Object strIngredient17){
		this.strIngredient17 = strIngredient17;
	}

	public Object getStrIngredient17(){
		return strIngredient17;
	}

	public void setStrArea(String strArea){
		this.strArea = strArea;
	}

	public String getStrArea(){
		return strArea;
	}

	public void setStrCreativeCommonsConfirmed(Object strCreativeCommonsConfirmed){
		this.strCreativeCommonsConfirmed = strCreativeCommonsConfirmed;
	}

	public Object getStrCreativeCommonsConfirmed(){
		return strCreativeCommonsConfirmed;
	}

	public void setStrIngredient19(Object strIngredient19){
		this.strIngredient19 = strIngredient19;
	}

	public Object getStrIngredient19(){
		return strIngredient19;
	}

	public void setStrTags(String strTags){
		this.strTags = strTags;
	}

	public String getStrTags(){
		return strTags;
	}

	public void setIdMeal(String idMeal){
		this.idMeal = idMeal;
	}

	public String getIdMeal(){
		return idMeal;
	}

	public void setStrInstructions(String strInstructions){
		this.strInstructions = strInstructions;
	}

	public String getStrInstructions(){
		return strInstructions;
	}

	public void setStrIngredient1(String strIngredient1){
		this.strIngredient1 = strIngredient1;
	}

	public String getStrIngredient1(){
		return strIngredient1;
	}

	public void setStrIngredient3(String strIngredient3){
		this.strIngredient3 = strIngredient3;
	}

	public String getStrIngredient3(){
		return strIngredient3;
	}

	public void setStrIngredient2(String strIngredient2){
		this.strIngredient2 = strIngredient2;
	}

	public String getStrIngredient2(){
		return strIngredient2;
	}

	public void setStrIngredient20(Object strIngredient20){
		this.strIngredient20 = strIngredient20;
	}

	public Object getStrIngredient20(){
		return strIngredient20;
	}

	public void setStrIngredient5(String strIngredient5){
		this.strIngredient5 = strIngredient5;
	}

	public String getStrIngredient5(){
		return strIngredient5;
	}

	public void setStrIngredient4(String strIngredient4){
		this.strIngredient4 = strIngredient4;
	}

	public String getStrIngredient4(){
		return strIngredient4;
	}

	public void setStrIngredient7(String strIngredient7){
		this.strIngredient7 = strIngredient7;
	}

	public String getStrIngredient7(){
		return strIngredient7;
	}

	public void setStrIngredient6(String strIngredient6){
		this.strIngredient6 = strIngredient6;
	}

	public String getStrIngredient6(){
		return strIngredient6;
	}

	public void setStrIngredient9(String strIngredient9){
		this.strIngredient9 = strIngredient9;
	}

	public String getStrIngredient9(){
		return strIngredient9;
	}

	public void setStrIngredient8(String strIngredient8){
		this.strIngredient8 = strIngredient8;
	}

	public String getStrIngredient8(){
		return strIngredient8;
	}

	public void setStrMealThumb(String strMealThumb){
		this.strMealThumb = strMealThumb;
	}

	public String getStrMealThumb(){
		return strMealThumb;
	}

	public void setStrYoutube(String strYoutube){
		this.strYoutube = strYoutube;
	}

	public String getStrYoutube(){
		return strYoutube;
	}

	public void setStrMeal(String strMeal){
		this.strMeal = strMeal;
	}

	public String getStrMeal(){
		return strMeal;
	}

	public void setDateModified(Object dateModified){
		this.dateModified = dateModified;
	}

	public Object getDateModified(){
		return dateModified;
	}

	public void setStrDrinkAlternate(Object strDrinkAlternate){
		this.strDrinkAlternate = strDrinkAlternate;
	}

	public Object getStrDrinkAlternate(){
		return strDrinkAlternate;
	}

	public void setStrSource(Object strSource){
		this.strSource = strSource;
	}

	public Object getStrSource(){
		return strSource;
	}


	@Override
 	public String toString(){
		return 
			"MealsItem{" + 
			"strImageSource = '" + strImageSource + '\'' + 
			",strIngredient10 = '" + strIngredient10 + '\'' + 
			",strIngredient12 = '" + strIngredient12 + '\'' + 
			",strIngredient11 = '" + strIngredient11 + '\'' + 
			",strIngredient14 = '" + strIngredient14 + '\'' + 
			",strCategory = '" + strCategory + '\'' + 
			",strIngredient13 = '" + strIngredient13 + '\'' + 
			",strIngredient16 = '" + strIngredient16 + '\'' + 
			",strIngredient15 = '" + strIngredient15 + '\'' + 
			",strIngredient18 = '" + strIngredient18 + '\'' + 
			",strIngredient17 = '" + strIngredient17 + '\'' + 
			",strArea = '" + strArea + '\'' + 
			",strCreativeCommonsConfirmed = '" + strCreativeCommonsConfirmed + '\'' + 
			",strIngredient19 = '" + strIngredient19 + '\'' + 
			",strTags = '" + strTags + '\'' + 
			",idMeal = '" + idMeal + '\'' + 
			",strInstructions = '" + strInstructions + '\'' + 
			",strIngredient1 = '" + strIngredient1 + '\'' + 
			",strIngredient3 = '" + strIngredient3 + '\'' + 
			",strIngredient2 = '" + strIngredient2 + '\'' + 
			",strIngredient20 = '" + strIngredient20 + '\'' + 
			",strIngredient5 = '" + strIngredient5 + '\'' + 
			",strIngredient4 = '" + strIngredient4 + '\'' + 
			",strIngredient7 = '" + strIngredient7 + '\'' + 
			",strIngredient6 = '" + strIngredient6 + '\'' + 
			",strIngredient9 = '" + strIngredient9 + '\'' + 
			",strIngredient8 = '" + strIngredient8 + '\'' + 
			",strMealThumb = '" + strMealThumb + '\'' + 
			",strYoutube = '" + strYoutube + '\'' +
			",strMeal = '" + strMeal + '\'' + 

			",dateModified = '" + dateModified + '\'' + 
			",strDrinkAlternate = '" + strDrinkAlternate + '\'' + 
			",strSource = '" + strSource + '\'' + 

			"}";
		}
}