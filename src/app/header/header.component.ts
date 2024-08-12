import { CustomerService } from './../services/customer.service';
import { Component, Input, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Customer } from '../customer-login/customer.interface';
import * as M from 'materialize-css';
import { flatten } from '@angular/compiler';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css']
})
export class HeaderComponent implements OnInit {

  selectedRestaurant:string = '';

  cart: string = 'Cart';

  session: any = localStorage.getItem("session");
  customerResponce: Customer = JSON.parse(this.session);

  @Input() customerName = '';
  @Input() activity = '';
  @Input() cartState = '';

  constructor(private router: Router, private customerService: CustomerService) {}

  ngOnInit(): void {
    console.log(this.activity);

    if(this.activity === 'logout') {
      document.addEventListener('DOMContentLoaded', () => {
        var elems = document.querySelectorAll('.dropdown-trigger');
        M.Dropdown.init(elems, {
          hover: true,
          coverTrigger: false
        });
      });
    }
  }

  performActivity() {

    if(this.activity === "logout") {

      if(confirm("Are you sure you want to logout ?")){
        this.goToHome();
        this.customerService.logoutCustomer(this.customerResponce).subscribe();
      }
    }
    if(this.activity === "login") {
      this.router.navigate(['/login']);
      this.cart = "";
    }
  }

  goToHome() {
    localStorage.removeItem("session");
    this.router.navigate(['/home']).then(() => {
      this.cart = "Cart";
      window.location.reload();
    });
  }

  goToCart(){
    this.router.navigate(['/cart']);
  }
}
