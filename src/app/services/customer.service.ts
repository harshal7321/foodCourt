import { Injectable } from '@angular/core';
import {HttpClient} from '@angular/common/http';
import { Customer } from '../customer-login/customer.interface';

@Injectable({
  providedIn: 'root'
})
export class CustomerService {

  constructor(private http: HttpClient) { }

  loginCustomer(customer: Customer) {
    return this.http.post<Customer>("http://localhost:8080/customerLogin",customer);
  }

  logoutCustomer(customer: Customer) {
    return this.http.post<Customer>("http://localhost:8080/customerLogout", customer);
  }

  addCustomer(customer: Customer) {
    return this.http.post<Customer>("http://localhost:8080/customerRegistration", customer);
  }

  updateProfile(customer: Customer) {
    return this.http.post<Customer>("http://localhost:8080/updateProfile", customer);
  }

  getPassword(customer: Customer) {
    return this.http.post<Customer>("http://localhost:8080/getPassword", customer);
  }

  updatePassword(customer: Customer) {
    return this.http.post<Customer>("http://localhost:8080/updatePassword", customer);
  }
}
