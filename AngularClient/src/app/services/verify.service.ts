import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {User} from "../model/user";
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
    return this.http.get(this.url+'?token='+token);

  }
}
