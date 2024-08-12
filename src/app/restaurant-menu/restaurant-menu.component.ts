import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { RestaurantDataService } from '../services/restaurant-data.service';
import { Customer } from '../customer-login/customer.interface';
import { Restaurant } from '../restuarants/restaurants.interface';
import { FoodItemService } from '../services/food-item.service';
import { FoodItem } from './food-item.interface';
import { Cart } from '../cart/cart.iterface';
import { CartService } from '../services/cart.service';

@Component({
  selector: 'app-restaurant-menu',
  templateUrl: './restaurant-menu.component.html',
  styleUrls: ['./restaurant-menu.component.css']
})
export class RestaurantMenuComponent implements OnInit {

  selectedRestaurant = '';

  isActive = true;

  selectedRestaurantData: Restaurant = {
    restaurantId: 0,
    restaurantName: '',
    restaurantLocation: '',
    openAt: 0,
    closeAt: 0
  };

  foodItems: FoodItem[] = [];
  counter = 0;
  currentTime: any;
  status = '';

  carts: Cart[] = []

  session: any = localStorage.getItem("session");
  customerResponce: Customer = JSON.parse(this.session);

  constructor(private route: ActivatedRoute,
    private restaurant: RestaurantDataService,
    private foodItemService: FoodItemService,
    private cartService: CartService,
    private router: Router) { }
  ngOnInit(): void {

    console.log("Route value : " + this.customerResponce);
    if (this.session == null) {
      this.router.navigate(['/home']);
    }

    this.selectedRestaurant = this.route.snapshot.paramMap.get('name');

    this.restaurant.getRestaurantByName(this.selectedRestaurant).subscribe(restaurant => {
      this.selectedRestaurantData = restaurant;
      console.log(this.selectedRestaurantData);
    });

    this.foodItemService.getFoodItems(this.selectedRestaurant).subscribe(foodItem => {
      this.foodItems = foodItem;
      console.log("Food items : " + this.foodItems);
      this.updateStatus();
    })
  }

  updateStatus() {

    this.currentTime = new Date().getHours();

    if (this.currentTime >= this.selectedRestaurantData.openAt && this.currentTime <= this.selectedRestaurantData.closeAt) {
      this.status = "Open";
    }
    else {
      this.status = "Closed";
    }
    console.log(this.status);
  }

  updateCheckBox(food: FoodItem, checked: boolean, id: number) {

    console.log("Checkbox id : " + id);

    const cart: Cart = {
      cartId: 0,
      customerId: this.customerResponce.customerId,
      itemId: food.itemId,
      quantity: 1,
      orderId: 0
    }

    if (checked) {
      this.counter += food.itemPrice;
      console.log(cart);
      this.carts.push(cart);
      console.log("Total : ", this.carts);

    } else {

      this.counter -= food.itemPrice;

      this.carts = this.carts.filter(i => i.itemId !== id);
      console.log("Total : ", this.carts);
    }
  }

  addToCart() {

    console.log(this.carts);

    for (let cart of this.carts) {
      this.cartService.addToCart(cart).subscribe(cart => {
        console.log("Cart added : " + cart.cartId + ', ' + cart.customerId + ', ' + cart.itemId);
      });
    }

    alert('Your orders of Rs. ' + this.counter + ' has been added | go to cart');
    this.counter = 0;
    this.router.navigate(['/cart/' + this.selectedRestaurant]);
  }

}
