import {Component, ElementRef, ViewChild} from '@angular/core';
import {LocalService} from "../services/local.service";
import {ActivatedRoute, Router} from "@angular/router";
import {DrivingService} from "../services/driving.service";
import {UserProfile} from "../model/user-profile";
import {UserService} from "../services/user.service";

@Component({
  selector: 'app-admin-user-table',
  templateUrl: './admin-user-table.component.html',
  styleUrls: ['./admin-user-table.component.css']
})
export class AdminUserTableComponent {
  @ViewChild('myTable') table: ElementRef;
  data: UserProfile[];
  isDriver: boolean;

  constructor(private localService: LocalService, private router: Router, private activatedRoute: ActivatedRoute, private userService: UserService) {


  }

  ngOnInit() {
    if (this.router.url == "/admin/drivers") this.isDriver = true;
    else if (this.router.url == "/admin/clients") this.isDriver = false;
    else this.isDriver = false;

    if(this.isDriver) this.userService.getDrivers().subscribe(data => {
      this.setData(data as UserProfile[])
    })
    else this.userService.getClients().subscribe(data => {
      this.setData(data as UserProfile[])
    })
  }

  setData(data: any) {
    console.log(data);
    this.data = data;
  }
}
