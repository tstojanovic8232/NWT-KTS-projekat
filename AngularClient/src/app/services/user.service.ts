import {Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {UserRole} from "../model/user-role";
import {UserData} from "../model/user-data";
import {User} from "../model/user";
import {BillingData} from "../model/billing-data";
import {UserFull} from "../model/user-full";

@Injectable({
  providedIn: 'root'
})
export class UserService {

  private usersUrl: string;

  constructor(private http: HttpClient) {
    this.usersUrl = "http://localhost:8084/users";
  }


  getUserProfile(user: UserRole): Observable<object> {
    console.log(user);
    return this.http.post<UserRole>(this.usersUrl + "/data", user);
  }

  updateUser(user: UserData): Observable<object> {
    console.log(user);
    return this.http.post<UserData>(this.usersUrl + "/update", user);
  }

  updatePass(user: User): Observable<object> {
    console.log(user);
    return this.http.post<User>(this.usersUrl + "/updatePass", user);
  }

  updateEmail(email: User): Observable<object> {
    console.log(email);
    return this.http.post<User>(this.usersUrl + "/updateEmail", email);
  }

  updateBilling(B: BillingData): Observable<object> {
    console.log(B);
    return this.http.post<BillingData>(this.usersUrl + "/updateBilling", B);
  }

  onSwitchChange(state: boolean, email: string): Observable<object> {
    console.log(state);
    return this.http.post(this.usersUrl + "/switch", {state: state, email: email});
  }

  getStatus(user: UserRole): Observable<object> {
    console.log(user);
    return this.http.post<UserRole>(this.usersUrl + "/status", user);
  }

  getClients() : Observable<object> {
    return this.http.get(this.usersUrl + "/clients")
  }

  getDrivers() : Observable<object> {
    return this.http.get(this.usersUrl + "/drivers")
  }

  // updateBlockedStatus(email: string): Observable<any> {
  //   const url = `${this.usersUrl}/block`; // Replace with your API endpoint
  //
  //
  //
  //
  //   // Make the HTTP PUT request to update the blokiran status
  //   return this.http.post(url, email);
  // }

  updateBlockedStatus(email: string, blocked: boolean): Observable<any> {
    const url = `${this.usersUrl}/block`; // Replace with your API endpoint

    // Make the HTTP POST request to update the blocked status
    return this.http.post(url, { email, blocked });
  }
  getBlockedStatus(user: UserRole): Observable<any> {
    const url = `${this.usersUrl}/blockstatus`; // Replace with your API endpoint




    // Make the HTTP PUT request to update the blokiran status
    return this.http.post(url,user);
  }

  setPass(user: User): Observable<object> {
    console.log(user);
    return this.http.post<User>(this.usersUrl + "/setPass", user);
  }
}
