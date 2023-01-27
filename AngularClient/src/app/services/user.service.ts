import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {Observable} from "rxjs";
import {UserProfile} from "../model/user-profile";
import {UserRole} from "../model/user-role";

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
}
