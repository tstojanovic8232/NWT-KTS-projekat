import {Injectable} from '@angular/core';
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {Observable} from "rxjs";
import {UserRegister} from "../model/user-register";
import {DriverRegister} from "../model/driver-register";

@Injectable({
  providedIn: 'root'
})
export class UserRegisterService {
  private loginUrl: string;

  constructor(private http: HttpClient) {
    this.loginUrl = "http://localhost:8084/register";
  }

  registerUser(user: UserRegister): Observable<object> {
    console.log(user);
    const httpOptions = {
      headers: new HttpHeaders({'Content-Type': 'application/json'})
    };
    return this.http.post<UserRegister>(this.loginUrl, JSON.stringify(user), httpOptions);
  }

  registerDriver(user: DriverRegister): Observable<object> {
    console.log(user);
    const httpOptions = {
      headers: new HttpHeaders({'Content-Type': 'application/json'})
    };
    return this.http.post<DriverRegister>(`${this.loginUrl}Driver`, JSON.stringify(user), httpOptions);
  }
}
