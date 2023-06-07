import {Component, ElementRef, OnInit, ViewChild} from '@angular/core';
import {DrivingEntry} from "../model/driving-entry";
import {LocalService} from "../services/local.service";
import {ActivatedRoute, Router} from "@angular/router";
import {DrivingService} from "../services/driving.service";
import {UserRole} from "../model/user-role";

@Component({
  selector: 'app-upcoming-table',
  templateUrl: './upcoming-table.component.html',
  styleUrls: ['./upcoming-table.component.css']
})
export class UpcomingTableComponent implements OnInit{
  @ViewChild('myTable') table: ElementRef;
  data: DrivingEntry[];
  isOnline: boolean;

  constructor(private localService: LocalService, private router: Router, private activatedRoute: ActivatedRoute, private drivingService: DrivingService) {



  }

  ngOnInit() {
    let user:UserRole = new UserRole();
    let email = this.localService.getData('user');
    let role = this.localService.getData('role');
    if(email && role) {
      user.email = email;
      user.role = role;
    }
    this.drivingService.getUpcoming(user).subscribe(data=>{
    this.setData(data as DrivingEntry[]);
  })
  }

  setData(data : any) {
    console.log(data);
    this.data=data;
  }

  startDrive(i:number) {
     this.router.navigate(['driver-home/driving'],{
       state:{
         from:this.data[i].origin,
         to:this.data[i].destination
       }
     });


  }
}
