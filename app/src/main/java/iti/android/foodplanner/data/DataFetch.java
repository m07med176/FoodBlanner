package iti.android.foodplanner.data;

import io.reactivex.rxjava3.disposables.Disposable;
import iti.android.foodplanner.data.models.meal.MealsList;

public interface DataFetch<T> {
    public void onDataSuccessResponse(T data);
    public void onDataFailedResponse(String message);
    public void onDataLoading();
}
