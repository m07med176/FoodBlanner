package iti.android.foodplanner.data.models;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.SerializedName;

@Entity(tableName = "categories_feed")
public class CategoriesItem{

	@PrimaryKey(autoGenerate = true)
	private int id;

	@ColumnInfo
	private String strCategory;

	@ColumnInfo
	private String strCategoryDescription;

	@ColumnInfo
	private String idCategory;

	@ColumnInfo
	private String strCategoryThumb;

	public CategoriesItem(int id,String strCategory, String strCategoryDescription, String idCategory, String strCategoryThumb) {
		this.id = id;
		this.strCategory = strCategory;
		this.strCategoryDescription = strCategoryDescription;
		this.idCategory = idCategory;
		this.strCategoryThumb = strCategoryThumb;
	}

	public void setStrCategory(String strCategory){
		this.strCategory = strCategory;
	}

	public String getStrCategory(){
		return strCategory;
	}

	public void setStrCategoryDescription(String strCategoryDescription){
		this.strCategoryDescription = strCategoryDescription;
	}

	public String getStrCategoryDescription(){
		return strCategoryDescription;
	}

	public void setIdCategory(String idCategory){
		this.idCategory = idCategory;
	}

	public String getIdCategory(){
		return idCategory;
	}

	public void setStrCategoryThumb(String strCategoryThumb){
		this.strCategoryThumb = strCategoryThumb;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getStrCategoryThumb(){
		return strCategoryThumb;
	}

	@Override
 	public String toString(){
		return 
			"CategoriesItem{" + 
			"strCategory = '" + strCategory + '\'' + 
			",strCategoryDescription = '" + strCategoryDescription + '\'' + 
			",idCategory = '" + idCategory + '\'' + 
			",strCategoryThumb = '" + strCategoryThumb + '\'' + 
			"}";
		}
}