import {Component, OnInit} from '@angular/core';
import {LocalService} from "../services/local.service";
import {ActivatedRoute, Router} from "@angular/router";
import {DrivingService} from "../services/driving.service";
import {DrivingReservation} from "../model/driving-reservation";

@Component({
  selector: 'app-bill',
  templateUrl: './bill.component.html',
  styleUrls: ['./bill.component.css']
})
export class BillComponent implements OnInit {
  km: number;
  tip: string;
  nap: string;
  from: string;
  to: string;
  cena: number;
  coords: {
    from: string,
    to: string
  }
  = {
    from: "",
    to: ""
  }

  constructor(private localService: LocalService, private router: Router, private activatedRoute: ActivatedRoute, private drivingService: DrivingService) {
  }

  ngOnInit() {
    this.tip = history.state.tip;
    this.nap = history.state.nap;
    this.from = history.state.from;
    this.to = history.state.to;
    this.km = history.state.km;
    this.coords.from=history.state.coords.from;
    this.coords.to=history.state.coords.to;
    console.log(history.state.coords);
    this.cena = this.km * 120;
    history.state.tip = undefined;
    history.state.nap = undefined;
    history.state.from = undefined;
    history.state.to = undefined;
    history.state.km = undefined;
    history.state.coords = undefined;
  }

  reserveDrive() {
    const currentDate = new Date();
    currentDate.setMinutes(currentDate.getMinutes() + 10);
    let res: DrivingReservation = new DrivingReservation();
    res.km = this.km;
    res.from = this.coords.from;
    res.to = this.coords.to;
    res.date = currentDate.toISOString();
    res.warn = this.nap;
    res.client = this.localService.getData('user');
    res.price = this.cena;
    this.drivingService.addReservation(res).subscribe(() => {
      this.router.navigate(['/client-home']);
    })
  }
}
