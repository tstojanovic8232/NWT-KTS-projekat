import {Component, ElementRef, OnInit, ViewChild} from '@angular/core';
import {DrivingEntry} from "../model/driving-entry";
import {LocalService} from "../services/local.service";
import {ActivatedRoute, Router} from "@angular/router";
import {DrivingService} from "../services/driving.service";
import {UserRole} from "../model/user-role";

@Component({
  selector: 'app-istorija-voznji-vozac',
  templateUrl: './istorija-voznji-vozac.component.html',
  styleUrls: ['./istorija-voznji-vozac.component.css']
})
export class IstorijaVoznjiVozacComponent implements OnInit{
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
