import {HttpClient, HttpHeaders} from '@angular/common/http';
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
    const httpOptions = {
      headers: new HttpHeaders(
        {'Content-Type': 'application/json'}
      )
    };
    return this.http.post<User>(this.loginUrl, JSON.stringify(user), httpOptions);
  }
}
