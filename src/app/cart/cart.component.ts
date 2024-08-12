import { CartService } from './../services/cart.service';
import { Component, OnInit } from '@angular/core';
import { Customer } from '../customer-login/customer.interface';
import { RestaurantDataService } from '../services/restaurant-data.service';
import { FoodItemService } from '../services/food-item.service';
import { ActivatedRoute, Router } from '@angular/router';
import { Cart } from './cart.iterface';
import { FoodItem, FoodResponce } from '../restaurant-menu/food-item.interface';
import { Order } from './order.interface';
import { OrderService } from '../services/order.service';

@Component({
  selector: 'app-cart',
  templateUrl: './cart.component.html',
  styleUrls: ['./cart.component.css']
})
export class CartComponent implements OnInit {

  carts: Cart[] = [];

  selectedRestaurant: string = '';

  addMore: boolean = false;

  totalCarts: number = 0;

  food: FoodItem = {
    itemId: 0,
    itemrName: '',
    itemPrice: 0
  }

  foods: FoodResponce[] = [];

  total: number = 0;

  session: any = localStorage.getItem("session");
  customerResponce: Customer = JSON.parse(this.session);

  loading: boolean = false;

  constructor(private restaurant: RestaurantDataService,
    private foodItemService: FoodItemService,
    private cartService: CartService,
    private orderService: OrderService,
    private router: Router,
    private activeRoute: ActivatedRoute) {

  }

  ngOnInit(): void {

    this.selectedRestaurant = this.activeRoute.snapshot.paramMap.get('name');
    console.log("selected restaurant" + this.selectedRestaurant);

    console.log("Route value : " + this.customerResponce);
    if (this.session == null) {
      this.router.navigate(['/home']);
    }

    this.cartService.getCarts(this.customerResponce).subscribe(cart => {
      this.carts = cart;
      console.log(this.carts);

      for (let cart of this.carts) {

        this.totalCarts += 1;

        this.food.itemId = cart.itemId;
        this.foodItemService.getItemById(this.food).subscribe(foodItem => {

          this.total += foodItem.itemPrice * cart.quantity;

          const foodResponce: FoodResponce = {
            itemId: foodItem.itemId,
            itemrName: foodItem.itemrName,
            itemPrice: foodItem.itemPrice,
            itemQuantity: cart.quantity,
            totalPrice: foodItem.itemPrice * cart.quantity
          }

          console.log(foodResponce);
          this.foods.push(foodResponce);
        })
      }
      console.log(this.foods);
    })
  }

  updateQuantity(item: FoodResponce, update: boolean, quantity: number) {

    for (let food of this.foods) {

      if (food.itemId === item.itemId) {
        if (update === true) {
          quantity++;
          food.itemQuantity = quantity;
          food.totalPrice = food.itemPrice * quantity;
          this.total += food.itemPrice;
        }
        else {
          quantity--;
          food.itemQuantity = quantity;
          food.totalPrice = food.itemPrice * quantity;
          this.total -= food.itemPrice;
        }
        console.log(quantity);

        const cart: Cart = {
          cartId: 0,
          customerId: this.customerResponce.customerId,
          itemId: food.itemId,
          quantity: food.itemQuantity,
          orderId: 0
        }
        this.cartService.updateQuantity(cart).subscribe();
      }
    }
  }

  deleteItem(item: FoodResponce) {

    if (confirm("Are you sure you want to remove ?")) {

      const cart: Cart = {
        cartId: 0,
        customerId: this.customerResponce.customerId,
        itemId: item.itemId,
        quantity: 0,
        orderId: 0
      }
      this.cartService.deleteFromCart(cart).subscribe();
      setTimeout(() => {
        window.location.reload();
      }, 200);
    }
  }

  order() {

    if (confirm("Are you sure you want to order ?")) {

      const order: Order = {
        orderNo: 0,
        orderId: 0,
        orderAmount: this.total,
        orderDate: '',
        customerId: this.customerResponce.customerId
      }
      this.orderService.placeOrder(order).subscribe(order => {
        console.log(order);

        for (let cart of this.carts) {

          cart.orderId = order.orderId;
          this.cartService.updateOrderId(cart).subscribe();
        }

      });

      alert("Your order is placed, THANK YOU!");
      this.loading = true;

      setTimeout(() => {
        this.loading = false;
        this.router.navigate(['/profile']);
      }, 1500);


      // if ("Your order is placed, THANK YOU!, See your order details ") {
      //   this.router.navigate(['/profile']).then(() => {
      //     window.location.reload();
      //   });
      // }
    }
  }

  addMoreItem() {
    this.router.navigate(['/restaurant-menu/' + this.selectedRestaurant]);
  }

  searchRestaurant() {
    this.router.navigate(['/restaurants']);
  }
}
