import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';
import { Customer } from '../customer-login/customer.interface';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { Order } from '../cart/order.interface';
import { OrderService } from '../services/order.service';
import { Details } from './order-detail.iterface';
import { CustomerService } from '../services/customer.service';
import { Router } from '@angular/router';

declare var html2pdf: any;

@Component({
  selector: 'app-popup',
  templateUrl: './popup.component.html',
  styleUrls: ['./popup.component.css']
})
export class PopupComponent implements OnInit {

  @Input() isActive: boolean = false;
  @Input() popupType: string = '';

  @Input() orderData: Order = {
    orderNo: 0,
    orderId: 0,
    orderAmount: 0,
    orderDate: '',
    customerId: 0
  }

  @Input() details: Details[] = []

  @Output() close: EventEmitter<any> = new EventEmitter();

  session: any = localStorage.getItem("session");
  customerResponce: Customer = JSON.parse(this.session);

  updationForm: FormGroup;
  passwordUpdationForm: FormGroup;

  customer: Customer = {
    customerId: 0,
    customerName: '',
    customerEmail: '',
    customerContact: '',
    customerLocation: '',
    customerPassword: ' ',
    status: ''
  }

  constructor(private orderService: OrderService, private customerService: CustomerService, private router: Router) { }

  ngOnInit(): void {

    if(this.session == null) {
      this.router.navigate(['/home']);
    }

    this.updationForm = new FormGroup({
      customerName: new FormControl(this.customerResponce.customerName,[Validators.required, Validators.pattern('[A-Za-z]+[ ]{1}[A-Za-z]+[ ]*')]),
      customerEmail: new FormControl(this.customerResponce.customerEmail, [Validators.required, Validators.email, Validators.pattern('[a-z.]+@{1}[a-z]+[.]+[a-z]{2,3}')]),
      customerLocation: new FormControl(this.customerResponce.customerLocation,[Validators.required, Validators.pattern('[A-Za-z]+')]),
      customerContact: new FormControl(this.customerResponce.customerContact, [Validators.required,Validators.pattern('[7-9]{1}[0-9]{9}')]),
    });

    this.passwordUpdationForm = new FormGroup({
      oldPassword: new FormControl('', [Validators.required]),
      newPassword: new FormControl('', [Validators.required, Validators.pattern('[A-Z]{1}[a-zA-Z0-9@$#%&!]{6,50}')]),
      confirmPassword: new FormControl('', [Validators.required])
    });
  }

  generatePdf() {
    var element = document.getElementById('bill');
    var options = {
      margin: 1,
      filename: 'bill-FoodCourt-'+this.customerResponce.customerName+'.pdf',
      image: { type: 'jpeg', quality: 1 },
      html2canvas: { scale: 3 },
      jsPDF: { unit: 'in', format: 'letter', orientation: 'portrait' }
    };

    html2pdf().from(element).set(options).save();
  }

  updateCustomer() {

    if(this.updationForm.valid) {

      this.customer.customerId = this.customerResponce.customerId;
      this.customer.customerName = this.updationForm.controls['customerName'].value;
      this.customer.customerEmail = this.updationForm.controls['customerEmail'].value;
      this.customer.customerContact = this.updationForm.controls['customerContact'].value;
      this.customer.customerLocation = this.updationForm.controls['customerLocation'].value;

      this.closePopup();

      this.customerService.updateProfile(this.customer).subscribe( data => {

        localStorage.setItem('session', JSON.stringify(data));
      });

      alert("Profile updated successfully");

      setTimeout(() => {
        window.location.reload();
      }, 200);
    }
    else{

      if(this.updationForm.controls['customerName'].invalid){
        alert("Name is required and should contain only alphabets! There should be space between firstname and lastname.");
        return;
      }
      if(this.updationForm.controls['customerEmail'].invalid) {
        alert("Email is required and should be a valid email!");
        return;
      }
      if(this.updationForm.controls['customerLocation'].invalid) {
        alert("Location is required and should contain only alphabets!");
        return;
      }
      if(this.updationForm.controls['customerContact'].invalid) {
        alert("Contact number is required and should be 10 digits long!");
        return;
      }
    }
  }

  updatePassword() {

    if (this.passwordUpdationForm.valid) {

      this.customer.customerId = this.customerResponce.customerId;
      this.customer.customerPassword = this.passwordUpdationForm.controls['oldPassword'].value;

      this.customerService.getPassword(this.customer).subscribe(data => {
        this.customer = data;

        if (this.customer.status === 'success') {

          if (this.passwordUpdationForm.controls['newPassword'].value === this.passwordUpdationForm.controls['confirmPassword'].value) {

            this.customer.customerPassword = this.passwordUpdationForm.controls['newPassword'].value;
            console.log(this.customer.customerPassword);

            this.closePopup();
            this.customerService.updatePassword(this.customer).subscribe();
            alert("Congrats, Password changed successfully !");
            return;
          }
          else {
            alert("Password and confirm password sould be same");
            this.customer.customerPassword = '';
            return;
          }
        }
        if (this.customer.status === 'fail') {
          alert("Old Password is incorrect!");
          return;
        }
      });
    }
    else {
      if(this.passwordUpdationForm.controls['oldPassword'].invalid) {
        alert("Please enter your old password !");
        return;
      }
      if(this.passwordUpdationForm.controls['newPassword'].invalid) {
        alert("New Password is required and should be Minimum 6 characters, at least one uppercase letter, one lowercase letter and one number !");
        return;
      }
    }
  }

  closePopup() {
    this.isActive = false;
    this.close.emit();
  }
}
