import { Component, NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { HomeComponent } from './home/home.component';
import { AboutComponent } from './about/about.component';
import { NotFoundComponent } from './not-found/not-found.component';
import { ContactComponent } from './contact/contact.component';
import { RestuarantsComponent } from './restuarants/restuarants.component';
import { RestaurantdetailsComponent } from './restaurantdetails/restaurantdetails.component';
import { RestaurantMenuComponent } from './restaurant-menu/restaurant-menu.component';
import { CustomerLoginComponent } from './customer-login/customer-login.component';
import { RegisterComponent } from './register/register.component';
import { CartComponent } from './cart/cart.component';
import { ProfileComponent } from './profile/profile.component';

const routes: Routes = [
  {
    path: "",
    redirectTo: "/home",
    pathMatch: "full"
  },
  {
    path: "home",
    component: HomeComponent,
  },
  {
    path: "register",
    component: RegisterComponent,
  },
  {
    path: "login",
    component: CustomerLoginComponent,
  },
  {
    path: "profile",
    component: ProfileComponent,
  },
  {
    path: "restaurants",
    component: RestuarantsComponent,
  },
  {
    path: "restaurant-menu/:name",
    component: RestaurantMenuComponent,
  },
  {
    path: "restaurant-details/:name",
    component: RestaurantdetailsComponent,
  },
  {
    path: "cart",
    component: CartComponent,
  },
  {
    path: "cart/:name",
    component: CartComponent,
  },
  {
    path: "about",
    component: AboutComponent
  },
  {
    path: "contact",
    component: ContactComponent
  },
  {
    path: "**",
    component: NotFoundComponent
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
