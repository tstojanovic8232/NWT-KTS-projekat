import { TestBed } from '@angular/core/testing';

import { LocalService } from './local.service';
import {BrowserModule} from "@angular/platform-browser";
import {AppRoutingModule} from "../app-routing.module";
import {HttpClientModule} from "@angular/common/http";
import {FormsModule} from "@angular/forms";
import {NgxPaginationModule} from "ngx-pagination";
import {UserService} from "./user.service";

describe('LocalService', () => {
  let service: LocalService;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports:[
        BrowserModule,
        AppRoutingModule,
        HttpClientModule,
        FormsModule,
        NgxPaginationModule,
      ],
      providers: [LocalService]
    });
    service = TestBed.inject(LocalService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
