import {Component, Input, OnInit} from '@angular/core';
import {UserProfile} from "../model/user-profile";

@Component({
  selector: 'app-billing-info',
  templateUrl: './billing-info.component.html',
  styleUrls: ['./billing-info.component.css']
})
export class BillingInfoComponent implements OnInit{
  @Input() user: UserProfile;
  billing = [
    {id: null, name: "Choose payment method"},
    {id: "PayPal", name: "PayPal"},
    {id: "Bitcoin", name: "Bitcoin"}
  ];
  selectedValue:string|null;

  constructor() {

  }
  ngOnInit() {
    this.selectedValue= this.user.billingType;
  }
}
