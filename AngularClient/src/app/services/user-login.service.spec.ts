import { TestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import { UserLoginService } from './user-login.service';

import { User } from '../model/user';
import { LocalService } from './local.service';
import { Observable } from 'rxjs';
import {RouterTestingModule} from "@angular/router/testing";

import {BrowserModule} from "@angular/platform-browser";
import {AppRoutingModule} from "../app-routing.module";
import {HttpClientModule} from "@angular/common/http";
import {FormsModule} from "@angular/forms";
import {NgxPaginationModule} from "ngx-pagination";
import {UserService} from "./user.service";


describe('UserLoginService', () => {
  let service: UserLoginService;
  let httpTestingController: HttpTestingController;

  beforeEach(() => {
    TestBed.configureTestingModule({

      imports: [HttpClientTestingModule,
        BrowserModule,
        AppRoutingModule,
        HttpClientModule,
        FormsModule,
        NgxPaginationModule,
        RouterTestingModule.withRoutes([
          {path: 'admin', redirectTo:''},
          {path: 'client-home', redirectTo:''},
          {path: 'driver-home', redirectTo:''}
        ])],
      providers: [UserLoginService, LocalService],
    });



    service = TestBed.inject(UserLoginService);
    httpTestingController = TestBed.inject(HttpTestingController);
  });

  afterEach(() => {
    httpTestingController.verify();
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });

  it('should send a POST request when loginUser is called', () => {
    const user: User = { email: 'test@example.com', password: 'password' };
    const mockResponse = { email: 'test@example.com', role: 'Klijent' };

    service.loginUser(user).subscribe((response: any) => {
      expect(response).toEqual(mockResponse);
    });

    const req = httpTestingController.expectOne('http://localhost:8084/login');
    expect(req.request.method).toEqual('POST');
    req.flush(mockResponse);
  });

  it('should send a POST request when LoginWithGoogle is called', () => {
    const credential = 'fakeCredential';
    const mockResponse = { email: 'test@example.com', role: 'Klijent' };

    service.LoginWithGoogle(credential).subscribe((response: any) => {
      expect(response).toEqual(mockResponse);
    });

    const req = httpTestingController.expectOne('http://localhost:8084/login/google');
    expect(req.request.method).toEqual('POST');
    req.flush(mockResponse);
  });

  it('should send a POST request when LoginWithFacebook is called', () => {
    const accessToken = 'fakeAccessToken';
    const mockResponse = { email: 'test@example.com', role: 'Klijent' };

    service.LoginWithFacebook(accessToken).subscribe((response: any) => {
      expect(response).toEqual(mockResponse);
    });

    const req = httpTestingController.expectOne('http://localhost:8084/login/facebook');
    expect(req.request.method).toEqual('POST');
    req.flush(mockResponse);
  });

  it('should clear data in localService when logoutUser is called', () => {
    spyOn(service['localService'], 'clearData');

    service.logoutUser();

    expect(service['localService'].clearData).toHaveBeenCalled();
  });

});
