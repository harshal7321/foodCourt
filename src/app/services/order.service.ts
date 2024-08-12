import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Order } from '../cart/order.interface';
import { Details } from '../popup/order-detail.iterface';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class OrderService {

  constructor(private http: HttpClient) { }

  placeOrder(order:Order) {
    return this.http.post<Order>("http://localhost:8080/addOrder", order);
  }

  getOrders(order:Order) {
    return this.http.post<Order[]>("http://localhost:8080/orders", order);
  }

  deleteorder(order:Order)  {
    return this.http.post<Order>("http://localhost:8080/deleteOrder", order);
  }

  getItemByOrderId(order: Order) : Observable<any> {
    return this.http.post<Details[]>("http://localhost:8080/orderedItem", order);
  }
}
