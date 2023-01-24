import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";

import {Observable} from "rxjs";
import {UserRegister} from "../model/user-register";

@Injectable({
  providedIn: 'root'
})
export class UserRegisterService {
  private loginUrl : string;
  constructor(private http : HttpClient) {
    this.loginUrl="http://localhost:8084/register";
  }



  registerUser(user : UserRegister):Observable<object> {
    console.log(user);
    return this.http.post<UserRegister>(this.loginUrl, user);
  }
}
