import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Cart } from '../cart/cart.iterface';
import { Customer } from '../customer-login/customer.interface';

@Injectable({
  providedIn: 'root'
})

export class CartService {

  constructor(private http: HttpClient) {}

  addToCart(cart: Cart) {
    return this.http.post<Cart>("http://localhost:8080/addToCart", cart);
  }

  getCarts(customer: Customer) {
    return this.http.post<Cart[]>("http://localhost:8080/carts", customer);
  }

  deleteFromCart(cart: Cart) {
    return this.http.post<Cart>("http://localhost:8080/deleteFromCart", cart);
  }

  updateQuantity(cart: Cart) {
    return this.http.post<Cart>("http://localhost:8080/quantity", cart);
  }

  updateOrderId(cart: Cart) {
    return this.http.post<Cart>("http://localhost:8080/updateOrderId", cart);
  }
}
