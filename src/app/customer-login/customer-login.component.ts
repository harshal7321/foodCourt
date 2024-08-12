import { Component, OnInit } from '@angular/core';
import { Customer } from './customer.interface';
import { Router } from '@angular/router';
import { CustomerService } from '../services/customer.service';

@Component({
  selector: 'app-customer-login',
  templateUrl: './customer-login.component.html',
  styleUrls: ['./customer-login.component.css']
})
export class CustomerLoginComponent implements OnInit {

  customer: Customer = {
    customerId: 0,
    customerName: '',
    customerEmail: '',
    customerContact: '',
    customerLocation: '',
    customerPassword: '',
    status: ''
  }

  customerResponce: Customer = {
    customerId: 0,
    customerName: '',
    customerEmail: '',
    customerContact: '',
    customerLocation: '',
    customerPassword: '',
    status: ''
  }

  constructor(private router: Router, private customerService: CustomerService) {}

  ngOnInit(): void {
    this.goToHome();
  }

  loginCustomer() {
    if (this.customer.customerEmail === '' || this.customer.customerPassword === '') {
      alert("Please enter Email and Password !");
    }
    else {
      this.customerService.loginCustomer(this.customer).subscribe(customer => {
        this.customerResponce = customer;
        console.log(this.customerResponce);

        if (this.customerResponce.status === 'success') {
          localStorage.setItem('session', JSON.stringify(this.customerResponce));
          this.goToHome();
        }
        if (this.customerResponce.status === 'failed') {
          alert("Invalid Email or Password!");
        }
        if (this.customerResponce.status === 'alreadyLogin') {
          this.goToHome();
        }
      });
      this.customer = {
        customerId: 0,
        customerName: '',
        customerEmail: '',
        customerContact: '',
        customerLocation: '',
        customerPassword: '',
        status: ''
      }
    }
  }

  goToHome() {
    const session = localStorage.getItem('session');
    if (session != null) {

      this.router.navigate(['/restaurants']).then(() => {
        window.location.reload();
      });
    }
  }
}
