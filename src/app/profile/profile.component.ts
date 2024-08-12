import { OrderService } from './../services/order.service';
import { Component, EventEmitter, OnInit, Output } from '@angular/core';
import { Order } from '../cart/order.interface';
import { Customer } from '../customer-login/customer.interface';
import { Router } from '@angular/router';
import { Details } from '../popup/order-detail.iterface';

@Component({
  selector: 'app-profile',
  templateUrl: './profile.component.html',
  styleUrls: ['./profile.component.css']
})
export class ProfileComponent implements OnInit {

  orders: Order[] = [];

  isActive: boolean = false;

  popupType: string = '';

  orderData: Order = {
    orderNo: 0,
    orderId: 0,
    orderAmount: 0,
    orderDate: '',
    customerId: 0
  }

  order: Order = {
    orderNo: 0,
    orderId: 0,
    orderAmount: 0,
    orderDate: '',
    customerId: 0
  }

  details: Details[] = []

  session: any = localStorage.getItem("session");
  customerResponce: Customer = JSON.parse(this.session);

  constructor(private orderService: OrderService, private router: Router) { }

  ngOnInit(): void {

    console.log("Route value : "+this.customerResponce);
    if(this.session == null) {
      this.router.navigate(['/home']);
    }

    this.order.customerId = this.customerResponce.customerId;
    this.orderService.getOrders(this.order).subscribe(order => {
      this.orders = order;
      console.log(this.orders);
    })
  }

  showPopup(type: string) {
    this.isActive = true;
    this.popupType = type;
  }
  closePopup() {
    this.isActive = false;
  }

  getOrderDetail(type: string, order: Order) {
    this.isActive = true;
    this.popupType = type;
    this.orderData = order;

    this.orderService.getItemByOrderId(order).subscribe(data => {
      this.details = data;
      console.log("Inside subscribe : ",this.details);
    });
  }

  deleteOrder(order: Order) {

    if (confirm("Are you sure you want to remove?")) {

      this.orderService.deleteorder(order).subscribe();
    }
    setTimeout(() => {
      window.location.reload();
    }, 200);
  }
}
