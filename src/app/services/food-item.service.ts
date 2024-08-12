import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Restaurant } from '../restuarants/restaurants.interface';
import { FoodItem } from '../restaurant-menu/food-item.interface';

@Injectable({
  providedIn: 'root'
})
export class FoodItemService {

  restaurant: Restaurant = {
    restaurantId: 0,
    restaurantName: '',
    restaurantLocation: '',
    openAt: 0,
    closeAt: 0
  };

  constructor(private http: HttpClient) {}

  getFoodItems(selectedRestaurant: string) {

    this.restaurant.restaurantName = selectedRestaurant;
    return this.http.post<FoodItem[]>("http://localhost:8080/foodItems",this.restaurant);
  }

  getItemById(food: FoodItem) {
    return this.http.post<FoodItem>("http://localhost:8080/foodItem", food);
  }

}
