import { ComponentFixture, TestBed } from '@angular/core/testing';

import { DriverPageComponent } from './driver-page.component';
import {BrowserModule} from "@angular/platform-browser";
import {AppRoutingModule} from "../app-routing.module";
import {HttpClientModule} from "@angular/common/http";
import {FormsModule} from "@angular/forms";
import {NgxPaginationModule} from "ngx-pagination";

describe('DriverPageComponent', () => {
  let component: DriverPageComponent;
  let fixture: ComponentFixture<DriverPageComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ DriverPageComponent ],
      imports:[
        BrowserModule,
        AppRoutingModule,
        HttpClientModule,
        FormsModule,
        NgxPaginationModule,
      ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(DriverPageComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
