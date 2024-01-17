import { TestBed, inject } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import { UserRegisterService } from './user-register.service';
import { UserRegister } from '../model/user-register';
import { DriverRegister } from '../model/driver-register';
import {BrowserModule} from "@angular/platform-browser";
import {AppRoutingModule} from "../app-routing.module";
import {HttpClientModule} from "@angular/common/http";
import {FormsModule} from "@angular/forms";
import {NgxPaginationModule} from "ngx-pagination";

describe('UserRegisterService', () => {
  let service: UserRegisterService;
  let httpMock: HttpTestingController;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule,
        BrowserModule,
        AppRoutingModule,
        HttpClientModule,
        FormsModule,
        NgxPaginationModule],

      providers: [UserRegisterService]
    });
    service = TestBed.inject(UserRegisterService);
    httpMock = TestBed.inject(HttpTestingController);
  });

  afterEach(() => {
    httpMock.verify();
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });

  it('should register a user', () => {
    const mockUser = {
      name: 'John',
      lastname: 'Doe',
      password: 'password123',
      email: 'john.doe@example.com',
      phone: '123456789',
      city: 'SomeCity'
    };




    service.registerUser(mockUser).subscribe(response => {
      expect(response).toEqual(jasmine.objectContaining({ status: 'success' }));
    });

    const req = httpMock.expectOne('http://localhost:8084/register');
    expect(req.request.method).toBe('POST');
    expect(req.request.body).toEqual(JSON.stringify(mockUser));
    req.flush({ status: 'success' });
  });

  it('should register a driver', () => {
    const mockDriver: DriverRegister = {
      name: 'Johni',
      lastname: 'Doe',
      password: 'password1238',
      email: 'john.doe@example.com',
      phone: '0423234',
      city: 'Belgrade',
      price: 300,
      registration: 'yes'
    };

    service.registerDriver(mockDriver).subscribe(response => {
      expect(response).toEqual(jasmine.objectContaining({ status: 'success' }));
    });

    const req = httpMock.expectOne('http://localhost:8084/registerDriver');
    expect(req.request.method).toBe('POST');

    expect(req.request.body).toEqual(JSON.stringify(mockDriver));
    req.flush({ status: 'success' });
  });
});
