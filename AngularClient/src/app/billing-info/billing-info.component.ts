import {Component, Input, OnInit} from '@angular/core';
import {UserProfile} from "../model/user-profile";
import {UserService} from "../services/user.service";
import {LocalService} from "../services/local.service";
import {Router} from "@angular/router";
import {BillingData} from "../model/billing-data";

@Component({
  selector: 'app-billing-info',
  templateUrl: './billing-info.component.html',
  styleUrls: ['./billing-info.component.css']
})
export class BillingInfoComponent implements OnInit {
  @Input() user: UserProfile=new UserProfile();
  billing = [
    {id: "Default", name: "Choose payment method"},
    {id: "PayPal", name: "PayPal"},
    {id: "Bitcoin", name: "Bitcoin"}
  ];
  selectedValue: string;
  billingData: string;
  role: string | null;

  constructor(private userService: UserService, private localService: LocalService, private router: Router) {
    this.role = this.localService.getData('role');
  }

  ngOnInit() {
    this.selectedValue = this.user.billingType;
  }

  updateBilling() {

    let Billing: BillingData = new BillingData();
    let email = this.localService.getData('user');
     Billing.email=email;
    Billing.billingType = this.selectedValue;
    Billing.billingData = this.billingData;
    Billing.role=this.role;
    console.log(Billing);
    this.userService.updateBilling(Billing).subscribe(() => {
      if (this.role == 'Klijent') {
        this.router.navigate(['/client-home/profile']);
      } else if (this.role == 'Vozac') {
        this.router.navigate(['/driver-home/profile']);
      }
    })

  }
}
