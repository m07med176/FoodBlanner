package iti.android.foodplanner.data.room;

import androidx.room.Dao;
import androidx.room.Query;

import java.util.List;

import io.reactivex.rxjava3.core.Observable;
import iti.android.foodplanner.data.models.CategoriesItem;

@Dao
public interface FeedCashDAO {

    /**
     * Select All Categories That Feed from Home Page
     * @return List<CategoriesItem>
     */
    @Query("SELECT * FROM categories_feed")
    public Observable<List<CategoriesItem>> getListOfCategories();


    // TODO Moamen [DATA]: insert all [or] override all of cash feed
    // TODO Moamen [DATA]: delete all of cash feed
    // TODO Moamen [DATA]: retrieve all of cash feed
}
