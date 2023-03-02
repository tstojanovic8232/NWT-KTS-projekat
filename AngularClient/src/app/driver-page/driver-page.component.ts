import {Component, ElementRef, OnInit, ViewChild} from '@angular/core';
import {LocalService} from "../services/local.service";
import {ActivatedRoute, Router} from "@angular/router";
import {DrivingService} from "../services/driving.service";
import {DrivingEntry} from "../model/driving-entry";
import {UserRole} from "../model/user-role";

export interface Iputovanje {
  clientName: string;
  date: Date;
  price: number;
  duration: string;
  origin: string;
  destination: string;
}

@Component({
  selector: 'app-driver-page',
  templateUrl: './driver-page.component.html',
  styleUrls: ['./driver-page.component.css']
})


export class DriverPageComponent{


}

