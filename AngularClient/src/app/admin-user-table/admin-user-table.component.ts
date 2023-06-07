import {Component, ElementRef, ViewChild} from '@angular/core';
import {LocalService} from "../services/local.service";
import {ActivatedRoute, Router} from "@angular/router";
import {DrivingService} from "../services/driving.service";
import {UserFull} from "../model/user-full";
import {UserService} from "../services/user.service";
import {UserFullNamePipe} from "../pipes/user-full-name.pipe"

@Component({
  selector: 'app-admin-user-table',
  templateUrl: './admin-user-table.component.html',
  styleUrls: ['./admin-user-table.component.css']
})
export class AdminUserTableComponent {
  @ViewChild('myTable') table: ElementRef;
  data: UserFull[];
  isDriver: boolean;

  constructor(private localService: LocalService, private router: Router, private activatedRoute: ActivatedRoute, private userService: UserService) {


  }

  ngOnInit() {
    if (this.router.url == "/admin/drivers") this.isDriver = true;
    else if (this.router.url == "/admin/clients") this.isDriver = false;
    else this.isDriver = false;

    if(this.isDriver) this.userService.getDrivers().subscribe(data => {
      this.setData(data as UserFull[])
    })
    else this.userService.getClients().subscribe(data => {
      this.setData(data as UserFull[])
    })
  }

  setData(data: any) {
    console.log(data);
    this.data = data;
  }
}
