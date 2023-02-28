import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {DrivingReservation} from "../model/driving-reservation";

@Injectable({
  providedIn: 'root'
})
export class ReservationService {

  private usersUrl : string;
  constructor(private http : HttpClient) {
    this.usersUrl="http://localhost:8084/drives";
  }

  addReservation(res : DrivingReservation):Observable<object> {
    console.log(res);
    return this.http.post<DrivingReservation>(this.usersUrl+"/add", res);
  }
}
