import {HttpClient, HttpHeaders} from '@angular/common/http';
import {Injectable} from '@angular/core';
import {User} from '../model/user';
import {Observable} from 'rxjs';
import {LocalService} from "./local.service";
declare const FB:any;
@Injectable({
  providedIn: 'root'
})
export class UserLoginService {

  private loginUrl: string;

  constructor(private http: HttpClient,
              private localService: LocalService) {
    this.loginUrl = "http://localhost:8084/login";
  }

  loginUser(user: User): Observable<object> {
    console.log(user);
    const httpOptions = {
      headers: new HttpHeaders(
        {'Content-Type': 'application/json'}
      )
    };
    return this.http.post<User>(this.loginUrl, JSON.stringify(user), httpOptions);
  }

  logoutUser() {
    this.localService.clearData()
    // FB.logout();
  }

  LoginWithGoogle(credential: string): Observable<object> {
    const httpOptions = {
      headers: new HttpHeaders(
        {'Content-Type': 'application/json'}
      )
    };
    return this.http.post<User>(`${this.loginUrl}/google`, credential, httpOptions);
  }

  LoginWithFacebook(accessToken: string): Observable<object> {
    const httpOptions = {
      headers: new HttpHeaders(
        {'Content-Type': 'application/json'}
      )
    };
    return this.http.post<User>(`${this.loginUrl}/facebook`, accessToken, httpOptions);
  }
}
