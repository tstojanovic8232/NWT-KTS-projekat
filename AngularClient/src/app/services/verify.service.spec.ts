import { TestBed } from '@angular/core/testing';

import { VerifyService } from './verify.service';
import {BrowserModule} from "@angular/platform-browser";
import {AppRoutingModule} from "../app-routing.module";
import {HttpClientModule} from "@angular/common/http";
import {FormsModule} from "@angular/forms";
import {NgxPaginationModule} from "ngx-pagination";
import {UserService} from "./user.service";

describe('VerifyService', () => {
  let service: VerifyService;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports:[
        BrowserModule,
        AppRoutingModule,
        HttpClientModule,
        FormsModule,
        NgxPaginationModule,
      ],
      providers: [VerifyService]
    });
    service = TestBed.inject(VerifyService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
