import { ComponentFixture, TestBed } from '@angular/core/testing';

import { NavBarLogOutComponent } from './nav-bar-log-out.component';
import {BrowserModule} from "@angular/platform-browser";
import {AppRoutingModule} from "../app-routing.module";
import {HttpClientModule} from "@angular/common/http";
import {FormsModule} from "@angular/forms";
import {NgxPaginationModule} from "ngx-pagination";

describe('NavBarLogOutComponent', () => {
  let component: NavBarLogOutComponent;
  let fixture: ComponentFixture<NavBarLogOutComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ NavBarLogOutComponent ],
      imports:[
        BrowserModule,
        AppRoutingModule,
        HttpClientModule,
        FormsModule,
        NgxPaginationModule,
      ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(NavBarLogOutComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
