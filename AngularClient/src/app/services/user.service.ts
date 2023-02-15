import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {Observable} from "rxjs";
import {UserProfile} from "../model/user-profile";
import {UserRole} from "../model/user-role";
import {UserData} from "../model/user-data";
import {User} from "../model/user";
import {BillingData} from "../model/billing-data";

@Injectable({
  providedIn: 'root'
})
export class UserService {

  private usersUrl : string;
  constructor(private http : HttpClient) {
    this.usersUrl="http://localhost:8084/users";
  }

  getUser(user : UserRole):Observable<object> {
    console.log(user);
    return this.http.post<UserRole>(this.usersUrl+"/data", user);
  }
  updateUser(user:UserData):Observable<object>{
    console.log(user);
    return  this.http.post<UserData>(this.usersUrl+"/update",user);
  }
  updatePass(user:User):Observable<object>{
    console.log(user);
    return  this.http.post<User>(this.usersUrl+"/updatePass",user);
  }
  updateEmail(email:User):Observable<object>{
    console.log(email);
    return  this.http.post<User>(this.usersUrl+"/updateEmail",email);
  }
  updateBilling(B:BillingData):Observable<object>{
    console.log(B);
    return  this.http.post<BillingData>(this.usersUrl+"/updateBilling",B);
  }
}
