import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { User } from '../model/user';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class UserLoginService {

  private loginUrl : string;
  constructor(private http : HttpClient) {
    this.loginUrl="http://localhost:8084/login";
   }

  loginUser(user : User):Observable<object> {
    console.log(user);
    return this.http.post<User>(this.loginUrl, user);
  }
}
