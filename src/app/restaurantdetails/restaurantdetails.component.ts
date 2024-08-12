import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { RestaurantDataService } from '../services/restaurant-data.service';
import { Customer } from '../customer-login/customer.interface';
import { Restaurant } from '../restuarants/restaurants.interface';

@Component({
  selector: 'app-restaurantdetails',
  templateUrl: './restaurantdetails.component.html',
  styleUrls: ['./restaurantdetails.component.css']
})
export class RestaurantdetailsComponent implements OnInit {

  selectedRestaurant = '';
  selectedRestaurantData: Restaurant = {
    restaurantId: 0,
    restaurantName: '',
    restaurantLocation: '',
    openAt: 0,
    closeAt: 0
  };

  //restaurants: Restaurant[] = [];

  location = '';
  status = '';
  start = 0;
  end = 0;
  open = '';
  close = '';
  currentTime: any;

  session: any = localStorage.getItem("session");
  customerResponce: Customer = JSON.parse(this.session);

  //Injection
  constructor(private route: ActivatedRoute,
              private restaurant: RestaurantDataService,
              private router: Router) { }

  ngOnInit(): void {

    if (this.session == null) {
      this.router.navigate(['/home']);
    }

    this.selectedRestaurant = this.route.snapshot.paramMap.get('name');

    this.restaurant.getRestaurantByName(this.selectedRestaurant).subscribe(restaurant => {
      this.selectedRestaurantData = restaurant;

      this.updateStatus();
      this.getRestaurantdata();
      this.updateTime();
    });
  }

  updateStatus() {

    this.currentTime = new Date().getHours();
    console.log("Current Time : " + this.currentTime);

    if (this.currentTime >= this.selectedRestaurantData.openAt && this.currentTime < this.selectedRestaurantData.closeAt) {
      this.status = "Open";
    }
    else {
      this.status = "Closed";
    }
    console.log(this.status);
  }

  getRestaurantdata() {
    this.location = this.selectedRestaurantData.restaurantLocation;
    this.start = this.selectedRestaurantData.openAt;
    this.end = this.selectedRestaurantData.closeAt;
  }

  updateTime() {
    if (this.start == 12) {
      this.open = this.start + " pm"
    }
    else if (this.start > 12) {
      this.open = this.start - 12 + " pm";
    }
    else {
      this.open = this.start + " am";
    }

    if (this.end == 12) {
      this.open = this.end + " am"
    }
    else if (this.end > 12) {
      this.close = this.end - 12 + " pm";
    }
    else {
      this.close = this.end + " am";
    }

    console.log(this.open + " " + this.close);
    console.log("Current Time : " + this.currentTime);
    console.log(this.start + " " + this.end);
  }

  getMenu(selectedRestaurant: String) {
    if (this.status == "Open") {
      this.router.navigate(['/restaurant-menu/' + selectedRestaurant]);
    }
    else {
      alert("Sorry! Restaurant is currently closed");
    }
  }
}
