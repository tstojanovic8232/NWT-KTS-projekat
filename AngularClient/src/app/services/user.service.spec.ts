import { TestBed } from '@angular/core/testing';

import { UserService } from './user.service';
import {HttpClientTestingModule} from "@angular/common/http/testing";
import {BrowserModule} from "@angular/platform-browser";
import {AppRoutingModule} from "../app-routing.module";
import {HttpClientModule} from "@angular/common/http";
import {FormsModule} from "@angular/forms";
import {NgxPaginationModule} from "ngx-pagination";

describe('UserService', () => {
  let service: UserService;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports:[
        BrowserModule,
        AppRoutingModule,
        HttpClientModule,
        FormsModule,
        NgxPaginationModule,
        HttpClientTestingModule
      ],
      providers: [UserService]
    });
    service = TestBed.inject(UserService);

  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
