
import {Component, ViewChild, ElementRef, OnInit} from '@angular/core';
import {DrivingService} from "../services/driving.service";
import {LocalService} from "../services/local.service";
import {ActivatedRoute, Router} from "@angular/router";
import {UserRole} from "../model/user-role";
import {DrivingEntry} from "../model/driving-entry";

export interface Iputovanje {
clientName: string;
date: Date;
price: number;
duration: string;
origin: string;
destination: string;
}

@Component({
  selector: 'app-istorija-voznji',
  templateUrl: './istorija-voznji.component.html',
  styleUrls: ['./istorija-voznji.component.css']
})
export class IstorijaVoznjiComponent implements OnInit{
  @ViewChild('myTable') table: ElementRef;
  data:DrivingEntry[];

  currentPage = 1;
  itemsPerPage = 2;

  constructor(private localService: LocalService, private router: Router, private activatedRoute: ActivatedRoute, private drivingService: DrivingService) {
    let user:UserRole = new UserRole();
    let email = this.localService.getData('user');
    let role = this.localService.getData('role');
    if(email && role) {
      user.email = email;
      user.role = role;
    }
    this.drivingService.getHistory(user).subscribe(data=>{
      console.log(data);
      this.setData(data as DrivingEntry[]);
    })
  }

  ngOnInit() {

  }

  setData(data : any) {
    this.data=data;
  }

  get paginatedData() {
    const startIndex = (this.currentPage - 1) * this.itemsPerPage;
    return this.data.slice(startIndex, startIndex + this.itemsPerPage);
  }

  onPageChange(event: number) {
    this.currentPage = event;
    this.table.nativeElement.scrollIntoView();
  }
  NextPage() {
    const startIndex = this.currentPage * this.itemsPerPage;
    if (startIndex < this.data.length) {
      this.currentPage++;
      this.table.nativeElement.scrollIntoView();
    }
  }

  previousPage() {
    if (this.currentPage > 1) {
      this.currentPage--;
      this.table.nativeElement.scrollIntoView();
    }
  }

}

