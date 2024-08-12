
import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Restaurant } from '../restuarants/restaurants.interface';

@Injectable({
  providedIn: 'root'
})
export class RestaurantDataService {

  restaurant: Restaurant = {
    restaurantId: 0,
    restaurantName: '',
    restaurantLocation: '',
    openAt: 0,
    closeAt: 0
  };

  constructor(private http: HttpClient) {

  }

  getRestaurants() {
    return this.http.get<Restaurant[]>("http://localhost:8080/restaurants");
  }

  getRestaurantByName(name:string) {
    this.restaurant.restaurantName = name;
    return this.http.post<Restaurant>("http://localhost:8080/restaurant",this.restaurant);
  }

  // data = [
  //   {
  //     name: 'Tasty Burgers',
  //     location: 'New York',
  //     open:9,
  //     close:20,
  //     foodItems: [
  //       {item: 'Classic burger', price:50},
  //       {item: 'Cheese burger', price:60},
  //       {item: 'Veggie burger', price:70},
  //       {item: 'Chiken burger', price:80},
  //       {item: 'Double bacon burger', price:100}
  //     ]
  //   },
  //   {
  //     name: 'Pizza Delight',
  //     location: 'Mumbai',
  //     open:14,
  //     close:22,
  //     foodItems: [
  //       {item: 'Pepperoni Pizza', price:100},
  //       {item: 'Vegeterian Pizza', price:90},
  //       {item: 'Hawalian Pizza', price:110},
  //       {item: 'Margherita Pizza', price:80},
  //       {item: 'BBQ chiken Pizza', price:120}
  //     ]
  //   },
  //   {
  //     name: 'Coffie Corner',
  //     location: 'London',
  //     open:7,
  //     close:18,
  //     foodItems: [
  //       {item: 'Espresso', price:30},
  //       {item: 'Cappuccino', price:40},
  //       {item: 'Latte', price:50},
  //       {item: 'Mocha', price:45},
  //       {item: 'Americano', price:35}
  //     ]
  //   },
  //   {
  //     name: 'bakery Bliss',
  //     location: 'Pune',
  //     open:8,
  //     close:19,
  //     foodItems: [
  //       {item: 'Croiss', price:25},
  //       {item: 'baguette', price:35},
  //       {item: 'Danish Pastry', price:50},
  //       {item: 'Sourdough Bread', price:30},
  //       {item: 'Cinnamon Roll', price:60}
  //     ]
  //   },
  // ]
}
