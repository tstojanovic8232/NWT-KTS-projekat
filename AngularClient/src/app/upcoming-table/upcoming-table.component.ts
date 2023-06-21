import {Component, ElementRef, OnInit, ViewChild} from '@angular/core';
import {DrivingEntry} from "../model/driving-entry";
import {LocalService} from "../services/local.service";
import {ActivatedRoute, Router} from "@angular/router";
import {DrivingService} from "../services/driving.service";
import {UserRole} from "../model/user-role";
import {UserService} from "../services/user.service";

@Component({
  selector: 'app-upcoming-table',
  templateUrl: './upcoming-table.component.html',
  styleUrls: ['./upcoming-table.component.css']
})
export class UpcomingTableComponent implements OnInit{
  @ViewChild('myTable') table: ElementRef;
  data: DrivingEntry[];
  isOnline: boolean;
  blocked:boolean;
  constructor(private localService: LocalService, private router: Router, private activatedRoute: ActivatedRoute, private drivingService: DrivingService,private userservice:UserService) {



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
    this.userservice.getBlockedStatus(user).subscribe((data:any)=>{
      this.blocked=data as boolean;
      console.log(this.blocked)

    })
    // console.log(this.blocked);

  }

  setData(data : any) {
    console.log(data);
    this.data=data;
    this.data.sort((a, b) => a.date.localeCompare(b.date));
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
