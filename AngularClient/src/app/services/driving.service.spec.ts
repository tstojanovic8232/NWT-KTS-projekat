import { TestBed } from '@angular/core/testing';

import { DrivingService } from './driving.service';
import {BrowserModule} from "@angular/platform-browser";
import {AppRoutingModule} from "../app-routing.module";
import {HttpClientModule} from "@angular/common/http";
import {FormsModule} from "@angular/forms";
import {NgxPaginationModule} from "ngx-pagination";
import {UserService} from "./user.service";

describe('ReservationService', () => {
  let service: DrivingService;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports:[
        BrowserModule,
        AppRoutingModule,
        HttpClientModule,
        FormsModule,
        NgxPaginationModule,
      ],
      providers: [DrivingService]
    });
    service = TestBed.inject(DrivingService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
