import {Component, OnInit} from '@angular/core';
import {LocalService} from "../services/local.service";
import {ActivatedRoute, Router} from "@angular/router";
import {ReservationService} from "../services/reservation.service";
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

  constructor(private localService: LocalService, private router: Router, private activatedRoute: ActivatedRoute, private reservationService: ReservationService) {
  }

  ngOnInit() {
    this.tip = history.state.tip;
    this.nap = history.state.nap;
    this.from = history.state.from;
    this.to = history.state.to;
    this.km = history.state.km;
    history.state.tip = undefined;
    history.state.nap = undefined;
    history.state.from = undefined;
    history.state.to = undefined;
    history.state.km = undefined;

  }

  reserveDrive() {
    const currentDate = new Date();
    currentDate.setMinutes(currentDate.getMinutes() + 10);
    let res : DrivingReservation = new DrivingReservation();
    res.km=this.km;
    res.from=this.from;
    res.to=this.to;
    res.date=currentDate.toISOString();
    res.warn=this.nap;
    res.client=this.localService.getData('user');
    res.price=this.km*120;
    this.reservationService.addReservation(res).subscribe(() => {
      this.router.navigate(['/client-home']);
    })
  }
}
