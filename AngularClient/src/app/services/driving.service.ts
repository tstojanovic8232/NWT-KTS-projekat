import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {DrivingReservation} from "../model/driving-reservation";
import {UserRole} from "../model/user-role";
import {DrivingEntry} from "../model/driving-entry";

@Injectable({
  providedIn: 'root'
})
export class DrivingService {

  private usersUrl : string;
  constructor(private http : HttpClient) {
    this.usersUrl="http://localhost:8084/drives";
  }

  addReservation(res : DrivingReservation):Observable<object> {
    console.log(res);
    return this.http.post<DrivingReservation>(this.usersUrl+"/add", res);
  }

  getUpcoming(user : UserRole):Observable<object> {
    console.log(user);
    return this.http.post<UserRole>(this.usersUrl+"/getUpcoming", user);
  }
  getHistory(user : UserRole):Observable<object> {
    console.log(user);
    return this.http.post<UserRole>(this.usersUrl+"/getHistory", user);
  }
}
