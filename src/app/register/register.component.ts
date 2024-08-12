import { FormBuilder, FormControl, FormGroup, Validators } from '@angular/forms';
import { Customer } from './../customer-login/customer.interface';
import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { CustomerService } from '../services/customer.service';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent implements OnInit {

  registrationForm: FormGroup;

  constructor(private router: Router, private customerSevice: CustomerService) { }

  ngOnInit(): void {

    this.registrationForm = new FormGroup({
      customerName: new FormControl('',[Validators.required, Validators.pattern('[A-Za-z]+[ ]{1}[A-Za-z]+[ ]*')]),
      customerEmail: new FormControl('', [Validators.required, Validators.email, Validators.pattern('[a-z0-9.]+@{1}[a-z0-9]+[.]+(com|in)')]),
      customerLocation: new FormControl('',[Validators.required, Validators.pattern('[A-Za-z]+')]),
      customerContact: new FormControl('', [Validators.required,Validators.pattern('[6-9]{1}[0-9]{9}')]),
      customerPassword: new FormControl('', [Validators.required, Validators.pattern('[A-Z]{1}[a-zA-Z0-9@$#%&!]{6,50}')]),
      confirmPassword: new FormControl('', [Validators.required])
    });

    console.log(this.registrationForm);
  }

  customer: Customer = {
    customerId: 0,
    customerName: '',
    customerEmail: '',
    customerContact: '',
    customerLocation: '',
    customerPassword: ' ',
    status: ''
  }

  addCustomer() {

    console.log(this.registrationForm);

    if(this.registrationForm.valid) {

      this.customer.customerName = this.registrationForm.controls['customerName'].value;
      this.customer.customerEmail = this.registrationForm.controls['customerEmail'].value;
      this.customer.customerContact = this.registrationForm.controls['customerContact'].value;
      this.customer.customerLocation = this.registrationForm.controls['customerLocation'].value;
      this.customer.customerPassword = this.registrationForm.controls['customerPassword'].value;

      console.log(this.registrationForm.controls['customerPassword'].value);

      if(this.registrationForm.controls['customerPassword'].value === this.registrationForm.controls['confirmPassword'].value ) {

        this.customerSevice.addCustomer(this.customer).subscribe( data => {

          if(data.status === 'success') {
            alert("Register successfully, go to login");
          this.router.navigate(['/login']);
          }
          if(data.status === 'exist') {
            alert("Email Id already Exist !");
          }
        });

      }
      else {
        alert("Password and confirm password sould be same !");
        this.registrationForm.controls['confirmPassword'].invalid;
      }
    }
    else{

      if(this.registrationForm.controls['customerName'].invalid){
        alert("Name is required and should contain only alphabets! There should be space between firstname and lastname.");
        return;
      }
      if(this.registrationForm.controls['customerEmail'].invalid) {
        alert("Email is required and should be a valid email!");
        return;
      }
      if(this.registrationForm.controls['customerLocation'].invalid) {
        alert("Location is required and should contain only alphabets!");
        return;
      }
      if(this.registrationForm.controls['customerContact'].invalid) {
        alert("Contact number is required and should be 10 digits long!");
        return;
      }
      if(this.registrationForm.controls['customerLocation'].invalid) {
        alert("Location is required and should contain only alphabets!");
        return;
      }
      if(this.registrationForm.controls['customerPassword'].invalid) {
        alert("Password is required and should be Minimum 6 characters, at least one uppercase letter, one lowercase letter and one number !");
        return;
      }
    }
  }

}
