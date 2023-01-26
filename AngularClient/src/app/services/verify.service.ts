import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {Observable} from "rxjs";

@Injectable({
  providedIn: 'root'
})
export class VerifyService {


  private url:string;
  constructor(private http : HttpClient) {
    this.url="http://localhost:8084/confirm";
  }

  verify(token:string|null):Observable<object> {
    console.log(this.url+'?token='+token)
    const httpOptions = {
      headers: new HttpHeaders(
        {'Content-Type': 'application/json'}
      )
    };
    return this.http.get(this.url+'?token='+token, httpOptions);

  }
}
