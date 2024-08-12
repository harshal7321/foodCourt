import { Component, OnInit, ViewChild, ElementRef } from '@angular/core';

@Component({
  selector: 'app-about',
  templateUrl: './about.component.html',
  styleUrls: ['./about.component.css']
})
export class AboutComponent implements OnInit {

  constructor() { }

  ngOnInit(): void {
  }

  userName  = '';

  @ViewChild('name') inputElement:ElementRef;

  displayName() {
    this.userName = this.inputElement.nativeElement.value;
    console.log(this.userName);
  }
}
