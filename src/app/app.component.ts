import { Component, OnInit } from '@angular/core';
import { RestaurantDataService } from './services/restaurant-data.service';
import { Customer } from './customer-login/customer.interface';
import { Router } from '@angular/router';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css'],
  providers: [RestaurantDataService]
})
export class AppComponent implements OnInit{

  title = 'FOOD COURTE';

  customerName: string = '';

  activityStatus = '';

  cartStatus = '';

  session: any = localStorage.getItem("session");
  customerResponce: Customer = JSON.parse(this.session);

  constructor() {
  }

  ngOnInit(): void {

    console.log(this.session);

    if(this.session != null) {
      this.customerName = this.customerResponce.customerName;
      this.activityStatus = "logout";
      this.cartStatus = "Cart";
      console.log(this.activityStatus);
    }else {
      this.activityStatus = "login";
      this.cartStatus = ""
      console.log(this.activityStatus);
    }
  }
}
