import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {


  constructor(private router: Router) {}

  ngOnInit(): void {
    const session = localStorage.getItem('session');
    if (session != null) {
      this.router.navigate(['/restaurants']);
    }
  }

  loginCustomer() {
    this.router.navigate(['/login']);
  }

  submitForm() {

  }
}
