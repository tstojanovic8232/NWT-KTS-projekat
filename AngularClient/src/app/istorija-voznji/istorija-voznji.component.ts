import {Component, ElementRef, OnInit, ViewChild} from '@angular/core';
import {DrivingService} from "../services/driving.service";
import {LocalService} from "../services/local.service";
import {ActivatedRoute, Router} from "@angular/router";
import {UserRole} from "../model/user-role";
import {DrivingEntry} from "../model/driving-entry";

@Component({
  selector: 'app-istorija-voznji',
  templateUrl: './istorija-voznji.component.html',
  styleUrls: ['./istorija-voznji.component.css']
})
export class IstorijaVoznjiComponent implements OnInit {
  @ViewChild('myTable') table: ElementRef;
  data: DrivingEntry[];
  private loggedIn: boolean = false;
  private driver: boolean = false;
  private email: string = '';
  other: string;
  emailTemp: string | null = "";
  role: string | null = "";
  currentPage = 1;
  itemsPerPage = 5;
  private isAscending: boolean;

  constructor(private localService: LocalService, private router: Router, private activatedRoute: ActivatedRoute, private drivingService: DrivingService) {
    this.activatedRoute.data.subscribe((data) => {
      console.log(data);
      this.loggedIn = data['loggedIn'];
      if(this.loggedIn) this.driver = data['driver'];
      this.activatedRoute.queryParams.subscribe((params) => {
        console.log(params);
        this.email = params['email'];
        if(!this.loggedIn) this.driver = params['driver'];
        this.other = this.driver ? "Client" : "Driver";
        this.getHistory();
      })
    })
  }

  ngOnInit() {

  }

  getHistory() {
    let user: UserRole = new UserRole();
    if (!this.loggedIn) {
      this.emailTemp = this.email;
      this.role="Admin";
    } else {
      this.emailTemp = this.localService.getData('user');
      this.role = this.localService.getData('role');
    }
    if (this.emailTemp && this.role) {
      user.email = this.emailTemp;
      user.role = this.role;
    }
    this.drivingService.getHistory(user).subscribe(data => {
      this.setData(data as DrivingEntry[]);
    })
  }

  setData(data: any) {
    this.data = data;
  }

  get paginatedData() {
    const startIndex = (this.currentPage - 1) * this.itemsPerPage;
    return this.data.slice(startIndex, startIndex + this.itemsPerPage);
  }

  nextPage() {
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

  sortKlijent() {
    if (this.isAscending) {
      this.data.sort((a, b) => a.clientName.localeCompare(b.clientName));
    } else {
      this.data.sort((a, b) => b.clientName.localeCompare(a.clientName));
    }
    this.isAscending = !this.isAscending;
  }

  sortDatum() {
    if (this.isAscending) {
      this.data.sort((a, b) => a.date.localeCompare(b.date));
    } else {
      this.data.sort((a, b) => b.date.localeCompare(a.date));
    }
    this.isAscending = !this.isAscending;
  }

  sortCena() {
    if (this.isAscending) {
      this.data.sort((a, b) => a.price.localeCompare(b.price));
    } else {
      this.data.sort((a, b) => b.price.localeCompare(a.price));
    }
    this.isAscending = !this.isAscending;
  }

  sortPolaziste() {
    if (this.isAscending) {
      this.data.sort((a, b) => a.origin.localeCompare(b.origin));
    } else {
      this.data.sort((a, b) => b.origin.localeCompare(a.origin));
    }
    this.isAscending = !this.isAscending;
  }

  sortOdrediste() {
    if (this.isAscending) {
      this.data.sort((a, b) => a.destination.localeCompare(b.destination));
    } else {
      this.data.sort((a, b) => b.destination.localeCompare(a.destination));
    }
    this.isAscending = !this.isAscending;
  }

}

