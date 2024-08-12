import { Component, OnInit } from '@angular/core';
import { RestaurantDataService } from '../services/restaurant-data.service';
import { Router } from '@angular/router';
import { Customer } from '../customer-login/customer.interface';
import { Restaurant } from './restaurants.interface';

@Component({
  selector: 'app-restuarants',
  templateUrl: './restuarants.component.html',
  styleUrls: ['./restuarants.component.css']
})
export class RestuarantsComponent implements OnInit {

  restaurants: Restaurant[] = []
  filteredRestaurants: Restaurant[] = [];
  selectedRestaurants: String = '';
  error = '';

  session: any = localStorage.getItem("session");
  customerResponce: Customer = JSON.parse(this.session);

  constructor(private restaurantService: RestaurantDataService, private router: Router) {}
  
  ngOnInit(): void {

    console.log(this.session);
    console.log("Route value : " + this.customerResponce);
    if (this.session == null) {
      this.router.navigate(['/home']);
    }

    this.restaurantService.getRestaurants().subscribe(restaurant => {
      this.restaurants = restaurant;
      this.filteredRestaurants = this.restaurants;
    });
  }

  onInput() {
    this.error = '';
    if(this.selectedRestaurants === '') {
      this.filteredRestaurants = this.restaurants;
    }
    else {
      this.filteredRestaurants = this.restaurants.filter(restaurant => restaurant.restaurantName.toLowerCase().includes(this.selectedRestaurants.toLowerCase()));
    }
  }

  onSelect(restaurant: String) {
    this.selectedRestaurants = restaurant;
    this.filteredRestaurants = null;
  }

  onSearchClick() {
    if (this.selectedRestaurants == '') {
      alert('Please select a restaurant');
      return;
    }

    for (let restaurant of this.restaurants) {
      if (restaurant.restaurantName == this.selectedRestaurants) {
        this.router.navigate(['/restaurant-details/' + this.selectedRestaurants]);
        return;
      }
      this.error = "Restuarant could not be found !";
    }
  }
}
